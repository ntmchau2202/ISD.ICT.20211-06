package views.screen;

import controllers.PaymentController;
import entities.CreditCard;
import exceptions.ecobike.EcoBikeUndefinedException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utils.Configs;

import java.io.IOException;

/**
 * This is the class handler for payment method screen
 *
 * @author Duong
 */
public class PaymentMethodScreenHandler extends EcoBikeBaseScreenHandler {

    private static PaymentMethodScreenHandler paymentMethodScreenHandler;
    private CreditCard currentCreditCard = null;

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


    private PaymentMethodScreenHandler(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);
    }

    public static PaymentMethodScreenHandler getPaymentMethodScreenHandler(Stage stage, EcoBikeBaseScreenHandler prevScreen, CreditCard creditCard) {
        if (paymentMethodScreenHandler == null) {
            try {
                paymentMethodScreenHandler = new PaymentMethodScreenHandler(stage, Configs.PAYMENT_METHOD_SCREEN_PATH);
                paymentMethodScreenHandler.setbController(PaymentController.getPaymentController());
                paymentMethodScreenHandler.setScreenTitle("Payment method screen");
                paymentMethodScreenHandler.initializePaymentScreen();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (prevScreen != null) {
            paymentMethodScreenHandler.setPreviousScreen(prevScreen);
        }

        if (creditCard != null) {
            paymentMethodScreenHandler.currentCreditCard = creditCard;
        }

        paymentMethodScreenHandler.renderPaymentMethod();

        return paymentMethodScreenHandler;
    }

    /**
     * This is the method to do initialization and register button event.
     */
    private void initializePaymentScreen() {
        confirmPaymentButton.setOnMouseClicked(e -> validateInput());
    }

    /**
     * This is the method to render payment method if a card is provided.
     */
    private void renderPaymentMethod() {
        if (currentCreditCard != null) {
            cardHolderName.setText(currentCreditCard.getCardHolderName());
            cardNumber.setText(currentCreditCard.getCardNumber());
            securityCode.setText(currentCreditCard.getCardSecurity());
            expirationDate.setText(currentCreditCard.getExpirationDate().toString());
        }
    }

    /**
     * This is the method to call when user click confirm payment method button.
     */
    private void validateInput() {
        if (PaymentController.getPaymentController().validateCardHolderName(cardHolderName.getText()) == false) {
            popUpError("Invalid card holder name!");
            return;
        }
        if (PaymentController.getPaymentController().validateCardNumber(cardNumber.getText()) == false) {
            popUpError("Invalid card number!");
            return;
        }
        if (PaymentController.getPaymentController().validateExdpirationDate(expirationDate.getText()) == false) {
            popUpError("Invalid expiration date!");
            return;
        }
        if (PaymentController.getPaymentController().validateCardSecurity(securityCode.getText()) == false) {
            popUpError("Invalid security code!");
            return;
        }

        //if all information provided is valid, create new credit card and show
        CreditCard card = new CreditCard(cardNumber.getText(), cardHolderName.getText(), "acb", expirationDate.getText(), securityCode.getText());
        try {
            confirmPaymentMethod(card);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Get payment method information from the form and go to transaction screen
     *
     * @throws EcoBikeUndefinedException If there is an unexpected error occurs during the renting process
     */
    private void confirmPaymentMethod(CreditCard card) throws EcoBikeUndefinedException {

    }

    private void popUpError(String errorMessage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Invalid input");

        alert.setHeaderText(null);
        alert.setContentText(errorMessage);

        alert.showAndWait();
    }

}
