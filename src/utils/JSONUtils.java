package utils;

import org.json.JSONObject;

import entities.Bike;
import entities.CreditCard;
import entities.Customer;
import entities.Dock;
import entities.Invoice;
import entities.PaymentTransaction;
import exceptions.ecobike.InvalidEcoBikeInformationException;

/**
 * This class provides functions for handling JSON objects and strings to use in communication between controllers and screen handlers, as well as between subsystems if needed
 * @author chauntm
 */
public class JSONUtils {
	public static String serializeBikeInformation(Bike bike) {
		JSONObject obj = new JSONObject();
		obj.put("bike_name", bike.getName());
		obj.put("bike_type", bike.getBikeType());
		obj.put("bike_barcode", bike.getBarCode());
		obj.put("bike_rental_price", bike.getBikeRentalPrice());
		obj.put("bike_create_date", bike.getCreateDate().toString());
		obj.put("bike_deposit_price", bike.getDeposit());
		obj.put("currency_unit", bike.getCurrency());
		return obj.toString();
	}
	
	public static Bike toBike(String bikeStr) throws InvalidEcoBikeInformationException {
		JSONObject result = new JSONObject(bikeStr);
		if (!result.has("bike_barcode")) {
			throw new InvalidEcoBikeInformationException("invalid JSON string to parse to Bike");
		}
		return new Bike(result.getString("bike_name"), 
				result.getString("bike_type"), 
				result.getString("bike_image"), 
				result.getString("bike_barcode"), 
				result.getDouble("bike_rental_price"),
				result.getDouble("bike_deposit_price"), 
				result.getString("currency_unit"), 
				result.getString("bike_create_date"));
	}
	
	public static String serializeDockInformation(Dock dock) {
		JSONObject obj = new JSONObject();
		obj.put("dock_name", dock.getName());
		obj.put("dock_id", dock.getDockID());
		obj.put("dock_address", dock.getDockAddress());
		obj.put("dock_area", dock.getDockArea());
		obj.put("num_available_bike", dock.getNumAvailableBike());
		obj.put("num_free_dock", dock.getNumDockSpaceFree());
		return obj.toString();
	}
	
	public static Dock toDock(String dockStr) throws InvalidEcoBikeInformationException {
		JSONObject result = new JSONObject(dockStr);
		if (!result.has("dock_id")) {
			throw new InvalidEcoBikeInformationException("invalid JSON string to parse to Dock");
		}
		return new Dock(result.getString("dock_name"), 
				result.getString("dock_id"), 
				result.getString("dock_address"), 
				result.getDouble("dock_area"),
				result.getInt("num_available_bike"),
				result.getInt("num_free_dock"));
	}
	
	public static String serializeInvoiceInformation(Invoice invoice) {
		// TODO consider this carefully
		return "";
	}
	
	public static Invoice toInvoice(String invoiceStr) throws InvalidEcoBikeInformationException {
		// TODO consider this carefully
		return null;
	}
	
	public static String serializeTransactionInformation(PaymentTransaction transaction) {
		// TODO consider this carefully
		return "";
	}

	public static PaymentTransaction toTransaction(String transactionStr) throws InvalidEcoBikeInformationException {
		// TODO consider this carefully
		return null;
	}
	
	public static String serializeCreditCardInformation(CreditCard card) {
		JSONObject obj = new JSONObject();
		obj.put("cardholder_name", card.getCardHolderName());
		obj.put("creditcard_number", card.getCardNumber());
		obj.put("security_code", card.getCardSecurity());
		obj.put("issusing_bank", card.getIssueBank());
		// TODO do we need a field of balance here?
		return obj.toString();
	}
	
	public static CreditCard toCreditCard(String cardStr) throws InvalidEcoBikeInformationException {
		JSONObject result = new JSONObject(cardStr);
		if (!result.has("creditcard_number")) {
			throw new InvalidEcoBikeInformationException("invalid JSON string to parse to CreditCard");
		}
		return new CreditCard(result.getString("creditcard_number"), 
				result.getString("cardholder_name"), 
				result.getString("issuing_bank"), 
				result.getString("expiration_date"), // TODO need field this in DB
				result.getString("security_code"));
	}
	
	public static String serializeCustomerInformation(Customer customer) {
		JSONObject obj = new JSONObject();
		obj.put("customer_id", customer.getCustomerID());
		obj.put("customer_name", customer.getCustomerName());
		obj.put("customer_email", customer.getCustomerEmail());
		return obj.toString();
	}
	
	public static Customer toCustomer(String customerStr) throws InvalidEcoBikeInformationException {
		JSONObject result = new JSONObject(customerStr);
		if (!result.has("customer_id")) {
			throw new InvalidEcoBikeInformationException("invalid JSON string to parse to Customer");
		}
		return new Customer(result.getString("customer_id"),
				result.getString("customer_name"),
				result.getString("customer_email")); 
	}
	
}
