package controllers;

import java.io.IOException;

import entities.CreditCard;
import entities.PaymentTransaction;
import entities.RefreshAccountMessage;
import entities.RefreshAccountResponse;
import entities.TransactionMessage;
import entities.TransactionResponse;
import exceptions.ecobike.InvalidEcoBikeInformationException;
import exceptions.interbank.InterbankException;
import exceptions.interbank.InterbankUndefinedException;
import exceptions.interbank.InternalServerException;
import exceptions.interbank.InvalidCardException;
import exceptions.interbank.InvalidTransactionAmountException;
import exceptions.interbank.NotEnoughBalanceException;
import exceptions.interbank.NotEnoughInformationException;
import exceptions.interbank.SuspectTransactionException;
import exceptions.interbank.VersionMissingException;
import utils.API;
import utils.Configs;

/**
 * This is the controller for performing transaction on the bank side
 * @author chauntm
 *
 */
public class InterbankController {	
	private static final String APP_CODE = "raQdFQH5jg==";
	private static final String COMMAND_PAY = "pay";
	private static final String COMMAND_REFUND = "refund";
	private static final String VERSION = "1.0.1";
	
	public InterbankController() {
		super();
	}
	
	public PaymentTransaction payRental(CreditCard card, double amount, String content) throws IOException, InvalidEcoBikeInformationException {
		// TODO: this is the original code; however, the API is having some problems, so we use a mock instead for testing
//		TransactionMessage msgToSend = new TransactionMessage(VERSION, APP_CODE, COMMAND_PAY, card.getCardNumber(), card.getCardHolderName(), card.getCardSecurity(), card.getExpirationDate(), content, amount);
//		String jsonMsg = msgToSend.toJSONString();
//		String result = API.patch(Configs.API_BASE_URL + Configs.API_TRANSACTION, jsonMsg);
//		if (result == null & result.length() == 0) {
//			throw handlingExceptionCode(-1);
//		}
//		
//		TransactionResponse tranResp = new TransactionResponse(result);
//		InterbankException excpt = handlingExceptionCode(tranResp.getErrorCode());
//		if (excpt != null) {
//			throw excpt;
//		}
//		PaymentTransaction transaction = new PaymentTransaction(tranResp.getTransactionID(), tranResp.getCardCode(), tranResp.getAmount(), tranResp.getTransactionContent());
		
		// this is for testing, since the api is daed
		PaymentTransaction transaction = new PaymentTransaction("123", "ict_group6_2021", amount, content);
		return transaction;
	}
	
	public PaymentTransaction payDeposit(CreditCard card, double amount, String content) throws IOException, InvalidEcoBikeInformationException {
		// TODO: this is the original code; however, the API is having some problems, so we use a mock instead for testing
//		TransactionMessage msgToSend = new TransactionMessage(VERSION, APP_CODE, COMMAND_PAY, card.getCardNumber(), card.getCardHolderName(), card.getCardSecurity(), card.getExpirationDate(), content, amount);
//		String jsonMsg = msgToSend.toJSONString();
//		String result = API.patch(Configs.API_BASE_URL + Configs.API_TRANSACTION, jsonMsg);
//		if (result == null & result.length() == 0) {
//			throw handlingExceptionCode(-1);
//		}
//		
//		TransactionResponse tranResp = new TransactionResponse(result);
//		InterbankException excpt = handlingExceptionCode(tranResp.getErrorCode());
//		if (excpt != null) {
//			throw excpt;
//		}
//		PaymentTransaction transaction = new PaymentTransaction(tranResp.getTransactionID(), tranResp.getCardCode(), tranResp.getAmount(), tranResp.getTransactionContent());
		PaymentTransaction transaction = new PaymentTransaction("123", "ict_group6_2021", amount, content);
		return transaction;
	}
	
	public PaymentTransaction refund(CreditCard card, double amount, String content) throws IOException, InvalidEcoBikeInformationException {
//		TransactionMessage msgToSend = new TransactionMessage(VERSION, APP_CODE, COMMAND_REFUND, card.getCardNumber(), card.getCardHolderName(), card.getCardSecurity(), card.getExpirationDate(), content, amount);
//		String jsonMsg = msgToSend.toJSONString();
//		String result = API.patch(Configs.API_BASE_URL + Configs.API_TRANSACTION, jsonMsg);
//		if (result == null & result.length() == 0) {
//			throw handlingExceptionCode(-1);
//		}
//		
//		TransactionResponse tranResp = new TransactionResponse(result);
//		InterbankException excpt = handlingExceptionCode(tranResp.getErrorCode());
//		if (excpt != null) {
//			throw excpt;
//		}
//		PaymentTransaction transaction = new PaymentTransaction(tranResp.getTransactionID(), tranResp.getCardCode(), tranResp.getAmount(), tranResp.getTransactionContent());
		PaymentTransaction transaction = new PaymentTransaction("123", "ict_group6_2021", amount, content);
		return transaction;
	}
	
	public void resetBalance(CreditCard card) throws IOException, InterbankException {
		RefreshAccountMessage msgToSend = new RefreshAccountMessage(card.getCardNumber(), card.getCardHolderName(), card.getCardSecurity(), card.getExpirationDate());
		String jsonMsg = msgToSend.toJSONString();
		String result = API.patch(Configs.API_BASE_URL + Configs.API_RESET_BALANCE, jsonMsg);
		if (result == null & result.length() == 0) {
			throw handlingExceptionCode(-1);
		}
		RefreshAccountResponse resp = new RefreshAccountResponse(result);
		InterbankException excpt = handlingExceptionCode(resp.getErrorCode());
		if (excpt != null) {
			throw excpt;
		}
	}
	
	private InterbankException handlingExceptionCode(int exceptionCode) {
		switch(exceptionCode) {
			case 0: {
				return null;
			}
			case 1: {
				return new InvalidCardException("Invalid card for performing transaction");
			}
			case 2: {
				return new NotEnoughBalanceException("Current card does not have enough balance for performing transaction");
			}
			case 3: {
				return new InternalServerException("An unexpected error occured from the server side. Please try again");
			}
			case 4: {
				return new SuspectTransactionException("Invalid transaction");
			}
			case 5: {
				return new NotEnoughInformationException("Not enough information for performing transaction");
			}
			case 6: {
				return new VersionMissingException("API version missing");
			}
			case 7: {
				return new InvalidTransactionAmountException("Invalid transaction amount");
			}
			default: {
				return new InterbankUndefinedException("Undefined error occurred. Please try again later");
			}
		}
	}

}