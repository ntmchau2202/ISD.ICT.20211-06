package entities;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import org.json.JSONException;

import exceptions.ecobike.InvalidEcoBikeInformationException;
import utils.Configs;
import utils.FunctionalUtils;
import utils.JSONUtils;

public abstract class Bike {
	
	private String name;
	
	/**
	 * type of the bike.
	 */
	private Configs.BikeType bikeType;
	
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
	
	private Dock currentDock;

	
	private PropertyChangeSupport statusNotifier;
	private PropertyChangeSupport dockNotifier;
	
	public Bike(String name, String licensePlateCode, String bikeImage, 
			String bikeBarcode, String currencyUnit, double deposit, 
			String createDate) throws InvalidEcoBikeInformationException {
		this.setName(name);
		this.setLicensePlateCode(licensePlateCode);
		this.setBikeImage(bikeImage);
		this.setBikeBarCode(bikeBarcode);
		this.setCurrency(currencyUnit);
		this.setDeposit(deposit);
		this.setCreateDate(createDate);
		this.statusNotifier = new PropertyChangeSupport(this);
		this.dockNotifier = new PropertyChangeSupport(this);
	}
	
	public void addStatusObserver(PropertyChangeListener pcl) {
		this.statusNotifier.addPropertyChangeListener(pcl);
	}
	
	public void removeStatusObserver(PropertyChangeListener pcl) {
		this.statusNotifier.removePropertyChangeListener(pcl);
	}
	
	public void addDockObserver(PropertyChangeListener pcl) {
		this.dockNotifier.addPropertyChangeListener(pcl);
	}

	public void removeDockObserver(PropertyChangeListener pcl) {
		this.dockNotifier.removePropertyChangeListener(pcl);
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
		return bikeType.toString();
	}

	protected void setBikeType(Configs.BikeType bikeType) throws InvalidEcoBikeInformationException {				
		this.statusNotifier.firePropertyChange("bikeType", this.bikeType, bikeType);
		this.bikeType = bikeType;
		this.bikeRentalPrice = Configs.chargeTimeIntervalCost * Configs.chargeMultiplierDictionary.get(bikeType);
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
		this.createDate = createDate.toString();
	}

	public Configs.BIKE_STATUS getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(Configs.BIKE_STATUS currentStatus) {
		this.statusNotifier.firePropertyChange("currentStatus", this.currentStatus, currentStatus);
		this.currentStatus = currentStatus;
	}
	
	public void goToDock(Dock dock) {
		this.currentDock = dock;
		this.addDockObserver(dock);
		dock.addBikeToDock(this);
		this.setCurrentStatus(Configs.BIKE_STATUS.FREE);
		this.dockNotifier.firePropertyChange("currentStatus", this.currentStatus, currentStatus);
	}
	
	public void getOutOfDock() {
		this.setCurrentStatus(Configs.BIKE_STATUS.RENTED);
		this.currentDock.removeBikeFromDock(this);
		this.removeDockObserver(this.currentDock);
		this.currentDock = null;
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

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}
	
	public Dock getCurrentDock() {
		return this.currentDock;
	}
}
