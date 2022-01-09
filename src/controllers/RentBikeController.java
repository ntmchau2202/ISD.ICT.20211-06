package controllers;

import exceptions.ecobike.EcoBikeException;
import exceptions.ecobike.EcoBikeUndefinedException;
import exceptions.ecobike.RentBikeException;
import subsystem.InterbankInterface;
import entities.Bike;
import entities.Cart;
import entities.CreditCard;
import entities.Dock;
import utils.*;
import views.screen.popup.PopupScreen;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;

/**
 * This class handles rent bike, return bike and pause bike rental request from customers
 */
public class RentBikeController extends EcoBikeBaseController {
	@SuppressWarnings("unused")
	private InterbankInterface interbankSystem;

	private static RentBikeController rentBikeServiceController;

	public static RentBikeController getRentBikeServiceController(){
		if (rentBikeServiceController == null)
			rentBikeServiceController = new RentBikeController();
		return rentBikeServiceController;
	}
	/**
	 * Initialize the controller for EcoBike rent bike service
	 */
	public RentBikeController() {
	}

	/**
	 * Start renting bike process, including calling the interbank subsystem for performing transaction
	 *
	 * @param bikeToRent barCode of the bike to be rented
	 * @throws IOException 
	 * @throws RentBikeException If the bike is not currently available, the barcode is not valid
	 * @throws EcoBikeUndefinedException If there is an unexpected error occurs during the renting process
	 */
	public void rentBike(Bike bikeToRent) throws EcoBikeException, SQLException, IOException {
		
		// TODO: process the payment before updating the database;
		
		//get bike from repository
		Bike bike = DBUtils.getBikeByBarcode(bikeToRent.getBikeBarCode());
		
		// create new customerRent record
		startCountingRentTime(bike);

		//Update status
		String sql2 = "Update BikeStatus set current_status = ? where bike_barcode = ?";
		PreparedStatement stm2 = DBUtils.getConnection().prepareStatement(sql2);
		stm2.setString(1, String.valueOf(Configs.BIKE_STATUS.RENT).toLowerCase());
		stm2.setString(2, bikeToRent.getBikeBarCode());
		stm2.executeUpdate();
		
		Cart.getCart().addBike(bike);
		updateDock(bike.getDockId());
	}
	
	public boolean checkAvailableBike(Bike bike) throws EcoBikeException, SQLException, IOException {
		if(bike.getCurrentStatus() == Configs.BIKE_STATUS.RENT) {
			PopupScreen.error("This bike has been rented");
			return false;
		}
		return true;
	}
	
	/**
	 * Start pausing bike rental process
	 * @param bikeBarcode barCode of the bike to be rented
	 */
	public void pauseBikeRental(String bikeBarcode) throws EcoBikeException, SQLException {

		Bike bike = DBUtils.getBikeByBarcode(bikeBarcode);
		String sql = "Update RentBike set end_time = ? where bike_barcode = ?";
		PreparedStatement stm = DBUtils.getConnection().prepareStatement(sql);
		stm.setTime(1, Time.valueOf(LocalTime.now()));
		stm.setString(2, bike.getBikeBarCode());
		stm.executeUpdate();
	}
	
	/**
	 * Invoke counter for tracking rental time
	 * @throws EcoBikeException 
	 * @throws SQLException 
	 */
	private void startCountingRentTime(Bike bike) throws SQLException, EcoBikeException {
		// create new rentBike record
		
		String sql = "Insert into RentBike(bike_barcode, start_time) values(?, ?)";
		PreparedStatement stm = DBUtils.getConnection().prepareStatement(sql);
		stm.setString(1, bike.getBikeBarCode());
		stm.setTime(2, Time.valueOf(LocalTime.now()));
		stm.executeUpdate();
	}

	private void updateDock(int dockID) throws SQLException, EcoBikeException {
		String sql = "Update Dock set num_available_bike = num_available_bike - 1, num_free_dock = num_free_dock + 1 WHERE dock_Id = ?";
		PreparedStatement stm = DBUtils.getConnection().prepareStatement(sql);
		stm.setInt(1, dockID);
		stm.executeUpdate();
	}

}
