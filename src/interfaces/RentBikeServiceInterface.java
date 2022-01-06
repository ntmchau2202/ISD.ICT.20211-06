package interfaces;

import java.io.IOException;
import java.sql.SQLException;

import entities.Bike;
import entities.CreditCard;
import exceptions.ecobike.EcoBikeException;
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
	 * @throws IOException 
	 * @throws SQLException 
	 * @throws EcoBikeException 
	 */
	public void rentBike(Bike bike) throws RentBikeException, EcoBikeUndefinedException, IOException, EcoBikeException, SQLException;
	
	/**
	 * Calls the Return bike sequence of operations for customers to return bike
	 * @param bikeBarcode Barcode of the bike to be returned
	 * @throws RentBikeException If the bike is not currently available or the barcode is not valid
	 * @throws EcoBikeUndefinedException If there is an unexpected error occurs during the renting process
	 * @throws IOException 
	 */
	public void returnBike(Bike bike) throws RentBikeException, EcoBikeUndefinedException, IOException;
	
	/**
	 * Calls the Pause bike rental sequence of operations for customers to pause bike rental
	 * @param bikeBarcode Barcode of the bike having rental time to be paused
	 * @throws RentBikeException If the bike is not currently available, not being rented or the barcode is not valid
	 * @throws EcoBikeUndefinedException If there is an unexpected error occurs during the renting process
	 * @throws SQLException 
	 * @throws EcoBikeException 
	 */
	public void pauseBikeRental(Bike bike) throws RentBikeException, EcoBikeUndefinedException, EcoBikeException, SQLException;
}
