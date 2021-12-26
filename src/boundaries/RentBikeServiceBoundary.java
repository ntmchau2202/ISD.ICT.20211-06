package boundaries;

import entities.Bike;
import exceptions.ecobike.EcoBikeUndefinedException;
import exceptions.ecobike.RentBikeException;
import interfaces.RentBikeServiceInterface;

/**
 * This class is a real communicator of the interbank subsystem
 */
public class RentBikeServiceBoundary implements RentBikeServiceInterface {
	private static RentBikeServiceBoundary rentBikeService;
	private RentBikeServiceBoundary() {
		super();
	}
	
	public static RentBikeServiceBoundary getRentBikeService() {
		if (rentBikeService == null) {
			rentBikeService = new RentBikeServiceBoundary();
		}
		return rentBikeService;
		
	}
	public void rentBike(Bike bike) throws RentBikeException, EcoBikeUndefinedException {
	
	}
	
	public void returnBike(Bike bike) throws RentBikeException, EcoBikeUndefinedException {
		
	}
	
	public void pauseBikeRental(Bike bike) throws RentBikeException, EcoBikeUndefinedException {
		
	}
}
