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
import utils.FunctionalUtils;

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
     * Display a screen including transaction information
     *
     * @param transaction The transaction entity ({@link entities.PaymentTransaction}
     * @throws RentBikeException         If the transaction is not valid
     * @throws EcoBikeUndefinedException If there is an unexpected error occurs during the renting process
     */
    public void displayTransactionInfo(PaymentTransaction transaction) throws RentBikeException, EcoBikeUndefinedException {
    	
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
     * Display a screen including the invoice information
     *
     * @param invoice The Invoice entity ({@link entities.Invoice}
     * @return The invoice entity ({@link entities.Invoice}
     * @throws EcoBikeException 
     * @throws SQLException 
     */
    public void displayInvoiceScreen(Invoice invoice) throws SQLException, EcoBikeException {
    	
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
        return FunctionalUtils.contains(cardHolderName, "^[a-zA-Z ]");
    }

    public boolean validateIssueBank(String issueBank) {
        return true;
    }

    public boolean validateExpirationDate(String expirationDate) {
        SimpleDateFormat format = new java.text.SimpleDateFormat("mm/yy");
        try {
            format.parse(expirationDate);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public boolean validateCardSecurity(String cardSecurity) {
    	if (cardSecurity.length() != 3) {
    		return false;
    	}
        return FunctionalUtils.contains(cardSecurity, "^[0-9]");
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
//    public String getExpirationDate(String date) throws InvalidCardException {
//        String strs[] = date.split("/");
//        if (strs.length != 2) {
//            throw new InvalidCardException("Invalid format of date");
//        }
//
//        String expirationDate = null;
//        int month = -1;
//        int year = -1;
//
//        try {
//            month = Integer.parseInt(strs[0]);
//            year = Integer.parseInt(strs[1]);
//            if (month < 1 || month > 12 || year < Calendar.getInstance()
//                    .get(Calendar.YEAR) % 100 || year > 100) {
//                throw new InvalidCardException("Invalid date");
//            }
//            expirationDate = strs[0] + strs[1];
//        } catch (Exception e) {
//            throw new InvalidCardException("Invalid date");
//        }
//        return expirationDate;
//    }

}
