package views.screen;

import java.awt.Label;
import java.io.IOException;
import java.sql.SQLException;
import controllers.EcoBikeInformationController;
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
public class DepositScreenHandler extends EcoBikeBaseScreenHandler {

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


    private static DepositScreenHandler depositScreenHandler;

    private Bike bike;
    private CreditCard card;

    protected DepositScreenHandler(Stage stage, String sreenPath, EcoBikeBaseScreenHandler prev) throws IOException {
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
        bikeToRent.setText(bike.getName());
        bikeType.setText(bike.getBikeType());
        deposit.setText(bike.getDeposit() + " " + bike.getCurrency());

        confirmDepositButton.setOnMouseClicked(e -> confirmDeposit());
        changeCardInformationButton.setOnMouseClicked(e -> changeCardInformation());
    }

    private void confirmDeposit(){

    }

    private void changeCardInformation(){

    }

    public void updateCardInfo(CreditCard card) {
        this.card = card;
        customerName.setText(card.getCardHolderName());
    }
}