package views.screen;

import java.io.IOException;

import controllers.EcoBikeBaseController;
import controllers.EcoBikeInformationController;
import controllers.PaymentController;
import entities.Bike;
import entities.Invoice;
import exceptions.ecobike.EcoBikeUndefinedException;
import exceptions.ecobike.RentBikeException;
import javafx.stage.Stage;
import utils.Configs;

/**
 * This is the class handler for invoice screen
 * @author Duong
 *
 */
public class InvoiceScreenHandler extends EcoBikeBaseScreenHandler {
	private static InvoiceScreenHandler invoiceScreenHandler = null;

	private InvoiceScreenHandler(Stage stage, String screenPath) throws IOException {
		super(stage, screenPath);
	}

	public static InvoiceScreenHandler getInvoiceScreenHandler(Stage stage, EcoBikeBaseScreenHandler prevScreen) {
		if (invoiceScreenHandler == null) {
			try {
				invoiceScreenHandler = new InvoiceScreenHandler(stage, Configs.BIKE_INFORMATION_SCREEN_PATH);
				invoiceScreenHandler.setbController(PaymentController.getPaymentController());
				invoiceScreenHandler.setScreenTitle("Bike information screen");
				invoiceScreenHandler.initializeBikeScreen();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (prevScreen != null) {
			invoiceScreenHandler.setPreviousScreen(prevScreen);
		}

		invoiceScreenHandler.renderBikeScreen();

		return invoiceScreenHandler;
	}

	/**
	 * This is the method to do initialization and register button event.
	 */
	private void initializeBikeScreen(){
	}

	/**
	 * This is the method to do render the screen with the data.
	 */
	private void renderBikeScreen() {
	}
}
