package views.screen;

import controllers.EcoBikeInformationController;
import controllers.RentBikeController;
import controllers.ReturnBikeController;
import entities.Bike;
import entities.Cart;
import entities.Dock;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import subsystem.boundaries.InterbankSubsystemController;
import utils.Configs;
import utils.DBUtils;
import views.screen.popup.PopupScreen;

import java.io.IOException;

/**
 * This class creates a handler for getting customer's behaviors on the bike information screen
 *
 * @author chauntm
 */
public class BikeInformationScreenHandler extends EcoBikeBaseScreenHandler {

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
    private Button pauseRentalButton;
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
                bikeInformationScreenHandler.currentBike = bike;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        bikeInformationScreenHandler.initializeBikeScreen();
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
    	System.out.println("Hello!");
    	if(currentBike.getCurrentStatus() == Configs.BIKE_STATUS.FREE) {
			System.out.println("FREEE");
			rentBikeButton.setDisable(false);
    		returnBikeButton.setDisable(true);
    		pauseRentalButton.setDisable(true);
		} else {
			System.out.println("RENTED");
			rentBikeButton.setDisable(true);
    		returnBikeButton.setDisable(false);
    		pauseRentalButton.setDisable(false);
		}
    	pauseRentalButton.setOnMouseClicked(e -> pauseBikeRental());
        rentBikeButton.setOnMouseClicked(e -> rentBike());
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
    }


    private void rentBike() {
        try {
            System.out.println("rent bike");
            if(!RentBikeController.getRentBikeServiceController().checkAvailableBike(currentBike)) {
            	return;
            }
            // TODO: change job to RentBikeServiceBoundary to invoke the RentBike subsystem
            PaymentMethodScreenHandler.getPaymentMethodScreenHandler(this.stage, this, null,Configs.TransactionType.PAY_DEPOSIT,currentBike).show();
//            RentBikeController.getRentBikeServiceController().rentBike(currentBike);
//            DepositScreenHandler.getDepositScreenHandler(this.stage, this, null, currentBike);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void returnBike() {
        try {
            System.out.println("return bike");
            System.out.println(currentBike.toString());
            // TODO: change job to RentBikeServiceBoundary to invoke the RentBike subsystem
            Dock dock = DBUtils.getDockInformation(currentBike.getDockId());
            if(!ReturnBikeController.getReturnBikeController().checkDockFreeSpace(dock)) {
            	PopupScreen.error("Dock is full!");
            	return;
            }
            
            PaymentMethodScreenHandler.getPaymentMethodScreenHandler(this.stage, this, null, Configs.TransactionType.PAY_RENTAL, currentBike).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void pauseBikeRental() {
        try {
        	System.out.println("pause bike rental");
            // TODO: change job to RentBikeServiceBoundary to invoke the RentBike subsystem
//            InterbankSubsystemController.getRentBikeService(this.getPreviousScreen()).pauseBikeRental(this.stage, this.currentBike);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   
}
