package views;

import entities.Bike;
import exceptions.ecobike.EcoBikeUndefinedException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import views.screen.EcoBikeBaseScreenHandler;

import java.io.File;
import java.io.IOException;

/**
 * This is the class handler for bike in dock screen
 *
 * @author Long
 */
public class BikeInDockHandler extends EcoBikeBaseScreenHandler {

    private Bike currentBike;
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

    public BikeInDockHandler(Bike bike, String screenPath) throws IOException {
        super(null, screenPath);
        this.currentBike = bike;
        initialize();
    }

    protected void initialize() {
        bikeImage.setImage(new Image((new File(currentBike.getBikeImage())).toURI().toString()));
        bikeName.setText(currentBike.getName());
        bikeBattery.setText(currentBike.getCurrentBattery() + "%");
        distanceEstimation.setText(.2 * currentBike.getCurrentBattery() + "km");
        viewBikeButton.setOnMouseClicked(e -> viewBikeInformation());
    }

    /**
     * This method is called when user click view bike button
     *
     */
    public void viewBikeInformation() {

    }

}
