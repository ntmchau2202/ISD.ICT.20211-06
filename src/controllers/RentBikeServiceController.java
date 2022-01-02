package controllers;

import exceptions.ecobike.EcoBikeException; 
import exceptions.ecobike.EcoBikeUndefinedException;
import exceptions.ecobike.RentBikeException;
import interfaces.InterbankInterface;
import entities.Bike;
import utils.*;
import views.screen.popup.PopupScreen;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * This class handles rent bike, return bike and pause bike rental request from customers
 */
public class RentBikeServiceController extends EcoBikeBaseController {
	@SuppressWarnings("unused")
	private InterbankInterface interbankSystem;

	private static RentBikeServiceController rentBikeServiceController;

	public static RentBikeServiceController getRentBikeServiceController(){
		if (rentBikeServiceController == null)
			rentBikeServiceController = new RentBikeServiceController();
		return rentBikeServiceController;
	}
	/**
	 * Initialize the controller for EcoBike rent bike service
	 */
	public RentBikeServiceController() {
	}

	/**
	 * Start renting bike process, including calling the interbank subsystem for performing transaction
	 *
	 * @param bikeBarcode barCode of the bike to be rented
	 * @throws IOException 
	 * @throws RentBikeException If the bike is not currently available, the barcode is not valid
	 * @throws EcoBikeUndefinedException If there is an unexpected error occurs during the renting process
	 */
	public void rentBike(String bikeBarcode) throws EcoBikeException, SQLException, IOException {
		//get bike from repository
		Bike bike = DBUtils.getBikeByBarcode(bikeBarcode);
		
		//check status
		if(bike.getCurrentStatus() == Configs.BIKE_STATUS.RENTED) {
			PopupScreen.error("This bike has been rented");
			return;
		}
		
		//show payment method screen
		startCountingRentTime(bike);

		//Update status
		try {
			String sql = "Update BikeStatus set current_status = ? where bike_barcode = ?";
			PreparedStatement stm = DBUtils.getConnection().prepareStatement(sql);
			stm.setString(1, String.valueOf(Configs.BIKE_STATUS.RENTED));
			stm.setString(2, bikeBarcode);
			stm.executeUpdate();
		} catch (Exception e) {
			throw e;
		};
		
		//update table rent bike
		try {
			String sql = "";
			PreparedStatement stm = DBUtils.getConnection().prepareStatement(sql);
			stm.setString(1, String.valueOf(Configs.BIKE_STATUS.RENTED));
			stm.setString(2, bikeBarcode);
			stm.executeUpdate();
		} catch (Exception e) {
			throw e;
		};
	}
	
	/**
	 * Start pausing bike rental process
	 * @param bikeBarcode barCode of the bike to be rented
	 */
	public void pauseBikeRental(String bikeBarcode) throws EcoBikeException, SQLException {

		Bike bike = DBUtils.getBikeByBarcode(bikeBarcode);
	}
	
	/**
	 * Invoke counter for tracking rental time
	 */
	private void startCountingRentTime(Bike bike) {
		//todo: start counting rental time
	}


}
