package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

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
            String url = "jdbc:sqlite:src/ecobikedb.db";
            connection = DriverManager.getConnection(url);
            System.out.println("Connect database successfully");
        } catch (Exception e) {
        	throw new EcoBikeException("Error when initialize database for EcoBike:"+e.getMessage());
        }
        return connection;
	}
	
	public static Dock getDockInformation(String dockID) throws SQLException, EcoBikeException {
		if (connection == null) {
			getConnection();
		}
		PreparedStatement stm = connection.prepareStatement(
				"SELECT * FROM Dock WHERE dock_id=?");
		stm.setString(1, dockID);
		ResultSet result = stm.executeQuery();
		Dock dockRes = new Dock(result.getString("name"), 
				result.getString("dock_id"), 
				result.getString("dock_address"), 
				result.getDouble("dock_area"),
				result.getInt("num_available_bike"),
				result.getInt("num_free_dock"));
		return dockRes;
	}
	
	public static String getInvoiceInformation(String invoiceID) throws EcoBikeException, SQLException {
		if (connection == null) {
			getConnection();
		}
		PreparedStatement stmI = connection.prepareStatement(
				"SELECT * FROM Invoice WHERE invoice_id=?");
		stmI.setString(1, invoiceID);
		ResultSet resultI = stmI.executeQuery();
		int rentID = resultI.getInt("rent_id");

		Invoice invoiceRes = new Invoice();
		while(resultI.next()) {
			String transactionID = resultI.getString("transaction_id");
			PreparedStatement stmT = connection.prepareStatement(
					"SELECT * FROM EcoBikeTransaction WHERE transaction_id=?");
			stmT.setString(1, transactionID);
			ResultSet resultT = stmT.executeQuery();
			PaymentTransaction transaction = new PaymentTransaction();
			transaction.setAmount(resultT.getFloat("transaction_amount"));
			transaction.setTransactionId(transactionID);
			// doing a little bit structured here. Will change it to OO later
			String transactionDetails = resultT.getString("transaction_detail");
			if(transactionDetails.contains(Configs.TransactionType.PAY_DEPOSIT.toString()) ||
					transactionDetails.contains(Configs.TransactionType.PAY_RENTAL.toString()) || 
							transactionDetails.contains(Configs.TransactionType.RETURN_DEPOSIT.toString())) {
				transaction.setContent(transactionDetails);
			} else {
				throw new InvalidEcoBikeInformationException("invalid transaction message in database");
			}
			invoiceRes.addTransaction(transaction);
		}
		
		PreparedStatement stmR = connection.prepareStatement(
				"SELECT * FROM RentBike WHERE rent_id=?");
		stmR.setInt(1, rentID);
		ResultSet resultR = stmR.executeQuery();
		invoiceRes.setStartTime(resultR.getString("start_time"));
		invoiceRes.setEndTime(resultR.getString("end_time"));
		String strRes = JSONUtils.serializeInvoiceInformation(invoiceRes);
		return strRes;
	}	
	
	public static String getCustomerInformation(String customerID) throws EcoBikeException, SQLException {
		if (connection == null) {
			getConnection();
		}
		PreparedStatement stm = connection.prepareStatement(
				"SELECT * FROM Customer WHERE customer_id=?");
		stm.setString(1, customerID);
		ResultSet result = stm.executeQuery();
		Customer customerRes = new Customer(result.getString("customer_id"),
				result.getString("customer_name"),
				result.getString("customer_email")); 
		String strRes = JSONUtils.serializeCustomerInformation(customerRes);
		return strRes;
	}
	
	public static String getCreditCardInformation(String creditCardNumber) throws SQLException, EcoBikeException {
		if (connection == null) {
			getConnection();
		}
		PreparedStatement stm = connection.prepareStatement(
				"SELECT * FROM CreditCard WHERE creditcard_number=?");
		stm.setString(1, creditCardNumber);
		ResultSet result = stm.executeQuery();
		CreditCard card = new CreditCard(result.getString("creditcard_number"), 
				result.getString("cardholder_name"), 
				result.getString("issuing_bank"), 
				result.getString("expiration_date"), 
				result.getString("security_code"));
		String strRes = JSONUtils.serializeCreditCardInformation(card);
		return strRes;
	}
	
	public static String getTransactionInformation(String transactionID) throws SQLException, EcoBikeException {
		if (connection == null) {
			getConnection();
		}
		PreparedStatement stm = connection.prepareStatement(
				"SELECT * FROM EcoBikeTransaction WHERE transaction_id=?");
		stm.setString(1, transactionID);
		ResultSet result = stm.executeQuery();
		PaymentTransaction transaction = new PaymentTransaction();
		transaction.setCreditCardNumber(result.getString("creditcard_number"));
		transaction.setTransactionId(transactionID);
		transaction.setAmount(result.getFloat("transaction_amount"));
		String transactionDetails = result.getString("transaction_detail");
		if(transactionDetails.contains(Configs.TransactionType.PAY_DEPOSIT.toString()) ||
				transactionDetails.contains(Configs.TransactionType.PAY_RENTAL.toString()) || 
						transactionDetails.contains(Configs.TransactionType.RETURN_DEPOSIT.toString())) {
			transaction.setContent(transactionDetails);
		} else {
			throw new InvalidEcoBikeInformationException("invalid transaction message in database");
		}
		String strRes = JSONUtils.serializeTransactionInformation(transaction);
		return strRes;
	}

	public static Bike getBikeInformation(String barcode) throws EcoBikeException, SQLException {
		if (connection == null) {
			getConnection();
		}
		
		String sql = "SELECT * FROM Bike WHERE bike_barcode=?";
		PreparedStatement stm = DBUtils.getConnection().prepareStatement(sql);
		stm.setString(1, barcode);
		ResultSet result = stm.executeQuery();
		Bike bikeRes = new Bike(result.getString("name"), 
				result.getString("bike_type"), 
				result.getString("bike_image"), 
				result.getString("bike_barcode"), 
				result.getDouble("bike_rental_price"),
				result.getDouble("deposit_price"), 
				result.getString("currency_unit"), 
				result.getString("create_date"));
		// get and set bike current status
		stm = DBUtils.getConnection().prepareStatement(
				"SELECT * from BikeStatus WHERE bike_barcode=?");
		stm.setString(1, barcode);
		result = stm.executeQuery();
		String bikeStatus = result.getString("current_status");
		Configs.BIKE_STATUS bikeStat;
		if(bikeStatus.equalsIgnoreCase("FREE")) {
			bikeStat = Configs.BIKE_STATUS.FREE;
		} else if (bikeStatus.equalsIgnoreCase("RENTED")) {
			bikeStat = Configs.BIKE_STATUS.RENTED;
		} else {
			throw new InvalidEcoBikeInformationException("invalid status of bike in database");
		}
		bikeRes.setCurrentStatus(bikeStat);
		bikeRes.setTotalRentTime(result.getInt("total_rent_time"));
		bikeRes.setCurrentBattery(result.getFloat("current_battery"));
		return bikeRes;
	}

	public static void updateBikeStatus(String bike) throws EcoBikeException, SQLException {
		if (connection == null) {
			getConnection();
		}

	}
	
	public ArrayList<Bike> getAllBike() throws SQLException, EcoBikeException {
		if (connection == null) {
			getConnection();
		}
		PreparedStatement stm = connection.prepareStatement(
				"SELECT * FROM EcoBikeTransaction WHERE transaction_id=?");
	ResultSet result = stm.executeQuery("Select * from Bike");
	ArrayList<Bike> list = new ArrayList<Bike>();
	while(result.next()) {
		Bike bikeRes = new Bike(result.getString("name"), 
				result.getString("bike_type"), 
				result.getString("bike_image"), 
				result.getString("bike_barcode"), 
				result.getDouble("bike_rental_price"),
				result.getDouble("deposit_price"), 
				result.getString("currency_unit"), 
				result.getString("create_date"));
		bikeRes.setTotalRentTime(result.getInt("total_rent_time"));
		bikeRes.setCurrentBattery(result.getFloat("current_battery"));
		list.add(bikeRes);
	}
	
	ResultSet result2 = stm.executeQuery("Select * from BikeStatus");
	int i = 0;
	while(result2.next()) {
		String bikeStatus = result2.getString("current_status");
		Configs.BIKE_STATUS bikeStat;
		if(bikeStatus.equalsIgnoreCase("FREE")) {
			bikeStat = Configs.BIKE_STATUS.FREE;
		} else if (bikeStatus.equalsIgnoreCase("RENTED")) {
			bikeStat = Configs.BIKE_STATUS.RENTED;
		} else {
			throw new InvalidEcoBikeInformationException("invalid status of bike in database");
		}
		list.get(i).setCurrentStatus(bikeStat);
		i++;
	}
	return list;
}
	
}
