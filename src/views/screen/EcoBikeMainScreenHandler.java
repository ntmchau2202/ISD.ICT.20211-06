package views.screen;

import entities.Bike;
import entities.Dock;
import exceptions.ecobike.EcoBikeException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import utils.Configs;
import utils.DBUtils;
import views.screen.popup.PopupScreen;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * This class creates a handler for displaying the map and getting customer's activities on the main screen
 *
 * @author chauntm
 */
public class EcoBikeMainScreenHandler extends EcoBikeBaseScreenHandler {

    private static EcoBikeMainScreenHandler ecoBikeMainScreenHandler = null;
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

    /**
     * This class return an instance of main screen handler, initialize it with the stage and prevScreen
     *
     * @param stage         the stage to show this screen
     * @param prevScreen    the screen that call to this screen
     *
     */
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

        searchButton.setOnMouseClicked(e -> {
			try {
				search();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

        //TODO: assign dock id for each dock icon on the map
        dock1.setOnMouseClicked(e -> {
			try {
				showDock("1");
			} catch (NumberFormatException | SQLException | EcoBikeException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
        dock2.setOnMouseClicked(e -> {
			try {
				showDock("2");
			} catch (NumberFormatException | SQLException | EcoBikeException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
        dock3.setOnMouseClicked(e -> {
			try {
				showDock("3");
			} catch (NumberFormatException | SQLException | EcoBikeException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
    }

    /**
     * This is the method called when the user press search button.
     * @throws IOException 
     */
    private void search() throws IOException {
        String searchString = searchBarField.getText();

        //check if user is finding bike or dock
        if (choiceBox.getValue() == "Bike") {
        	// TODO: Create a search function, in the bike information controller
            Bike bike = null;
            //TODO : search bike from somewhere
            if (bike != null) {
                //render bike screen
                BikeInformationScreenHandler.getBikeInformationScreenHandler(this.stage, this, bike).show();
            } else {
                PopupScreen popup = new PopupScreen(this.stage);
                popup.error("Cannot find bike matching keyword!");
            }
        } else if (choiceBox.getValue() == "Dock") {
            Dock dock = null;
            //TODO: search bike from somewhere
            
            if (dock != null) {
                //TODO : if have dock, get bikes of the dock, too!
            	// ArrayList<Bike> bikeList = bike.getListCurrentBikeInDock();
                ArrayList<Bike> bikeList = new ArrayList<Bike>();

                //render dock screen
                DockInformationScreenHandler.getDockInformationScreenHandler(this.stage, this, dock).show();
            } else {
                PopupScreen popup = new PopupScreen(this.stage);
                popup.error("Cannot find dock matching keyword!");
            }
        }
    }

    /**
     * This is the method called when the user press dock icons on the map to view specific dock.
     * @throws EcoBikeException 
     * @throws SQLException 
     * @throws NumberFormatException 
     */
    private void showDock(String dockID) throws NumberFormatException, SQLException, EcoBikeException {
        //TODO: get dock with dock id from somewhere
        Dock dock = DBUtils.getDockInformation(Integer.parseInt(dockID));

        //render dock screen (always find the dock because it is shown on the map!!!)
        DockInformationScreenHandler.getDockInformationScreenHandler(this.stage, this, dock).show();
    }
}
