package views;

import java.io.File;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import controllers.EcoBikeBaseController;
import entities.Bike;
import entities.CreditCard;
import exceptions.ecobike.EcoBikeUndefinedException;
import exceptions.ecobike.RentBikeException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * This is the class handler for bike in dock screen
 * @author Long
 *
 */
public class BikeInDockHandler extends FXMLScreenHandler {

    private Bike currentBike;
    /**
     * Initialize handler for paying method screen of EcoBike application
     * @param screenTitle Title of the screen
     * @param controller Controller for handling request from the screen
     * @param prevScreen An instance to the screen that called this screen
     */
    protected BikeInDockHandler(Bike bike, String screenPath) {
        super(screenPath);
        this.currentBike = bike;
        initialize();
    }

    @FXML
    private ImageView bikeImage;

    @FXML
    private Label bikeName;

    @FXML
    private Label bikeBattery;

    @FXML
    private Label distanceEstimation;

    @FXML
    private Button viewBikeButton;

    protected void initialize() {
        bikeImage.setImage(new Image((new File(currentBike.getBikeImage())).toURI().toString()));
        bikeName.setText(currentBike.getName());
        bikeBattery.setText(currentBike.getCurrentBattery() + "%");
        distanceEstimation.setText(.2 * currentBike.getCurrentBattery() + "km");
        viewBikeButton.setOnMouseClicked(e -> viewBikeInformation());
    }

    /**
     * Get payment method information from the form and go to transaction screen
     * @throws EcoBikeUndefinedException If there is an unexpected error occurs during the renting process
     */
    public void viewBikeInformation() {

    }

}
