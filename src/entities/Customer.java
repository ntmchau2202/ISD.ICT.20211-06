package entities;

import java.util.ArrayList;

/**
 * This is the class for object entity Customer including information of the customer
 * @author Duong
 *
 */
public class Customer {
	
	/**
	 * The id of the customer
	 */
	private String customerID;
	
	/**
	 * The name of the customer
	 */
	private String customerName;
	
	public Customer() {
		
	}

	public Customer(String customerID, String customerName) {
		super();
		this.customerID = customerID;
		this.customerName = customerName;
	}
	
}
