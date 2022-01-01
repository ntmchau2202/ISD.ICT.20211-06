package entities;

import java.sql.PreparedStatement; 
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import exceptions.ecobike.EcoBikeException;
import exceptions.ecobike.InvalidEcoBikeInformationException;
import utils.Configs;
import utils.DBUtils;
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
	private Configs.BIKE_STATUS currentStatus;
	
	/**
	 * The current battery of the bike
	 */
	private float currentBattery;
	
	/**
	 * The total time the customer has rent calculated in minute
	 */
	private int totalRentTime;
	
	private String dockName;

	public Bike(String name, String bike_type, String bike_image, String bar_code, double bike_rental_price,
			double deposit, String currency, String create_date, String dockName) throws InvalidEcoBikeInformationException {
		this.setName(name); 
		this.setBikeType(bike_type);
		this.setBikeImage(bike_image);
		this.setBarCode(bar_code);
		this.setBikeRentalPrice(bike_rental_price);
		this.setDeposit(deposit);
		this.setCurrency(currency);
		this.setCreateDate(create_date);
		this.setTotalRentTime(0);
		this.setCurrentStatus(Configs.BIKE_STATUS.FREE);
		this.setCurrentBattery(100);
		this.dockName = dockName;
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

	public String getBikeImage() {
		return bikeImage;
	}

	private void setBikeImage(String bikeImage) {
		this.bikeImage = bikeImage;
	}

	public String getBarCode() {
		return barCode;
	}

	private void setBarCode(String barCode) throws InvalidEcoBikeInformationException {
		if (barCode == null) {
			throw new InvalidEcoBikeInformationException("bike barcode must not be null");
		}
		
		if (barCode.length() == 0) {
			throw new InvalidEcoBikeInformationException("bike barcode must not be empty");
		}
		this.barCode = barCode;
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
		return currency;
	}

	private void setCurrency(String currency) {
		this.currency = currency;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	private void setCreateDate(String createDate) throws InvalidEcoBikeInformationException {
		try {
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");	
			Date date = dateFormat.parse(createDate);
			this.createDate = new Timestamp(date.getTime());
		} catch (Exception e) {
			throw new InvalidEcoBikeInformationException("invalid date format");
		}
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
	public String toString() {
		return JSONUtils.serializeBikeInformation(this);
	}
	
	public Bike getBikeByBarcode(String bikeBarcode) throws EcoBikeException, SQLException {
		String sql = "SELECT * FROM Bike WHERE bike_barcode=?";
		PreparedStatement stm = DBUtils.getConnection().prepareStatement(sql);
		stm.setString(1, bikeBarcode);
		ResultSet result = stm.executeQuery();
		Bike bikeRes = new Bike(result.getString("name"), 
				result.getString("bike_type"), 
				result.getString("bike_image"), 
				result.getString("bike_barcode"), 
				result.getDouble("bike_rental_price"),
				result.getDouble("deposit_price"), 
				result.getString("currency_unit"), 
				result.getString("create_date"),
				result.getString("dock_name"));
		// get and set bike current status
		stm = DBUtils.getConnection().prepareStatement(
				"SELECT * from BikeStatus WHERE bike_barcode=?");
		stm.setString(1, bikeBarcode);
		result = stm.executeQuery();
		String bikeStatus = result.getString("current_status");
		Configs.BIKE_STATUS bikeStat;
		if(bikeStatus.equalsIgnoreCase("FREE")) {
			bikeStat = Configs.BIKE_STATUS.FREE;
		} else if (bikeStatus.equalsIgnoreCase("RENTED")) {
			bikeStat = Configs.BIKE_STATUS.RENTED;
		} else {
			throw new InvalidEcoBikeInformationException("invalid status of bike in database");
		}
		bikeRes.setCurrentStatus(bikeStat);
		bikeRes.setTotalRentTime(result.getInt("total_rent_time"));
		bikeRes.setCurrentBattery(result.getFloat("current_battery"));
		return bikeRes;
//		String strRes = JSONUtils.serializeBikeInformation(bikeRes);
//		return strRes;
	}
	
	public java.util.List<Bike> getAllBike() throws SQLException, EcoBikeException {
		Statement stm = DBUtils.getConnection().createStatement();
		ResultSet result = stm.executeQuery("Select * from Bike");
		ArrayList<Bike> list = new ArrayList<Bike>();
		while(result.next()) {
			Bike bikeRes = new Bike(result.getString("name"), 
					result.getString("bike_type"), 
					result.getString("bike_image"), 
					result.getString("bike_barcode"), 
					result.getDouble("bike_rental_price"),
					result.getDouble("deposit_price"), 
					result.getString("currency_unit"), 
					result.getString("create_date"),
					result.getString("dock_name"));
			bikeRes.setTotalRentTime(result.getInt("total_rent_time"));
			bikeRes.setCurrentBattery(result.getFloat("current_battery"));
			list.add(bikeRes);
		}
		
		ResultSet result2 = stm.executeQuery("Select * from BikeStatus");
		int i = 0;
		while(result2.next()) {
			String bikeStatus = result2.getString("current_status");
			Configs.BIKE_STATUS bikeStat;
			if(bikeStatus.equalsIgnoreCase("FREE")) {
				bikeStat = Configs.BIKE_STATUS.FREE;
			} else if (bikeStatus.equalsIgnoreCase("RENTED")) {
				bikeStat = Configs.BIKE_STATUS.RENTED;
			} else {
				throw new InvalidEcoBikeInformationException("invalid status of bike in database");
			}
			list.get(i).setCurrentStatus(bikeStat);
			i++;
		}
		return list;
	}

	public String getDockName() {
		return dockName;
	}
	
	public float getDistanceEstimated() {
		return this.currentBattery * 10;
	}
}

	