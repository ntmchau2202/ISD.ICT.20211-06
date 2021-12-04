package views;

import controllers.EcoBikeBaseController;
import entities.PaymentTransaction;
import exceptions.ecobike.EcoBikeUndefinedException;
import exceptions.ecobike.RentBikeException;

/**
 * This is the class handler for transaction screen
 * @author Duong
 *
 */
public class TransactionScreenHandler extends EcoBikeBaseScreenHandler {
	/**
	 * Initialize handler for transaction screen of EcoBike application
	 * @param screenTitle Title of the screen
	 * @param controller Controller for handling request from the screen
	 * @param prevScreen An instance to the screen that called this screen
	 */
	protected TransactionScreenHandler(String screenTitle, EcoBikeBaseController controller, EcoBikeBaseScreenHandler prevScreen) {
		super(screenTitle, controller, prevScreen);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Show details of the transaction and allow customer to confirm or eject payment
	 * @param transaction  The transaction entity ({@link entities.PaymentTransaction}
	 * @throws RentBikeException If the transaction is invalid
	 * @throws EcoBikeUndefinedException If there is an unexpected error occurs during the renting process
	 */
	public void confirmTransaction(PaymentTransaction transaction) throws  RentBikeException, EcoBikeUndefinedException {
		boolean check = validationTransaction(transaction);
	}
	
	public boolean validationTransaction(PaymentTransaction transaction) {
		return true;
	}

}
