package boundaries;

import java.io.IOException;

import controllers.InterbankController;
import entities.CreditCard;
import entities.PaymentTransaction;
import exceptions.ecobike.InvalidEcoBikeInformationException;
import interfaces.InterbankInterface;

/**
 * This class implements functions for performing transactions on the bank side
 * It must have functions in InterbankInterface for further communication with the EcoBike subsystems
 */
public class InterbankBoundary implements InterbankInterface {
	private String bankName;
	private InterbankController interbankController; 
	
	public InterbankBoundary(String issuingBank) {
		super();
		this.bankName = issuingBank;
		interbankController = new InterbankController(); // should we need this?
	}

	@Override
	public PaymentTransaction payDeposit(CreditCard creditCard, double amount, String content) {
		PaymentTransaction transaction = null;
		try {
			transaction = interbankController.payDeposit(creditCard, amount, content);
		} catch (IOException | InvalidEcoBikeInformationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return transaction;
	}

	@Override
	public PaymentTransaction returnDeposit(CreditCard creditCard, double amount, String content) {
		PaymentTransaction transaction = null;
		try {
			transaction = interbankController.refund(creditCard, amount, content);
		} catch (IOException | InvalidEcoBikeInformationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return transaction;	
	}

	@Override
	public PaymentTransaction payRental(CreditCard creditCard, double amount, String content) {
		PaymentTransaction transaction = null;
		try {
			transaction = interbankController.payRental(creditCard, amount, content);
		} catch (IOException | InvalidEcoBikeInformationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return transaction;	
		
	}

	@Override
	public double getBalance(CreditCard creditCard) {
		return 0; // what is the api for getting balance?
	}

}
