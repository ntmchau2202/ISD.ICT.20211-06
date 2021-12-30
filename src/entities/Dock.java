package entities;

import exceptions.ecobike.InvalidEcoBikeInformationException; 
import utils.FunctionalUtils;
import utils.JSONUtils;

/**
 * This is the class for object entity Dock including all information of the dock
 * @author Duong
 *
 */
public class Dock {

	/**
	 * name of the dock
	 */
	private String name;
	
	/**
	 * An unique string describes the id of the dock
	 */
	private String dockID;
	
	/**
	 * The address of the dock
	 */
	private String dockAddress;
	
	/**
	 * The area of the dock calculated in meter square
	 */
	private double dockArea;
	
	/**
	 * The number of available bikes in the dock that customers can rent
	 */
	private int numAvailableBike;

	/**
	 * The number of blank occupation for customers to return bike 
	 */
	private int numDockSpaceFree;
	
	public Dock() {
		
	}

	public Dock(String name, String dockID, String dock_address, double dock_area, int num_available_bike,
			int num_dock_space_free) throws InvalidEcoBikeInformationException {
		this.setName(name);
		this.setDockID(dockID);
		this.setDockAddress(dock_address);
		this.setDockArea(dock_area);
		this.setNumAvailableBike(num_available_bike);
		this.setNumDockSpaceFree(num_dock_space_free);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) throws InvalidEcoBikeInformationException {
		if (name == null) {
			throw new InvalidEcoBikeInformationException("dock name parameter must not be null");
		}
		
		if (name.length() == 0) {
			throw new InvalidEcoBikeInformationException("dock must have a name");
		}
		
		if (!Character.isLetter(name.charAt(0))) {
			throw new InvalidEcoBikeInformationException("dock name must start with a letter");
		} 
		
		if (FunctionalUtils.contains(name, "[^a-z0-9 -_]")) {
			throw new InvalidEcoBikeInformationException("dock name can only contain letters, digits, space, hypen and underscore");
		}
		this.name = name;
	}

	public String getDockID() {
		return dockID;
	}

	public void setDockID(String dockID) {
		this.dockID = dockID;
	}

	public String getDockAddress() {
		return dockAddress;
	}

	public void setDockAddress(String dockAddress) throws InvalidEcoBikeInformationException {
		if (dockAddress == null) {
			throw new InvalidEcoBikeInformationException("dock address parameter must not be null");
		}
		
		if (dockAddress.length() == 0) {
			throw new InvalidEcoBikeInformationException("dock address must not empty");
		}
		
		if (!Character.isLetterOrDigit(dockAddress.charAt(0))) {
			throw new InvalidEcoBikeInformationException("dock address must start with a letter");
		} 
		
		if (FunctionalUtils.contains(dockAddress, "[^a-z0-9 -/,]")) {
			throw new InvalidEcoBikeInformationException("dock address can only contain letters, digits, space, hypen, comma and slash");
		}

		this.dockAddress = dockAddress;
	}

	public double getDockArea() {
		return dockArea;
	}

	public void setDockArea(double dockArea) throws InvalidEcoBikeInformationException {
		if (dockArea <= 0) {
			throw new InvalidEcoBikeInformationException("area of bike dock must be positive");
		}
		this.dockArea = dockArea;
	}

	public int getNumAvailableBike() {
		return numAvailableBike;
	}

	public void setNumAvailableBike(int numAvailableBike) throws InvalidEcoBikeInformationException {
		if (numAvailableBike < 0) {
			throw new InvalidEcoBikeInformationException("number of available bike in dock must be non-negative");
		}
		this.numAvailableBike = numAvailableBike;
	}

	public int getNumDockSpaceFree() {
		return numDockSpaceFree;
	}

	public void setNumDockSpaceFree(int numDockSpaceFree) throws InvalidEcoBikeInformationException {
		if (numDockSpaceFree < 0) {
			throw new InvalidEcoBikeInformationException("number of free space in dock must be non-negative");
		}
		this.numDockSpaceFree = numDockSpaceFree;
	}
	
	public String toString() {
		return JSONUtils.serializeDockInformation(this);
	}
	
	
}
