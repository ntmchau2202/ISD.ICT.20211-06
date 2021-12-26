package views;

import java.sql.SQLException;

import controllers.EcoBikeBaseController;
import controllers.EcoBikeInformationController;
import entities.Bike;
import entities.Dock;
import exceptions.ecobike.EcoBikeException;
import utils.JSONUtils;

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
	
	private static EcoBikeMainScreenHandler mainScreenHandler;
	
	protected EcoBikeMainScreenHandler(String screenTitle, EcoBikeBaseScreenHandler prevScreen) {
		super(screenTitle, prevScreen);
	}
	
	public static EcoBikeMainScreenHandler getMainScreenHandler(EcoBikeBaseScreenHandler prevScreen) {
		if (mainScreenHandler == null) {
			mainScreenHandler = new EcoBikeMainScreenHandler("EcoBike Main", null);
		}
		mainScreenHandler.prevScreen = prevScreen;
		return mainScreenHandler;
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
	 * @throws EcoBikeException 
	 * @throws SQLException 
	 */
	public void viewDockInformation(String dockID) throws SQLException, EcoBikeException {
		String dockInf = EcoBikeInformationController.getEcoBikeInformationController().getDockInformation(dockID);
		Dock dock = JSONUtils.toDock(dockInf);
		// use the dock entity to display on the screen here
		DockInformationScreenHandler dockScreen = DockInformationScreenHandler.getDockInformationScreenHandler(dock, this);
		// dockScreen.show();
	}
	
	/**
	 * Open the scanner for user to scan bike barcode and get bike information, as well as services related to the bike
	 */
	public void scanBarcode() {
		
	}
	
	public void viewBikeInformation(String bikeBarcode) throws EcoBikeException, SQLException {
		String bikeInf = EcoBikeInformationController.getEcoBikeInformationController().getBikeInformation(bikeBarcode);
		Bike bike = JSONUtils.toBike(bikeInf);
		BikeInformationScreenHandler bikeScreen = BikeInformationScreenHandler.getBikeInformationScreenHandler(bike, this);
		// bikeScreen.show();		
	}
}
