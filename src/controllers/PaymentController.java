package controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import boundaries.InterbankBoundary;
import entities.CreditCard;
import entities.Invoice;
import entities.PaymentTransaction;
import exceptions.ecobike.EcoBikeUndefinedException;
import exceptions.ecobike.RentBikeException;
import exceptions.interbank.InterbankException;
import exceptions.interbank.InvalidCardException;
import interfaces.InterbankInterface;

/**
 * This is the class controller including all the methods and operations for payment use case.
 * <br>@author Duong
 *
 */
public class PaymentController {
	
	/**
	 * Represent the Interbank subsystem.
	 */
	private InterbankInterface interbank;
	
	public PaymentController(InterbankInterface interbank) {
		super();
		this.interbank = interbank;
	}
	
	/**
	 * Pay for the deposit for the rental bike and return message result.
	 * <br>@param cardNumber  the credit card number
	 * <br>@param amount  the money to pay deposit
	 * <br>@param content  the content of transaction
	 * <br>@throws RentBikeException If the transaction is invalid
	 * <br>@throws EcoBikeUndefinedException If there is an unexpected error occurs during the renting process
	 * <br>@return {@link java.util.Map Map} represent the payment result with a
	 *         message.
	 */
	@SuppressWarnings("unused")
	public Map<String, String> payDeposit(int amount, String content, 
			CreditCard card) throws RentBikeException, EcoBikeUndefinedException {
		Map<String, String> result = new Hashtable<String, String>();
		result.put("RESULT", "PAYMENT FAILED!");
		try {
			PaymentTransaction transaction = interbank.payDeposit(card, amount, content);
			result.put("RESULT", "PAYMENT SUCCESSFUL!");
			result.put("MESSAGE", "You have succesffully paid the deposit!");
		} catch (Exception e) {
			result.put("MESSAGE", e.getMessage());
		}
		return result;
	}
	
	/**
	 * Return the deposit for the rental bike that was paid before
	 * @param card  the credit card used to pay
	 * @param amount  the money to pay deposit
	 * @param content  the content of transaction
	 * @throws RentBikeException If the transaction is invalid
	 * @throws EcoBikeUndefinedException If there is an unexpected error occurs during the renting process
	 * <br>@return {@link java.util.Map Map} represent the payment result with a
	 *         message.
	 */
	@SuppressWarnings("unused")
	public Map<String, String> returnDeposit(int amount, String content, 
			CreditCard card) throws RentBikeException, EcoBikeUndefinedException {
		Map<String, String> result = new Hashtable<String, String>();
		result.put("RESULT", "RETURN FAILED!");
		try {
			PaymentTransaction transaction = interbank.returnDeposit(card, amount, content);
			result.put("RESULT", "RETURN SUCCESSFUL!");
			result.put("MESSAGE", "You have succesffully receive the deposit!");
		} catch (Exception e) {
			result.put("MESSAGE", e.getMessage());
		}
		return result;
	}
	
	/**
	 * Pay for the for the rental bike
	 * @param card  the credit card used to pay
	 * @param amount  the money to pay deposit
	 * @param content  the content of transaction
	 * @throws RentBikeException If the transaction is invalid
	 * @throws EcoBikeUndefinedException If there is an unexpected error occurs during the renting process
	 * <br>@return {@link java.util.Map Map} represent the payment result with a
	 *         message.
	 */
	@SuppressWarnings("unused")
	public Map<String, String> payRental(int amount, String content, 
			CreditCard card) throws RentBikeException, EcoBikeUndefinedException {
		Map<String, String> result = new Hashtable<String, String>();
		result.put("RESULT", "PAYMENT FAILED!");
		try {
//			CreditCard card = new CreditCard(cardNumber, cardHolderName, issueBank, 
//					expirationDate, securityCode);
			PaymentTransaction transaction = interbank.payRental(card, amount, content);
			result.put("RESULT", "PAYMNET SUCCESSFUL!");
			result.put("MESSAGE", "You have succesffully pay the rental!");
		} catch (Exception e) {
			result.put("MESSAGE", e.getMessage());
		}
		return result;
	}
	
