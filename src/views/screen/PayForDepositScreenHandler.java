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
import entities.Bike;
import entities.CreditCard;
import exceptions.ecobike.EcoBikeException;
import exceptions.ecobike.EcoBikeUndefinedException;
import exceptions.ecobike.RentBikeException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utils.Configs;

/**
 * This is the class handler for payment method screen
 *
 * @author Duong
 */
public class PayForDepositScreenHandler extends EcoBikeBaseScreenHandler {
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
    
    private static PayForDepositScreenHandler paymentScreenHandler;

    /**
     * Initialize handler for paying method screen of EcoBike application
     *
     * @param screenTitle Title of the screen
     * @param controller  Controller for handling request from the screen
     * @param prevScreen  An instance to the screen that called this screen
     * @throws IOException 
     */
    
    private Bike bikeToRent;
    
    private PayForDepositScreenHandler(Stage stage, String screenPath, EcoBikeBaseScreenHandler prevScreen) throws IOException {
        super(stage, screenPath, prevScreen);
//        initialize();
    }
    
    public static PayForDepositScreenHandler getPayForDepositScreenHandler(Stage stage, String screenPath, EcoBikeBaseScreenHandler prevScreen, Bike bike) throws IOException {
    	if (paymentScreenHandler == null) {
    		paymentScreenHandler = new PayForDepositScreenHandler(stage, screenPath, prevScreen);
    		paymentScreenHandler.setScreenTitle("Register payment method");
    	}
    	if (bike != null) {
    		paymentScreenHandler.bikeToRent = bike;
    	}
    	return paymentScreenHandler;
    	
    }
    
//    @Override
//    protected void initialize() {
//        confirmPaymentButton.setOnMouseClicked(e -> validateInput());
//
//    }

	public void validateInput() {
		if(PaymentController.getPaymentController().validateCardHolderName(cardHolderName.getText()) == false){
            popUpError("Invalid card holder name!");
            return;
        }
        if(PaymentController.getPaymentController().validateCardNumber(cardNumber.getText()) == false){
            popUpError("Invalid card number!");
            return;
        }
        if(PaymentController.getPaymentController().validateExdpirationDate(expirationDate.getText()) == false){
            popUpError("Invalid expiration date!");
            return;
        }
        if(PaymentController.getPaymentController().validateCardSecurity(securityCode.getText()) == false){
            popUpError("Invalid security code!");
            return;
        }

        CreditCard card = new CreditCard(cardNumber.getText(), cardHolderName.getText(), "", expirationDate.getText(), securityCode.getText());
        try{
            confirmPaymentMethod(card);
        } catch (Exception e){
            e.printStackTrace();
        }
	}

    /**
     * Get payment method information from the form and go to transaction screen
     * @throws IOException 
     * @throws SQLException 
     * @throws EcoBikeException 
     */
    public void confirmPaymentMethod(CreditCard card) throws EcoBikeException, SQLException, IOException {
    	// this is paying for deposit
    	RentBikeController.getRentBikeServiceController().rentBike(bikeToRent, card);
    	
    }

    private void popUpError(String errorMessage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Invalid input");

        alert.setHeaderText(null);
        alert.setContentText(errorMessage);

        alert.showAndWait();
    }

}
