package subsystem;

import entities.CreditCard;
import entities.PaymentTransaction;
import exceptions.ecobike.InvalidEcoBikeInformationException;

/**
 * This interface provides functions that the interbank subsystem must be implemented for its communication with other subsystem
 * @author chauntm
 *
 */
public interface InterbankInterface {
	/**
	 * Perform pay deposit operation on the given credit card, with the specified amount of money.
	 * <br>@param creditCard The credit card used for transaction
	 * <br>@param amount The amount of transaction
	 * <br>@param content Details about the transaction
	 * <br>@return PaymentTransaction  the transaction {@link entities.PaymentTransaction} 
	 * @throws InvalidEcoBikeInformationException 
	 * @throws NumberFormatException 
	 */
	public PaymentTransaction payDeposit(CreditCard creditCard, double amount, String content) throws NumberFormatException, InvalidEcoBikeInformationException;

	/**
	 * Perform return deposit operation on the given credit card, with the specified amount of money.
	 * <br>@param creditCard The credit card used for transaction
	 * <br>@param amount The amount of transaction
	 * <br>@param content Details about the transaction
	 * <br>@return PaymentTransaction  the transaction {@link entities.PaymentTransaction}
	 * @throws InvalidEcoBikeInformationException 
	 * @throws NumberFormatException 
	 */
	public PaymentTransaction returnDeposit(CreditCard creditCard, double amount, String content) throws NumberFormatException, InvalidEcoBikeInformationException;
	
	/**
	 * Perform pay rental operation on the given credit card, with the specified amount of money.
	 * <br>@param creditCard The credit card used for transaction
	 * <br>@param amount The amount of transaction
	 * <br>@param content Details about the transaction
	 * <br>@return PaymentTransaction  the transaction {@link entities.PaymentTransaction}
	 * @throws InvalidEcoBikeInformationException 
	 * @throws NumberFormatException 
	 */
	public PaymentTransaction payRental(CreditCard creditCard, double amount, String content) throws NumberFormatException, InvalidEcoBikeInformationException;
	
	/**
	 * Get current balance of a given credit card.
	 * <br>@param creditCard The card with the balance to be queried
	 * @return 
	 */
	public double getBalance(CreditCard creditCard);
}
