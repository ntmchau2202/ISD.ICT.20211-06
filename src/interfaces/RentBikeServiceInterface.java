package interfaces;

import entities.Bike;
import exceptions.ecobike.EcoBikeUndefinedException;
import exceptions.ecobike.RentBikeException;

/**
 * This interface allow communication between the information subsystem and the rent bike service subsystem
 * There is a boundary class in the subsystem implement this interface as a loose connection between the subsystems
 * @author chauntm
 *
 */
public interface RentBikeServiceInterface {
	/**
	 * Calls the Rent bike sequence of operations for customers to rent bike
	 * @param bikeBarcode Barcode of the bike to be rented
	 * @throws RentBikeException If the bike is not currently available, the barcode is not valid
	 * @throws EcoBikeUndefinedException If there is an unexpected error occurs during the renting process
	 */
	public void rentBike(Bike bike) throws RentBikeException, EcoBikeUndefinedException;
	
	/**
	 * Calls the Return bike sequence of operations for customers to return bike
	 * @param bikeBarcode Barcode of the bike to be returned
	 * @throws RentBikeException If the bike is not currently available or the barcode is not valid
	 * @throws EcoBikeUndefinedException If there is an unexpected error occurs during the renting process
	 */
	public void returnBike(Bike bike) throws RentBikeException, EcoBikeUndefinedException;
	
	/**
	 * Calls the Pause bike rental sequence of operations for customers to pause bike rental
	 * @param bikeBarcode Barcode of the bike having rental time to be paused
	 * @throws RentBikeException If the bike is not currently available, not being rented or the barcode is not valid
	 * @throws EcoBikeUndefinedException If there is an unexpected error occurs during the renting process
	 */
	public void pauseBikeRental(Bike bike) throws RentBikeException, EcoBikeUndefinedException;
}
