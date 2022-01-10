package views.screen;

import controllers.EcoBikeInformationController;
import entities.NormalBike;
import entities.Bike;
import entities.Dock;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utils.Configs;
import views.screen.popup.BikeInDockHandler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class creates a handler for getting behaviors of customer on dock information screen
 *
 * @author chauntm
 */
public class DockInformationScreenHandler extends EcoBikeBaseScreenHandler {

    private static DockInformationScreenHandler dockInformationScreenHandler = null;
    private Dock currentDock = null;
    private ArrayList<Bike> currentBikeList = null;

    @FXML
    private ImageView dockImageView;
    @FXML
    private Label dockNameText;
    @FXML
    private Label dockAddressText;
    @FXML
    private Label dockAreaText;
    @FXML
    private Label dockCount;
    @FXML
    private Label availableBikeCount;
    @FXML
    private Label availableDocksCount;
    @FXML
    private Label distance;
    @FXML
    private Label estimateWalkTime;
    @FXML
    private Button returnBikeButton;
    @FXML
    private VBox bikeVBox;
    @FXML
    private ImageView mainScreenIcon;
    @FXML
    private ImageView backIcon;

    private DockInformationScreenHandler(Stage stage, String screenPath, EcoBikeBaseScreenHandler prevScreen) throws IOException {
        super(stage, screenPath);
        this.setPreviousScreen(prevScreen);
    }

    /**
     * This class return an instance of dock screen handler, initialize it with the stage, prevScreen, dock and bikeList
     *
     * @param stage         the stage to show this screen
     * @param prevScreen    the screen that call to this screen
     * @param dock          the dock to render this screen, provide null if update is not needed
     * @param bikeList      the bikeList to render this screen, provide null if update is not needed
     *
     */
    public static DockInformationScreenHandler getDockInformationScreenHandler(Stage stage, EcoBikeBaseScreenHandler prevScreen, Dock dock) {
    	// TODO: BikeList should be returned from dock, no need to pass it here
    	if (dockInformationScreenHandler == null) {
            try {
                dockInformationScreenHandler = new DockInformationScreenHandler(stage, Configs.VIEW_DOCK_SCREEN_PATH, prevScreen);
                dockInformationScreenHandler.setbController(EcoBikeInformationController.getEcoBikeInformationController());
                dockInformationScreenHandler.setScreenTitle("Dock information screen");
                dockInformationScreenHandler.initializeDockInformationScreen();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (prevScreen != null) {
            dockInformationScreenHandler.setPreviousScreen(prevScreen);
        }

        if (dock != null) {
            dockInformationScreenHandler.currentDock = dock;
        }

        ArrayList<Bike> bikeList = dock.getAllBikeInDock();
        if (bikeList != null && bikeList.size() != 0) {
            dockInformationScreenHandler.currentBikeList = bikeList;
        }

        dockInformationScreenHandler.renderDockInformation();

        return dockInformationScreenHandler;
    }

    /**
     * This is the method to do initialization and register button event.
     */
    private void initializeDockInformationScreen() {
        returnBikeButton.setOnMouseClicked(e -> returnBike());
        mainScreenIcon.setOnMouseClicked(e -> EcoBikeMainScreenHandler.getEcoBikeMainScreenHandler(this.stage, null).show());
        backIcon.setOnMouseClicked(e -> {
            if (this.getPreviousScreen() != null)
                this.getPreviousScreen().show();
        });
    }

    /**
     * This is the method to render the screen with data.
     */
    private void renderDockInformation() {
        // provide dock image
    	if (currentDock.getDockImage() != null && currentDock.getDockImage().length() != 0) {
    		dockImageView.setImage(new Image(new File(Configs.DOCK_IMAGE_LIB + "/" + currentDock.getDockImage()).toURI().toString()));
    	}
        dockNameText.setText(currentDock.getName());
        dockAddressText.setText(currentDock.getDockAddress());
        dockAreaText.setText(currentDock.getDockArea() + " km2");
        dockCount.setText(currentDock.getNumDockSpaceFree() + currentDock.getNumAvailableBike() + "");
        availableDocksCount.setText(currentDock.getNumDockSpaceFree() + "");
        availableBikeCount.setText(currentDock.getNumAvailableBike() + "");
        distance.setText("100 km");
        estimateWalkTime.setText("100 minutes");
        addBike(currentBikeList);
    }

    /**
     * This is the method called when the user press button return bike.
     */
    private void returnBike() {
    	// TODO: Call return bike here
    }

    /**
     * This is the method to render bike list in the dock screen.
     */
    private void addBike(ArrayList<Bike> bikeList) {
    	for (Bike b : bikeList) {
    		BikeInDockHandler bikeHandler;
			try {
				// error in this constructor
				bikeHandler = new BikeInDockHandler(this.stage, b, Configs.BIKE_IN_DOCK_PATH, this);
				bikeVBox.getChildren().add(bikeHandler.getContent());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }


}
