package views.screen;

import controllers.PaymentController;
import controllers.RentBikeController;
import entities.Bike;
import entities.CreditCard;
import exceptions.ecobike.EcoBikeException;
import exceptions.ecobike.EcoBikeUndefinedException;
import exceptions.ecobike.RentBikeException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import utils.Configs;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

/**
 * This class creates a handler for deposit screen
 *
 * @author longnt
 */
public class DepositScreenHandler extends EcoBikeBaseScreenHandler {

    private Bike currentBike = null;
    private CreditCard currentCreditCard = null;

    @FXML
    private Label customerName;
    @FXML
    private Label bikeToRent;
    @FXML
    private Label bikeType;
    @FXML
    private Label deposit;
    @FXML
    private Button confirmDepositButton;
    @FXML
    private Button changeCardInformationButton;
    @FXML
    private ImageView mainScreenIcon;
    @FXML
    private ImageView backIcon;

    public DepositScreenHandler(Stage stage, String screenPath, EcoBikeBaseScreenHandler prevScreen, CreditCard creditCard,Bike bike) throws IOException {
        super(stage, screenPath);
        setbController(PaymentController.getPaymentController());
        setScreenTitle("Deposit screen");
        initializeDepositScreen();
        
        if (prevScreen != null) {
            setPreviousScreen(prevScreen);
        }

        if (creditCard != null) {
            currentCreditCard = creditCard;
        }

        if (bike != null) {
            currentBike = bike;
        }

        renderDepositScreen();
    }

//    @Override
//    public void show() {
//        if (currentCreditCard != null) {
//            //if already provided a credit card, just show the screen
//            super.show();
//        } else {
//            //show payment method
//            PaymentMethodScreenHandler.getPaymentMethodScreenHandler(this.stage, this, null, Configs.TransactionType.PAY_DEPOSIT).show();
//        }
//    }

    /**
     * This is the method to do initialization and register button event.
     */
    private void initializeDepositScreen() {
        confirmDepositButton.setOnMouseClicked(e -> {
			try {
				try {
					confirmDeposit();
				} catch (EcoBikeException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} catch (NumberFormatException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
        changeCardInformationButton.setOnMouseClicked(e -> {
			try {
				changeCardInformation();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		});
        mainScreenIcon.setOnMouseClicked(e -> {
        	try {
				EcoBikeMainScreenHandler handler = new EcoBikeMainScreenHandler(this.stage, Configs.MAIN_SCREEN_PATH);
				handler.show();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        });
        backIcon.setOnMouseClicked(e -> {
            if (this.getPreviousScreen() != null)
                this.getPreviousScreen().show();
        });
    }

    /**
     * This is the method to do render the screen with data.
     */
    private void renderDepositScreen() {
        if (currentCreditCard != null) {
            customerName.setText(currentCreditCard.getCardHolderName());
        }
        bikeToRent.setText(currentBike.getName());
        bikeType.setText(currentBike.getBikeType());
        deposit.setText(currentBike.getDeposit() + " " + currentBike.getCurrency());
    }

    /**
     * This is the method to be called when user press confirm deposit.
     * @throws NumberFormatException 
     * @throws IOException 
     * @throws SQLException 
     * @throws EcoBikeException 
     */
    private void confirmDeposit() throws NumberFormatException, EcoBikeException, SQLException, IOException {
        //todo: call payment controller to proceed deposit transaction with current bike and credit card
    	Map<String, String> result = PaymentController.getPaymentController().payDeposit((int) currentBike.getDeposit(), "pay deposit", currentCreditCard.getCardHolderName()
    			, currentCreditCard.getCardNumber(), currentCreditCard.getIssueBank(), (float) currentCreditCard.getBalance(), 
    			currentCreditCard.getExpirationDate().toString(), currentCreditCard.getCardSecurity());
    	RentBikeController.getRentBikeServiceController().rentBike(currentBike);
    	try {
			EcoBikeMainScreenHandler handler = new EcoBikeMainScreenHandler(this.stage, Configs.MAIN_SCREEN_PATH);
			handler.show();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }

    /**
     * This is the method to be called when user press change card information.
     * @throws IOException 
     */
    private void changeCardInformation() throws IOException {
    	PaymentMethodScreenHandler paymentMethodScreenHandler = new PaymentMethodScreenHandler
    			(this.stage, Configs.PAYING_METHOD_SCREEN_PATH, this, currentCreditCard, Configs.TransactionType.PAY_DEPOSIT, currentBike); 
        paymentMethodScreenHandler.setCreditCard(currentCreditCard);
        paymentMethodScreenHandler.show();
    }
}