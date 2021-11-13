package entities;

import java.sql.Timestamp;

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
	
	public Invoice() {
		
	}

	public Invoice(String invoiceID, String bikeName, double deposit, Timestamp start_time, Timestamp end_time,
			int total_rent_time, double subtotal, double total, Timestamp timeCreate) {
		super();
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

	
}
