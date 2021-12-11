package entities;

import java.sql.Timestamp;
import java.util.ArrayList;

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
	private Timestamp start_time;
	
	/**
	 * The time customers return bike
	 */
	private Timestamp end_time;
	
	/**
	 * The total time customers rent the bike calculated in minute
	 */
	private int total_rent_time;
	
	/**
	 * The sub-total money customers have to pay which is the value calculated based on the total rental time 
	 */
	private double subtotal;
	
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

	public Invoice(String invoiceID, String bikeName, double deposit, Timestamp start_time, Timestamp end_time,
			int total_rent_time, double subtotal, double total, Timestamp timeCreate) {
		super();
		listTransaction = new ArrayList<PaymentTransaction>();
		this.invoiceID = invoiceID;
		this.bikeName = bikeName;
		this.deposit = deposit;
		this.start_time = start_time;
		this.end_time = end_time;
		this.total_rent_time = total_rent_time;
		this.subtotal = subtotal;
		this.total = total;
		this.timeCreate = timeCreate;
	}
	
	public void addTransaction(PaymentTransaction transaction) {
		this.listTransaction.add(transaction);
	}
	
	public void removeTransaction(PaymentTransaction transaction) {
		this.listTransaction.remove(transaction);
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

	public void setBikeName(String bikeName) {
		this.bikeName = bikeName;
	}

	public double getDeposit() {
		return deposit;
	}

	public void setDeposit(double deposit) {
		this.deposit = deposit;
	}

	public Timestamp getStart_time() {
		return start_time;
	}

	public void setStart_time(Timestamp start_time) {
		this.start_time = start_time;
	}

	public Timestamp getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Timestamp end_time) {
		this.end_time = end_time;
	}

	public int getTotal_rent_time() {
		return total_rent_time;
	}

	public void setTotal_rent_time(int total_rent_time) {
		this.total_rent_time = total_rent_time;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Timestamp getTimeCreate() {
		return timeCreate;
	}

	public void setTimeCreate(Timestamp timeCreate) {
		this.timeCreate = timeCreate;
	}

	
}
