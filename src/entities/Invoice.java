package entities;

import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import exceptions.ecobike.EcoBikeException;
import exceptions.ecobike.InvalidEcoBikeInformationException;
import utils.DBUtils;

/**
 * This is the class for object entity Invoice including all information of an invoice
 * @author Duong
 *
 */
public class Invoice {
	
	/**
	 * The id of the invoice
	 */
	private String invoiceID;
	
	/**
	 * The barcode of the bike customers rent
	 */
	private String bikeBarCode;
	
	private ArrayList<PaymentTransaction> listTransaction;
	
	/**
	 * The amount of deposit customers have to pay before renting the bike
	 */
	private double deposit;
	
	/**
	 * The time customers begin to rent bike 
	 */
	private Time startTime;
	
	/**
	 * The time customers return bike
	 */
	private Time endTime;
	
	/**
	 * The total money customers have to pay including sub-total and VAT
	 */
	private double total;
	
	private int rentID;
	
	private Time timeCreate;
	
	public int getRentID() {
		return rentID;
	}

	public void setRentID(int rentID) {
		this.rentID = rentID;
	}

	public Invoice() {
		listTransaction = new ArrayList<PaymentTransaction>();
	}

	public Invoice(String invoiceID, String bikeBarCode, String startTime,
			String endTime) throws InvalidEcoBikeInformationException {
		listTransaction = new ArrayList<PaymentTransaction>();
		this.bikeBarCode = bikeBarCode;
		this.setStartTime(startTime);
		this.setEndTime(endTime);
	}
	
	public void addTransaction(PaymentTransaction transaction) {
		if (this.listTransaction.contains(transaction)) {
			return;
		}
		this.listTransaction.add(transaction);
	}
	
	public void removeTransaction(PaymentTransaction transaction) {
		if (this.listTransaction.contains(transaction)) {
			this.listTransaction.remove(transaction);			
		}
	}
	
	public ArrayList<PaymentTransaction> getTransactionList() {
		return this.listTransaction;
	}

	public String getInvoiceID() {
		return invoiceID;
	}

	public String getBikeBarcode() {
		return bikeBarCode;
	}

	public double getDeposit() {
		return deposit;
	}
	
	public void setDeposit(double deposit) {
		this.deposit = deposit;
	}

	public Time getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) throws InvalidEcoBikeInformationException {
		try {
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");	
			Date date = dateFormat.parse(startTime);
			this.startTime = new Time(date.getTime());
		} catch (Exception e) {
			throw new InvalidEcoBikeInformationException("invalid date format");
		}
	}

	public Time getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) throws InvalidEcoBikeInformationException {
		try {
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");	
			Date date = dateFormat.parse(endTime);
			Time tmpEnd = new Time(date.getTime());
			if (tmpEnd.getTime() < this.startTime.getTime()) {
				throw new InvalidEcoBikeInformationException("end time must be later than start time");
			}
			this.endTime = tmpEnd;
		} catch (Exception e) {
			throw new InvalidEcoBikeInformationException("invalid date format");
		}
	}

	public int getTotalRentTime() {
		
		return (int)endTime.getTime()- (int)startTime.getTime();
	}


	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public void setInvoiceID(String invoiceID) {
		this.invoiceID = invoiceID;
	}

	public Time getTimeCreate() {
		return timeCreate;
	}

	public void setTimeCreate(Time timeCreate) {
		this.timeCreate = timeCreate;
	}
	
	public String getBikeName() throws EcoBikeException, SQLException {
		Bike bike = DBUtils.getBikeByBarcode(bikeBarCode);
		return bike.getName();
	}
}
