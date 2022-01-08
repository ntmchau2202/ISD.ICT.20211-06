package controllers;

import boundaries.InterbankBoundary; 
import entities.CreditCard;
import entities.Invoice;
import entities.PaymentTransaction;
import exceptions.ecobike.EcoBikeException;
import exceptions.ecobike.EcoBikeUndefinedException;
import exceptions.ecobike.RentBikeException;
import exceptions.interbank.InvalidCardException;
import interfaces.InterbankInterface;
import utils.DBUtils;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * This is the class controller including all the methods and operations for payment use case.
 * <br>@author Duong
 */
public class PaymentController extends EcoBikeBaseController {

    private static PaymentController paymentController;
    @SuppressWarnings("unused")
	private static Logger LOGGER = utils.FunctionalUtils.getLogger(PaymentController.class.getName());
    /**
     * Represent the card used for payment.
     */
    private CreditCard card;
    /**
     * Represent the Interbank subsystem.
     */
    private InterbankInterface interbank;

    private PaymentController() {
    }

    public static PaymentController getPaymentController() {
		if (paymentController == null)
			paymentController = new PaymentController();
		return paymentController;
    }

    /**
     * Pay for the deposit for the rental bike and return message result.
     * <br>@param cardNumber  the credit card number
     * <br>@param amount  the money to pay deposit
     * <br>@param content  the content of transaction
     * <br>@throws RentBikeException If the transaction is invalid
     * <br>@throws EcoBikeUndefinedException If there is an unexpected error occurs during the renting process
     * <br>@return {@link java.util.Map Map} represent the payment result with a
     * message.
     */
    @SuppressWarnings("unused")
    public Map<String, String> payDeposit(int amount, String content,
                                          String cardHolderName,
                                          String cardNumber,
                                          String issueBank, float balance,
                                          String expirationDate,
                                          String securityCode) throws RentBikeException, EcoBikeUndefinedException {
        Map<String, String> result = new Hashtable<String, String>();
        result.put("RESULT", "PAYMENT FAILED!");
        try {
            this.card = new CreditCard(cardHolderName, cardNumber,issueBank, securityCode, 
            		balance, getExpirationDate(expirationDate));
            this.interbank = new InterbankBoundary(issueBank);
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
     *
     * @param card    the credit card used to pay
     * @param amount  the money to pay deposit
     * @param content the content of transaction
     * @throws RentBikeException         If the transaction is invalid
     * @throws EcoBikeUndefinedException If there is an unexpected error occurs during the renting process
     *                                   <br>@return {@link java.util.Map Map} represent the payment result with a
     *                                   message.
     */
    @SuppressWarnings("unused")
    public Map<String, String> returnDeposit(int amount, String content, String cardHolderName, 
    		String cardNumber, String issueBank, float balance, 
    		String expirationDate, String securityCode) 
    				throws RentBikeException, EcoBikeUndefinedException {
        Map<String, String> result = new Hashtable<String, String>();
        result.put("RESULT", "RETURN FAILED!");
        try {
        	this.card = new CreditCard(cardHolderName, cardNumber,issueBank, securityCode, 
            		balance, getExpirationDate(expirationDate));
            this.interbank = new InterbankBoundary(issueBank);
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
     *
     * @param card    the credit card used to pay
     * @param amount  the money to pay deposit
     * @param content the content of transaction
     * @throws RentBikeException         If the transaction is invalid
     * @throws EcoBikeUndefinedException If there is an unexpected error occurs during the renting process
     *                                   <br>@return {@link java.util.Map Map} represent the payment result with a
     *                                   message.
     */
    @SuppressWarnings("unused")
    public Map<String, String> payRental(int amount, String content, String cardHolderName, 
    		String cardNumber, String issueBank, float balance, 
    		String expirationDate, String securityCode) throws RentBikeException, EcoBikeUndefinedException {
        Map<String, String> result = new Hashtable<String, String>();
        result.put("RESULT", "PAYMENT FAILED!");
        try {
        	this.card = new CreditCard(cardHolderName, cardNumber,issueBank, securityCode, 
            		balance, getExpirationDate(expirationDate));
            this.interbank = new InterbankBoundary(issueBank);
            PaymentTransaction transaction = interbank.payRental(card, amount, content);
            result.put("RESULT", "PAYMNET SUCCESSFUL!");
            result.put("MESSAGE", "You have succesffully pay the rental!");
        } catch (Exception e) {
            result.put("MESSAGE", e.getMessage());
        }
        return result;
    }

    /**
     * Request to update payment method
     *
     * @param card The credit card used to pay
     * @throws RentBikeException         If the card is invalid
     * @throws EcoBikeUndefinedException If there is an unexpected error occurs during the renting process
     */
    public void requestToUpdatePaymentMethod(CreditCard card) throws RentBikeException, EcoBikeUndefinedException {
    	this.card = card;
    }

    /**
     * save the transaction to the database
     *
     * @param transaction The transaction entity ({@link entities.PaymentTransaction}
     * @throws EcoBikeException 
     * @throws SQLException 
     */
    public void saveTransaction(PaymentTransaction transaction) throws SQLException, EcoBikeException {
    	String sql = "Insert into EcoBikeTransaction values(?, ?, ?, ?, ?)";
    	PreparedStatement stm = DBUtils.getConnection().prepareStatement(sql);
    	stm.setString(1, transaction.getTransactionId());
    	stm.setDouble(2, transaction.getAmount());
    	stm.setDate(3, Date.valueOf(transaction.getTransactionTime().toString()));
    	stm.setString(4, transaction.getContent());
    	stm.setString(5, transaction.getCreditCardNumber());
    	
    	stm.executeUpdate();
    }

    /**
     * Create an invoice including all the information relevant to renting bike
     *
     * @param transaction The transaction entity ({@link entities.PaymentTransaction}
     * @return The invoice entity ({@link entities.Invoice}
     * @throws EcoBikeException 
     * @throws SQLException 
     */
    public Invoice createInvoice(String invoiceID ,int rentId, List<PaymentTransaction> transactions) 
					throws SQLException, EcoBikeException {
    	String sql = "Select * from RentBike where rent_id = ?";
    	PreparedStatement stm = DBUtils.getConnection().prepareStatement(sql);
    	stm.setInt(1, rentId);
    	ResultSet result = stm.executeQuery();
    	Invoice invoice = new Invoice(invoiceID, result.getString("bike_barcode"), 
    			result.getTime("start_time").toString(), result.getTime("end_time").toString());
    	for(PaymentTransaction transaction : transactions) {
    		invoice.addTransaction(transaction);
    	}
    	invoice.setRentID(rentId);
    	return invoice;
    }

    /**
     * save the invoice to the database
     *
     * @param transaction The invoice entity ({@link entities.Invoice}
     * @throws EcoBikeException 
     * @throws SQLException 
     */
    public void saveInvoice(Invoice invoice) throws SQLException, EcoBikeException {
    	for(PaymentTransaction transaction : invoice.getTransactionList()) {
    		String sql = "Insert into invoice values(?, ?, ?)";
    		PreparedStatement stm = DBUtils.getConnection().prepareStatement(sql);
    		stm.setString(1, invoice.getInvoiceID());
    		stm.setString(2, transaction.getTransactionId());
    		stm.setInt(3, invoice.getRentID());
    		stm.executeUpdate();
    	}
    }

    public boolean validateCard(CreditCard card) throws RentBikeException, EcoBikeUndefinedException {

        return validateCardNumber(card.getCardNumber()) && validateCardHolderName(card.getCardHolderName())
        		&& validateCardSecurity(card.getCardSecurity()) && 
        		validateExprirationDate(card.getExpirationDate().toString()) && 
        		validateIssueBank(card.getIssueBank());
    }

    public boolean validateCardNumber(String cardNumber) {
        if (cardNumber.length() != 10) return false;
        try {
            Integer.parseInt(cardNumber);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public boolean validateCardHolderName(String cardHolderName) {
        return cardHolderName.matches("^[\\p{L} .'-]+$");
    }

    public boolean validateIssueBank(String issueBank) {
        return issueBank.equalsIgnoreCase("VCB") || issueBank.equalsIgnoreCase("AGB");
    }

    public boolean validateExprirationDate(String expirationDate) {
        SimpleDateFormat format = new java.text.SimpleDateFormat("mm/yy");
        try {
            format.parse(expirationDate);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public boolean validateCardSecurity(String cardSecurity) {
        return cardSecurity.matches("^[0-9]{3,4}$");
    }

    /**
     * Validate the input date which should be in the format "mm/yy", and then
     * return a {@link java.lang.String String} representing the date in the
     * required format "mmyy".
     *
     * <br>@param date - the {@link java.lang.String String} represents the input date
     * <br>@return {@link java.lang.String String} - date in the required format
     * <br>@throws InvalidCardException - If the string does not represent a valid date
     * in the expected format
     */
    public String getExpirationDate(String date) throws InvalidCardException {
        String strs[] = date.split("/");
        if (strs.length != 2) {
            throw new InvalidCardException("Invalid format of date");
        }

        String expirationDate = null;
        int month = -1;
        int year = -1;

        try {
            month = Integer.parseInt(strs[0]);
            year = Integer.parseInt(strs[1]);
            if (month < 1 || month > 12 || year < Calendar.getInstance()
                    .get(Calendar.YEAR) % 100 || year > 100) {
                throw new InvalidCardException("Invalid date");
            }
            expirationDate = strs[0] + strs[1];
        } catch (Exception e) {
            throw new InvalidCardException("Invalid date");
        }
        return expirationDate;
    }

}

