package views.screen;

import controllers.EcoBikeInformationController;
import controllers.RentBikeServiceController;
import controllers.ReturnBikeController;
import entities.Bike;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import utils.Configs;

import java.io.File;
import java.io.IOException;

/**
 * This class creates a handler for getting customer's behaviors on the bike information screen
 *
 * @author chauntm
 */
public class BikeInformationScreenHandler extends EcoBikeBaseScreenHandler {

    private static BikeInformationScreenHandler bikeInformationScreenHandler;
    private Bike currentBike;

    @FXML
    private Label bikeNameText;
    @FXML
    private Label bikeTypeText;
    @FXML
    private Label bikeStatusText;
    @FXML
    private Label bikeBatteryText;
    @FXML
    private Label bikeDistanceText;
    @FXML
    private Label bikeRentingText;
    @FXML
    private Label bikeDepositText;
    @FXML
    private Label bikeLocationText;
    @FXML
    private Button rentBikeButton;
    @FXML
    private Button returnBikeButton;
    @FXML
    private ImageView bikeImage;

    private BikeInformationScreenHandler(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);
    }

    public static BikeInformationScreenHandler getBikeInformationScreenHandler(Stage stage, EcoBikeBaseScreenHandler prevScreen, Bike bike) {
        if (bikeInformationScreenHandler == null) {
            try {
                bikeInformationScreenHandler = new BikeInformationScreenHandler(stage, Configs.BIKE_INFORMATION_SCREEN_PATH);
                bikeInformationScreenHandler.setbController(EcoBikeInformationController.getEcoBikeInformationController());
                bikeInformationScreenHandler.setScreenTitle("Bike information screen");
                bikeInformationScreenHandler.initializeBikeScreen();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (prevScreen != null) {
            bikeInformationScreenHandler.setPreviousScreen(prevScreen);
        }

        if (bike != null) {
            bikeInformationScreenHandler.currentBike = bike;
        }

        bikeInformationScreenHandler.renderBikeScreen();

        return bikeInformationScreenHandler;
    }

    /**
     * This is the method to do initialization and register button event.
     */
    private void initializeBikeScreen(){
        rentBikeButton.setOnMouseClicked(e -> rentBike());
        returnBikeButton.setOnMouseClicked(e -> returnBike());
    }

    /**
     * This is the method to do render the screen with the data.
     */
    private void renderBikeScreen() {
        super.setImage(bikeImage, currentBike.getBikeImage());
        bikeNameText.setText(currentBike.getName());
        bikeTypeText.setText(currentBike.getBikeType());
        bikeStatusText.setText(currentBike.getCurrentStatus() == Configs.BIKE_STATUS.FREE ? "Free" : "Rented");
        bikeBatteryText.setText(currentBike.getCurrentBattery() + "%");
        bikeDistanceText.setText("30 km");
        bikeRentingText.setText(currentBike.getBikeRentalPrice() + " " + currentBike.getCurrency());
        bikeDepositText.setText(currentBike.getDeposit() + " " + currentBike.getCurrency());
        bikeLocationText.setText("Dai Co Viet");

        rentBikeButton.setDisable(currentBike.getCurrentStatus() == Configs.BIKE_STATUS.FREE ? false : true);
        returnBikeButton.setDisable(currentBike.getCurrentStatus() == Configs.BIKE_STATUS.FREE ? true : false);
    }


    public void rentBike() {
        try {
            System.out.println("rent bike");
            //show deposit screen
            DepositScreenHandler.getDepositScreenHandler(this.stage, this, null, currentBike);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void returnBike() {
        try {
            System.out.println("return bike");
            //show deposit screen
            PaymentScreenHandler.getPaymentScreenHandler(this.stage, this, null, currentBike);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void pauseBikeRental() {
        try {
            RentBikeServiceController.getRentBikeServiceController().pauseBikeRental(currentBike.getBarCode());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
