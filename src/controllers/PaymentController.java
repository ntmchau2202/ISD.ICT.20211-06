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

    public boolean validateCardNumber(String cardNumber) {
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

}
