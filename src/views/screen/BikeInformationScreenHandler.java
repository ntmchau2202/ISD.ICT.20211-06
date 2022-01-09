package views.screen;

import controllers.EcoBikeInformationController;
import controllers.RentBikeController;
import entities.Bike;
import exceptions.ecobike.EcoBikeException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import utils.Configs;
import utils.DBUtils;
import views.screen.popup.PopupScreen;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.sql.SQLException;

import boundaries.RentBikeServiceBoundary;

/**
 * This class creates a handler for getting customer's behaviors on the bike information screen
 *
 * @author chauntm
 */
public class BikeInformationScreenHandler extends EcoBikeBaseScreenHandler implements PropertyChangeListener {

    private static BikeInformationScreenHandler bikeInformationScreenHandler = null;
    private Bike currentBike = null;

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
    private Button pauseBikeButton;
    @FXML
    private ImageView bikeImage;
    @FXML
    private ImageView mainScreenIcon;
    @FXML
    private ImageView backIcon;

    private BikeInformationScreenHandler(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);
    }

    /**
     * This class return an instance of bike information screen handler, initialize it with the stage, prevScreen and bike
     *
     * @param stage         the stage to show this screen
     * @param prevScreen    the screen that call to this screen
     * @param bike          the bike to render this screen, provide null if update is not needed
     *
     */
    public static BikeInformationScreenHandler getBikeInformationScreenHandler(Stage stage, EcoBikeBaseScreenHandler prevScreen, Bike bike) {
        if (bikeInformationScreenHandler == null) {
            try {
                bikeInformationScreenHandler = new BikeInformationScreenHandler(stage, Configs.VIEW_BIKE_SCREEN_PATH);
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
        	// refresh information
            try {
				bikeInformationScreenHandler.currentBike = DBUtils.getBikeByBarcode(bike.getBikeBarCode());
				bikeInformationScreenHandler.currentBike.addObserver(bikeInformationScreenHandler);
			} catch (EcoBikeException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }

        bikeInformationScreenHandler.renderBikeScreen();

        return bikeInformationScreenHandler;
    }

    public void propertyChange(PropertyChangeEvent evt) {
    	Configs.BIKE_STATUS newStatus = (Configs.BIKE_STATUS)evt.getNewValue();
    	
    	if (newStatus == Configs.BIKE_STATUS.FREE) {
    		rentBikeButton.setDisable(false);
    		pauseBikeButton.setDisable(true);
    		returnBikeButton.setDisable(true);
    	} else if (newStatus == Configs.BIKE_STATUS.PAUSED) {
    		rentBikeButton.setDisable(true);
    		pauseBikeButton.setText("Continue rental");
    		pauseBikeButton.setDisable(false);
    		returnBikeButton.setDisable(false);
    	} else if (newStatus == Configs.BIKE_STATUS.RENTED) {
    		rentBikeButton.setDisable(true);
    		pauseBikeButton.setText("Pause rental");
    		pauseBikeButton.setDisable(false);
    		returnBikeButton.setDisable(false);
    	}
    	
        returnBikeButton.setDisable((Configs.BIKE_STATUS)evt.getNewValue() == Configs.BIKE_STATUS.FREE ? true : false);
    }
    
    /**
     * This is the method to do initialization and register button event.
     */
    private void initializeBikeScreen(){
        rentBikeButton.setOnMouseClicked(e -> rentBike());
        pauseBikeButton.setOnMouseClicked(e->pauseBikeRental());
        returnBikeButton.setOnMouseClicked(e -> returnBike());
        mainScreenIcon.setOnMouseClicked(e -> EcoBikeMainScreenHandler.getEcoBikeMainScreenHandler(this.stage, null).show());
        backIcon.setOnMouseClicked(e -> {
            if (this.getPreviousScreen() != null)
                this.getPreviousScreen().show();
        });
    }

    /**
     * This is the method to do render the screen with the data.
     */
    private void renderBikeScreen() {
    	if (currentBike.getBikeImage() != null && currentBike.getBikeImage().length() != 0) {
    		super.setImage(bikeImage, currentBike.getBikeImage());    		
    	}
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
        pauseBikeButton.setDisable(false);
    }


    public void rentBike() {
        try {
            System.out.println("rent bike");
            RentBikeServiceBoundary.getRentBikeService(this.getPreviousScreen()).rentBike(this.currentBike);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void returnBike() {
        try {
            System.out.println("return bike");
            RentBikeServiceBoundary.getRentBikeService(this.getPreviousScreen()).returnBike(this.currentBike);
        } catch (Exception e) {
            e.printStackTrace();
            try {
				PopupScreen.error("An error occured when returning the bike");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }
    }

    public void pauseBikeRental() {
        try {
        	System.out.println("pause bike rental");
        	if (this.currentBike.getCurrentStatus() == Configs.BIKE_STATUS.RENTED) {
                RentBikeServiceBoundary.getRentBikeService(this.getPreviousScreen()).pauseBikeRental(this.currentBike);
    			PopupScreen.success("Pause bike rental successfully!");
        	} else if (this.currentBike.getCurrentStatus() == Configs.BIKE_STATUS.PAUSED) {
                RentBikeServiceBoundary.getRentBikeService(this.getPreviousScreen()).resumeBikeRental(this.currentBike);
    			PopupScreen.success("Resume bike rental successfully!");
        	}
        } catch (Exception e) {
        	try {
				PopupScreen.error("An error occured when pausing renting the bike");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
            e.printStackTrace();
        }
    }

}
