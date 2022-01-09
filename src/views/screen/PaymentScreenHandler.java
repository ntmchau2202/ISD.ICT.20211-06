package views.screen;

import controllers.PaymentController;
import controllers.RentBikeController;
import controllers.ReturnBikeController;
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
import utils.DBUtils;
import utils.FunctionalUtils;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

/**
 * This class creates a handler for deposit screen
 *
 * @author longnt
 */
public class PaymentScreenHandler extends EcoBikeBaseScreenHandler {

    private Bike currentBike = null;
    private CreditCard currentCreditCard = null;

    @FXML
    private Label customerName;
    @FXML
    private Label bikeRented;
    @FXML
    private Label bikeType;
    @FXML
    private Label timeRented;
    @FXML
    private Label total;
    @FXML
    private Button confirmPaymentButton;
    @FXML
    private Button changeCardInformationButton;
    @FXML
    private ImageView mainScreenIcon;
    @FXML
    private ImageView backIcon;

    public PaymentScreenHandler(Stage stage, String screenPath, EcoBikeBaseScreenHandler prevScreen, CreditCard creditCard, Bike bike) throws IOException {
        super(stage, screenPath);
        setbController(ReturnBikeController.getReturnBikeController());
        setScreenTitle("Payment method screen");
        initializePaymentScreen();
        
        if (prevScreen != null) {
            setPreviousScreen(prevScreen);
        }

        if (creditCard != null) {
            currentCreditCard = creditCard;
        }

        if (bike != null) {
            currentBike = bike;
        }

        renderPaymentScreen();
    }

    /**
     * This is the method to do initialization and register button event.
     */
    private void initializePaymentScreen() {
        confirmPaymentButton.setOnMouseClicked(e -> {
			try {
				setConfirmPaymentButton();
			} catch (EcoBikeException | SQLException | IOException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
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
     * This is the method to render payment method if a card is provided.
     */
    private void renderPaymentScreen() {
        customerName.setText(currentCreditCard.getCardHolderName());
        bikeRented.setText(currentBike.getName());
        bikeType.setText(currentBike.getBikeType());
        timeRented.setText(FunctionalUtils.convertTime(currentBike.getTotalRentTime()));
        total.setText(FunctionalUtils.getCurrencyFormat((int) ReturnBikeController
        		.getReturnBikeController().calculateFee(currentBike.getBikeType(), currentBike.getTotalRentTime()) 
        		)+ " " + currentBike.getCurrency());
    }

    /**
     * This is the method to be called when user press confirm payment button.
     * @throws IOException 
     * @throws SQLException 
     * @throws EcoBikeException 
     */
    private void setConfirmPaymentButton() throws EcoBikeException, SQLException, IOException {
    	Map<String, String> result = PaymentController.getPaymentController().payRental((int) currentBike.getDeposit(), "pay rental", currentCreditCard.getCardHolderName()
    			, currentCreditCard.getCardNumber(), currentCreditCard.getIssueBank(), (float) currentCreditCard.getBalance(), 
    			currentCreditCard.getExpirationDate().toString(), currentCreditCard.getCardSecurity());
    	ReturnBikeController.getReturnBikeController().returnBike(currentBike.getBikeBarCode());
    	
    	String sql = "Delete from RentBike where bike_barcode = ?";
    	PreparedStatement stm = DBUtils.getConnection().prepareStatement(sql);
    	stm.setString(1, currentBike.getBikeBarCode());
    	stm.executeUpdate();
    	
    	try {
			EcoBikeMainScreenHandler handler = new EcoBikeMainScreenHandler(this.stage, Configs.MAIN_SCREEN_PATH);
			handler.show();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }

    /**
     * This is the method to be called when user press change card information button.
     * @throws IOException 
     */
    private void changeCardInformation() throws IOException {
        PaymentMethodScreenHandler handler = new PaymentMethodScreenHandler(this.stage, Configs.PAYING_METHOD_SCREEN_PATH,this, currentCreditCard, Configs.TransactionType.PAY_RENTAL, currentBike);
        handler.show();
    }

}