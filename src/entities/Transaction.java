package entities;

import java.sql.Timestamp;

/**
 * This class stores informaton about the transaction for using in the interbank
 */

public class Transaction {
	/**
	 * ID of the transaction
	 */
	private String transactionId;
	private CreditCard card;
	private double amount;
	private Timestamp transactionTime;
	private String content;
	private String errorMessage;
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
