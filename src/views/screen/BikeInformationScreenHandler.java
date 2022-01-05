package views.screen;

import java.awt.Label;
import java.io.IOException;

import boundaries.RentBikeServiceBoundary;
import controllers.EcoBikeBaseController;
import entities.Bike;
import entities.Dock;
import exceptions.ecobike.EcoBikeUndefinedException;
import exceptions.ecobike.RentBikeException;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * This class creates a handler for getting customer's behaviors on the bike information screen
 * @author chauntm
 *
 */
public class BikeInformationScreenHandler extends EcoBikeBaseScreenHandler {
	
	@FXML
	Label bikeName;
	
	@FXML
	Label bikeType;
	
	@FXML
	Label bikeStatus;
	
	@FXML
	Label bikeBattery;
	
	@FXML
	Label distanceEstimated;
	
	@FXML
	Label rentingPrice;
	
	@FXML
	Label deposit;
	
	@FXML
	Label location;
	
	private static BikeInformationScreenHandler bikeInfoHandler;
	private Bike currentBike;
	private BikeInformationScreenHandler(Stage stage, String sreenPath, EcoBikeBaseScreenHandler prev, Bike bike) throws IOException {
		super(stage, sreenPath, prev);
		if(bike != null) {
			this.currentBike = bike;			
		}
	}

	/**
	 * Initialize the handler for bike information screen
	 * @param screenTitle Title of the screen
	 * @param controller Controller for handling request from the screen
	 * @param prevScreen An instance to the screen that called this screen
	 */
	public static BikeInformationScreenHandler getBikeInformationScreenHandler(Stage stage, String screenPath, EcoBikeBaseScreenHandler prev, Bike bikeToDisplay) throws IOException {
		if (bikeInfoHandler == null) {
			bikeInfoHandler = new BikeInformationScreenHandler(stage, screenPath, prev, bikeToDisplay);
			bikeInfoHandler.setScreenTitle("Bike "+bikeToDisplay.getName()+" information");
		}
		return bikeInfoHandler;
	}
	
	public void processBikeInfo() {
		bikeName.setText(currentBike.getName());
		bikeType.setText(currentBike.getCurrency());
		bikeStatus.setText(currentBike.getCurrentStatus().toString());
		bikeBattery.setText(currentBike.getCurrentBattery() + "%");
		distanceEstimated.setText(String.valueOf(currentBike.getDistanceEstimated()));
		rentingPrice.setText(String.valueOf(currentBike.getBikeRentalPrice()));
		deposit.setText(String.valueOf(currentBike.getDeposit()));
		location.setText(currentBike.getName());
	}


	/**
	 * Request the controller to start rent bike process for the currently selected bike
	 * @throws EcoBikeUndefinedException 
	 * @throws RentBikeException 
	 */
	@FXML
	public void rentBike(MouseEvent event) throws RentBikeException, EcoBikeUndefinedException {
		RentBikeServiceBoundary.getRentBikeService(this).rentBike(currentBike);
	}
	
//	/**
//	 * Request the controller to start return bike process for the currently selected bike
//	 * @throws EcoBikeUndefinedException 
//	 * @throws RentBikeException 
//	 */
	@FXML
	public void returnBike(MouseEvent event) throws RentBikeException, EcoBikeUndefinedException {
		RentBikeServiceBoundary.getRentBikeService(this).returnBike(currentBike);
	}
	
	/**
	 * Request the controller to pause renting the currently selected bike
	 * @throws EcoBikeUndefinedException 
	 * @throws RentBikeException 
	 */
	@FXML
	public void pauseRental(MouseEvent event) throws RentBikeException, EcoBikeUndefinedException {
		RentBikeServiceBoundary.getRentBikeService(this).pauseBikeRental(currentBike);
	}
}
