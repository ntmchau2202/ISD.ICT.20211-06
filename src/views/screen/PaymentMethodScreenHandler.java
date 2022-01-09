package views.screen;

import controllers.PaymentController;
import entities.Bike;
import entities.CreditCard;
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

    private static PaymentMethodScreenHandler paymentMethodScreenHandler = null;
    private CreditCard currentCreditCard = null;
    private Configs.TransactionType currentTransactionType = null;
    private Bike currentBike = null;

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
    
    private static PaymentMethodScreenHandler paymentScreenHandler;

    private PaymentMethodScreenHandler(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);
    }

    /**
     * This class return an instance of payment method screen handler, initialize it with the stage, prevScreen, creditCard and transactionType
     *
     * @param stage             the stage to show this screen
     * @param prevScreen        the screen that call to this screen
     * @param creditCard        the credit card to render this screen, provide null if update is not needed
     * @param transactionType   the transaction type of the process
     *
     */
    public static PaymentMethodScreenHandler getPaymentMethodScreenHandler(Stage stage, EcoBikeBaseScreenHandler prevScreen, 
    		CreditCard card,Configs.TransactionType transactionType,Bike bike) {
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
        
        if(bike != null) {
        	paymentMethodScreenHandler.currentBike = bike;
        }
        if (prevScreen != null) {
            paymentMethodScreenHandler.setPreviousScreen(prevScreen);
        }

        if(transactionType != null){
            paymentMethodScreenHandler.currentTransactionType = transactionType;
        }
        
        paymentMethodScreenHandler.currentCreditCard = card;

        return paymentMethodScreenHandler;
    }
    
    public void setCreditCard(CreditCard card) {
    	currentCreditCard = card;
    }

    /**
     * This is the method to do initialization and register button event.
     */
    private void initializePaymentScreen() {
    	if(currentCreditCard == null) currentCreditCard = new CreditCard();
        confirmPaymentButton.setOnMouseClicked(e -> {
        	validateInput();
        	paymentMethodScreenHandler.renderPaymentMethod();
        });
    }

    /**
     * This is the method to render payment method if a card is provided.
     */
    private void renderPaymentMethod() {
//        if (currentCreditCard != null) {
//            cardHolderName.setText(currentCreditCard.getCardHolderName());
//            cardNumber.setText(currentCreditCard.getCardNumber());
//            securityCode.setText(currentCreditCard.getCardSecurity());
//            expirationDate.setText(currentCreditCard.getExpirationDate().toString());
//        }
    }
    
//    @Override
//    protected void initialize() {
//        confirmPaymentButton.setOnMouseClicked(e -> validateInput());
//
//    }

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
        if (!PaymentController.getPaymentController().validateExprirationDate(expirationDate.getText())) {
            popUpError("Invalid expiration date!");
            return;
        }
        if (!PaymentController.getPaymentController().validateCardSecurity(securityCode.getText())) {
            popUpError("Invalid security code!");
            return;
        }

        //if all information provided is valid, create new credit card and show
        // TODO: create a creditcard here
        CreditCard card = null;
//        CreditCard card = new CreditCard(cardNumber.getText(), cardHolderName.getText(), "acb", expirationDate.getText(), securityCode.getText());
        try {
            confirmPaymentMethod();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is called when user click confirm payment button
     * It shows the previous screen that require this screen to be shown (deposit or pay rental)
     */
    private void confirmPaymentMethod() {
    	currentCreditCard = new CreditCard(cardHolderName.getText(), cardNumber.getText(), "VCB", securityCode.getText(), 10000000, expirationDate.getText());
        switch (currentTransactionType){
            case PAY_DEPOSIT:
                DepositScreenHandler.getDepositScreenHandler(this.stage, null, currentCreditCard, currentBike).show();
                break;
            case PAY_RENTAL:
                PaymentScreenHandler.getPaymentScreenHandler(this.stage, null, currentCreditCard, currentBike).show();
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
