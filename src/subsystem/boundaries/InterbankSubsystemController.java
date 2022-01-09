package subsystem.boundaries;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import controllers.RentBikeController;
import entities.Bike;
import entities.CreditCard;
import entities.PaymentTransaction;
import exceptions.ecobike.EcoBikeException;
import exceptions.ecobike.EcoBikeUndefinedException;
import exceptions.ecobike.InvalidEcoBikeInformationException;
import exceptions.ecobike.RentBikeException;
import exceptions.interbank.InterbankUndefinedException;
import exceptions.interbank.InvalidCardException;
import javafx.stage.Stage;
import subsystem.InterbankSubsystem;
import utils.Configs;
import utils.FunctionalUtils;
import utils.MyMap;
import views.screen.EcoBikeBaseScreenHandler;
import views.screen.PayForDepositScreenHandler;
import views.screen.PayForRentScreenHandler;

/**
 * This class is a real communicator of the rent bike subsystem
 */
public class InterbankSubsystemController {
	
	private static final String SECRET_KEY = "BtNH8J4Tl/I=";
	private String command = "pay";
	private static final String VERSION = "1.0.1";
	
	private static InterbankBoundary interbankBoundary = new InterbankBoundary();
	
	/**
	 * data to json form
	 * @param data
	 * @return
	 */
	private String generateData(Map<String, Object> data) {
		return ((MyMap) data).toJSON();
	}

	public PaymentTransaction payDeposit(CreditCard creditCard, double amount, String content) throws NumberFormatException, InvalidEcoBikeInformationException {
		this.command = "pay";
		return this.deductMoney(creditCard, amount, content);
	}

	public PaymentTransaction returnDeposit(CreditCard creditCard, double amount, String content) throws NumberFormatException, InvalidEcoBikeInformationException {
		this.command = "refund";
		return this.deductMoney(creditCard, amount, content);
	}

	public PaymentTransaction payRental(CreditCard creditCard, double amount, String content) throws NumberFormatException, InvalidEcoBikeInformationException {
		this.command = "pay";
		return this.deductMoney(creditCard, amount, content);
	}

	private PaymentTransaction deductMoney(CreditCard creditCard, double amount, String content) throws NumberFormatException, InvalidEcoBikeInformationException {
		Map<String, Object> transaction = new MyMap();
		try {
			transaction.putAll(MyMap.toMyMap(creditCard));
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			throw new InvalidCardException("ERROR: Invalid card!");
		}
		transaction.put("command", command);
		transaction.put("cardCode", creditCard.getCardNumber());
		transaction.put("owner", Configs.OWNER);
		transaction.put("cvvCode", creditCard.getCardSecurity());
		transaction.put("issueBank", creditCard.getIssueBank());
		transaction.put("balance", creditCard.getBalance());
		transaction.put("transactionContent", content);
		transaction.put("amount", amount);
		transaction.put("createdAt", FunctionalUtils.getToday());
		transaction.put("dateExpired", creditCard.getExpirationDate().toString());

		Map<String, Object> dataForHash = new MyMap();
		dataForHash.put("secretKey", SECRET_KEY);
		dataForHash.put("transaction", transaction);
		String hashCode = FunctionalUtils.md5(generateData(dataForHash));


		Map<String, Object> requestMap = new MyMap();
		requestMap.put("version", VERSION);
		requestMap.put("transaction", transaction);
		requestMap.put("appCode", Configs.appCode);
		requestMap.put("hashCode", hashCode);

		String responseText = interbankBoundary.query(Configs.PROCESS_TRANSACTION_URL, generateData(requestMap));
		MyMap response = null;
		try {
			response = MyMap.toMyMap(responseText, 0);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			throw new InterbankUndefinedException();
		}

		return makePaymentTransaction(response);
	}
	
	/**
	 * Get transaction response and throw exception depend on the map
	 * @param response
	 * @return
	 * @throws InvalidEcoBikeInformationException 
	 * @throws NumberFormatException 
	 */
	private PaymentTransaction makePaymentTransaction(MyMap response) throws NumberFormatException, InvalidEcoBikeInformationException {
		if (response == null)
			return null;
		PaymentTransaction trans;
		if(response.get("transaction") != null) {
			MyMap transaction = (MyMap) response.get("transaction");
			
			trans = new PaymentTransaction((String) transaction.get("cardCode"), 
					Double.parseDouble((String) transaction.get("amount")), 
					(String) transaction.get("transactionContent"), 
					(String) transaction.get("createdAt"),
					(String) response.get("errorCode"));
			switch (trans.getErrorMessage()) {
				case "00":
				case "01":
				case "02":
				case "03":
				case "04":
				case "05":
				case "06":
				case "07":
					break;
				default:
					throw new InterbankUndefinedException();
			}
		}
		else {
			trans = new PaymentTransaction(response.get("errorCode").toString());
		}
		return trans;

	}
}
