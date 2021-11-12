package views;

import controllers.EcoBikeBaseController;
import exceptions.ecobike.EcoBikeUndefinedException;

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
	 * Confirm the transaction
	 * @throws EcoBikeUndefinedException If there is an unexpected error occurs during the renting process
	 */
	public void confirmTransaction() throws EcoBikeUndefinedException {
		
	}

}
