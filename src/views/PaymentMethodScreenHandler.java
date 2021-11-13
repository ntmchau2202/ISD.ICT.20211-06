package views;

import controllers.EcoBikeBaseController;
import entities.CreditCard;
import exceptions.ecobike.EcoBikeUndefinedException;
import exceptions.ecobike.RentBikeException;

public class PaymentMethodScreenHandler extends EcoBikeBaseScreenHandler {

	protected PaymentMethodScreenHandler(String screenTitle, EcoBikeBaseController controller, EcoBikeBaseScreenHandler prevScreen) {
		super(screenTitle, controller, prevScreen);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Confirm the payment method
	 * @throws EcoBikeUndefinedException If there is an unexpected error occurs during the renting process
	 */
	public void confirmPaymentMethod() throws EcoBikeUndefinedException {
		
	}
	
	/**
	 * Confirm the transaction
	 * @throws RentBikeException If the card info is invalid
	 * @throws EcoBikeUndefinedException If there is an unexpected error occurs during the renting process
	 */
	public void validateForm() throws RentBikeException, EcoBikeUndefinedException {
		
	}
	
	/**
	 * Update the card info
	 * @param card The credit card used to update
	 * @throws RentBikeException If the card is invalid
	 * @throws EcoBikeUndefinedException If there is an unexpected error occurs during the renting process
	 */
	public void updateCard(CreditCard card) throws RentBikeException, EcoBikeUndefinedException {
		
	}
	
	/**
	 * display the error
	 * @throws EcoBikeUndefinedException If there is an unexpected error occurs during the renting process
	 */
	public void notifyError() throws EcoBikeUndefinedException {
		
	}
}
