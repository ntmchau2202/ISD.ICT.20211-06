package utils;

import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entities.Bike;
import entities.CreditCard;
import entities.Customer;
import entities.Dock;
import entities.Invoice;
import entities.PaymentTransaction;
import exceptions.ecobike.EcoBikeException;
import exceptions.ecobike.InvalidEcoBikeInformationException;


public class DBUtils {
	private static Connection connection;
	
	public static Connection getConnection() throws EcoBikeException {
        if (connection != null) return connection;
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:src/lib/ecobikedb.db";
            connection = DriverManager.getConnection(url);
            System.out.println("Connect database successfully");
        } catch (Exception e) {
        	throw new EcoBikeException("Error when initialize database for EcoBike:"+e.getMessage());
        }
        return connection;
	}
	
	
	public static void initializeDBInstance() {
		// TODO Auto-generated method stub
		
	}

	public static Invoice getInvoiceById(String invoiceID) throws EcoBikeException, SQLException {
		PreparedStatement stmI = DBUtils.getConnection().prepareStatement(
				"SELECT DISTINCT Invoice.rent_id, invoice_id, bike_barcode, start_time, end_time, "
				+ "rent_period FROM Invoice, RentBike WHERE invoice_id=? And Invoice.rent_id = RentBike.rent_id");
		stmI.setString(1, invoiceID);
		ResultSet resultI = stmI.executeQuery();
		Invoice invoiceRes = new Invoice(resultI.getString("invoice_id"), resultI.getString("bike_barcode"), 
				resultI.getTime("start_time").toString(), resultI.getTime("end_time").toString());
		invoiceRes.setRentID(resultI.getInt("rent_id"));
		String sql = "Select * from Invoice, EcoBikeTransaction where invoice_id = ? And "
				+ "Invoice.transaction_id = EcoBikeTransaction.transaction_id";
		PreparedStatement stm = DBUtils.getConnection().prepareStatement(sql);
		stm.setString(1, invoiceID);
		ResultSet result = stm.executeQuery();
		while(result.next()) {
			PaymentTransaction transaction = new PaymentTransaction(result.getString("transaction_id"),
					result.getString("creaditcard_number"), result.getDouble("transaction_amount"),
					result.getString("transaction_detail"), result.getDate("transaction_time").toString());
			// doing a little bit structured here. Will change it to OO later
			String transactionDetails = result.getString("transaction_detail");
			if(transactionDetails.contains(Configs.TransactionType.PAY_DEPOSIT.toString()) ||
					transactionDetails.contains(Configs.TransactionType.PAY_RENTAL.toString()) || 
							transactionDetails.contains(Configs.TransactionType.RETURN_DEPOSIT.toString())) {
				transaction.setContent(transactionDetails);
			} else {
				throw new InvalidEcoBikeInformationException("invalid transaction message in database");
			}
			invoiceRes.addTransaction(transaction);
		}
		return invoiceRes;
	}	
	
	public static Customer getCustomerInformation(int customerID) throws EcoBikeException, SQLException {
		if (connection == null) {
			initializeDBInstance();
		}
		PreparedStatement stm = connection.prepareStatement(
				"SELECT * FROM Customer WHERE customer_id=?");
		stm.setInt(1, customerID);
		ResultSet result = stm.executeQuery();
		Customer customerRes = new Customer(result.getString("customer_id"),
				result.getString("customer_name"),
				result.getString("customer_email")); 
		return customerRes;
	}
	
	public static CreditCard getCreditCardByNumber(String creditCardNumber) throws SQLException, EcoBikeException {
		if (connection == null) {
			initializeDBInstance();
		}
		PreparedStatement stm = connection.prepareStatement(
				"SELECT * FROM CreditCard WHERE creditcard_number=?");
		stm.setString(1, creditCardNumber);
		ResultSet result = stm.executeQuery();
		CreditCard card = new CreditCard(result.getString("cardholder_name"),
				result.getString("creditcard_number"), 
				result.getString("issuing_bank"), 
				result.getString("security_code"),
				result.getDouble("balance"),
				result.getDate("expiration_date").toString());
		return card;
	}
	
	public static PaymentTransaction getTransactionById(String transactionID) throws SQLException, EcoBikeException {
		PreparedStatement stm = DBUtils.getConnection().prepareStatement(
				"SELECT * FROM EcoBikeTransaction WHERE transaction_id=?");
		stm.setString(1, transactionID);
		ResultSet result = stm.executeQuery();
		PaymentTransaction transaction = new PaymentTransaction();
		transaction.setCreditCardNumber(result.getString("creditcard_number"));
		transaction.setTransactionId(transactionID);
		transaction.setAmount(result.getDouble("transaction_amount"));
		transaction.setTransactionTime(result.getDate("transaction_time").toString());
		String transactionDetails = result.getString("transaction_detail");
		if(transactionDetails.contains(Configs.TransactionType.PAY_DEPOSIT.toString()) ||
				transactionDetails.contains(Configs.TransactionType.PAY_RENTAL.toString()) || 
						transactionDetails.contains(Configs.TransactionType.RETURN_DEPOSIT.toString())) {
			transaction.setContent(transactionDetails);
		} else {
			throw new InvalidEcoBikeInformationException("invalid transaction message in database");
		}
		return transaction;
	}
	
	public static Bike getBikeByBarcode(String bikeBarcode) throws EcoBikeException, SQLException {
		String sql = "SELECT *, Bike.bike_barcode as BikeBarcode FROM Bike, BikeStatus, BikeInDock WHERE Bike.bike_barcode=? "
				+ "And Bike.bike_barcode = BikeStatus.bike_barcode And "
				+ "BikeStatus.bike_barcode = BikeInDock.bike_barcode";
		PreparedStatement stm = DBUtils.getConnection().prepareStatement(sql);
		stm.setString(1, bikeBarcode);
		ResultSet result = stm.executeQuery();
		// TODO: finish constructor here
		Bike bikeRes = new Bike(result.getString("name"), 
				result.getString("bike_type"),
				result.getString("license_plate_code"),
				result.getString("bike_image"), 
				result.getString("BikeBarcode"), 
				result.getDouble("bike_rental_price"),
				result.getString("currency_unit"),
				result.getDouble("deposit_price"),  
				result.getDate("create_date").toString());
//		 set bike current status
		String bikeStatus = result.getString("current_status");
		Configs.BIKE_STATUS bikeStat;
		if(bikeStatus.equalsIgnoreCase("FREE")) {
			bikeStat = Configs.BIKE_STATUS.FREE;
		} else if (bikeStatus.equalsIgnoreCase("RENT")) {
			bikeStat = Configs.BIKE_STATUS.RENT;
		} else {
			throw new InvalidEcoBikeInformationException("invalid status of bike in database");
		}
		bikeRes.setCurrentStatus(bikeStat);
		bikeRes.setTotalRentTime(result.getInt("total_rent_time"));
		bikeRes.setCurrentBattery(result.getFloat("current_battery"));
		
		// set dockId
		bikeRes.setDockId(result.getInt("dock_id"));
		
		return bikeRes;
//		String strRes = JSONUtils.serializeBikeInformation(bikeRes);
//		return strRes;
	}
	
	public static  ArrayList<Bike> getAllBikeByDockId(int dockId) throws SQLException, EcoBikeException {
		String sql = "SELECT Bike.name as name,"
				+ "Bike.bike_type as bike_type,"
				+ "Bike.license_plate_code as license_plate_code,"
				+ "Bike.bike_image as bike_image,"
				+ "Bike.bike_barcode as bike_barcode,"
				+ "Bike.bike_rental_price as bike_rental_price,"
				+ "Bike.currency_unit as currency_unit,"
				+ "Bike.bike_rental_price as deposit_price,"
				+ "Bike.create_date as create_date,"
				+ "BikeStatus.total_rent_time as total_rent_time,"
				+ "BikeStatus.current_battery as current_battery,"
				+ "BikeStatus.current_status as current_status FROM Bike, BikeStatus, BikeInDock WHERE dock_id=? "
				+ "And Bike.bike_barcode = BikeStatus.bike_barcode And "
				+ "BikeStatus.bike_barcode = BikeInDock.bike_barcode";
		PreparedStatement stm = DBUtils.getConnection().prepareStatement(sql);
		stm.setInt(1, dockId);
		ResultSet result = stm.executeQuery();
		ArrayList<Bike> list = new ArrayList<Bike>();
		while(result.next()) {
			// TODO: finish constructor here
			Bike bikeRes = new Bike(result.getString("name"), 
					result.getString("bike_type"),
					result.getString("license_plate_code"),
					result.getString("bike_image"), 
					result.getString("bike_barcode"), 
					result.getDouble("bike_rental_price"),
					result.getString("currency_unit"),
					result.getDouble("deposit_price"),  
					result.getDate("create_date").toString());
			bikeRes.setDockId(dockId);
			bikeRes.setTotalRentTime(result.getInt("total_rent_time"));
			bikeRes.setCurrentBattery(result.getFloat("current_battery"));
			list.add(bikeRes);
			
			// set bike status
			String bikeStatus = result.getString("current_status");
			System.out.println(bikeStatus);
			Configs.BIKE_STATUS bikeStat;
			if(bikeStatus.equalsIgnoreCase("FREE")) {
				bikeStat = Configs.BIKE_STATUS.FREE;
			} else if (bikeStatus.equalsIgnoreCase("RENT")) {
				bikeStat = Configs.BIKE_STATUS.RENT;
			} else {
				throw new InvalidEcoBikeInformationException("invalid status of bike in database");
			}
			bikeRes.setCurrentStatus(bikeStat);
			
			// set dockId
//			bikeRes.setDockId(result.getString("dock_id"));
		}
		
		return list;
	}

	public static Dock getDockInformation(int dockID) throws SQLException, EcoBikeException {
		PreparedStatement stm = DBUtils.getConnection().prepareStatement(
				"SELECT * FROM Dock WHERE dock_id=?");
		stm.setInt(1, dockID);
		ResultSet result = stm.executeQuery();
		Dock dockRes = new Dock(result.getString("name"), 
				result.getInt("dock_id"), 
				result.getString("dock_address"), 
				result.getDouble("dock_area"),
				result.getInt("num_available_bike"),
				result.getInt("num_free_dock"),
				result.getString("dock_image"));
		return dockRes;
	}
	
	public static List<Dock> getAllDock() throws SQLException, EcoBikeException {
		String sql = "Select * from Dock";
		Statement stm = DBUtils.getConnection().createStatement();
		ResultSet result = stm.executeQuery(sql);
		List<Dock> docks = new ArrayList<Dock>();
		while(result.next()) {
			Dock dockRes = new Dock(result.getString("name"), 
					result.getInt("dock_id"), 
					result.getString("dock_address"), 
					result.getDouble("dock_area"),
					result.getInt("num_available_bike"),
					result.getInt("num_free_dock"),
					result.getString("dock_image"));
			docks.add(dockRes);
		}
		return docks;
	}
	
}
