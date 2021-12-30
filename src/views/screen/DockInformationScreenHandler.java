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
	protected DockInformationScreenHandler(Stage stage, String sreenPath, EcoBikeBaseScreenHandler prev, Dock dock) throws IOException {
		super(stage, sreenPath);
		this.currentDock = dock;
		this.setPreviousScreen(prev);
	}


//	public static DockInformationScreenHandler getDockInformationScreenHandler(Dock dockToDisplay, EcoBikeBaseScreenHandler prevScreen) {
//		if (dockInformationScreenHandler == null) {
//			dockInformationScreenHandler = new DockInformationScreenHandler("EcoBike Dock " + dockToDisplay.getName() + " information", prevScreen);
//		}
//		dockInformationScreenHandler.prevScreen = prevScreen;
//		return dockInformationScreenHandler;
//	}

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
		distance.setText("1.5km");
		numDockSpaceFree.setText(String.valueOf(currentDock.getNumDockSpaceFree()));
		numAvailableBike.setText(String.valueOf(currentDock.getNumAvailableBike()));
		estimatedWalkingTime.setText("25 mins");
	}
	
	/**
	 * Request the controller to return information about the bike selected and call the screen for displaying data
	 * @throws SQLException 
	 * @throws EcoBikeException 
	 * @throws IOException 
	 */
	public void viewBikeInformation(String bikeBarcode) throws EcoBikeException, SQLException, IOException {
		String bikeInf = EcoBikeInformationController.getEcoBikeInformationController().getBikeInformation(bikeBarcode);
		Bike bike = JSONUtils.toBike(bikeInf);
		BikeInformationScreenHandler bikeScreen = new BikeInformationScreenHandler(this.stage, Configs.VIEW_BIKE_SCREEN_PATH, this, bike);
		bikeScreen.setMainScreenHandler(mainScreenHandler);
		bikeScreen.setScreenTitle("Bike Information Screen");
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
