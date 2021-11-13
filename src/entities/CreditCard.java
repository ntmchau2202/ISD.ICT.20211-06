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
	private Timestamp expirationDate;
	
	/**
	 * The security code of the card
	 */
	private int cardSecurity;
	
	public CreditCard() {
		
	}
	public CreditCard(String cardNumber, String cardHolderName, String issueBank, Timestamp expirationDate,
			int cardSecurity) {
		super();
		this.cardNumber = cardNumber;
		this.cardHolderName = cardHolderName;
		this.issueBank = issueBank;
		this.expirationDate = expirationDate;
		this.cardSecurity = cardSecurity;
	}
	
}
