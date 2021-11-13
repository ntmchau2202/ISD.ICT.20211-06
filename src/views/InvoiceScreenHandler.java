package views;

import controllers.EcoBikeBaseController;
import entities.Invoice;
import exceptions.ecobike.EcoBikeUndefinedException;
import exceptions.ecobike.RentBikeException;

/**
 * This is the class handler for invoice screen
 * @author Duong
 *
 */
public class InvoiceScreenHandler extends EcoBikeBaseScreenHandler {
	protected InvoiceScreenHandler(String screenTitle, EcoBikeBaseController controller, EcoBikeBaseScreenHandler prevScreen) {
		super(screenTitle, controller, prevScreen);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Confirm the invoice
	 * @param invoice  The invoice entity ({@link entities.Invoice}
	 * @throws RentBikeException If the invoice is invalid
	 * @throws EcoBikeUndefinedException If there is an unexpected error occurs during the renting process
	 */
	public void confirmInvoice(Invoice invoice) throws  RentBikeException, EcoBikeUndefinedException {
		
	}
}
