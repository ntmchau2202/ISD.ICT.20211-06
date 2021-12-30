package views.screen;

import java.io.IOException;
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
import javafx.stage.Stage;
import utils.Configs;

/**
 * This is the class handler for payment method screen
 * @author Duong
 *
 */
public class PaymentMethodScreenHandler extends EcoBikeBaseScreenHandler {
	
	private static PaymentMethodScreenHandler paymentScreenHandler;
	private Bike bikeToBeRent;
	protected PaymentMethodScreenHandler(Stage stage,String screenPath, EcoBikeBaseScreenHandler prevScreen) throws IOException {
		super(stage, screenPath);
		this.setPreviousScreen(prevScreen);
	}

	/**
	 * Initialize handler for paying method screen of EcoBike application
	 * @param screenTitle Title of the screen
	 * @param controller Controller for handling request from the screen
	 * @param prevScreen An instance to the screen that called this screen
	 * @throws IOException 
	 */
	
	public static PaymentMethodScreenHandler getPaymentMethodScreenHandler(Bike bike, EcoBikeBaseScreenHandler prevScreen) throws IOException {
		if (paymentScreenHandler == null) {
			paymentScreenHandler = new PaymentMethodScreenHandler(new Stage(), Configs.PAYING_METHOD_SCREEN_PATH,prevScreen);
			paymentScreenHandler.setScreenTitle("Register payment method for EcoBike");
		}
		paymentScreenHandler.setPreviousScreen(prevScreen);
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
