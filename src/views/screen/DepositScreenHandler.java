package views.screen;

import controllers.PaymentController;
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
public class DepositScreenHandler extends EcoBikeBaseScreenHandler {

    private static DepositScreenHandler depositScreenHandler;
    private Bike currentBike;
    private CreditCard currentCreditCard;

    @FXML
    private Label customerName;
    @FXML
    private Label bikeToRent;
    @FXML
    private Label bikeType;
    @FXML
    private Label deposit;
    @FXML
    private Button confirmDepositButton;
    @FXML
    private Button changeCardInformationButton;

    private DepositScreenHandler(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);
    }

    public static DepositScreenHandler getDepositScreenHandler(Stage stage, EcoBikeBaseScreenHandler prevScreen, CreditCard creditCard, Bike bike) {
        if (depositScreenHandler == null) {
            try {
                depositScreenHandler = new DepositScreenHandler(stage, Configs.DEPOSIT_SCREEN_PATH);
                depositScreenHandler.setbController(PaymentController.getPaymentController());
                depositScreenHandler.setScreenTitle("Deposit screen");
                depositScreenHandler.initializeDepositScreen();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (prevScreen != null) {
            depositScreenHandler.setPreviousScreen(prevScreen);
        }

        if (creditCard != null) {
            depositScreenHandler.currentCreditCard = creditCard;
        }

        if (bike != null) {
            depositScreenHandler.currentBike = bike;
        }

        depositScreenHandler.renderDepositScreen();

        return depositScreenHandler;
    }

    @Override
    public void show() {
        if(currentCreditCard != null) {
            //if already provided a credit card, just show the screen
            super.show();
        }
        else{
            //show payment method
            PaymentMethodScreenHandler.getPaymentMethodScreenHandler(this.stage, this, null).show();
        }
    }

    /**
     * This is the method to do initialization and register button event.
     */
    private void initializeDepositScreen() {
        confirmDepositButton.setOnMouseClicked(e -> confirmDeposit());
        changeCardInformationButton.setOnMouseClicked(e -> changeCardInformation());
    }

    /**
     * This is the method to do render the screen with data.
     */
    private void renderDepositScreen() {
        if (currentCreditCard != null) {
            customerName.setText(currentCreditCard.getCardHolderName());
        }
        bikeToRent.setText(currentBike.getName());
        bikeType.setText(currentBike.getBikeType());
        deposit.setText(currentBike.getDeposit() + " " + currentBike.getCurrency());
    }

    private void confirmDeposit() {

    }

    private void changeCardInformation() {

    }
}