	/**
	 * Display a screen including transaction information
	 * @param transaction  The transaction entity ({@link entities.PaymentTransaction}
	 * @throws RentBikeException If the transaction is not valid
	 * @throws EcoBikeUndefinedException If there is an unexpected error occurs during the renting process
	 */
	public void displayTransactionInfo(PaymentTransaction transaction) throws RentBikeException, EcoBikeUndefinedException {
		
	}
	
	/**
	 * save the transaction to the database
	 * @param transaction  The transaction entity ({@link entities.PaymentTransaction}
	 * @throws RentBikeException If the transaction is invalid
	 * @throws EcoBikeUndefinedException If there is an unexpected error occurs during the renting process
	 */
	public void saveTransaction(PaymentTransaction transaction) throws RentBikeException, EcoBikeUndefinedException {
		
	}
	
	/**
	 * Create an invoice including all the information relevant to renting bike
	 * @param transaction  The transaction entity ({@link entities.PaymentTransaction}
	 * @throws RentBikeException If the transaction is not valid
	 * @throws EcoBikeUndefinedException If there is an unexpected error occurs during the renting process
	 * @return The invoice entity ({@link entities.Invoice}
	 */
	public Invoice createInvoice(PaymentTransaction transaction) throws RentBikeException, EcoBikeUndefinedException {
		return null;
	}
	
	/**
	 * Display a screen including the invoice information
	 * @param invoice  The Invoice entity ({@link entities.Invoice}
	 * @throws RentBikeException If the invoice is not valid
	 * @throws EcoBikeUndefinedException If there is an unexpected error occurs during the renting process
	 * @return The invoice entity ({@link entities.Invoice}
	 */
	public void displayInvoiceScreen(Invoice invoice) throws RentBikeException, EcoBikeUndefinedException {
		
	}
	
	/**
	 * save the invoice to the database
	 * @param transaction  The invoice entity ({@link entities.Invoice}
	 * @throws RentBikeException If the invoice is invalid
	 * @throws EcoBikeUndefinedException If there is an unexpected error occurs during the renting process
	 */
	public void saveInvoice(Invoice invoice) throws RentBikeException, EcoBikeUndefinedException {
		
	}
	
	private boolean validateCard(CreditCard card) throws RentBikeException, EcoBikeUndefinedException {
		if (!validateCardNumber(card.getCardNumber())) {
			return false;
		}
		
		if (!validateCardHolderName(card.getCardHolderName())) {
			return false;
		}
		
		if (!validateIssueBank(card.getIssueBank())) {
			return false;
		}
		
		if (!validateExprirationDate(card.getExpirationDate().toString())) {
			return false;
		}
		
		if (!validateCardSecurity(card.getCardSecurity())) {
			return false;
		}
		
		return true;
	}
	
	private boolean validateCardNumber(String cardNumber) {
		if(cardNumber.length()!=10) return false;
		try {
			Integer.parseInt(cardNumber);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}
	
	private boolean validateCardHolderName(String cardHolderName) {
//		return cardHolderName.matches("^[\\p{L} .'-]+$");
		return true;
	}
	
	private boolean validateIssueBank(String issueBank) {
//		return issueBank.equalsIgnoreCase("VCB") || issueBank.equalsIgnoreCase("AGB");
		return true;
	}
	
	private boolean validateExprirationDate(String expirationDate) {
//		SimpleDateFormat format = new java.text.SimpleDateFormat("mm/yy");
//        try {
//            format.parse(expirationDate);
//            return true;
//        } catch (ParseException e) {
//            return false;
//        }
		return true;
	}
	
	public boolean validateCardSecurity(String cardSecurity) {
//		return cardSecurity.matches("^[0-9]{3,4}$");
		return true;
	}	
}

