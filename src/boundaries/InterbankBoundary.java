package boundaries;

import entities.CreditCard;
import interfaces.InterbankInterface;

/**
 * This class implements functions for performing transactions on the bank side
 * It must have functions in InterbankInterface for further communication with the EcoBike subsystems
 */
public class InterbankBoundary implements InterbankInterface {

	@Override
	public void payDeposit(CreditCard creditCard, int amount, String content) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void returnDeposit(CreditCard creditCard, int amount, String content) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void payRental(CreditCard creditCard, int amount, String content) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getBalance(CreditCard creditCard) {
		// TODO Auto-generated method stub
		
	}

}
