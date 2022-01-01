package views;

import boundaries.RentBikeServiceBoundary;
import controllers.EcoBikeBaseController;
import controllers.RentBikeServiceController;
import entities.Bike;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import utils.Configs;

import java.io.File;
import java.io.IOException;

/**
 * This class creates a handler for getting customer's behaviors on the bike information screen
 * @author chauntm
 *
 */
public class BikeInformationScreenHandler extends EcoBikeBaseScreenHandler {

	private entities.Bike currentBike;
	/**
	 * Initialize the handler for bike information screen
	 * @param screenTitle Title of the screen
	 * @param controller Controller for handling request from the screen
	 * @param prevScreen An instance to the screen that called this screen
	 */


	public BikeInformationScreenHandler(Stage stage, String screenTitle, EcoBikeBaseController controller, EcoBikeBaseScreenHandler prevScreen, String screenPath) {
		super(stage, screenTitle, controller, prevScreen, screenPath);
		initialize();
	}

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


	@Override
	protected void initialize() {
		// init bike to render
		try{
		currentBike =  new Bike("name", "bike_type", "bike_image", "bar_code", 1,
		1, "currency", "13/02/2000");

			bikeImage.setImage(new Image((new File(currentBike.getBikeImage())).toURI().toString()));
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
		catch (Exception bikeInformationScreenHandlerInitializeException){
			bikeInformationScreenHandlerInitializeException.printStackTrace();
		}



	}

	/**
	 * Request the controller to start rent bike process for the currently selected bike
	 * @throws EcoBikeUndefinedException 
	 * @throws RentBikeException 
	 */
	public void rentBike() {
		try {
			System.out.println("rent bike");
			RentBikeServiceController.getRentBikeServiceController().rentBike(currentBike.getBarCode());
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Request the controller to start return bike process for the currently selected bike
	 * @throws EcoBikeUndefinedException 
	 * @throws RentBikeException 
	 */
	public void returnBike() {

		
	}
	
	/**
	 * Request the controller to pause renting the currently selected bike
	 * @throws EcoBikeUndefinedException 
	 * @throws RentBikeException 
	 */
	public void pauseBikeRental() {
		try {
			RentBikeServiceController.getRentBikeServiceController().pauseBikeRental(currentBike.getBarCode());
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}
