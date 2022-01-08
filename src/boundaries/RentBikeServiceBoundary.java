package boundaries;

import java.io.IOException;
import java.sql.SQLException;

import controllers.RentBikeController;
import entities.Bike;
import exceptions.ecobike.EcoBikeException;
import exceptions.ecobike.EcoBikeUndefinedException;
import exceptions.ecobike.RentBikeException;
import interfaces.RentBikeServiceInterface;
import javafx.stage.Stage;
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
	
	public void rentBike(Stage stage, Bike bike) throws IOException, EcoBikeException, SQLException {
		// TODO: call pay for deposit handler here
		PayForDepositScreenHandler paymentScreenHandler = PayForDepositScreenHandler.getPayForDepositScreenHandler(stage, Configs.PAYMENT_METHOD_SCREEN_PATH, this.prevScreen, bike);
		paymentScreenHandler.show();
	}
	
	public void returnBike(Stage stage, Bike bike) throws RentBikeException, EcoBikeUndefinedException, IOException {
		// TODO: call pay for return handler here
		PayForRentScreenHandler paymentScreenHandler = PayForRentScreenHandler.getPayForRentScreenHandler(stage, Configs.PAYMENT_METHOD_SCREEN_PATH, this.prevScreen, bike);
		paymentScreenHandler.show();
	}
	
	public void pauseBikeRental(Stage stage, Bike bike) throws EcoBikeException, SQLException {
		RentBikeController.getRentBikeServiceController().pauseBikeRental(bike.getBikeBarCode());
		
	}
}
