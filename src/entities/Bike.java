package entities;

import java.text.DateFormat; 
import java.text.SimpleDateFormat;

import org.json.JSONException;

import java.sql.Date;
import exceptions.ecobike.InvalidEcoBikeInformationException;
import utils.Configs;
import utils.FunctionalUtils;
import utils.JSONUtils;

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
	 * type of the bike.
	 */
	private String bikeType;
	
	/**
	 * Plate code of the bike.
	 */
	private String licensePlateCode;
	
	/**
	 * The link to the image of the bike.
	 */
	private String bikeImage;
	
	/**
	 * The unique bar-code of the bike.
	 */
	private String bikeBarcode;
	
	/**
	 * The rental price of the bike per unit time.
	 */
	private double bikeRentalPrice;
	
	/**
	 * The currency of money the credit card uses.
	 */
	private String currencyUnit;
	
	/**
	 * The amount of deposit customers have to pay before renting the bike.
	 */
	private double deposit;
	
	
	/**
	 * The time the bike was added to the dock in defined format.
	 */
//	private Date createDate;
	private String createDate;
	
	/**
	 * creator of the bike.
	 */
	private String creator;
	
	/**
	 * The current status of the bike
	 */
	private Configs.BIKE_STATUS currentStatus;
	
	/**
	 * The current battery of the bike
	 */
	private float currentBattery;
	
	/**
	 * The total time the customer has rent calculated in minute
	 */
	private int totalRentTime;
	
	private int dockId;
	
	public Bike() {
		
	}

	public Bike(String name, String bikeType, String licensePlateCode, String bikeImage, 
			String bikeBarcode, double bikeRentalPrice, String currencyUnit, double deposit, 
			String createDate) throws InvalidEcoBikeInformationException {
		super();
		this.setName(name);
		this.setBikeType(bikeType);
		this.setLicensePlateCode(licensePlateCode);
		this.setBikeImage(bikeImage);
		this.setBikeBarCode(bikeBarcode);
		this.setBikeRentalPrice(bikeRentalPrice);
		this.setCurrency(currencyUnit);
		this.setDeposit(deposit);
		this.setCreateDate(createDate);
	}


	public String getName() {
		return name;
	}

	private void setName(String name) throws InvalidEcoBikeInformationException {
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
		this.name = name;
	}

	public String getBikeType() {
		return bikeType;
	}

	private void setBikeType(String bikeType) throws InvalidEcoBikeInformationException {
		if(bikeType == null) {
			throw new InvalidEcoBikeInformationException("bike type parameter must not be null");
		}
		
		if(bikeType.length() == 0) {
			throw new InvalidEcoBikeInformationException("a bike must have type information");
		}
		
		if (!Character.isLetter(bikeType.charAt(0))) {
			throw new InvalidEcoBikeInformationException("bike type must start with a letter");
		}
		
		if (FunctionalUtils.contains(bikeType, "[^a-z0-9 -_]")) {
			throw new InvalidEcoBikeInformationException("bike type can only contain letters, digits, space, hypen and underscore");
		}
		
		this.bikeType = bikeType;
	}
	

	public String getLicensePlateCode() {
		return licensePlateCode;
	}

	private void setLicensePlateCode(String licensePlateCode) {
		this.licensePlateCode = licensePlateCode;
	}

	public String getBikeImage() {
		return bikeImage;
	}

	private void setBikeImage(String bikeImage) {
		this.bikeImage = bikeImage;
	}

	public String getBikeBarCode() {
		return bikeBarcode;
	}

	private void setBikeBarCode(String barCode) throws InvalidEcoBikeInformationException {
		if (String.valueOf(barCode) == null) {
			throw new InvalidEcoBikeInformationException("bike barcode must not be null");
		}
		
		if (String.valueOf(barCode).length() == 0) {
			throw new InvalidEcoBikeInformationException("bike barcode must not be empty");
		}
		this.bikeBarcode = barCode;
	}

	public double getBikeRentalPrice() {
		return bikeRentalPrice;
	}

	private void setBikeRentalPrice(double bikeRentalPrice) throws InvalidEcoBikeInformationException {
		if(bikeRentalPrice <= 0) {
			throw new InvalidEcoBikeInformationException("bike rental price must be greater than 0");
		}
		this.bikeRentalPrice = bikeRentalPrice;
	}

	public double getDeposit() {
		return deposit;
	}

	private void setDeposit(double deposit) throws InvalidEcoBikeInformationException {
		if (deposit <= 0) {
			throw new InvalidEcoBikeInformationException("bike deposit price must be greater than 0");
		}
		this.deposit = deposit;
	}

	public String getCurrency() {
		return currencyUnit;
	}

	private void setCurrency(String currency) {
		this.currencyUnit = currency;
	}

	public String getCreateDate() {
		return createDate;
	}

	private void setCreateDate(String createDate) throws InvalidEcoBikeInformationException {
		// TODO: Fix bug here
//		try {
//			System.out.println(createDate);
//			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");	
//			Date date = (Date) dateFormat.parse(createDate);
//			this.createDate = new java.sql.Date(date.getTime());
//		} catch (Exception e) {
//			throw new InvalidEcoBikeInformationException("invalid date format");
//		}
		this.createDate = createDate.toString();
	}

	public Configs.BIKE_STATUS getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(Configs.BIKE_STATUS currentStatus) {
		this.currentStatus = currentStatus;
	}

	public float getCurrentBattery() {
		return currentBattery;
	}

	public void setCurrentBattery(float currentBattery) throws InvalidEcoBikeInformationException {
		if(currentBattery < 0 || currentBattery > 100) {
			throw new InvalidEcoBikeInformationException("bike batery percentage must be between 0 and 100");
		}
		this.currentBattery = currentBattery;
	}

	public int getTotalRentTime() {
		return totalRentTime;
	}

	public void setTotalRentTime(int totalRentTime) {
		this.totalRentTime = totalRentTime;
	}
	
	
	// return a JSON string containing information about the string
	public String toString(){
		try {
			return JSONUtils.serializeBikeInformation(this);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public float getDistanceEstimated() {
		return this.currentBattery * 5;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public int getDockId() {
		return dockId;
	}

	public void setDockId(int dockId) {
		this.dockId = dockId;
	}
	
	
}

	