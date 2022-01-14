package controllers;

import exceptions.ecobike.EcoBikeException;
import exceptions.ecobike.EcoBikeUndefinedException;
import exceptions.ecobike.InvalidEcoBikeInformationException;
import exceptions.ecobike.RentBikeException;
import interfaces.InterbankInterface;
import entities.NormalBike;
import entities.Bike;
import entities.CreditCard;
import entities.Dock;
import entities.Invoice;
import entities.PaymentTransaction;
import entities.TimeCounter;
import utils.*;
import views.screen.popup.PopupScreen;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

/**
 * This class handles rent bike, return bike and pause bike rental request from customers
 */
public class RentBikeController extends EcoBikeBaseController {
	@SuppressWarnings("unused")
	private static int invoiceCounter = 1;
	private InterbankInterface interbankSystem;
	private static RentBikeController rentBikeServiceController;
//	private TimeCounter timeCounter; // should make an array of rent counter here? 
//	/**
//	 * BikeTracker
//	 * - TimeCounter:
//	 * - thread
//	 * - bike
//	 * - start time, stop time
//	 * - rent id, time rented
//	 * - transaction list
//	 */
//	private Date startTime, stopTime;
//	private int rentID, timeRented;
//	private Bike currentBike;
//	private Invoice invoice;
//	private ArrayList<PaymentTransaction> transactionList;
//	private boolean isAllowed;

	public static RentBikeController getRentBikeServiceController(InterbankInterface interbankSystem){
		if (rentBikeServiceController == null) {
			rentBikeServiceController = new RentBikeController();
			rentBikeServiceController.transactionList = new ArrayList<PaymentTransaction>();
			rentBikeServiceController.isAllowed = true;
		}
		if (interbankSystem != null) {
			rentBikeServiceController.interbankSystem = interbankSystem;
		}
		return rentBikeServiceController;
	}
	/**
	 * Initialize the controller for EcoBike rent bike service
	 */
	private RentBikeController() {
		this.timeCounter = new TimeCounter();
	}

	/**
	 * Start renting bike process, including calling the interbank subsystem for performing transaction
	 *
	 * @param bikeToRent barCode of the bike to be rented
	 * @throws IOException 
	 * @throws RentBikeException If the bike is not currently available, the barcode is not valid
	 * @throws EcoBikeUndefinedException If there is an unexpected error occurs during the renting process
	 */
	
	public boolean isAllowedToRent() {
		return this.isAllowed;
	}
	
	public boolean rentBike(Bike bikeToRent, CreditCard card) throws EcoBikeException, SQLException, IOException {
		
		// NOTE: Since we cannot connect to the service
		// We will immediately check the card here
		// For java 11 and bellow, please uncomment this
		if (!checkCardIdentity(card)) {
			return false;
		}
		
		// TODO: process the payment before updating the database;
		PaymentTransaction transaction = interbankSystem.payDeposit(card, bikeToRent.getDeposit(), "PAY_DEPOSIT");
		
		if (transaction == null) {
			System.out.println("null transaction");
			return false;
		}
		System.out.println("Successfully performing transaction!");
		System.out.println("Transaction ID:"+transaction.getTransactionId());
		this.currentBike = bikeToRent;
		startCountingRentTime();
		System.out.println("Done invoking counter");
		
		// TODO: save transaction
		this.rentID = DBUtils.addStartRentBikeRecord(this.currentBike.getBikeBarCode());
		transaction.setRentID(this.rentID);
		DBUtils.addTransaction(transaction, rentID);
		this.transactionList.add(transaction);
		
		System.out.println("Added transaction for rent bike:"+ transaction.getTransactionId());
		System.out.println("Transaction list size:"+ transactionList.size());
		DBUtils.changeBikeStatus(this.currentBike.getBikeBarCode(), Configs.BIKE_STATUS.RENTED.toString());
		DBUtils.removeBikeFromDock(this.currentBike.getBikeBarCode());
		this.currentBike.getOutOfDock();
		this.isAllowed = false;
		return true;
	}
	
	private boolean checkCardIdentity(CreditCard card) {
		/**
		 * ict_group6_2021
		 * Group 6
		 * 936
		 * 11/25
		 */
		if (!card.getCardHolderName().equalsIgnoreCase("Group 6")) {
			System.out.println(card.getCardHolderName());
			System.out.println("Name failed");
			return false;
		}
		
		if (!card.getCardNumber().equalsIgnoreCase("ict_group6_2021")) {
			System.out.println("card number failed");
			return false;
		}
		
		if (!card.getCardSecurity().equalsIgnoreCase("936")) {
			System.out.println("cvv failed");
			return false;
		}
		
		if (!card.getExpirationDate().equalsIgnoreCase("11/25")) {
			System.out.println("expiration date failed");
			return false;
		}
		System.out.println("Everything's ok");
		return true;
	}
	
