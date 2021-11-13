package entities;

import java.sql.Timestamp;

/**
 * This is the class for object entity Bike including information of a bike
 * @author Duong
 *
 */
public class Bike {
	/**
	 * name of the bike
	 */
	private String name;
	
	/**
	 * type of the bike
	 */
	private String bike_type;
	
	/**
	 * The link to the image of the bike
	 */
	private String bike_image;
	
	/**
	 * The unique bar-code of the bike 
	 */
	private String bar_code;
	
	/**
	 * The rental price of the bike per unit time
	 */
	private double bike_rental_price;
	
	/**
	 * The amount of deposit customers have to pay before renting the bike
	 */
	private double deposit;
	
	/**
	 * The currency of money the credit card uses
	 */
	private String currency;
	
	/**
	 * The time the bike was added to the dock in defined format
	 */
	private Timestamp create_date;
	
	/**
	 * The current status of the bike
	 */
	private int current_status;
	
	/**
	 * The current battery of the bike
	 */
	private int current_battery;
	
	/**
	 * The total time the customer has rent calculated in minute
	 */
	private int total_rent_time;

	public Bike() {
		
	}

	public Bike(String name, String bike_type, String bike_image, String bar_code, double bike_rental_price,
			double deposit, String currency, Timestamp create_date, int current_status, int current_battery,
			int total_rent_time) {
		super();
		this.name = name;
		this.bike_type = bike_type;
		this.bike_image = bike_image;
		this.bar_code = bar_code;
		this.bike_rental_price = bike_rental_price;
		this.deposit = deposit;
		this.currency = currency;
		this.create_date = create_date;
		this.current_status = current_status;
		this.current_battery = current_battery;
		this.total_rent_time = total_rent_time;
	}
	
	
}
