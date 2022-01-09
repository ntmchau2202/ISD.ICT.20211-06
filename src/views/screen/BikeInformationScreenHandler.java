package views.screen;

import controllers.EcoBikeInformationController;
import controllers.RentBikeController;
import controllers.ReturnBikeController;
import entities.Bike;
import entities.Cart;
import entities.Dock;
import exceptions.ecobike.EcoBikeException;
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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;

/**
 * This class creates a handler for getting customer's behaviors on the bike information screen
 *
 * @author chauntm
 */
public class BikeInformationScreenHandler extends EcoBikeBaseScreenHandler {

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

    public BikeInformationScreenHandler(Stage stage, String screenPath, EcoBikeBaseScreenHandler prevScreen, Bike bike) throws IOException {
        super(stage, screenPath);
        setbController(EcoBikeInformationController.getEcoBikeInformationController());
        setScreenTitle("Bike information screen");
        currentBike = bike;
        initializeBikeScreen();
        if (prevScreen != null) {
            setPreviousScreen(prevScreen);
        }

        if (bike != null) {
            currentBike = bike;
        }
        
        renderBikeScreen();
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
        mainScreenIcon.setOnMouseClicked(e -> {
        	try {
				EcoBikeMainScreenHandler handler = new EcoBikeMainScreenHandler(this.stage, Configs.MAIN_SCREEN_PATH);
				handler.show();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        });
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
            PaymentMethodScreenHandler handler = new PaymentMethodScreenHandler(this.stage, Configs.PAYING_METHOD_SCREEN_PATH, this, null,Configs.TransactionType.PAY_DEPOSIT,currentBike);
            handler.show();
//            RentBikeController.getRentBikeServiceController().rentBike(currentBike);
//            DepositScreenHandler.getDepositScreenHandler(this.stage, this, null, currentBike);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void returnBike() {
        try {
            System.out.println("return bike");
            
            // TODO: change job to RentBikeServiceBoundary to invoke the RentBike subsystem
            Dock dock = DBUtils.getDockInformation(currentBike.getDockId());
            if(!ReturnBikeController.getReturnBikeController().checkDockFreeSpace(dock)) {
            	PopupScreen.error("Dock is full!");
            	return;
            }
            updateBikeRent();
            updateRentPeriod();
            currentBike.setTotalRentTime(getTotalRentTime());
            PaymentMethodScreenHandler handler = new PaymentMethodScreenHandler(this.stage, Configs.PAYING_METHOD_SCREEN_PATH, this, null,Configs.TransactionType.PAY_RENTAL,currentBike);
            handler.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void updateBikeRent() throws SQLException, EcoBikeException {
    	String sql = "Update RentBike set end_time = ?, rent_period = end_time - start_time where bike_barcode = ?";
    	PreparedStatement stm = DBUtils.getConnection().prepareStatement(sql);
    	stm.setTime(1, Time.valueOf(LocalTime.now()));
    	stm.setString(2, currentBike.getBikeBarCode());
    	stm.executeUpdate();
    }
    
    private void updateRentPeriod() throws SQLException, EcoBikeException {
    	String sql = "Update RentBike set rent_period = end_time - start_time where bike_barcode = ?";
    	PreparedStatement stm = DBUtils.getConnection().prepareStatement(sql);
    	stm.setString(1, currentBike.getBikeBarCode());
    	stm.executeUpdate();
    }
    
    private int getTotalRentTime() throws SQLException, EcoBikeException {
    	String sql = "Select * from RentBike where bike_barcode = ?";
    	PreparedStatement stm = DBUtils.getConnection().prepareStatement(sql);
    	stm.setString(1, currentBike.getBikeBarCode());
    	ResultSet result = stm.executeQuery();
    	while(result.next()) {
    		long total = (int)result.getTime("rent_period").getTime() / 1000l;
    		return (int) total;
    	}
    	return 0;
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
