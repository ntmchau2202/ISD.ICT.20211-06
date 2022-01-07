package views.screen;

import controllers.PaymentController;
import entities.Invoice;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import utils.Configs;

import java.io.IOException;

/**
 * This is the class handler for invoice screen
 *
 * @author Duong
 */
public class InvoiceScreenHandler extends EcoBikeBaseScreenHandler {
    private static InvoiceScreenHandler invoiceScreenHandler = null;
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

    private InvoiceScreenHandler(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);
    }

    public static InvoiceScreenHandler getInvoiceScreenHandler(Stage stage, EcoBikeBaseScreenHandler prevScreen, Invoice invoice) {
        if (invoiceScreenHandler == null) {
            try {
                invoiceScreenHandler = new InvoiceScreenHandler(stage, Configs.INVOICE_SCREEN_PATH);
                invoiceScreenHandler.setbController(PaymentController.getPaymentController());
                invoiceScreenHandler.setScreenTitle("Invoice screen");
                invoiceScreenHandler.initializeInvoiceScreen();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (invoice != null) {
            invoiceScreenHandler.currentInvoice = invoice;
        }

        if (prevScreen != null) {
            invoiceScreenHandler.setPreviousScreen(prevScreen);
        }

        invoiceScreenHandler.renderInvoiceScreen();

        return invoiceScreenHandler;
    }

    /**
     * This is the method to do initialization and register button event.
     */
    private void initializeInvoiceScreen() {
        backToMainScreenButton.setOnMouseClicked(e -> EcoBikeMainScreenHandler.getEcoBikeMainScreenHandler(this.stage, null).show());
    }

    /**
     * This is the method to do render the screen with the data.
     */
    private void renderInvoiceScreen() {
        invoiceID.setText(currentInvoice.getInvoiceID());
        invoiceDate.setText(currentInvoice.getTimeCreate().toString());
        bikeName.setText(currentInvoice.getBikeName());
        startRentTime.setText(currentInvoice.getStartTime().toString());
        endRentTime.setText(currentInvoice.getEndTime().toString());
        total.setText(currentInvoice.getTotal() + " VND");
    }
}
