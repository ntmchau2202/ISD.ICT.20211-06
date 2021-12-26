package views;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import controllers.EcoBikeBaseController;
import entities.Bike;
import entities.CreditCard;
import exceptions.ecobike.EcoBikeUndefinedException;
import exceptions.ecobike.RentBikeException;

/**
 * This is the class handler for payment method screen
 * @author Duong
 *
 */
public class PaymentMethodScreenHandler extends EcoBikeBaseScreenHandler {
	private static PaymentMethodScreenHandler paymentScreenHandler;
	private Bike bikeToBeRent;
	protected PaymentMethodScreenHandler(String screenTitle, EcoBikeBaseScreenHandler prevScreen) {
		super(screenTitle, prevScreen);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Initialize handler for paying method screen of EcoBike application
	 * @param screenTitle Title of the screen
	 * @param controller Controller for handling request from the screen
	 * @param prevScreen An instance to the screen that called this screen
	 */

	

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}
	
	public static PaymentMethodScreenHandler getPaymentMethodScreenHandler(Bike bike, EcoBikeBaseScreenHandler prevScreen) {
		if (paymentScreenHandler == null) {
			paymentScreenHandler = new PaymentMethodScreenHandler("Register payment method for EcoBike", prevScreen);
		}
		paymentScreenHandler.prevScreen = prevScreen;
		if (bike != null) {
			paymentScreenHandler.bikeToBeRent = bike;
		}
		return paymentScreenHandler;
	}
	

	/**
	 * Get payment method information from the form and go to transaction screen
	 * @throws EcoBikeUndefinedException If there is an unexpected error occurs during the renting process
	 */
	public void confirmPaymentMethod() throws EcoBikeUndefinedException {
		
	}
	
	
}
