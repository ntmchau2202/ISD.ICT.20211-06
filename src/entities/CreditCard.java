package entities;

import java.sql.Timestamp;

/**
 * This is the class for object entity Credit card including all information of a card 
 * @author Duong
 *
 */
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
	private String expirationDate;
	
	/**
	 * The security code of the card
	 */
	private String cardSecurity;
	
	public CreditCard(String cardNumber, String cardHolderName, String issueBank, String expirationDate,
			String cardSecurity) {
		super();
		this.cardNumber = cardNumber;
		this.cardHolderName = cardHolderName;
		this.issueBank = issueBank;
		this.expirationDate = expirationDate;
		this.cardSecurity = cardSecurity;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getCardHolderName() {
		return cardHolderName;
	}
	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}
	public String getIssueBank() {
		return issueBank;
	}
	public void setIssueBank(String issueBank) {
		this.issueBank = issueBank;
	}
	public String getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}
	public String getCardSecurity() {
		return cardSecurity;
	}
	public void setCardSecurity(String cardSecurity) {
		this.cardSecurity = cardSecurity;
	}
	
}
