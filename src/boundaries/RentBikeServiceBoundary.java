package boundaries;

import exceptions.ecobike.EcoBikeUndefinedException;
import exceptions.ecobike.RentBikeException;
import interfaces.RentBikeServiceInterface;

/**
 * This class is a real communicator of the interbank subsystem
 */
public class RentBikeServiceBoundary implements RentBikeServiceInterface {
	public void rentBike(String bikeBarcode) throws RentBikeException, EcoBikeUndefinedException {
	
	}
	
	public void returnBike(String bikeBarcode) throws RentBikeException, EcoBikeUndefinedException {
		
	}
	
	public void pauseBikeRental(String bikeBarcode) throws RentBikeException, EcoBikeUndefinedException {
		
	}
}
