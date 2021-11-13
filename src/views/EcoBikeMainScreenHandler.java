package views;

import controllers.EcoBikeBaseController;

/**
 * This class creates a handler for displaying the map and getting customer's activities on the main screen
 * @author chauntm
 *
 */
public class EcoBikeMainScreenHandler extends EcoBikeBaseScreenHandler {

	/**
	 * Initialize handler for main screen of EcoBike application
	 * @param screenTitle Title of the screen
	 * @param controller Controller for handling request from the screen
	 * @param prevScreen An instance to the screen that called this screen
	 */
	protected EcoBikeMainScreenHandler(String screenTitle, EcoBikeBaseController controller, EcoBikeBaseScreenHandler prevScreen) {
		super(screenTitle, controller, prevScreen);
	}

	@Override
	protected void initialize() {
		
	}
	
	/**
	 * View map of the current location of user in the main screen
	 */
	public void viewMap() {
		
	}
	
	/**
	 * Request the information controller to get information about the selected dock
	 */
	public void viewDockInformation() {
		
	}
	
	/**
	 * Open the scanner for user to scan bike barcode and get bike information, as well as services related to the bike
	 */
	public void scanBarcode() {
		
	}
}
