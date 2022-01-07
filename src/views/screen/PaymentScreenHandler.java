package views.screen;

import controllers.PaymentController;
import controllers.ReturnBikeController;
import entities.Bike;
import entities.CreditCard;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import utils.Configs;

import java.awt.*;
import java.io.IOException;

/**
 * This class creates a handler for deposit screen
 *
 * @author longnt
 */
public class PaymentScreenHandler extends EcoBikeBaseScreenHandler {

    private static PaymentScreenHandler paymentScreenHandler = null;
    private Bike currentBike = null;
    private CreditCard currentCreditCard = null;

    @FXML
    private Label customerName;
    @FXML
    private Label bikeRented;
    @FXML
    private Label bikeType;
    @FXML
    private Label timeRented;
    @FXML
    private Label total;
    @FXML
    private Button confirmPaymentButton;
    @FXML
    private Button changeCardInformationButton;
    @FXML
    private ImageView mainScreenIcon;
    @FXML
    private ImageView backIcon;

    private PaymentScreenHandler(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);
    }

    /**
     * This class return an instance of payment screen handler, initialize it with the stage, prevScreen, creditCard and transactionType
     *
     * @param stage             the stage to show this screen
     * @param prevScreen        the screen that call to this screen
     * @param creditCard        the credit card to render this screen, provide null if update is not needed
     * @param bike              the bike to render this screen, provide null if update is not needed
     *
     */
    public static PaymentScreenHandler getPaymentScreenHandler(Stage stage, EcoBikeBaseScreenHandler prevScreen, CreditCard creditCard, Bike bike) {
        if (paymentScreenHandler == null) {
            try {
                paymentScreenHandler = new PaymentScreenHandler(stage, Configs.PAYMENT_METHOD_SCREEN_PATH);
                paymentScreenHandler.setbController(ReturnBikeController.getReturnBikeController());
                paymentScreenHandler.setScreenTitle("Payment method screen");
                paymentScreenHandler.initializePaymentScreen();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (prevScreen != null) {
            paymentScreenHandler.setPreviousScreen(prevScreen);
        }

        if (creditCard != null) {
            paymentScreenHandler.currentCreditCard = creditCard;
        }

        if (bike != null) {
            paymentScreenHandler.currentBike = bike;
        }

        paymentScreenHandler.renderPaymentScreen();

        return paymentScreenHandler;
    }

    @Override
    public void show() {
        if(currentCreditCard != null) {
            //if already provided a credit card, just show the screen
            super.show();
        }
        else{
            //show payment method
            PaymentMethodScreenHandler.getPaymentMethodScreenHandler(this.stage, this, null, Configs.TransactionType.PAY_RENTAL).show();
        }
    }

    /**
     * This is the method to do initialization and register button event.
     */
    private void initializePaymentScreen() {
        confirmPaymentButton.setOnMouseClicked(e -> setConfirmPaymentButton());
        changeCardInformationButton.setOnMouseClicked(e -> changeCardInformation());
        mainScreenIcon.setOnMouseClicked(e -> EcoBikeMainScreenHandler.getEcoBikeMainScreenHandler(this.stage, null).show());
        backIcon.setOnMouseClicked(e -> {
            if (this.getPreviousScreen() != null)
                this.getPreviousScreen().show();
        });
    }

    /**
     * This is the method to render payment method if a card is provided.
     */
    private void renderPaymentScreen() {
        customerName.setText(currentCreditCard.getCardHolderName());
        bikeRented.setText(currentBike.getName());
        bikeType.setText(currentBike.getBikeType());
        timeRented.setText(currentBike.getTotalRentTime() + " hour");
        total.setText(ReturnBikeController.getReturnBikeController().calculateFee(currentBike.getBikeType(), currentBike.getTotalRentTime()) + " " + currentBike.getCurrency());
    }

    /**
     * This is the method to be called when user press confirm payment button.
     */
    private void setConfirmPaymentButton() {
        //todo: call controller to proceed payment transaction with current credit card and bike
    }

    /**
     * This is the method to be called when user press change card information button.
     */
    private void changeCardInformation() {
        PaymentMethodScreenHandler.getPaymentMethodScreenHandler(this.stage, this, currentCreditCard, Configs.TransactionType.PAY_RENTAL).show();
    }

}