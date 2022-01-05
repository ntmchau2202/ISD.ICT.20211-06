package views.screen;

import java.io.IOException;
import java.sql.SQLException;

import controllers.EcoBikeBaseController;
import controllers.EcoBikeInformationController;
import entities.Bike;
import entities.Dock;
import exceptions.ecobike.EcoBikeException;
import javafx.stage.Stage;
import utils.Configs;
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
	
	protected EcoBikeMainScreenHandler(Stage stage, String screenPath) throws IOException {
		super(stage, screenPath, null);
	}
	
	public static EcoBikeMainScreenHandler getMainScreenHandler(Stage stage, String screenPath) throws IOException {
		if (mainScreenHandler == null) {
			mainScreenHandler = new EcoBikeMainScreenHandler(stage, Configs.MAIN_SCREEN_PATH);
			mainScreenHandler.setScreenTitle("EcoBike Main");
		}
		return mainScreenHandler;
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
	 * @throws IOException 
	 */
	public void viewDockInformation(String dockID) throws SQLException, EcoBikeException, IOException {
		Dock dock = EcoBikeInformationController.getEcoBikeInformationController().getDockInformation(dockID);
		// use the dock entity to display on the screen here
		DockInformationScreenHandler dockScreen = DockInformationScreenHandler.getDockInformationScreenHandler(this.stage, Configs.VIEW_DOCK_SCREEN_PATH, this, dock);
		 dockScreen.show();
	}
	
	/**
	 * Open the scanner for user to scan bike barcode and get bike information, as well as services related to the bike
	 */
	public void scanBarcode() {
		
	}
	
	public void viewBikeInformation(String bikeBarcode) throws EcoBikeException, SQLException, IOException {
		Bike bike = EcoBikeInformationController.getEcoBikeInformationController().getBikeInformation(bikeBarcode);
		BikeInformationScreenHandler bikeScreen = BikeInformationScreenHandler.getBikeInformationScreenHandler(this.stage, Configs.VIEW_BIKE_SCREEN_PATH, this, bike);
		bikeScreen.show();		
	}
	
}
