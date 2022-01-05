package views.screen;

import java.awt.Label;
import java.io.IOException;
import java.sql.SQLException;
import controllers.EcoBikeInformationController;
import controllers.ReturnBikeController;
import entities.Bike;
import entities.Dock;
import exceptions.ecobike.EcoBikeException;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utils.Configs;
import utils.JSONUtils;

/**
 * This class creates a handler for getting behaviors of customer on dock information screen 
 * @author chauntm
 *
 */
public class DockInformationScreenHandler extends EcoBikeBaseScreenHandler {
	
	@FXML
	Label dockName;
	
	@FXML
	Label address;

	@FXML
	Label dockArea;

	@FXML
	Label distance;
	
	@FXML
	Label numDockSpaceFree;
	
	@FXML
	Label numAvailableBike;
	
	@FXML
	Label estimatedWalkingTime;
	
	private static DockInformationScreenHandler dockInformationScreenHandler;
	private Dock currentDock;
	private DockInformationScreenHandler(Stage stage, String sreenPath, EcoBikeBaseScreenHandler prev, Dock dock) throws IOException {
		super(stage, sreenPath, prev);
		if(dock != null) {
			this.currentDock = dock;			
		}
		this.setPreviousScreen(prev);
	}


	public static DockInformationScreenHandler getDockInformationScreenHandler(Stage stage, String screenPath, EcoBikeBaseScreenHandler prev, Dock dockToDisplay) throws IOException {
		if (dockInformationScreenHandler == null) {
			dockInformationScreenHandler = new DockInformationScreenHandler(stage, screenPath, prev, dockToDisplay);
			dockInformationScreenHandler.setScreenTitle("Dock "+dockToDisplay.getName()+" information");
		}
		return dockInformationScreenHandler;
	}

	/**
	 * Initialize handler for dock information screen
	 * @param screenTitle Title of the screen
	 * @param controller Controller for handling request from the screen
	 * @param prevScreen An instance to the screen that called this screen
	 */
	
//	@Override
//	protected void initialize() {
//		// TODO Auto-generated method stub
//		
//	}
	
	public void processDockInfo() {
		dockName.setText(currentDock.getName());
		address.setText(currentDock.getDockAddress());
		dockArea.setText(String.valueOf(currentDock.getDockArea()));
		distance.setText("1.5km"); // TODO: Can we do a real distance estimation here?
		numDockSpaceFree.setText(String.valueOf(currentDock.getNumDockSpaceFree()));
		numAvailableBike.setText(String.valueOf(currentDock.getNumAvailableBike()));
		estimatedWalkingTime.setText("25 mins"); // TODO: Can we do a real estimation here?
	}
	
	/**
	 * Request the controller to return information about the bike selected and call the screen for displaying data
	 * @throws SQLException 
	 * @throws EcoBikeException 
	 * @throws IOException 
	 */
	public void viewBikeInformation(String bikeBarcode) throws EcoBikeException, SQLException, IOException {
		Bike bike = EcoBikeInformationController.getEcoBikeInformationController().getBikeInformation(bikeBarcode);
		BikeInformationScreenHandler bikeScreen = BikeInformationScreenHandler.getBikeInformationScreenHandler(this.stage, Configs.VIEW_BIKE_SCREEN_PATH, this, bike);
		bikeScreen.show();
	}
	
	@FXML
	public void returnBikeHere(MouseEvent event) throws EcoBikeException, SQLException {
		ReturnBikeController controller = getbController();
	}
	
	public ReturnBikeController getbController() {
	    return (ReturnBikeController) super.getbController();
	  }
}
