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

    /**
     * Initialize the handler for bike information screen
     *
     * @param screenTitle Title of the screen
     * @param controller Controller for handling request from the screen
     * @param prevScreen An instance to the screen that called this screen
     */

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

    private BikeInformationScreenHandler(Stage stage, String screenPath, EcoBikeBaseScreenHandler prevScreen) throws IOException {
        super(stage, screenPath, prevScreen);
    }

    public static BikeInformationScreenHandler getBikeInformationScreenHandler(Stage stage, EcoBikeBaseScreenHandler prevScreen, Bike bike) {
        if (bikeInformationScreenHandler == null) {
            try {
                bikeInformationScreenHandler = new BikeInformationScreenHandler(stage, Configs.BIKE_INFORMATION_SCREEN_PATH, prevScreen);
                bikeInformationScreenHandler.setbController(EcoBikeInformationController.getEcoBikeInformationController());
                bikeInformationScreenHandler.setScreenTitle("Bike information screen");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (bike != null) {
            bikeInformationScreenHandler.currentBike = bike;
            bikeInformationScreenHandler.initializeBike();
        }

        bikeInformationScreenHandler.initializeBike();

        return bikeInformationScreenHandler;
    }

    private void initializeBike() {

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

        rentBikeButton.setOnMouseClicked(e -> rentBike());

        returnBikeButton.setOnMouseClicked(e -> returnBike());
    }


    public void rentBike() {
        try {
            System.out.println("rent bike");
            RentBikeServiceController.getRentBikeServiceController().rentBike(currentBike.getBarCode());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void returnBike() {
        try {
            ReturnBikeController.getReturnBikeController().returnBike(currentBike.getBarCode());
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
