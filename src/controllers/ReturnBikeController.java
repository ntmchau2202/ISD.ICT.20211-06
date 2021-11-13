package controllers;

import exceptions.ecobike.EcoBikeUndefinedException;
import exceptions.ecobike.RentBikeException;
import interfaces.RentBikeInterface;

/**
 * This is the class controller including all the methods and operations for return bike use case
 * @author Duong
 *
 */
public class ReturnBikeController implements RentBikeInterface {
	public ReturnBikeController() {
		
	}

	@Override
	public void rentBike(String bikeBarcode) throws RentBikeException, EcoBikeUndefinedException {}
	
	@Override
	public void pauseBikeRental(String bikeBarcode) throws RentBikeException, EcoBikeUndefinedException {}
	
	@Override
	public void returnBike(String bikeBarcode) throws RentBikeException, EcoBikeUndefinedException {
		// TODO Auto-generated method stub
	}
	
	/**
	 * Calculate the fee based on the time rent and deposit of the bike which got by searching barcode
	 * @param  bikeBarcode Barcode of the bike to be rented
	 * @throws RentBikeException If the bike is not currently available, the barcode is not valid
	 * @throws EcoBikeUndefinedException If there is an unexpected error occurs during the renting process
	 * @return a float value expresses the money have to pay in currency VND
	 */
	public double calculateFee(String bikeBarcode) throws RentBikeException, EcoBikeUndefinedException {
		return 0;
	}
	
	/**
	 * Check the fee whether to pay or not
	 * @param fee  The fee calculated before
	 * @throws EcoBikeUndefinedException If there is an unexpected error occurs during the renting process
	 * @return true/false
	 */
	public boolean checkHaveToPayOrNot(double fee) throws EcoBikeUndefinedException {
		return true;
	}
	
	/**
	 * Request to pay for rental bike
	 * @throws EcoBikeUndefinedException If there is an unexpected error occurs during the renting process
	 */
	public void requestToPay() throws EcoBikeUndefinedException {
		
	}
}
