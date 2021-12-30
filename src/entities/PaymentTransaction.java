package entities;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import exceptions.ecobike.InvalidEcoBikeInformationException;
import exceptions.interbank.InvalidCardException;
import utils.FunctionalUtils;

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
	private String creditCardNumber;
	
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
	
	public PaymentTransaction(String transactionId, String creditCardNumber, double amount, String content, String transactionTime) throws InvalidEcoBikeInformationException {
		this.setAmount(amount);
		this.setTransactionId(transactionId);
		this.setCreditCardNumber(creditCardNumber);
		this.setContent(content);
		this.setTransactionTime(transactionTime);
	}
	
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getCreditCardNumber() {
		return this.creditCardNumber;
	}
	public void setCreditCardNumber(String creditCardNumber) {
		if (creditCardNumber == null || creditCardNumber.length() == 0) {
			throw new InvalidCardException("card number must not be null");
		}
		
		if (FunctionalUtils.contains(creditCardNumber, "^[0-9 ]")) {
			throw new InvalidCardException("card number must not contains letters or special character");
		}
		this.creditCardNumber = creditCardNumber;
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
	public void setTransactionTime(String transactionTime) throws InvalidEcoBikeInformationException {
		try {
			this.transactionTime = FunctionalUtils.stringToTimeStamp(transactionTime);
		} catch (Exception e) {
			throw new InvalidEcoBikeInformationException("invalid date format");
		}
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