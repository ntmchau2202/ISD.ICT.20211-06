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
    private Configs.TransactionType currentTransactionType = null;

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

    public static PaymentMethodScreenHandler getPaymentMethodScreenHandler(Stage stage, EcoBikeBaseScreenHandler prevScreen, CreditCard creditCard, Configs.TransactionType transactionType) {
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

        if(transactionType != null){
            paymentMethodScreenHandler.currentTransactionType = transactionType;
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
        if (!PaymentController.getPaymentController().validateCardHolderName(cardHolderName.getText())) {
            popUpError("Invalid card holder name!");
            return;
        }
        if (!PaymentController.getPaymentController().validateCardNumber(cardNumber.getText())) {
            popUpError("Invalid card number!");
            return;
        }
        if (!PaymentController.getPaymentController().validateExdpirationDate(expirationDate.getText())) {
            popUpError("Invalid expiration date!");
            return;
        }
        if (!PaymentController.getPaymentController().validateCardSecurity(securityCode.getText())) {
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
     * This method is called when user click confirm payment button
     * It shows the previous screen that require this screen to be shown (deposit or pay rental)
     */
    private void confirmPaymentMethod(CreditCard creditCard) {
        switch (currentTransactionType){
            case PAY_DEPOSIT:
                DepositScreenHandler.getDepositScreenHandler(this.stage, null, creditCard, null).show();
                break;
            case PAY_RENTAL:
                PaymentScreenHandler.getPaymentScreenHandler(this.stage, null, creditCard, null).show();
                break;
            default:
                System.out.println("Unknown payment source");
                break;
        }
    }

    private void popUpError(String errorMessage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Invalid input");

        alert.setHeaderText(null);
        alert.setContentText(errorMessage);

        alert.showAndWait();
    }

}
