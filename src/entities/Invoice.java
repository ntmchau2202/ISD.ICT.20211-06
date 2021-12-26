package entities;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import exceptions.ecobike.InvalidEcoBikeInformationException;
import utils.FunctionalUtils;

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
	 * The total time customers rent the bike calculated in minute
	 */
	private int totalRentTime;
	
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

	public Invoice(String invoiceID, String bikeName, double deposit, String startTime, String endTime, double subTotal, double total, String timeCreate) throws InvalidEcoBikeInformationException {
		listTransaction = new ArrayList<PaymentTransaction>();
		this.setBikeName(bikeName);
		this.setDeposit(deposit);
		this.setStartTime(startTime);
		this.setEndTime(endTime);
		this.setsubTotal(subTotal);
		this.setTotal(total);
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

	public void setInvoiceID(String invoiceID) {
		this.invoiceID = invoiceID;
	}

	public String getBikeName() {
		return bikeName;
	}

	public void setBikeName(String name) throws InvalidEcoBikeInformationException {
		if (name == null) {
			throw new InvalidEcoBikeInformationException("name parameter must not be null");
		}
		
		if (name.length() == 0) {
			throw new InvalidEcoBikeInformationException("bike must have a name");
		}
		
		if (!Character.isLetter(name.charAt(0))) {
			throw new InvalidEcoBikeInformationException("bike name must start with a letter");
		} 
		
		if (FunctionalUtils.contains(name, "[^a-z0-9 -_]")) {
			throw new InvalidEcoBikeInformationException("bike name can only contain letters, digits, space, hypen and underscore");
		}
		this.bikeName = name;
	}

	public double getDeposit() {
		return deposit;
	}

	public void setDeposit(double deposit) throws InvalidEcoBikeInformationException {
		if (deposit <= 0) {
			throw new InvalidEcoBikeInformationException("bike deposit price must be greater than 0");
		}
		this.deposit = deposit;
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
		return totalRentTime;
	}

	public void setTotalRentTime() {
		
		this.totalRentTime = (int)endTime.getTime()- (int)startTime.getTime();
	}

	public double getsubTotal() {
		return subTotal;
	}

	public void setsubTotal(double subTotal) throws InvalidEcoBikeInformationException {
		if (subTotal < 0) {
			throw new InvalidEcoBikeInformationException("subTotal must not be less than 0");
		}
		this.subTotal = subTotal;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) throws InvalidEcoBikeInformationException {
		if (total < 0) {
			throw new InvalidEcoBikeInformationException("total must not be less than 0");
		}
		this.total = total;
	}

	public Timestamp getTimeCreate() {
		return timeCreate;
	}

	public void setTimeCreate(String timeCreate) throws InvalidEcoBikeInformationException {
		try {
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");	
			Date date = dateFormat.parse(timeCreate);
			this.timeCreate = new Timestamp(date.getTime());
		} catch (Exception e) {
			throw new InvalidEcoBikeInformationException("invalid date format");
		}
	}

	
}
