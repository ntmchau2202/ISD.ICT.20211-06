package views.screen;

import controllers.PaymentController;
import entities.Invoice;
import exceptions.ecobike.EcoBikeException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import utils.Configs;

import java.io.IOException;
import java.sql.SQLException;

/**
 * This is the class handler for invoice screen
 *
 * @author Duong
 */
public class InvoiceScreenHandler extends EcoBikeBaseScreenHandler {
	
    private Invoice currentInvoice = null;

    @FXML
    private Label invoiceID;
    @FXML
    private Label invoiceDate;
    @FXML
    private Label bikeName;
    @FXML
    private Label startRentTime;
    @FXML
    private Label endRentTime;
    @FXML
    private Label total;
    @FXML
    private Label backToMainScreenButton;

    public InvoiceScreenHandler(Stage stage, String screenPath, EcoBikeBaseScreenHandler prevScreen, Invoice invoice) throws IOException, EcoBikeException, SQLException {
        super(stage, screenPath);
        setbController(PaymentController.getPaymentController());
        setScreenTitle("Invoice screen");
        initializeInvoiceScreen();
        
        if (invoice != null) {
            currentInvoice = invoice;
        }

        if (prevScreen != null) {
            setPreviousScreen(prevScreen);
        }

        renderInvoiceScreen();
    }


    /**
     * This is the method to do initialization and register button event.
     */
    private void initializeInvoiceScreen() {
        backToMainScreenButton.setOnMouseClicked(e -> {
        	try {
				EcoBikeMainScreenHandler handler = new EcoBikeMainScreenHandler(this.stage, Configs.MAIN_SCREEN_PATH);
				handler.show();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        });
    }

    /**
     * This is the method to do render the screen with the data.
     * @throws SQLException 
     * @throws EcoBikeException 
     */
    private void renderInvoiceScreen() throws EcoBikeException, SQLException {
        invoiceID.setText(currentInvoice.getInvoiceID());
        invoiceDate.setText(currentInvoice.getTimeCreate().toString());
        bikeName.setText(currentInvoice.getBikeName());
        startRentTime.setText(currentInvoice.getStartTime().toString());
        endRentTime.setText(currentInvoice.getEndTime().toString());
        total.setText(currentInvoice.getTotal() + " VND");
    }
}
