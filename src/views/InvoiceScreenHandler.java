package views;

import controllers.EcoBikeBaseController;
import entities.Invoice;
import exceptions.ecobike.EcoBikeUndefinedException;
import exceptions.ecobike.RentBikeException;
import javafx.stage.Stage;

/**
 * This is the class handler for invoice screen
 * @author Duong
 *
 */
public class InvoiceScreenHandler extends EcoBikeBaseScreenHandler {
	protected InvoiceScreenHandler(Stage stage, String screenTitle, EcoBikeBaseController controller, EcoBikeBaseScreenHandler prevScreen, String screenPath) {
		super(stage, screenTitle, controller, prevScreen, screenPath);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Display the invoice and allow customer to accept if the invoice is correct, request update if there is any mismatch
	 * @param invoice  The invoice entity ({@link entities.Invoice}
	 * @throws RentBikeException If the invoice is invalid
	 * @throws EcoBikeUndefinedException If there is an unexpected error occurs during the renting process
	 */
	public void confirmInvoice(Invoice invoice) throws  RentBikeException, EcoBikeUndefinedException {
		
	}
}
