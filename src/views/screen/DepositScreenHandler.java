package views.screen;

import controllers.PaymentController;
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
public class DepositScreenHandler extends EcoBikeBaseScreenHandler {

    private static DepositScreenHandler depositScreenHandler = null;
    private Bike currentBike = null;
    private CreditCard currentCreditCard = null;

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
    @FXML
    private ImageView mainScreenIcon;
    @FXML
    private ImageView backIcon;

    private DepositScreenHandler(Stage stage, String screenPath, EcoBikeBaseScreenHandler prevScreen) throws IOException {
        super(stage, screenPath);
    }

    /**
     * This class return an instance of deposit screen handler, initialize it with the stage, prevScreen, creditCard and bike
     *
     * @param stage      the stage to show this screen
     * @param prevScreen the screen that call to this screen
     * @param creditCard the credit card to render this screen, provide null if update is not needed
     * @param bike       the bike to render this screen, provide null if update is not needed
     */
    public static DepositScreenHandler getDepositScreenHandler(Stage stage, EcoBikeBaseScreenHandler prevScreen, CreditCard creditCard,Bike bike) {
        if (depositScreenHandler == null) {
            try {
                depositScreenHandler = new DepositScreenHandler(stage, Configs.DEPOSIT_SCREEN_PATH, prevScreen);
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
        if (currentCreditCard != null) {
            //if already provided a credit card, just show the screen
            super.show();
        } else {
            //show payment method
            PaymentMethodScreenHandler.getPaymentMethodScreenHandler(this.stage, this, null, Configs.TransactionType.PAY_DEPOSIT).show();
        }
    }

    /**
     * This is the method to do initialization and register button event.
     */
    private void initializeDepositScreen() {
        confirmDepositButton.setOnMouseClicked(e -> confirmDeposit());
        changeCardInformationButton.setOnMouseClicked(e -> changeCardInformation());
        mainScreenIcon.setOnMouseClicked(e -> EcoBikeMainScreenHandler.getEcoBikeMainScreenHandler(this.stage, null).show());
        backIcon.setOnMouseClicked(e -> {
            if (this.getPreviousScreen() != null)
                this.getPreviousScreen().show();
        });
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

    /**
     * This is the method to be called when user press confirm deposit.
     */
    private void confirmDeposit() {
        //todo: call payment controller to proceed deposit transaction with current bike and credit card
    }

    /**
     * This is the method to be called when user press change card information.
     */
    private void changeCardInformation() {
        PaymentMethodScreenHandler.getPaymentMethodScreenHandler(this.stage, this, currentCreditCard, Configs.TransactionType.PAY_DEPOSIT);
    }
}