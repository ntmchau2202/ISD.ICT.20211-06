package controllers;

import java.sql.SQLException;

import entities.Bike;
import entities.CreditCard;
import entities.Dock;
import exceptions.ecobike.EcoBikeException;
import exceptions.ecobike.EcoBikeUndefinedException;
import exceptions.ecobike.RentBikeException;
import interfaces.InterbankInterface;
import utils.Configs;
import utils.DBUtils;

public class ReturnBikeController extends EcoBikeBaseController {
	
	private static ReturnBikeController returnController;	
	private InterbankInterface interbankSystem;
	
	public static ReturnBikeController getReturnBikeController() {
		if (returnController == null) {
			returnController = ReturnBikeController();
		}
		
		return returnController;
	}
	
	/**
	 * Check if the dock have free spaces.
	 * <br>@param dock
	 * <br>@return boolean
	 */
	public boolean checkDockFreeSpace(Dock dock) {
		if(dock.getNumDockSpaceFree() > 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * Start return bike process, including calling the interbank subsystem for performing transaction
	 * @param bikeBarcode barCode of the bike to be returned
	 * @throws RentBikeException If the bike is not currently available, the barcode is not valid
	 * @throws EcoBikeUndefinedException If there is an unexpected error occurs during the renting process
	 */
	public void returnBike(String bikeBarcode, CreditCard card) throws EcoBikeException, SQLException {
		Bike bike = DBUtils.getBikeByBarcode(bikeBarcode);
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
}
