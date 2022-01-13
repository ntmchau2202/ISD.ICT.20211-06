package entities;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import exceptions.ecobike.InvalidEcoBikeInformationException;

/**
 * This is the class for object entity Invoice including all information of an invoice
 * @author Duong
 *
 */
public class Invoice {
	
	/**
	 * The id of the invoice
	 */
	private int invoiceID;
	
	private Date dateIssued;
	
	/**
	 * The barcode of the bike customers rent
	 */
	private Bike bike;
	
	private ArrayList<PaymentTransaction> listTransaction;
	
	/**
	 * The amount of deposit customers have to pay before renting the bike
	 */
	private double deposit;
	
	/**
	 * The time customers begin to rent bike 
	 */
	private Date startTime;
	
	/**
	 * The time customers return bike
	 */
	private Date endTime;
	
	private int totalRentTime;
	
	/**
	 * The total money customers have to pay including sub-total and VAT
	 */
	private double total;
	
	private int rentID;
	
	public int getRentID() {
		return rentID;
	}

	public void setRentID(int rentID) {
		this.rentID = rentID;
	}

	public Invoice() {
		listTransaction = new ArrayList<PaymentTransaction>();
	}

	public Invoice(int invoiceID, Bike bike, Date startTime, Date endTime, int totalRentTime) throws InvalidEcoBikeInformationException {
		listTransaction = new ArrayList<PaymentTransaction>();
		this.setInvoiceID(invoiceID);
		this.setBike(bike);
		this.setTotalRentTime(totalRentTime);
		this.dateIssued = Calendar.getInstance().getTime();
		this.rentID = -1;
	}
	
	public String getIssuedDate() {
		return this.dateIssued.toString();
	}
	
	private void setTotalRentTime(int time) {
		this.totalRentTime = time;
	}
	
	public int getTotalRentTime() {
		return this.totalRentTime;
	}
	
	private void setBike(Bike bike) {
		this.bike = bike;
	}
	
	public void addTransaction(PaymentTransaction transaction) {
		if (this.listTransaction.contains(transaction)) {
			System.out.println("Hey the transaction is duplicated!:" + transaction.getTransactionId());
			return;
		}
		this.listTransaction.add(transaction);
		if (this.rentID == -1) {
			this.rentID = transaction.getRentID();
		}
		this.setTotal();
	}
	
	public void removeTransaction(PaymentTransaction transaction) {
		if (this.listTransaction.contains(transaction)) {
			this.listTransaction.remove(transaction);				
		}
	}
	
	public ArrayList<PaymentTransaction> getTransactionList() {
		return this.listTransaction;
	}

	public int getInvoiceID() {
		return invoiceID;
	}

	public Bike getBike() {
		return this.bike;
	}

	public double getDeposit() {
		return deposit;
	}
	
	public void setDeposit(double deposit) {
		this.deposit = deposit;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) throws InvalidEcoBikeInformationException {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) throws InvalidEcoBikeInformationException {
		this.endTime = endTime;
	}

	public double getTotal() {
		return total;
	}

	private void setTotal() {
		this.total = 0;
		for (PaymentTransaction trans : this.listTransaction) {
			this.total += trans.getAmount();
		}
	}

	public void setInvoiceID(int invoiceID) {
		this.invoiceID = invoiceID;
	}
	
}
