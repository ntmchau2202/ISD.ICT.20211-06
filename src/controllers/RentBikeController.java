package controllers;

import exceptions.ecobike.EcoBikeException;
import exceptions.ecobike.EcoBikeUndefinedException;
import exceptions.ecobike.InvalidEcoBikeInformationException;
import exceptions.ecobike.RentBikeException;
import interfaces.InterbankInterface;
import entities.NormalBike;
import entities.Bike;
import entities.BikeTracker;
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
import java.util.HashMap;

/**
 * This class handles rent bike, return bike and pause bike rental request from customers
 */
public class RentBikeController extends EcoBikeBaseController {
	@SuppressWarnings("unused")
	private static int invoiceCounter = 1;
	private InterbankInterface interbankSystem;
	private static RentBikeController rentBikeServiceController;
	private HashMap<String, BikeTracker> listBikeTracker;
	
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
		this.listBikeTracker = new HashMap<String, BikeTracker>();
	}

	/**
	 * Start renting bike process, including calling the interbank subsystem for performing transaction
	 *
	 * @param bikeToRent barCode of the bike to be rented
	 * @throws IOException 
	 * @throws RentBikeException If the bike is not currently available, the barcode is not valid
	 * @throws EcoBikeUndefinedException If there is an unexpected error occurs during the renting process
	 */
	
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
			return false;
		}
		
		// TODO: save transaction
		int rentID = DBUtils.addStartRentBikeRecord(bikeToRent.getBikeBarCode());
		transaction.setRentID(rentID);
		DBUtils.removeBikeFromDock(bikeToRent.getBikeBarCode());
		bikeToRent.getOutOfDock();

		BikeTracker newTracker = new BikeTracker(bikeToRent, rentID);
		DBUtils.addTransaction(transaction, rentID);
		newTracker.addTransaction(transaction);		
		newTracker.startCountingRentTime();
		DBUtils.changeBikeStatus(bikeToRent.getBikeBarCode(), Configs.BIKE_STATUS.RENTED.toString());
		this.listBikeTracker.put(bikeToRent.getBikeBarCode(), newTracker);
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
	public int pauseBikeRental(Bike bike) {
		BikeTracker tracker = this.listBikeTracker.get(bike.getBikeBarCode());
		if (tracker == null) {
			try {
				tracker = DBUtils.getCurrentBikeRenting(bike);
			} catch (SQLException | EcoBikeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			System.out.println("Going to stop counting rent time");
			tracker.stopCountingRentTime();
			bike.setCurrentStatus(Configs.BIKE_STATUS.PAUSED);
		} catch (SQLException | EcoBikeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tracker.getRentedTime();
		
	}
	
	public void resumeBikeRental(Bike bike) {
		BikeTracker tracker = this.listBikeTracker.get(bike.getBikeBarCode());
		if (tracker == null) {
			try {
				int rentPeriod = DBUtils.getCurrentRentPeriodOfBike(bike.getBikeBarCode());
				if (rentPeriod != 0) {
					// retrieve bike renting record
					tracker = DBUtils.getCurrentBikeRenting(bike);
					tracker.resumeCountingRentTime();
				}
			} catch (SQLException | EcoBikeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			tracker.resumeCountingRentTime();
			bike.setCurrentStatus(Configs.BIKE_STATUS.RENTED);
		} catch (SQLException | EcoBikeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Invoke counter for tracking rental time
	 */

	
	public Invoice returnBike(Bike bikeToRent, Dock dockToReturn, CreditCard card) throws IOException, SQLException, EcoBikeException, ParseException {
		if (!checkCardIdentity(card)) {
			return null;
		}
		
		BikeTracker tracker = this.listBikeTracker.get(bikeToRent.getBikeBarCode());
		if (tracker == null) {
			tracker = DBUtils.getCurrentBikeRenting(bikeToRent);
		}
		
		int period = tracker.stopCountingRentTime();
		float rentCost = calculateFee(bikeToRent.getBikeType(), period);
		
		PaymentTransaction transaction = interbankSystem.payRental(card, rentCost, "PAY_RENTAL");
		
		if (transaction == null) {
			return null;
		}
		
		DBUtils.addEndRentBikeRecord(tracker.getRentID(), period);
		transaction.setRentID(tracker.getRentID());;
		DBUtils.addTransaction(transaction, tracker.getRentID());
		tracker.addTransaction(transaction);
		Invoice invoice = tracker.createInvoice(invoiceCounter);
		invoiceCounter++;
		DBUtils.changeBikeStatus(bikeToRent.getBikeBarCode(), Configs.BIKE_STATUS.FREE.toString());
		bikeToRent.goToDock(dockToReturn);
		DBUtils.addBikeToDock(bikeToRent.getBikeBarCode(), dockToReturn.getDockID());
		this.listBikeTracker.remove(bikeToRent.getBikeBarCode());
		return invoice;
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
	
	public float getRentalFee(Bike bike)  {
		BikeTracker tracker = this.listBikeTracker.get(bike.getBikeBarCode());
		if (tracker == null) {
			try {
				tracker = DBUtils.getCurrentBikeRenting(bike);
			} catch (SQLException | EcoBikeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return calculateFee(bike.getBikeType(), tracker.getRentedTime());
	}
}
