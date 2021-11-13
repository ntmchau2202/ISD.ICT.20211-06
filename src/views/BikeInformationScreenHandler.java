package views;

import controllers.EcoBikeBaseController;

/**
 * This class creates a handler for getting customer's behaviors on the bike information screen
 * @author chauntm
 *
 */
public class BikeInformationScreenHandler extends EcoBikeBaseScreenHandler {
	/**
	 * Initialize the handler for bike information screen
	 * @param screenTitle Title of the screen
	 * @param controller Controller for handling request from the screen
	 * @param prevScreen An instance to the screen that called this screen
	 */
	protected BikeInformationScreenHandler(String screenTitle, EcoBikeBaseController controller, EcoBikeBaseScreenHandler prevScreen) {
		super(screenTitle, controller, prevScreen);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Request the controller to start rent bike process for the currently selected bike
	 */
	public void rentBike() {
		
	}
	
	/**
	 * Request the controller to start return bike process for the currently selected bike
	 */
	public void returnBike() {
		
	}
	
	/**
	 * Request the controller to pause renting the currently selected bike
	 */
	public void pauseBikeRental() {
		
	}
}