	/**
	 * Start pausing bike rental process
	 * @param bikeBarcode barCode of the bike to be rented
	 */
	public void pauseBikeRental(Bike bike) {
		if (this.currentBike != null) {
			// we are currently couting
			Date pauseTime = this.timeCounter.stopCounter();
			this.currentBike.setCurrentStatus(Configs.BIKE_STATUS.PAUSED);
			// do a little calculation
			long period =  (this.startTime.getTime() - pauseTime.getTime() / (1000 * 60)) % 60;
			
			// save period to database;
		} else {
			// retrieve start time from database
			// take current time
			// do a minus
			// save to database;
			// remember to save the bike to currentBike
		}
	}
	
	public void resumeBikeRental() {
		/**
		 * if currentBike is not null
		 * start counter again and set current status
		 * else, retrieve rented period from database
		 * set it to time rented 
		 * and continue counting
		 * remember to save the bike to currentBike
		 */
		this.timeCounter.startCounter();
		this.currentBike.setCurrentStatus(Configs.BIKE_STATUS.RENTED);
	}
	
	/**
	 * Invoke counter for tracking rental time
	 */
	private void startCountingRentTime() {
		Thread counterThread = new Thread(timeCounter);
		this.startTime = timeCounter.startCounter();
		counterThread.start();
	}
	
	public int stopCountingRentTime() {
		this.stopTime = timeCounter.stopCounter();
		calculateFee(this.currentBike.getBikeType(), this.timeRented);
		return timeCounter.getCountedTime();
	}

	public float getRentalFee() {
		return calculateFee(this.currentBike.getBikeType(), this.timeRented);
	}
	
	public boolean returnBike(Bike bikeToRent, Dock dockToReturn, CreditCard card) throws IOException, SQLException, EcoBikeException, ParseException {
		if (!checkCardIdentity(card)) {
			return false;
		}
		
		float rentCost = calculateFee(bikeToRent.getBikeType(), this.timeRented);
		PaymentTransaction transaction = interbankSystem.payRental(card, rentCost, "PAY_RENTAL");
		
		if (transaction == null) {
			return false;
		}
		
		DBUtils.addEndRentBikeRecord(rentID, timeCounter.getCountedTime());
		transaction.setRentID(this.rentID);;
		DBUtils.addTransaction(transaction, rentID);
		this.transactionList.add(transaction);
		System.out.println("Added transaction for rent bike:"+ transaction.getTransactionId());
		System.out.println("Transaction list size:"+ transactionList.size());
		this.createInvoice();
		DBUtils.addInvoice(invoice);
		DBUtils.changeBikeStatus(bikeToRent.getBikeBarCode(), Configs.BIKE_STATUS.FREE.toString());
		this.currentBike.goToDock(dockToReturn); 
		this.reset();
		return true;
	}
	
	private void createInvoice() throws InvalidEcoBikeInformationException {
		
		this.invoice = new Invoice(invoiceCounter, this.currentBike, this.startTime, this.stopTime, timeCounter.getCountedTime());
		for (PaymentTransaction trans : this.transactionList) {
			this.invoice.addTransaction(trans);
		}
		invoiceCounter++;
	}
	
	public Invoice getInvoice() {
		return this.invoice;
	}
	
	private float calculateFee(String bikeType, int rentTime) {
		//renting cost
		float rentingCost = rentTime <= Configs.freeOfChargeTimeInMinute
				? 0
				: rentTime - Configs.firstChargeTimeIntervalInMinute > 0
					? (Configs.firstChargeTimeIntervalCost + (float)Math.ceil((rentTime - Configs.firstChargeTimeIntervalInMinute)/ Configs.chargeTimeIntervalInMinute) * Configs.chargeTimeIntervalCost)
					: Configs.firstChargeTimeIntervalCost;

		rentingCost *= Configs.chargeMultiplierDictionary.get(Configs.getBikeType(bikeType));
		return rentingCost;
	}
	
	private void reset() {
//		this.customerID = 0;
		this.rentID = -1;
		this.startTime = null;
		this.stopTime = null;
		this.transactionList.clear();
		this.currentBike = null;
		this.timeCounter.resetCounter();
		this.isAllowed = true;
	}
}
