package utils;

import java.sql.Date;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import entities.Bike;
import entities.CreditCard;
import entities.Customer;
import entities.Dock;
import entities.Invoice;
import entities.PaymentTransaction;
import exceptions.ecobike.EcoBikeException;
import exceptions.ecobike.InvalidEcoBikeInformationException;

/**
 * This class provides functions for handling JSON objects and strings to use in communication between controllers and screen handlers, as well as between subsystems if needed
 * @author chauntm
 */
public class JSONUtils {
	public static String serializeBikeInformation(Bike bike) throws JSONException {
		JSONObject obj = new JSONObject();
		obj.put("bike_name", bike.getName());
		obj.put("bike_type", bike.getBikeType());
		obj.put("license_plate_code", bike.getLicensePlateCode());
		obj.put("bike_image", bike.getBikeImage());
		obj.put("bike_barcode", bike.getBikeBarCode());
		obj.put("bike_rental_price", bike.getBikeRentalPrice());
		obj.put("currency_unit", bike.getCurrency());
		obj.put("deposit_price", bike.getDeposit());
		obj.put("create_date", bike.getCreateDate().toString());
		obj.put("current_status", bike.getCurrentStatus().toString());
		obj.put("current_battery",String.valueOf(bike.getCurrentBattery()));
		obj.put("total_rent_time", bike.getTotalRentTime());
		return obj.toString();
	}
	
	public static Bike toBike(String bikeStr) throws InvalidEcoBikeInformationException, JSONException {
		JSONObject result = new JSONObject(bikeStr);
		if (!result.has("bike_barcode")) {
			throw new InvalidEcoBikeInformationException("invalid JSON string to parse to Bike");
		}
		// TODO: finish constructor here
		Bike bikeRes = null;
//		Bike bikeRes = new Bike(result.getString("name"), 
//				result.getString("bike_type"),
//				result.getString("license_plate_code"),
//				result.getString("bike_image"), 
//				result.getInt("bike_barcode"), 
//				result.getDouble("bike_rental_price"),
//				result.getString("currency_unit"),
//				result.getDouble("deposit_price"),  
//				result.getString("create_date"));
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
		bikeRes.setCurrentBattery(Float.valueOf(result.getString("current_battery")));
		return bikeRes;
	}
	
	public static String serializeDockInformation(Dock dock) throws JSONException {
		JSONObject obj = new JSONObject();
		obj.put("name", dock.getName());
		obj.put("dock_id", dock.getDockID());
		obj.put("dock_address", dock.getDockAddress());
		obj.put("dock_area", dock.getDockArea());
		obj.put("num_available_bike", dock.getNumAvailableBike());
		obj.put("num_free_dock", dock.getNumDockSpaceFree());
		obj.put("dock_image", dock.getDockImage());
		return obj.toString();
	}
	
	public static Dock toDock(String dockStr) throws InvalidEcoBikeInformationException, JSONException {
		JSONObject result = new JSONObject(dockStr);
		if (!result.has("dock_id")) {
			throw new InvalidEcoBikeInformationException("invalid JSON string to parse to Dock");
		}
		return null;
		// TODO: return properly
//		return new Dock(result.getString("name"), 
//				result.getInt("dock_id"), 
//				result.getString("dock_address"), 
//				result.getDouble("dock_area"),
//				result.getInt("num_available_bike"),
//				result.getInt("num_free_dock"),
//				result.getString("dock_image"));
	}
	
	public static String serializeInvoiceInformation(Invoice invoice) throws JSONException {
		JSONObject obj = new JSONObject();
		obj.put("invoice_id", invoice.getInvoiceID());
		obj.put("rent_id", invoice.getRentID());
		ArrayList<PaymentTransaction> transactionList = invoice.getTransactionList();
		JSONArray transArr = new JSONArray();
		for(PaymentTransaction t : transactionList) {
			JSONObject objt = new JSONObject();
			objt.put("transaction_id", t.getTransactionId());
			objt.put("transaction_amount", t.getAmount());
			objt.put("transaction_time", t.getTransactionTime());
			objt.put("transaction_detail", t.getContent());
			objt.put("creditcard_number", t.getCreditCardNumber());
			transArr.put(objt);
		}
		obj.put("transactions", transArr);
		return obj.toString();
	}
	
