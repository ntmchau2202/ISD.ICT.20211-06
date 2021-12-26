package views;

import boundaries.RentBikeServiceBoundary;
import controllers.EcoBikeBaseController;
import entities.CreditCard;
import entities.PaymentTransaction;
import exceptions.ecobike.EcoBikeUndefinedException;
import exceptions.ecobike.RentBikeException;

/**
 * This is the class handler for transaction screen
 * @author Duong
 *
 */
public class TransactionScreenHandler extends EcoBikeBaseScreenHandler {
	private static TransactionScreenHandler transactionScreenHandler;
	private PaymentTransaction transaction;
	protected TransactionScreenHandler(String screenTitle, EcoBikeBaseScreenHandler prevScreen) {
		super(screenTitle, prevScreen);
		// TODO Auto-generated constructor stub
	}

	public static TransactionScreenHandler getTransactionScreenHandler(PaymentTransaction transaction, EcoBikeBaseScreenHandler prevScreen) {
		if (transactionScreenHandler == null) {
			transactionScreenHandler = new TransactionScreenHandler("Transaction details", prevScreen);
		}
		transactionScreenHandler.prevScreen = prevScreen;
		transactionScreenHandler.transaction = transaction;
		return transactionScreenHandler;
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
	public void confirmTransaction(String transactionType) throws  RentBikeException, EcoBikeUndefinedException {
		if (validationTransaction(transaction)) {
			if (transactionType.compareToIgnoreCase("RENT") == 0) {
//				RentBikeServiceBoundary.getRentBikeService().rentBike(null);
			}
			// show confirmation dialog here
			// then return to main screen
			EcoBikeMainScreenHandler mainScreenHandler = EcoBikeMainScreenHandler.getMainScreenHandler(this);
			// mainScreenHandler.show();
		}
	}
	
	public boolean validationTransaction(PaymentTransaction transaction) {
		return true;
	}
	
	/**
	 * Request to update payment method
	 * @param card  The credit card used to pay
	 * @throws RentBikeException If the card is invalid
	 * @throws EcoBikeUndefinedException If there is an unexpected error occurs during the renting process
	 */
	public void requestToUpdatePaymentMethod(CreditCard card) throws RentBikeException, EcoBikeUndefinedException {
		
	}

}
