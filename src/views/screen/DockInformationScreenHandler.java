package views.screen;

import controllers.EcoBikeInformationController;
import controllers.ReturnBikeController;
import entities.Bike;
import entities.Cart;
import entities.Dock;
import exceptions.ecobike.EcoBikeException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utils.Configs;
import utils.DBUtils;
import views.screen.popup.BikeInDockHandler;
import views.screen.popup.PopupScreen;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * This class creates a handler for getting behaviors of customer on dock information screen
 *
 * @author chauntm
 */
public class DockInformationScreenHandler extends EcoBikeBaseScreenHandler {

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

    public DockInformationScreenHandler(Stage stage, String screenPath, EcoBikeBaseScreenHandler prevScreen, Dock dock) throws IOException {
        super(stage, screenPath);
        this.setPreviousScreen(prevScreen);
        setbController(EcoBikeInformationController.getEcoBikeInformationController());
        setScreenTitle("Dock information screen");
        initializeDockInformationScreen();
        
        if (prevScreen != null) {
            setPreviousScreen(prevScreen);
        }

        if (dock != null) {
            currentDock = dock;
        }

        ArrayList<Bike> bikeList = dock.getAllBikeInDock();
        if (bikeList != null && bikeList.size() != 0) {
            currentBikeList = bikeList;
        }

        renderDockInformation();
    }

    /**
     * This is the method to do initialization and register button event.
     */
    private void initializeDockInformationScreen() {
    	if(!Cart.getCart().getListMedia().isEmpty()) {
    		returnBikeButton.setVisible(true);
    	} else returnBikeButton.setVisible(false);
        returnBikeButton.setOnMouseClicked(e -> returnBike());
        mainScreenIcon.setOnMouseClicked(e -> {
        	try {
				EcoBikeMainScreenHandler handler = new EcoBikeMainScreenHandler(this.stage, Configs.MAIN_SCREEN_PATH);
				handler.show();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        });
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
//        super.setImage(dockImageView,Configs.IMAGE_PATH + currentDock.getDockImage());
        dockNameText.setText(currentDock.getName());
        dockAddressText.setText(currentDock.getDockAddress());
        dockAreaText.setText(currentDock.getDockArea() + " km2");
        dockCount.setText(currentDock.getNumDockSpaceFree() + currentDock.getNumAvailableBike() + "");
        // TODO: Fix bug: availableBikeCount is null
//      availableBikeCount.setText(currentDock.getNumAvailableBike() + "");
        availableDocksCount.setText(currentDock.getNumDockSpaceFree() + "");
        distance.setText("100 km");
        estimateWalkTime.setText("100 minutes");
        addBike(currentBikeList);
    }

    /**
     * This is the method called when the user press button return bike.
     */
    private void returnBike() {
    	try {
            System.out.println("return bike");
            Bike currentBike = Cart.getCart().getListMedia().get(0);
            // TODO: change job to RentBikeServiceBoundary to invoke the RentBike subsystem
            Dock dock = DBUtils.getDockInformation(currentDock.getDockID());
            if(!ReturnBikeController.getReturnBikeController().checkDockFreeSpace(dock)) {
            	PopupScreen.error("Dock is full!");
            	return;
            }
            updateBikeRent(currentBike);
            updateRentPeriod(currentBike);
            currentBike.setTotalRentTime(getTotalRentTime(currentBike));
            PaymentMethodScreenHandler handler = new PaymentMethodScreenHandler(this.stage, Configs.PAYING_METHOD_SCREEN_PATH, this, null,Configs.TransactionType.PAY_RENTAL,currentBike);
            handler.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateBikeRent(Bike currentBike) throws SQLException, EcoBikeException {
    	String sql = "Update RentBike set end_time = ?, rent_period = end_time - start_time where bike_barcode = ?";
    	PreparedStatement stm = DBUtils.getConnection().prepareStatement(sql);
    	stm.setTime(1, Time.valueOf(LocalTime.now()));
    	stm.setString(2, currentBike.getBikeBarCode());
    	stm.executeUpdate();
    }
    
    private void updateRentPeriod(Bike currentBike) throws SQLException, EcoBikeException {
    	String sql = "Update RentBike set rent_period = end_time - start_time where bike_barcode = ?";
    	PreparedStatement stm = DBUtils.getConnection().prepareStatement(sql);
    	stm.setString(1, currentBike.getBikeBarCode());
    	stm.executeUpdate();
    }
    
    private int getTotalRentTime(Bike currentBike) throws SQLException, EcoBikeException {
    	String sql = "Select * from RentBike where bike_barcode = ?";
    	PreparedStatement stm = DBUtils.getConnection().prepareStatement(sql);
    	stm.setString(1, currentBike.getBikeBarCode());
    	ResultSet result = stm.executeQuery();
    	while(result.next()) {
    		long total = (int)result.getTime("rent_period").getTime() / 1000l;
    		return (int) total;
    	}
    	return 0;
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
