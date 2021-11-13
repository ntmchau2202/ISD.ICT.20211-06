package views;

import controllers.EcoBikeBaseController;
import entities.CreditCard;
import exceptions.ecobike.EcoBikeUndefinedException;
import exceptions.ecobike.RentBikeException;

/**
 * This is the class handler for payment method screen
 * @author Duong
 *
 */
public class PaymentMethodScreenHandler extends EcoBikeBaseScreenHandler {
	/**
	 * Initialize handler for paying method screen of EcoBike application
	 * @param screenTitle Title of the screen
	 * @param controller Controller for handling request from the screen
	 * @param prevScreen An instance to the screen that called this screen
	 */
	protected PaymentMethodScreenHandler(String screenTitle, EcoBikeBaseController controller, EcoBikeBaseScreenHandler prevScreen) {
		super(screenTitle, controller, prevScreen);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Get payment method information from the form and go to transaction screen
	 * @throws EcoBikeUndefinedException If there is an unexpected error occurs during the renting process
	 */
	public void confirmPaymentMethod() throws EcoBikeUndefinedException {
		
	}
	
	/**
	 * Check if the form has any blank field or input that does not satisfy requirements
	 * @throws RentBikeException If the card info is invalid
	 * @throws EcoBikeUndefinedException If there is an unexpected error occurs during the renting process
	 */
	private void validateForm() throws RentBikeException, EcoBikeUndefinedException {
		
	}
	
	/**
	 * Get inputs from the form and send request to controller to update the card information
	 * @param card The credit card used to update
	 * @throws RentBikeException If the card is invalid
	 * @throws EcoBikeUndefinedException If there is an unexpected error occurs during the renting process
	 */
	public void updateCard() throws RentBikeException, EcoBikeUndefinedException {
		
	}
}
