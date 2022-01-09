package views.screen;

import controllers.PaymentController;
import controllers.ReturnBikeController;
import entities.Bike;
import entities.CreditCard;
import exceptions.ecobike.EcoBikeException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Popup;
import javafx.stage.Stage;
import utils.Configs;
import views.screen.popup.PopupScreen;

import java.io.IOException;
import java.sql.SQLException;

/**
 * This is the class handler for payment method screen
 *
 * @author Duong
 */
public class PaymentMethodScreenHandler extends EcoBikeBaseScreenHandler {

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
    
    public PaymentMethodScreenHandler(Stage stage, String screenPath, EcoBikeBaseScreenHandler prev, 
    		CreditCard card,Configs.TransactionType transactionType,Bike bike) throws IOException {
        super(stage, screenPath);
        setbController(PaymentController.getPaymentController());
        setScreenTitle("Payment method screen");
        initializePaymentScreen();
        
        if(bike != null) {
        	currentBike = bike;
        }
        if (prev != null) {
            setPreviousScreen(prev);
        }

        if(transactionType != null){
            currentTransactionType = transactionType;
        }
        
        setCreditCard(card);
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
        	renderPaymentMethod();
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
     * @throws IOException 
     * @throws SQLException 
     * @throws EcoBikeException 
     */
    private void confirmPaymentMethod() throws IOException, EcoBikeException, SQLException {
    	currentCreditCard = new CreditCard(cardHolderName.getText(), cardNumber.getText(), "VCB", securityCode.getText(), 10000000, expirationDate.getText());
        switch (currentTransactionType){
            case PAY_DEPOSIT:
            	DepositScreenHandler handler = new DepositScreenHandler(stage, Configs.DEPOSIT_SCREEN_PATH, this, currentCreditCard, currentBike);
            	handler.show();
                break;
            case PAY_RENTAL:
            	PaymentScreenHandler handler1 = new PaymentScreenHandler(stage, Configs.PAYMENT_SCREEN_PATH, this, currentCreditCard, currentBike);
            	handler1.show();
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
