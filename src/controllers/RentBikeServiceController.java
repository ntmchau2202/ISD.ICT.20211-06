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
public class RentBikeServiceController {
	private InterbankInterface interbankSystem;
	
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
	 * Start return bike process, including calling the interbank subsystem for performing transaction
	 * @param bikeBarcode barCode of the bike to be returned
	 * @throws RentBikeException If the bike is not currently available, the barcode is not valid
	 * @throws EcoBikeUndefinedException If there is an unexpected error occurs during the renting process
	 */
	public void returnBike(String bikeBarcode) throws EcoBikeException, SQLException {
		Bike bike = DBUtils.getBike(bikeBarcode);
	}
	
	/**
	 * Calculate the fee based on the time rent and deposit of the bike which got by searching barcode
	 * @param  bike Barcode of the bike to be rented
	 * @return a float value expresses the money have to pay in currency VND
	 */
	public float calculateFee(String bikeType, float rentTime) {
		//renting cost
		float rentingCost = rentTime <= Configs.freeOfChargeTimeInMinute
				? 0
				: rentTime - Configs.firstChargeTimeIntervalInMinute > 0
					? (Configs.firstChargeTimeIntervalCost + (float)Math.ceil((rentTime - Configs.firstChargeTimeIntervalInMinute)/ Configs.chargeTimeIntervalInMinute) * Configs.chargeTimeIntervalCost)
					: Configs.firstChargeTimeIntervalCost;

		rentingCost *= Configs.chargeMultiplierDictionary.get(bikeType);

		return rentingCost;
	}
	
	/**
	 * Check whether user have to pay or not
	 * @param bike The bike which is being returned
	 * @return true/false
	 */
	public boolean checkHaveToPayOrNot(Bike bike) {

		return bike.getTotalRentTime() <= 10 ? false : true;
	}

	/**
	 * Request to pay for rental bike
	 * @throws EcoBikeUndefinedException If there is an unexpected error occurs during the renting process
	 */
	public void requestToPay(CreditCard card, double amount, String content) throws EcoBikeUndefinedException {
		//todo: request to pay
		this.interbankSystem.payRental(card, amount, content);
	}
	
	/**
	 * Invoke counter for tracking rental time
	 */
	private void startCountingRentTime(Bike bike) {
		//todo: start counting rental time
	}


}
