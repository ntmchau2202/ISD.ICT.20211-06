package entities;

import java.sql.Timestamp;

/**
 * This is the class for object entity Transaction including all information of a transaction
 * @author Duong
 *
 */
public class Transaction {
	/**
	 * The id of transaction. This must be a unique String
	 */
	private String transactionId;
	
	/**
	 * The Credit card entity including all information about the credit card used to pay
	 * {@link entity.CreditCard}
	 */
	private CreditCard card;
	
	/**
	 * A double value describes the amount of money to make a transaction. The currency is VND
	 */
	private double amount;
	
	/**
	 * The time transaction is made with the a defined format
	 */
	private Timestamp transactionTime;
	
	/**
	 * The content of the transaction
	 */
	private String content;
	
	/**
	 * The message describes the error if there is any exception
	 */
	private String errorMessage;
	
	public Transaction() {
		
	}
	public Transaction(String transactionId, CreditCard card, double amount, Timestamp transactionTime, String content,
			String errorMessage) {
		super();
		this.transactionId = transactionId;
		this.card = card;
		this.amount = amount;
		this.transactionTime = transactionTime;
		this.content = content;
		this.errorMessage = errorMessage;
	}
	
}
