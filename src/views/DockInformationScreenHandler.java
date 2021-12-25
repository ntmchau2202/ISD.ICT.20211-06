package views;

import java.sql.SQLException;

import controllers.EcoBikeBaseController;
import controllers.EcoBikeInformationController;
import entities.Bike;
import entities.Dock;
import exceptions.ecobike.EcoBikeException;
import utils.JSONUtils;

/**
 * This class creates a handler for getting behaviors of customer on dock information screen 
 * @author chauntm
 *
 */
public class DockInformationScreenHandler extends EcoBikeBaseScreenHandler {
	private static DockInformationScreenHandler dockInformationScreenHandler;

	protected DockInformationScreenHandler(String screenTitle, EcoBikeBaseScreenHandler prevScreen) {
		super(screenTitle, prevScreen);
		// TODO Auto-generated constructor stub
	}


	public static DockInformationScreenHandler getDockInformationScreenHandler(Dock dockToDisplay, EcoBikeBaseScreenHandler prevScreen) {
		if (dockInformationScreenHandler == null) {
			dockInformationScreenHandler = new DockInformationScreenHandler("EcoBike Dock " + dockToDisplay.getName() + " information", prevScreen);
		}
		dockInformationScreenHandler.prevScreen = prevScreen;
		return dockInformationScreenHandler;
	}

	/**
	 * Initialize handler for dock information screen
	 * @param screenTitle Title of the screen
	 * @param controller Controller for handling request from the screen
	 * @param prevScreen An instance to the screen that called this screen
	 */
	
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Request the controller to return information about the bike selected and call the screen for displaying data
	 * @throws SQLException 
	 * @throws EcoBikeException 
	 */
	public void viewBikeInformation(String bikeBarcode) throws EcoBikeException, SQLException {
		String bikeInf = EcoBikeInformationController.getEcoBikeInformationController().getBikeInformation(bikeBarcode);
		Bike bike = JSONUtils.toBike(bikeInf);
		BikeInformationScreenHandler bikeScreen = BikeInformationScreenHandler.getBikeInformationScreenHandler(bike, this);
	}
	
	
	
	
}
