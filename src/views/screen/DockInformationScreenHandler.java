package views.screen;

import controllers.EcoBikeInformationController;
import entities.Bike;
import entities.Dock;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

    private static DockInformationScreenHandler dockInformationScreenHandler;
    private Dock currentDock;
    private ArrayList<Bike> currentBikeList;

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
    private Button returnBike;
    @FXML
    private VBox bikeVBox;

    private DockInformationScreenHandler(Stage stage, String screenPath, EcoBikeBaseScreenHandler prevScreen) throws IOException {
        super(stage, screenPath, prevScreen);
    }

    public static DockInformationScreenHandler getDockInformationScreenHandler(Stage stage, EcoBikeBaseScreenHandler prevScreen, Dock dock, ArrayList<Bike> bikeList) {
        if (dockInformationScreenHandler == null) {
            try {
                dockInformationScreenHandler = new DockInformationScreenHandler(stage, Configs.VIEW_DOCK_SCREEN_PATH, prevScreen);
                dockInformationScreenHandler.setbController(EcoBikeInformationController.getEcoBikeInformationController());
                dockInformationScreenHandler.setScreenTitle("Dock information screen");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (dock != null) {
            dockInformationScreenHandler.currentDock = dock;
        }

        if (bikeList != null) {
            dockInformationScreenHandler.currentBikeList = bikeList;
        }

        return dockInformationScreenHandler;
    }


    protected void initializeDockInformation() {
        dockNameText.setText(currentDock.getName());
        dockAddressText.setText(currentDock.getDockAddress());
        dockAreaText.setText(currentDock.getDockArea() + " km2");
        dockCount.setText(currentDock.getNumDockSpaceFree() + currentDock.getNumAvailableBike() + "");
        availableBikeCount.setText(currentDock.getNumAvailableBike() + "");
        availableDocksCount.setText(currentDock.getNumDockSpaceFree() + "");
        distance.setText("100 km");
        estimateWalkTime.setText("100 minutes");

        returnBike.setOnMouseClicked(e -> returnBike());

        // get bike list
        bikeList = new ArrayList<Bike>();
        bikeList.add(new Bike("name", "bike_type", "bike_image", "bar_code", 1,
                1, "currency", "13/02/2000"));
        bikeList.add(new Bike("name", "bike_type", "bike_image", "bar_code", 1,
                1, "currency", "13/02/2000"));

        addBike(bikeList);
    }

    public void returnBike() {

    }

    public void addBike(ArrayList<Bike> bikeList) {
        while (!bikeList.isEmpty()) {
            BikeInDockHandler bike = new BikeInDockHandler(bikeList.get(0), Configs.BIKE_IN_DOCK_PATH);
            bikeVBox.getChildren().add(bike.getContent());
            bikeList.remove(bikeList.get(0));
        }
    }


}
