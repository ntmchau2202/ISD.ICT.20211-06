package views;

import boundaries.RentBikeServiceBoundary;
import controllers.EcoBikeBaseController;
import entities.Bike;
import exceptions.ecobike.EcoBikeUndefinedException;
import exceptions.ecobike.RentBikeException;

/**
 * This class creates a handler for getting customer's behaviors on the bike information screen
 * @author chauntm
 *
 */
public class BikeInformationScreenHandler extends EcoBikeBaseScreenHandler {
	private static BikeInformationScreenHandler bikeInfoHandler;
	private Bike currentBike;
	protected BikeInformationScreenHandler(String screenTitle, EcoBikeBaseScreenHandler prevScreen) {
		super(screenTitle, prevScreen);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Initialize the handler for bike information screen
	 * @param screenTitle Title of the screen
	 * @param controller Controller for handling request from the screen
	 * @param prevScreen An instance to the screen that called this screen
	 */
	public static BikeInformationScreenHandler getBikeInformationScreenHandler(Bike bikeToView, EcoBikeBaseScreenHandler prevScreen) {
		if (bikeInfoHandler == null) {
			bikeInfoHandler = new BikeInformationScreenHandler("EcoBike bike "+ bikeToView.getName() + "information", prevScreen);
		}
		bikeInfoHandler.prevScreen = prevScreen;
		if (bikeToView != null) {
			bikeInfoHandler.currentBike = bikeToView;
		}
		return bikeInfoHandler;
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Request the controller to start rent bike process for the currently selected bike
	 * @throws EcoBikeUndefinedException 
	 * @throws RentBikeException 
	 */
	public void rentBike() throws RentBikeException, EcoBikeUndefinedException {
		RentBikeServiceBoundary.getRentBikeService(this).rentBike(currentBike);
	}
	
	/**
	 * Request the controller to start return bike process for the currently selected bike
	 * @throws EcoBikeUndefinedException 
	 * @throws RentBikeException 
	 */
	public void returnBike() throws RentBikeException, EcoBikeUndefinedException {
		RentBikeServiceBoundary.getRentBikeService(this).returnBike(currentBike);
	}
	
	/**
	 * Request the controller to pause renting the currently selected bike
	 * @throws EcoBikeUndefinedException 
	 * @throws RentBikeException 
	 */
	public void pauseBikeRental() throws RentBikeException, EcoBikeUndefinedException {
		RentBikeServiceBoundary.getRentBikeService(this).pauseBikeRental(currentBike);
	}
}
