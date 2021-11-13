package views;

import controllers.EcoBikeBaseController;
import exceptions.ecobike.EcoBikeUndefinedException;

/**
 * This class spawns a handler for displaying customer's transaction details and get confirmations from customer
 * @author chauntm
 *
 */
public class TransactionScreenHandler extends EcoBikeBaseScreenHandler {
	
	protected TransactionScreenHandler(String screenTitle, EcoBikeBaseController controller, EcoBikeBaseScreenHandler prevScreen) {
		super(screenTitle, controller, prevScreen);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Display the transaction information and allow customer to confirm or reject transaction
	 * @throws EcoBikeUndefinedException If there is an unexpected error occurs during the renting process
	 */
	public void confirmTransaction() throws EcoBikeUndefinedException {
		
	}

}
