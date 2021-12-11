package boundaries;

import entities.CreditCard;
import entities.PaymentTransaction;
import interfaces.InterbankInterface;

/**
 * This class implements functions for performing transactions on the bank side
 * It must have functions in InterbankInterface for further communication with the EcoBike subsystems
 */
public class InterbankBoundary implements InterbankInterface {

	@Override
	public PaymentTransaction payDeposit(CreditCard creditCard, int amount, String content) {
		return null;
		// TODO Auto-generated method stub
		
	}

	@Override
	public PaymentTransaction returnDeposit(CreditCard creditCard, int amount, String content) {
		return null;
		// TODO Auto-generated method stub
		
	}

	@Override
	public PaymentTransaction payRental(CreditCard creditCard, int amount, String content) {
		return null;
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getBalance(CreditCard creditCard) {
		// TODO Auto-generated method stub
		
	}

}
