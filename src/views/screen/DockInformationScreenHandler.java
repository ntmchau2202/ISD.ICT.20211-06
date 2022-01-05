package views.screen;

import controllers.EcoBikeInformationController;
import entities.Bike;
import entities.Dock;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utils.Configs;
import views.BikeInDockHandler;

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

    private DockInformationScreenHandler(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);
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
    public static DockInformationScreenHandler getDockInformationScreenHandler(Stage stage, EcoBikeBaseScreenHandler prevScreen, Dock dock, ArrayList<Bike> bikeList) {
        if (dockInformationScreenHandler == null) {
            try {
                dockInformationScreenHandler = new DockInformationScreenHandler(stage, Configs.VIEW_DOCK_SCREEN_PATH);
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

        if (bikeList != null) {
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
    }

    /**
     * This is the method to render the screen with data.
     */
    private void renderDockInformation() {
        // provide dock image
        //super.setImage(dockImageView, currentDock.getDockImage);
        dockNameText.setText(currentDock.getName());
        dockAddressText.setText(currentDock.getDockAddress());
        dockAreaText.setText(currentDock.getDockArea() + " km2");
        dockCount.setText(currentDock.getNumDockSpaceFree() + currentDock.getNumAvailableBike() + "");
        availableBikeCount.setText(currentDock.getNumAvailableBike() + "");
        availableDocksCount.setText(currentDock.getNumDockSpaceFree() + "");
        distance.setText("100 km");
        estimateWalkTime.setText("100 minutes");

        addBike(currentBikeList);
    }

    /**
     * This is the method called when the user press button return bike.
     */
    private void returnBike() {

    }

    /**
     * This is the method to render bike list in the dock screen.
     */
    private void addBike(ArrayList<Bike> bikeList) {
        while (!bikeList.isEmpty()) {
            try {
                BikeInDockHandler bike = new BikeInDockHandler(bikeList.get(0), Configs.BIKE_IN_DOCK_PATH);
                bikeVBox.getChildren().add(bike.getContent());
                bikeList.remove(bikeList.get(0));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
