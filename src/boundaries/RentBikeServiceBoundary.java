package boundaries;

import java.io.IOException;
import java.sql.SQLException;

import controllers.RentBikeController;
import entities.Bike;
import exceptions.ecobike.EcoBikeException;
import exceptions.ecobike.EcoBikeUndefinedException;
import exceptions.ecobike.RentBikeException;
import interfaces.RentBikeServiceInterface;
import utils.Configs;
import views.screen.EcoBikeBaseScreenHandler;
import views.screen.PayForDepositScreenHandler;
import views.screen.PayForRentScreenHandler;

/**
 * This class is a real communicator of the rent bike subsystem
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
	public void rentBike(Bike bike) throws IOException, EcoBikeException, SQLException {
		PayForDepositScreenHandler paymentScreenHandler = PayForDepositScreenHandler.getPayForDepositScreenHandler(null, Configs.PAYMENT_METHOD_SCREEN_PATH, this.prevScreen, bike);
		paymentScreenHandler.show();
	}
	
	public void returnBike(Bike bike) throws RentBikeException, EcoBikeUndefinedException, IOException {
		PayForRentScreenHandler paymentScreenHandler = PayForRentScreenHandler.getPayForRentScreenHandler(null, Configs.PAYMENT_METHOD_SCREEN_PATH, this.prevScreen, bike);
		paymentScreenHandler.show();
	}
	
	public void pauseBikeRental(Bike bike) throws EcoBikeException, SQLException {
		if (RentBikeController.getRentBikeServiceController().pauseBikeRental(bike.getBarCode())) {
			// show popup here
		}
		
	}
}
