package controllers;

import exceptions.ecobike.EcoBikeException;
import exceptions.ecobike.EcoBikeUndefinedException;
import exceptions.ecobike.RentBikeException;
import interfaces.InterbankInterface;
import entities.Bike;
import entities.CreditCard;
import utils.*;

import java.sql.SQLException;

/**
 * This class handles rent bike, return bike and pause bike rental request from customers
 */
public class RentBikeServiceController extends EcoBikeBaseController {
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
	 * @throws RentBikeException If the bike is not currently available, the barcode is not valid
	 * @throws EcoBikeUndefinedException If there is an unexpected error occurs during the renting process
	 */
	public void rentBike(String bikeBarcode) throws EcoBikeException, SQLException {
		//get bike from repository
		Bike bike = DBUtils.getBike(bikeBarcode);
		//show payment method screen
		startCountingRentTime(bike);

	}
	
	/**
	 * Start pausing bike rental process
	 * @param bikeBarcode barCode of the bike to be rented
	 */
	public void pauseBikeRental(String bikeBarcode) throws EcoBikeException, SQLException {

		Bike bike = DBUtils.getBike(bikeBarcode);
	}
	
	
	/**
	 * Invoke counter for tracking rental time
	 */
	private void startCountingRentTime(Bike bike) {
		//todo: start counting rental time
	}


}
