package controllers;

import entities.CreditCard;
import entities.Transaction;
import exceptions.ecobike.EcoBikeUndefinedException;
import exceptions.ecobike.RentBikeException;

public class PaymentController {
	
	public PaymentController() {
		
	}
	
	/**
	 * Pay for the deposit for the rental bike
	 * @param card  the credit card used to pay
	 * @param amount  the money to pay deposit
	 * @param content  the content of transaction
	 * @throws RentBikeException If the transaction is invalid
	 * @throws EcoBikeUndefinedException If there is an unexpected error occurs during the renting process
	 * @return {@link entities.Transaction} a record of transaction
	 */
	public Transaction payDeposit(CreditCard card, double amount, String content) throws RentBikeException, EcoBikeUndefinedException {
		return null;
	}
	
	/**
	 * Pay for the for the rental bike
	 * @param card  the credit card used to pay
	 * @param amount  the money to pay deposit
	 * @param content  the content of transaction
	 * @throws RentBikeException If the transaction is invalid
	 * @throws EcoBikeUndefinedException If there is an unexpected error occurs during the renting process
	 * @return {@link entities.Transaction} a record of transaction
	 */
	public void payRental(CreditCard card, double amount, String content) throws RentBikeException, EcoBikeUndefinedException {
		
	}
	
	/**
	 * Request to update payment method
	 * @param card  The credit card used to pay
	 * @throws RentBikeException If the card is invalid
	 * @throws EcoBikeUndefinedException If there is an unexpected error occurs during the renting process
	 */
	public void requestToUpdatePaymentMethod(CreditCard card) throws RentBikeException, EcoBikeUndefinedException {
		
	}
	
	/**
	 * Display a screen including transaction information
	 * @param transaction  The transaction {@link entities.Transaction}
	 * @throws RentBikeException If the bike is not currently available or the barcode is not valid
	 * @throws EcoBikeUndefinedException If there is an unexpected error occurs during the renting process
	 */
	public void displayTransactionInfo(Transaction transaction) throws EcoBikeUndefinedException {
		
	}
	
	/**
	 * save the transaction
	 * @param transaction  The transaction {@link entities.Transaction}
	 * @throws RentBikeException If the transaction is invalid
	 * @throws EcoBikeUndefinedException If there is an unexpected error occurs during the renting process
	 */
	public void saveTransaction(Transaction transaction) throws RentBikeException, EcoBikeUndefinedException {
		
	}
}
