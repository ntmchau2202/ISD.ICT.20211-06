package controllers;

import exceptions.ecobike.EcoBikeException;
import exceptions.ecobike.EcoBikeUndefinedException;
import exceptions.ecobike.RentBikeException;
import interfaces.InterbankInterface;
import entities.Bike;
import entities.CreditCard;
import entities.PaymentTransaction;
import entities.TimeCounter;
import utils.*;
import views.screen.popup.PopupScreen;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;

/**
 * This class handles rent bike, return bike and pause bike rental request from customers
 */
public class RentBikeController extends EcoBikeBaseController {
	@SuppressWarnings("unused")
	private InterbankInterface interbankSystem;
	private TimeCounter timeCounter;
	private LocalTime startTime, stopTime;
	private int customerID, rentID, timeRented;
	private Bike currentBike;
	private static RentBikeController rentBikeServiceController;

	public static RentBikeController getRentBikeServiceController(InterbankInterface interbankSystem){
		if (rentBikeServiceController == null)
			rentBikeServiceController = new RentBikeController();
		if (interbankSystem != null) {
			rentBikeServiceController.interbankSystem = interbankSystem;
		}
		return rentBikeServiceController;
	}
	/**
	 * Initialize the controller for EcoBike rent bike service
	 */
	public RentBikeController() {
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
		
		// TODO: process the payment before updating the database;
		PaymentTransaction transaction = interbankSystem.payDeposit(card, bikeToRent.getDeposit(), "PAY_DEPOSIT");
		
		if (transaction == null) {
			return false;
		}
		System.out.println("Successfully performing transaction!");
		System.out.println("Transaction ID:"+transaction.getTransactionId());
		// save credit card information
		this.currentBike = bikeToRent;
		startCountingRentTime();
		System.out.println("Done invoking counter");
		DBUtils.saveCardInformation(card);
		
		// TODO: save transaction
		this.customerID = DBUtils.saveCustomer(card.getCardHolderName());
		this.rentID = DBUtils.createNewRentRecord(customerID);
		DBUtils.changeBikeStatus(this.currentBike.getBikeBarCode(), Configs.BIKE_STATUS.RENTED.toString());
		this.currentBike.setCurrentStatus(Configs.BIKE_STATUS.RENTED);
		return true;
	}
	
	/**
	 * Start pausing bike rental process
	 * @param bikeBarcode barCode of the bike to be rented
	 */
	public void pauseBikeRental() {
		this.timeCounter.stopCounter();
		this.currentBike.setCurrentStatus(Configs.BIKE_STATUS.PAUSED);
	}
	
	public void resumeBikeRental() {
		this.timeCounter.startCounter();
		this.currentBike.setCurrentStatus(Configs.BIKE_STATUS.RENTED);
	}
	
	/**
	 * Invoke counter for tracking rental time
	 */
	private void startCountingRentTime() {
		this.timeCounter = new TimeCounter();
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
	
	public boolean returnBike(Bike bikeToRent, CreditCard card) throws IOException, SQLException, EcoBikeException {
		float rentCost = calculateFee(bikeToRent.getBikeType(), this.timeRented);
		PaymentTransaction transaction = interbankSystem.payRental(card, rentCost, "PAY_RENTAL");
		
		if (transaction == null) {
			return false;
		}
		
		// TODO: save transaction
		// TODO: reset everything
		DBUtils.changeBikeStatus(bikeToRent.getBikeBarCode(), Configs.BIKE_STATUS.FREE.toString());
		this.currentBike.setCurrentStatus(Configs.BIKE_STATUS.FREE);
		return true;
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
}
