package views.screen;

import entities.Bike;
import entities.Dock;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import utils.Configs;

import java.io.IOException;
import java.util.ArrayList;

/**
 * This class creates a handler for displaying the map and getting customer's activities on the main screen
 *
 * @author chauntm
 */
public class EcoBikeMainScreenHandler extends EcoBikeBaseScreenHandler {

    private static EcoBikeMainScreenHandler ecoBikeMainScreenHandler;
    @FXML
    private ImageView dock1;
    @FXML
    private ImageView dock2;
    @FXML
    private ImageView dock3;
    @FXML
    private ChoiceBox choiceBox;
    @FXML
    private TextField searchBarField;
    @FXML
    private Button searchButton;

    private EcoBikeMainScreenHandler(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);
    }

    public static EcoBikeMainScreenHandler getEcoBikeMainScreenHandler(Stage stage, EcoBikeBaseScreenHandler prevScreen) {
        if (ecoBikeMainScreenHandler == null) {
            try {
                ecoBikeMainScreenHandler = new EcoBikeMainScreenHandler(stage, Configs.MAIN_SCREEN_PATH);
                ecoBikeMainScreenHandler.setScreenTitle("Main screen");
                ecoBikeMainScreenHandler.initializeMainScreen();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (prevScreen != null) {
            ecoBikeMainScreenHandler.setPreviousScreen(prevScreen);
        }

        return ecoBikeMainScreenHandler;
    }

    /**
     * This is the method to do initialization and register button event.
     */
    private void initializeMainScreen() {
        choiceBox.getItems().add("Bike");
        choiceBox.getItems().add("Dock");

        searchButton.setOnMouseClicked(e -> search());

        //todo: assign dock id for each dock icon on the map
        dock1.setOnMouseClicked(e -> showDock("dockid1"));
        dock2.setOnMouseClicked(e -> showDock("dockid2"));
        dock3.setOnMouseClicked(e -> showDock("dockid3"));
    }

    /**
     * This is the method called when the user press search button.
     */
    private void search() {
        String searchString = searchBarField.getText();

        //check if user is finding bike or dock
        if (choiceBox.getValue() == "Bike") {
            //todo : search bike from somewhere
            Bike bike = null;

            if (bike != null) {
                //render bike screen
                BikeInformationScreenHandler.getBikeInformationScreenHandler(this.stage, this, bike).show();
            } else {
                //todo: popup can not find bike
            }
        } else if (choiceBox.getValue() == "Dock") {
            //todo : search bike from somewhere
            Dock dock = null;

            if (dock != null) {
                //todo : if have dock, get bikes of the dock, too!
                ArrayList<Bike> bikeList = new ArrayList<Bike>();

                //render dock screen
                DockInformationScreenHandler.getDockInformationScreenHandler(this.stage, this, dock, bikeList).show();
            } else {
                //todo: popup can not find dock
            }
        }
    }

    /**
     * This is the method called when the user press dock icons on the map to view specific dock.
     */
    private void showDock(String dockID) {
        //todo : get dock with dock id from somewhere
        Dock dock = null;
        //todo : get bikes of the dock, too!
        ArrayList<Bike> bikeList = new ArrayList<Bike>();

        //render dock screen (always find the dock because it is shown on the map!!!)
        DockInformationScreenHandler.getDockInformationScreenHandler(this.stage, this, dock, bikeList).show();
    }
}
