package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

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
			initializeDBInstance();
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
	
	public static void initializeDBInstance() {
		// TODO Auto-generated method stub
		
	}

	public static String getInvoiceInformation(String invoiceID) throws EcoBikeException, SQLException {
		if (connection == null) {
			initializeDBInstance();
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
			initializeDBInstance();
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
			initializeDBInstance();
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
			initializeDBInstance();
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

	public static Bike getBike(String barcode) throws EcoBikeException, SQLException {
		if (connection == null) {
			initializeDBInstance();
		}
		//todo: return bike entities
//		return new Bike();
		return null;
	}

	public static void updateBikeStatus(String bike) throws EcoBikeException, SQLException {
		if (connection == null) {
			initializeDBInstance();
		}
		//todo: update bike entities
	}
}