	public static Invoice toInvoice(String invoiceStr) throws JSONException, EcoBikeException {
		JSONObject result = new JSONObject(invoiceStr);
		if(!result.has("invoice_id")) {
			throw new InvalidEcoBikeInformationException("invalid JSON string to parse to Invoice");
		}
		Invoice invoice = new Invoice();
		invoice.setInvoiceID(result.getString("invoice_id"));
		invoice.setRentID(result.getInt("rent_id"));
		JSONArray transArr = result.getJSONArray("transactions");
		for(int i=0;i<transArr.length();i++) {
			PaymentTransaction transaction = new PaymentTransaction();
			JSONObject jsonTrans = (JSONObject) transArr.get(i);
			transaction.setTransactionId(jsonTrans.getString("transaction_id"));
			transaction.setAmount(jsonTrans.getInt("transaction_amount"));
			transaction.setContent(jsonTrans.getString("transaction_detail"));
			transaction.setCreditCardNumber(jsonTrans.getString("creditcard_number"));
			transaction.setTransactionTime(jsonTrans.getString("transaction_time"));
			invoice.addTransaction(transaction);
		}
		invoice.setStartTime(result.getString("start_time"));
		invoice.setEndTime(result.getString("end_time"));
		return invoice;
	}
	
	public static String serializeTransactionInformation(PaymentTransaction transaction) throws JSONException {
		JSONObject obj = new JSONObject();
		obj.put("transaction_id", transaction.getTransactionId());
		obj.put("transaction_amount", transaction.getAmount());
		obj.put("transaction_time", transaction.getTransactionTime());
		obj.put("transation_detail", transaction.getContent());
		obj.put("creditcard_number", transaction.getCreditCardNumber());
		return obj.toString();
	}

	public static PaymentTransaction toTransaction(String transactionStr) throws JSONException, EcoBikeException {
		JSONObject result = new JSONObject(transactionStr);
		if(!result.has("transaction_id")) {
			throw new InvalidEcoBikeInformationException("invalid JSON string to parse to Transaction");
		}
		PaymentTransaction transaction = new PaymentTransaction();
		transaction.setAmount(result.getDouble("transaction_amount"));
		transaction.setContent(result.getString("transaction_detail"));
		transaction.setCreditCardNumber(result.getString("creditcard_number"));
		transaction.setTransactionId(result.getString("transaction_id"));
		transaction.setTransactionTime(result.getString("transaction_time"));
		return transaction;
	}
	
	public static String serializeCreditCardInformation(CreditCard card) throws JSONException {
		JSONObject obj = new JSONObject();
		obj.put("cardholder_name", card.getCardHolderName());
		obj.put("creditcard_number", card.getCardNumber());
		obj.put("issusing_bank", card.getIssueBank());
		obj.put("security_code", card.getCardSecurity());
		obj.put("balance", card.getBalance());
		obj.put("expiration_date", card.getExpirationDate().toString());
		return obj.toString();
	}
	
	public static CreditCard toCreditCard(String cardStr) throws InvalidEcoBikeInformationException, JSONException {
		JSONObject result = new JSONObject(cardStr);
		if (!result.has("creditcard_number")) {
			throw new InvalidEcoBikeInformationException("invalid JSON string to parse to CreditCard");
		}
		return null;
		// TODO: return properly
//		return new CreditCard(result.getString("cardholder_name"),
//				result.getString("creditcard_number"), 
//				result.getString("issuing_bank"), 
//				result.getString("security_code"),
//				result.getDouble("balance"),
//				result.getString("expiration_date"));
	}
	
	public static String serializeCustomerInformation(Customer customer) throws JSONException {
		JSONObject obj = new JSONObject();
		obj.put("customer_id", customer.getCustomerID());
		obj.put("customer_name", customer.getCustomerName());
		obj.put("customer_email", customer.getCustomerEmail());
		return obj.toString();
	}
	
	public static Customer toCustomer(String customerStr) throws InvalidEcoBikeInformationException, JSONException {
		JSONObject result = new JSONObject(customerStr);
		if (!result.has("customer_id")) {
			throw new InvalidEcoBikeInformationException("invalid JSON string to parse to Customer");
		}
		return new Customer(result.getString("customer_id"),
				result.getString("customer_name"),
				result.getString("customer_email")); 
	}
	
}
