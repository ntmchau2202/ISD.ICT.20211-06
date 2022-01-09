package views.screen.popup;

import entities.Bike;
import exceptions.ecobike.EcoBikeUndefinedException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import utils.Configs;
import views.screen.BikeInformationScreenHandler;
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

    public BikeInDockHandler(Stage stage, Bike bike, String screenPath, EcoBikeBaseScreenHandler prevScreen) throws IOException {
        super(stage, screenPath);
        this.currentBike = bike;
        if(prevScreen != null) {
        	this.setPreviousScreen(prevScreen);
        }
        initialize();
    }

    protected void initialize() {
    	if(currentBike.getBikeImage() != null && currentBike.getBikeImage().length() != 0) {
    		bikeImage.setImage(new Image((new File(currentBike.getBikeImage())).toURI().toString()));    		
    	}
    	// testing information
    	System.out.println("Bike name:"+currentBike.getName());
    	System.out.println("Bike barcode:"+currentBike.getBikeBarCode());
        bikeName.setText(currentBike.getName());
        bikeBattery.setText(currentBike.getCurrentBattery() + "%");
        distanceEstimation.setText(.2 * currentBike.getCurrentBattery() + "km");
        viewBikeButton.setOnMouseClicked(e -> {
			try {
				viewBikeInformation();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
    }

    /**
     * This method is called when user click view bike button
     * @throws IOException 
     *
     */
    public void viewBikeInformation() throws IOException {
    	BikeInformationScreenHandler bikeInfHandler = new BikeInformationScreenHandler(this.stage, Configs.VIEW_BIKE_SCREEN_PATH, this.getPreviousScreen(), currentBike);
    	bikeInfHandler.show();
    }

}
