package views;

import controllers.EcoBikeBaseController;
import entities.Bike;
import entities.Dock;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utils.Configs;

import java.util.ArrayList;

/**
 * This class creates a handler for getting behaviors of customer on dock information screen
 *
 * @author chauntm
 */
public class DockInformationScreenHandler extends EcoBikeBaseScreenHandler {

    private Dock currentDock;
    private ArrayList<Bike> bikeList;

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

    /**
     * Initialize handler for dock information screen
     *
     * @param screenTitle Title of the screen
     * @param controller  Controller for handling request from the screen
     * @param prevScreen  An instance to the screen that called this screen
     */
    public DockInformationScreenHandler(Stage stage, String screenTitle, EcoBikeBaseController controller, EcoBikeBaseScreenHandler prevScreen, String screenPath) {
        super(stage, screenTitle, controller, prevScreen, screenPath);
        initialize();
    }

    @Override
    protected void initialize() {
        try {
            // get dock
            currentDock = new Dock("name", "dockID", "dockaddress", 100, 10, 10);
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


        } catch (Exception e) {
            e.printStackTrace();
        }


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
