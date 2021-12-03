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
	private String bikeType;
	
	/**
	 * The link to the image of the bike
	 */
	private String bikeImage;
	
	/**
	 * The unique bar-code of the bike 
	 */
	private String barCode;
	
	/**
	 * The rental price of the bike per unit time
	 */
	private double bikeRentalPrice;
	
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
	private Timestamp createDate;
	
	/**
	 * The current status of the bike
	 */
	private int currentStatus;
	
	/**
	 * The current battery of the bike
	 */
	private int currentBattery;
	
	/**
	 * The total time the customer has rent calculated in minute
	 */
	private int totalRentTime;

	public Bike() {
		
	}

	public Bike(String name, String bikeType, String bikeImage, String barCode, double bikeRentalPrice,
				double deposit, String currency, Timestamp createDate, int currentStatus, int currentBattery,
				int totalRentTime) {
		super();
		this.name = name;
		this.bikeType = bikeType;
		this.bikeImage = bikeImage;
		this.barCode = barCode;
		this.bikeRentalPrice = bikeRentalPrice;
		this.deposit = deposit;
		this.currency = currency;
		this.createDate = createDate;
		this.currentStatus = currentStatus;
		this.currentBattery = currentBattery;
		this.totalRentTime = totalRentTime;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBikeType() {
		return bikeType;
	}

	public void setBikeType(String bikeType) {
		this.bikeType = bikeType;
	}

	public String getBikeImage() {
		return bikeImage;
	}

	public void setBikeImage(String bikeImage) {
		this.bikeImage = bikeImage;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public double getBikeRentalPrice() {
		return bikeRentalPrice;
	}

	public void setBikeRentalPrice(double bikeRentalPrice) {
		this.bikeRentalPrice = bikeRentalPrice;
	}

	public double getDeposit() {
		return deposit;
	}

	public void setDeposit(double deposit) {
		this.deposit = deposit;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public int getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(int currentStatus) {
		this.currentStatus = currentStatus;
	}

	public int getCurrentBattery() {
		return currentBattery;
	}

	public void setCurrentBattery(int currentBattery) {
		this.currentBattery = currentBattery;
	}

	public int getTotalRentTime() {
		return totalRentTime;
	}

	public void setTotalRentTime(int totalRentTime) {
		this.totalRentTime = totalRentTime;
	}
}
