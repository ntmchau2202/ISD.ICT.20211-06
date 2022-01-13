package views.screen;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import controllers.EcoBikeBaseController;
import controllers.PaymentController;
import controllers.RentBikeController;
import entities.NormalBike;
import entities.Bike;
import entities.CreditCard;
import entities.Dock;
import exceptions.ecobike.EcoBikeException;
import exceptions.ecobike.EcoBikeUndefinedException;
import exceptions.ecobike.RentBikeException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utils.Configs;
import views.screen.popup.PopupScreen;

/**
 * This is the class handler for payment method screen
 *
 * @author Duong
 */
public class PayForRentScreenHandler extends EcoBikeBaseScreenHandler {
	@FXML
    private Label bikeName;
	@FXML
    private Label bikeType;
	@FXML
    private Label rentalTime;
	@FXML
    private Label rentalPrice;	
    @FXML
    private TextField cardHolderName;
    @FXML
    private TextField cardNumber;
    @FXML
    private TextField expirationDate;
    @FXML
    private TextField securityCode;
    @FXML
    private Button confirmPaymentButton;
    
    private static PayForRentScreenHandler paymentScreenHandler;

    /**
     * Initialize handler for paying method screen of EcoBike application
     *
     * @param screenTitle Title of the screen
     * @param controller  Controller for handling request from the screen
     * @param prevScreen  An instance to the screen that called this screen
     * @throws IOException 
     */
    
    private Bike bikeToRent;
    private Dock dockToReturn;
    
    private PayForRentScreenHandler(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);
//        initialize();
    }
    
    public static PayForRentScreenHandler getPayForRentScreenHandler(Stage stage, String screenPath, EcoBikeBaseScreenHandler prevScreen, Bike bike, Dock dock) throws IOException {
    	if (paymentScreenHandler == null) {
    		paymentScreenHandler = new PayForRentScreenHandler(stage, screenPath);
    		paymentScreenHandler.setScreenTitle("Register payment method");
    	}
    	if (bike != null) {
    		paymentScreenHandler.bikeToRent = bike;
    		paymentScreenHandler.dockToReturn = dock;
    		paymentScreenHandler.renderBikeRentInformation();
    		
    	}
    	return paymentScreenHandler;
    	
    }
    
    private void renderBikeRentInformation() {
    	bikeName.setText(this.bikeToRent.getName());
    	bikeType.setText(this.bikeToRent.getBikeType());
    	rentalTime.setText(Integer.toString(RentBikeController.getRentBikeServiceController(null).stopCountingRentTime())+" mins");
    	rentalPrice.setText(Float.toString(RentBikeController.getRentBikeServiceController(null).getRentalFee()) + this.bikeToRent.getCurrency());
    }
    
    public void initialize() {

    	confirmPaymentButton.setOnMouseClicked(e->{
			try {
				confirmPaymentMethod();
			} catch (EcoBikeException | SQLException | IOException | ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
    }
    
	public boolean validateInput() throws IOException {
		if(PaymentController.getPaymentController().validateCardHolderName(cardHolderName.getText()) == false){
            PopupScreen.error("Invalid card holder name!");
            return false;
        }
        if(PaymentController.getPaymentController().validateCardNumber(cardNumber.getText()) == false){
        	PopupScreen.error("Invalid card number!");
            return false;
        }
        if(PaymentController.getPaymentController().validateExpirationDate(expirationDate.getText()) == false){
        	PopupScreen.error("Invalid expiration date!");
            return false;
        }
        if(PaymentController.getPaymentController().validateCardSecurity(securityCode.getText()) == false){
        	PopupScreen.error("Invalid security code!");
            return false;
        }
        return true;
	}

    /**
     * Get payment method information from the form and go to transaction screen
     * @throws IOException 
     * @throws SQLException 
     * @throws EcoBikeException 
     * @throws ParseException 
     */
    public void confirmPaymentMethod() throws EcoBikeException, SQLException, IOException, ParseException {
    	if(validateInput()) {
    		System.out.println("Confirm successfully");
    		CreditCard card = new CreditCard(cardHolderName.getText(), cardNumber.getText(), "ACB", securityCode.getText(), expirationDate.getText());
    		if (RentBikeController.getRentBikeServiceController(null).returnBike(bikeToRent, dockToReturn, card)) {
    			PopupScreen.success("Return bike successfully");    			
    			InvoiceScreenHandler invoiceScreen = InvoiceScreenHandler.getInvoiceScreenHandler(this.stage, this, RentBikeController.getRentBikeServiceController(null).getInvoice()); 
    			invoiceScreen.show();
    		} else {
    			PopupScreen.error("Cannot perform transaction for paying rental");
    		}
    	} else {
    		System.out.println("Confirm failed");
    	}
    }

}
