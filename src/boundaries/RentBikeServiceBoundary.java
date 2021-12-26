package boundaries;

import entities.Bike;
import exceptions.ecobike.EcoBikeUndefinedException;
import exceptions.ecobike.RentBikeException;
import interfaces.RentBikeServiceInterface;
import views.EcoBikeBaseScreenHandler;
import views.PaymentMethodScreenHandler;

/**
 * This class is a real communicator of the interbank subsystem
 */
public class RentBikeServiceBoundary implements RentBikeServiceInterface {
	private static RentBikeServiceBoundary rentBikeService;
	private EcoBikeBaseScreenHandler prevScreen;
	private RentBikeServiceBoundary() {
		super();
	}
	
	public static RentBikeServiceBoundary getRentBikeService(EcoBikeBaseScreenHandler currentStage) {
		if (rentBikeService == null) {
			rentBikeService = new RentBikeServiceBoundary();
		}
		if (currentStage != null) {
			rentBikeService.prevScreen = currentStage;
		}
		return rentBikeService;
		
	}
	public void rentBike(Bike bike) throws RentBikeException, EcoBikeUndefinedException {
		PaymentMethodScreenHandler paymentScreenHandler = PaymentMethodScreenHandler.getPaymentMethodScreenHandler(bike, rentBikeService.prevScreen);
	
	}
	
	public void returnBike(Bike bike) throws RentBikeException, EcoBikeUndefinedException {
		
	}
	
	public void pauseBikeRental(Bike bike) throws RentBikeException, EcoBikeUndefinedException {
		
	}
}
