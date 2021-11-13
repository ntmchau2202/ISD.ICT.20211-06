package entities;

import java.sql.Timestamp;

public class CreditCard {
	private String cardNumber;
	private String cardHolderName;
	private String issueBank;
	private Timestamp expirationDate;
	private int cardSecurity;
	public CreditCard(String cardNumber, String cardHolderName, String issueBank, Timestamp expirationDate,
			int cardSecurity) {
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
	public Timestamp getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(Timestamp expirationDate) {
		this.expirationDate = expirationDate;
	}
	public int getCardSecurity() {
		return cardSecurity;
	}
	public void setCardSecurity(int cardSecurity) {
		this.cardSecurity = cardSecurity;
	}
	
}
