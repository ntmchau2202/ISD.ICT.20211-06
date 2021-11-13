package views;

import controllers.EcoBikeBaseController;
import entities.Transaction;
import exceptions.ecobike.EcoBikeUndefinedException;
import exceptions.ecobike.RentBikeException;

/**
 * This is the class handler for transaction screen
 * @author Duong
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
	 * Confirm the transaction
	 * @param transaction  The transaction entity ({@link entities.Transaction}
	 * @throws RentBikeException If the transaction is invalid
	 * @throws EcoBikeUndefinedException If there is an unexpected error occurs during the renting process
	 */
	public void confirmTransaction(Transaction transaction) throws  RentBikeException, EcoBikeUndefinedException {
		
	}

}
