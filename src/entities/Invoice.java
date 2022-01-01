package entities;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
	private String invoiceID;
	
	/**
	 * The name of the bike customers rent
	 */
	private String bikeName;
	
	private ArrayList<PaymentTransaction> listTransaction;
	
	/**
	 * The amount of deposit customers have to pay before renting the bike
	 */
	private double deposit;
	
	/**
	 * The time customers begin to rent bike 
	 */
	private Timestamp startTime;
	
	/**
	 * The time customers return bike
	 */
	private Timestamp endTime;
	
	
	/**
	 * The sub-total money customers have to pay which is the value calculated based on the total rental time 
	 */
	private double subTotal;
	
	/**
	 * The total money customers have to pay including sub-total and VAT
	 */
	private double total;
	
	/**
	 * The time customers pay for rental bike in defined time format
	 */
	private Timestamp timeCreate;
	
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

	public Invoice(String invoiceID, String bikeName, double deposit, String startTime,
			String endTime, double subTotal, double total, String timeCreate) throws InvalidEcoBikeInformationException {
		listTransaction = new ArrayList<PaymentTransaction>();
		this.bikeName = bikeName;
		this.deposit = deposit;
		this.setStartTime(startTime);
		this.setEndTime(endTime);
		this.subTotal = subTotal;
		this.total = total;
		this.setTimeCreate(timeCreate);
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

	public String getBikeName() {
		return bikeName;
	}

	public double getDeposit() {
		return deposit;
	}

	public Timestamp getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) throws InvalidEcoBikeInformationException {
		try {
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");	
			Date date = dateFormat.parse(startTime);
			this.startTime = new Timestamp(date.getTime());
		} catch (Exception e) {
			throw new InvalidEcoBikeInformationException("invalid date format");
		}
	}

	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) throws InvalidEcoBikeInformationException {
		try {
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");	
			Date date = dateFormat.parse(endTime);
			Timestamp tmpEnd = new Timestamp(date.getTime());
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

	public double getsubTotal() {
		return subTotal;
	}

	public double getTotal() {
		return total;
	}

	public Timestamp getTimeCreate() {
		return timeCreate;
	}

	private void setTimeCreate(String timeCreate) throws InvalidEcoBikeInformationException {
		try {
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");	
			Date date = dateFormat.parse(timeCreate);
			this.timeCreate = new Timestamp(date.getTime());
		} catch (Exception e) {
			throw new InvalidEcoBikeInformationException("invalid date format");
		}
	}

	public void setInvoiceID(String string) {
		// TODO Auto-generated method stub
		
	}

	
}
