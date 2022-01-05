package controllers;

import boundaries.InterbankBoundary; 
import entities.CreditCard;
import entities.Invoice;
import entities.PaymentTransaction;
import exceptions.ecobike.EcoBikeUndefinedException;
import exceptions.ecobike.InvalidEcoBikeInformationException;
import exceptions.ecobike.RentBikeException;
import exceptions.interbank.InvalidCardException;
import interfaces.InterbankInterface;
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
                                          String cardNumber, String cardHolderName,
                                          String issueBank, String expirationDate,
                                          String securityCode) throws RentBikeException, EcoBikeUndefinedException {
        Map<String, String> result = new Hashtable<String, String>();
        result.put("RESULT", "PAYMENT FAILED!");
        try {
            this.card = new CreditCard(cardNumber, cardHolderName, issueBank,
                    getExpirationDate(expirationDate), securityCode);
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
    public Map<String, String> returnDeposit(int amount, String content,
                                             String cardNumber, String cardHolderName,
                                             String issueBank, String expirationDate,
                                             String securityCode) throws RentBikeException, EcoBikeUndefinedException {
        Map<String, String> result = new Hashtable<String, String>();
        result.put("RESULT", "RETURN FAILED!");
        try {
            this.card = new CreditCard(cardNumber, cardHolderName, issueBank,
                    getExpirationDate(expirationDate), securityCode);
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
    public Map<String, String> payRental(int amount, String content,
                                         String cardNumber, String cardHolderName,
                                         String issueBank, String expirationDate,
                                         String securityCode) throws RentBikeException, EcoBikeUndefinedException {
        Map<String, String> result = new Hashtable<String, String>();
        result.put("RESULT", "PAYMENT FAILED!");
        try {
            this.card = new CreditCard(cardNumber, cardHolderName, issueBank,
                    getExpirationDate(expirationDate), securityCode);
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
     * @throws RentBikeException         If the transaction is invalid
     * @throws EcoBikeUndefinedException If there is an unexpected error occurs during the renting process
     */
    public void saveTransaction(PaymentTransaction transaction) throws RentBikeException, EcoBikeUndefinedException {
    	
    }

    /**
     * Create an invoice including all the information relevant to renting bike
     *
     * @param transaction The transaction entity ({@link entities.PaymentTransaction}
     * @return The invoice entity ({@link entities.Invoice}
     * @throws RentBikeException         If the transaction is not valid
     * @throws EcoBikeUndefinedException If there is an unexpected error occurs during the renting process
     * @throws InvalidEcoBikeInformationException 
     */
    public Invoice createInvoice(String invoiceID, String bikeName, double deposit, String startTime,
			String endTime, double subTotal, double total, String timeCreate, List<PaymentTransaction> transactions) 
					throws RentBikeException, EcoBikeUndefinedException, InvalidEcoBikeInformationException {
    	Invoice invoice = new Invoice(invoiceID, bikeName, deposit, startTime, endTime, subTotal, total, timeCreate);
    	for(PaymentTransaction transaction : transactions) {
    		invoice.addTransaction(transaction);
    	}
    	return invoice;
    }

    /**
     * Display a screen including the invoice information
     *
     * @param invoice The Invoice entity ({@link entities.Invoice}
     * @return The invoice entity ({@link entities.Invoice}
     * @throws RentBikeException         If the invoice is not valid
     * @throws EcoBikeUndefinedException If there is an unexpected error occurs during the renting process
     */
    public void displayInvoiceScreen(Invoice invoice) throws RentBikeException, EcoBikeUndefinedException {
    	
    }

    /**
     * save the invoice to the database
     *
     * @param transaction The invoice entity ({@link entities.Invoice}
     * @throws RentBikeException         If the invoice is invalid
     * @throws EcoBikeUndefinedException If there is an unexpected error occurs during the renting process
     */
    public void saveInvoice(Invoice invoice) throws RentBikeException, EcoBikeUndefinedException {
    	
    }

//    public boolean validateCard(CreditCard card) throws RentBikeException, EcoBikeUndefinedException {
//
//        return true;
//    }

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

    public boolean validateExdpirationDate(String expirationDate) {
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

