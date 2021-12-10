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
	
	private String customerEmail;
	
	public Customer() {
		
	}

	public Customer(String customerID, String customerName, String customerEmail) {
		super();
		this.customerID = customerID;
		this.customerName = customerName;
		this.customerEmail = customerEmail;
	}

	public String getCustomerID() {
		return customerID;
	}

	public String getCustomerName() {
		return customerName;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}
	
	
	
}
