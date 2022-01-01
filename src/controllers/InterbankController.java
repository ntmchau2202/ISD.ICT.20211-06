package controllers;

import java.io.IOException;

import entities.CreditCard;
import entities.RefreshAccountMessage;
import entities.TransactionMessage;
import utils.API;
import utils.Configs;

/**
 * This is the controller for performing transaction on the bank side
 * @author chauntm
 *
 */
public class InterbankController {	
	private static final String SECRET_KEY = "BQqfHMEQv+4=";
	private static final String APP_CODE = "raQdFQH5jg==";
	private static final String COMMAND_PAY = "pay";
	private static final String COMMAND_REFUND = "refund";
	private static final String VERSION = "1.0.1";
	
	public InterbankController() {
		super();
	}
	
	public void pay(CreditCard card, double amount, String content) throws IOException {
		TransactionMessage msgToSend = new TransactionMessage(VERSION, APP_CODE, COMMAND_PAY, card.getCardNumber(), card.getCardHolderName(), card.getCardSecurity(), card.getExpirationDate(), content, amount);
		String jsonMsg = msgToSend.toJSON();
		String result = API.patch(Configs.API_BASE_URL + Configs.API_TRANSACTION, jsonMsg);
		// TODO: Do some more things here to finish the transaction
	}
	
	public void refund(CreditCard card, double amount, String content) throws IOException {
		TransactionMessage msgToSend = new TransactionMessage(VERSION, APP_CODE, COMMAND_REFUND, card.getCardNumber(), card.getCardHolderName(), card.getCardSecurity(), card.getExpirationDate(), content, amount);
		String jsonMsg = msgToSend.toJSON();
		String result = API.patch(Configs.API_BASE_URL + Configs.API_TRANSACTION, jsonMsg);
		// TODO: Do some more things here to finish the transaction
	}
	
	public void resetBalance(CreditCard card) throws IOException {
		RefreshAccountMessage msgToSend = new RefreshAccountMessage(card.getCardNumber(), card.getCardHolderName(), card.getCardSecurity(), card.getExpirationDate());
		String jsonMsg = msgToSend.toJSON();
		String result = API.patch(Configs.API_BASE_URL + Configs.API_RESET_BALANCE, jsonMsg);
		// TODO: Do some more things here to finish the transaction
	}
}
