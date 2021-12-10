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


public class DBUtils {
	private static Connection connection;
	public static void initializeDBInstance() throws EcoBikeException {
        if (connection != null) return;
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:src/ecobikedb.db";
            connection = DriverManager.getConnection(url);
            System.out.println("Connect database successfully");
        } catch (Exception e) {
        	throw new EcoBikeException("Error when initialize database for EcoBike:"+e.getMessage());
        }
	}
	
	public static String getBikeInformation(String bikeBarcode) throws EcoBikeException, SQLException {
		if (connection == null) {
			initializeDBInstance();
		}
		PreparedStatement stm = connection.prepareStatement(
				"SELECT * FROM Bike WHERE bike_barcode=?");
		stm.setString(1, bikeBarcode);
		ResultSet result = stm.executeQuery();
		Bike bikeRes = new Bike(result.getString("name"), 
				result.getString("bike_type"), 
				result.getString("bike_image"), 
				result.getString("bike_barcode"), 
				result.getDouble("bike_rental_price"),
				result.getDouble("deposit_price"), 
				result.getString("currency_unit"), 
				result.getString("create_date"));
		String strRes = JSONUtils.serializeBikeInformation(bikeRes);
		return strRes;
	}
	
	public static String getDockInformation(String dockID) throws SQLException, EcoBikeException {
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
		String strRes = JSONUtils.serializeDockInformation(dockRes);
		return strRes;
	}
	
	public static String getInvoiceInformation(String invoiceID) throws EcoBikeException, SQLException {
		if (connection == null) {
			initializeDBInstance();
		}
		PreparedStatement stm = connection.prepareStatement(
				"SELECT * FROM Invoice WHERE invoice_id=?");
		stm.setString(1, invoiceID);
		ResultSet result = stm.executeQuery();
		Invoice invoiceRes = null; // TODO need to re-consider database and constructor compliance
//		Invoice invoiceRes = new Invoice(result.getString("invoice_id"), 
//				String bikeName, 
//				double deposit, 
//				Timestamp start_time, 
//				Timestamp end_time,
//				int total_rent_time, 
//				double subtotal, 
//				double total, 
//				Timestamp timeCreate);
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
				result.getString("expiration_date"), // TODO need field this in DB
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
		PaymentTransaction transaction = new PaymentTransaction(result.getString("transaction_id"), 
//				result.getString("creditcard_number"), // TODO need to compilance this w database
				null,
				result.getDouble("transaction_amount"),
//				result.getDouble("transaction_time"), // TODO need to convert this to timestamp first
				null,
				result.getString("transation_detail"));
		String strRes = JSONUtils.serializeTransactionInformation(transaction);
		return strRes;
	}
	
	
}
