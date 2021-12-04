package entities;

import java.sql.Timestamp;

/**
 * This is the class for object entity Transaction including all information of a transaction
 * @author Duong
 *
 */
public class PaymentTransaction {
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
	
	public PaymentTransaction() {
		
	}
	public PaymentTransaction(String transactionId, CreditCard card, double amount, Timestamp transactionTime, String content) {
		super();
		this.transactionId = transactionId;
		this.card = card;
		this.amount = amount;
		this.transactionTime = transactionTime;
		this.content = content;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public CreditCard getCard() {
		return card;
	}
	public void setCard(CreditCard card) {
		this.card = card;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Timestamp getTransactionTime() {
		return transactionTime;
	}
	public void setTransactionTime(Timestamp transactionTime) {
		this.transactionTime = transactionTime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
}
