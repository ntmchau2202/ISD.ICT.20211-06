package interfaces;

import entities.CreditCard;

/**
 * This interface provides functions that the interbank subsystem must be implemented for its communication with other subsystem
 * @author chauntm
 *
 */
public interface InterbankInterface {
	/**
	 * Perform pay deposit operation on the given credit card, with the specified amount of money
	 * @param creditCard The credit card used for transaction
	 * @param amount The amount of transaction
	 * @param content Details about the transaction
	 */
	public void payDeposit(CreditCard creditCard, int amount, String content);

	/**
	 * Perform return deposit operation on the given credit card, with the specified amount of money
	 * @param creditCard The credit card used for transaction
	 * @param amount The amount of transaction
	 * @param content Details about the transaction
	 */
	public void returnDeposit(CreditCard creditCard, int amount, String content);
	
	/**
	 * Perform pay rental operation on the given credit card, with the specified amount of money
	 * @param creditCard The credit card used for transaction
	 * @param amount The amount of transaction
	 * @param content Details about the transaction
	 */
	public void payRental(CreditCard creditCard, int amount, String content);
	
	/**
	 * Get current balance of a given credit card
	 * @param creditCard The card with the balance to be queried
	 */
	public void getBalance(CreditCard creditCard);
}
