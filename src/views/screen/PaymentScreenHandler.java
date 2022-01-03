package views.screen;

import java.awt.Label;
import java.io.IOException;
import java.sql.SQLException;
import controllers.EcoBikeInformationController;
import controllers.RentBikeServiceController;
import controllers.ReturnBikeController;
import entities.Bike;
import entities.CreditCard;
import entities.Dock;
import exceptions.ecobike.EcoBikeException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utils.Configs;
import utils.JSONUtils;

/**
 * This class creates a handler for deposit screen
 * @author longnt
 *
 */
public class PaymentScreenHandler extends EcoBikeBaseScreenHandler {

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


    private static DepositScreenHandler depositScreenHandler;

    private Bike bike;
    private CreditCard card;
    private ReturnBikeController returnBikeController;

    protected PaymentScreenHandler(Stage stage, String sreenPath, EcoBikeBaseScreenHandler prev) throws IOException {
        super(stage, sreenPath);
        this.setPreviousScreen(prev);
    }


//	public static DockInformationScreenHandler getDockInformationScreenHandler(Dock dockToDisplay, EcoBikeBaseScreenHandler prevScreen) {
//		if (dockInformationScreenHandler == null) {
//			dockInformationScreenHandler = new DockInformationScreenHandler("EcoBike Dock " + dockToDisplay.getName() + " information", prevScreen);
//		}
//		dockInformationScreenHandler.prevScreen = prevScreen;
//		return dockInformationScreenHandler;
//	}

    public void initialize() {
        customerName.setText(card.getCardHolderName());
        bikeRented.setText(bike.getName());
        bikeType.setText(bike.getBikeType());
        timeRented.setText(bike.getTotalRentTime() + " hour");
        cost.setText(returnBikeController.calculateFee(bike.getBikeType(), bike.getTotalRentTime()) + " " + bike.getCurrency());
        total.setText(returnBikeController.calculateFee(bike.getBikeType(), bike.getTotalRentTime()) * 1.1 + " " + bike.getCurrency());

        confirmPaymentButton.setOnMouseClicked(e -> setConfirmPaymentButton());
        changeCardInformationButton.setOnMouseClicked(e -> changeCardInformation());
    }

    private void setConfirmPaymentButton(){

    }

    private void changeCardInformation(){

    }

    public void updateCardInfo(CreditCard card) {
        this.card = card;
        customerName.setText(card.getCardHolderName());
    }
}