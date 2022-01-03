package views.screen;

import controllers.PaymentController;
import controllers.ReturnBikeController;
import entities.Bike;
import entities.CreditCard;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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

    private static PaymentScreenHandler paymentScreenHandler;
    private Bike currentBike;
    private CreditCard currentCreditCard;

    @FXML
    private Label customerName;
    @FXML
    private Label bikeRented;
    @FXML
    private Label bikeType;
    @FXML
    private Label timeRented;
    @FXML
    private Label cost;
    @FXML
    private Label total;
    @FXML
    private Button confirmPaymentButton;
    @FXML
    private Button changeCardInformationButton;


    private PaymentScreenHandler(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);
    }

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

    /**
     * This is the method to do initialization and register button event.
     */
    private void initializePaymentScreen() {
        confirmPaymentButton.setOnMouseClicked(e -> setConfirmPaymentButton());
        changeCardInformationButton.setOnMouseClicked(e -> changeCardInformation());
    }

    /**
     * This is the method to render payment method if a card is provided.
     */
    private void renderPaymentScreen() {
        customerName.setText(currentCreditCard.getCardHolderName());
        bikeRented.setText(currentBike.getName());
        bikeType.setText(currentBike.getBikeType());
        timeRented.setText(currentBike.getTotalRentTime() + " hour");
        cost.setText(ReturnBikeController.getReturnBikeController().calculateFee(currentBike.getBikeType(), currentBike.getTotalRentTime()) + " " + currentBike.getCurrency());
        total.setText(ReturnBikeController.getReturnBikeController().calculateFee(currentBike.getBikeType(), currentBike.getTotalRentTime()) * 1.1 + " " + currentBike.getCurrency());

    }

    private void setConfirmPaymentButton() {

    }

    private void changeCardInformation() {

    }

    public void updateCardInfo(CreditCard card) {
        this.currentCreditCard = card;
        customerName.setText(card.getCardHolderName());
    }
}