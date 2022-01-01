package entities;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import utils.FunctionalUtils;
import exceptions.interbank.InvalidCardException;

/**
 * This is the class for object entity Credit card including all information of a card 
 * @author Duong
 *
 */
@SuppressWarnings("unused")
public class CreditCard {
	
	/**
	 * The valid number of the card 
	 */
	private String cardNumber;
	
	/**
	 * The name of the holder of the card
	 */
	private String cardHolderName;
	
	/**
	 * The bank responsible for the card
	 */
	private String issueBank;
	
	/**
	 * The expired date of the card in defined time format
	 */
	private Timestamp expirationDate;
	
	/**
	 * The security code of the card
	 */
	private String cardSecurity;
	
	public CreditCard(String cardNumber, String cardHolderName, String issueBank, String expirationDate,
			String cardSecurity) {
		super();
		this.setCardNumber(cardNumber);
		this.setCardHolderName(cardHolderName);
		this.setIssueBank(issueBank);
		this.setCardSecurity(cardSecurity);
		this.setExpirationDate(expirationDate);
	}
	public String getCardNumber() {
		return cardNumber;
	}
	
	public void setCardNumber(String cardNumber) throws InvalidCardException {
		if (cardNumber == null || cardNumber.length() == 0) {
			throw new InvalidCardException("card number must not be null");
		}
		
		if (FunctionalUtils.contains(cardNumber, "^[0-9 ]")) {
			throw new InvalidCardException("card number must not contains letters or special character");
		}
		
		this.cardNumber = cardNumber;
	}
	public String getCardHolderName() {
		return cardHolderName;
	}
	public void setCardHolderName(String cardHolderName) {
		if (cardHolderName == null || cardHolderName.length() == 0) {
			throw new InvalidCardException("cardholder name must not be null");
		}
		
		if (FunctionalUtils.contains(cardHolderName, "^[a-zA-z ]")) {
			throw new InvalidCardException("card number must not contains digits or special characters");
		}
		this.cardHolderName = cardHolderName;
	}
	public String getIssueBank() {
		return issueBank;
	}
	public void setIssueBank(String issueBank) {
		if (issueBank == null || issueBank.length() == 0) {
			throw new InvalidCardException("issuing bank must not be null");
		}
		
		if (FunctionalUtils.contains(issueBank, "^[a-zA-z ]")) {
			throw new InvalidCardException("issuing bank must not contains digits or special characters");
		}
		this.issueBank = issueBank;
	}
	public String getExpirationDate() {
		return expirationDate.toString();
	}
	public void setExpirationDate(String expirationDate) {
		try {
			DateFormat dateFormat = new SimpleDateFormat("MM/yyyy");	
			Date date = dateFormat.parse(expirationDate);
			this.expirationDate = new Timestamp(date.getTime());
		} catch (Exception e) {
			throw new InvalidCardException("invalid expiration date");
		}
	}
	public String getCardSecurity() {
		return cardSecurity;
	}
	public void setCardSecurity(String cardSecurity) {
		this.cardSecurity = cardSecurity;
	}

}
