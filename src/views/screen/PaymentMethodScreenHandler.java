package views.screen;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import controllers.EcoBikeBaseController;
import controllers.PaymentController;
import entities.CreditCard;
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
public class PaymentMethodScreenHandler extends EcoBikeBaseScreenHandler {
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

    /**
     * Initialize handler for paying method screen of EcoBike application
     *
     * @param screenTitle Title of the screen
     * @param controller  Controller for handling request from the screen
     * @param prevScreen  An instance to the screen that called this screen
     */
    public PaymentMethodScreenHandler(Stage stage, String screenTitle, EcoBikeBaseController controller, EcoBikeBaseScreenHandler prevScreen, String screenPath) {
        super(stage, screenTitle, controller, prevScreen, screenPath);
        initialize();
    }

    @Override
    protected void initialize() {
        confirmPaymentButton.setOnMouseClicked(e -> validateInput());

    }

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

        CreditCard card = new CreditCard(cardNumber.getText(), cardHolderName.getText(), "acb", expirationDate.getText(), securityCode.getText());
        try{
            confirmPaymentMethod(card);
        } catch (Exception e){
            e.printStackTrace();
        }
	}

    /**
     * Get payment method information from the form and go to transaction screen
     *
     * @throws EcoBikeUndefinedException If there is an unexpected error occurs during the renting process
     */
    public void confirmPaymentMethod(CreditCard card) throws EcoBikeUndefinedException {

    }

    private void popUpError(String errorMessage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Invalid input");

        alert.setHeaderText(null);
        alert.setContentText(errorMessage);

        alert.showAndWait();
    }

}
