package controllers;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;
import java.util.ArrayList;

import entities.NormalBike;
import entities.Bike;
import entities.Dock;
import exceptions.ecobike.EcoBikeException;
import exceptions.ecobike.EcoBikeUndefinedException;
import exceptions.ecobike.NoInformationException;
import utils.Configs;
import utils.DBUtils;

/**
 * This class is in charge of getting information from the database and returns it to the screen handler for displaying
 * @author chauntm
 *
 */
public class EcoBikeInformationController extends EcoBikeBaseController implements PropertyChangeListener {
	private static EcoBikeInformationController ecoBikeInformationController;
	
	// structures to save information about docks and bikes
	// must be initialized at creation
	private ArrayList<Dock> listAllDocks = new ArrayList<Dock>();
	private ArrayList<Bike> listAllBikes = new ArrayList<Bike>();
	private ArrayList<Bike> listAllRentedBikes = new ArrayList<Bike>();
	private ArrayList<Bike> listAllFreeBikes = new ArrayList<Bike>();

	public static EcoBikeInformationController getEcoBikeInformationController() throws SQLException, EcoBikeException {
		if (ecoBikeInformationController == null) {
			System.out.println("Information controller initialization...");
			ecoBikeInformationController = new EcoBikeInformationController();
			ecoBikeInformationController.listAllDocks = DBUtils.getAllDock();
			// add all free bike
			for (Dock d: ecoBikeInformationController.listAllDocks) {
				for (Bike b: d.getAllBikeInDock()) {
					ecoBikeInformationController.listAllBikes.add(b);
					ecoBikeInformationController.listAllFreeBikes.add(b);
					b.addStatusObserver(ecoBikeInformationController);
				}
			}
			// get all rented bike, which is not in the dock
			ArrayList<Bike> rentedBikes = DBUtils.getAllRentedBike();
			for (Bike b: rentedBikes) {
				ecoBikeInformationController.listAllBikes.add(b);
				ecoBikeInformationController.listAllRentedBikes.add(b);
				b.addStatusObserver(ecoBikeInformationController);
			}
		}
		return ecoBikeInformationController;
	}
	
	public ArrayList<Dock> getAllDocks() {
		return this.listAllDocks;
	}
	
	public ArrayList<Bike> getAllBikes() {
		return this.listAllBikes;
	}
	
	public ArrayList<Bike> getAllRentedBikes() {
		return this.listAllRentedBikes;
	}
	
	public ArrayList<Bike> getAllFreeBikes() {
		return this.listAllFreeBikes;
	}
	
	/**
	 * Gets information about a given dock
	 * @param dock The dock having information to be queried
	 * @throws NoInformationException If there is no information about the entity
	 * @return a JSON contains dock information
	 * @throws EcoBikeException 
	 * @throws SQLException 
	 */
	public Dock getDockInformationByID(int dockID) throws SQLException, EcoBikeException {
		if (String.valueOf(dockID) == null) {
			throw new NoInformationException("no keyword to search");
		}
		
		if (String.valueOf(dockID).length() == 0) {
			throw new NoInformationException("no keyword to search");
		}

		for (Dock d : listAllDocks) {
			if (d.getDockID() == dockID) {
				return d;
			}
		}
		return null;
	}

	/**
	 * Gets information about a given bike
	 * @param dock The bike having information to be queried
	 * @throws EcoBikeUndefinedException If there is an unexpected error when querying for information
	 * @return a JSON contains bike information
	 * @throws SQLException 
	 * @throws EcoBikeException 
	 */
	public Bike getBikeInformationByBarcode(String barCode) throws EcoBikeException, SQLException {
		if (String.valueOf(barCode) == null) {
			throw new NoInformationException("no keyword to search");
		}
		
		if (String.valueOf(barCode).length() == 0) {
			throw new NoInformationException("no keyword to search");
		}

		for (Bike b : listAllBikes) {
			if (b.getBikeBarCode().equals(barCode)) {
				return b;
			}
		}
		return null;
	}
	
	// should handle the case of more than 1 result;
	public Bike getBikeInformationByName(String name) throws SQLException, EcoBikeException {
		if(String.valueOf(name) == null) {
			throw new NoInformationException("no keyword to search");
		}
		
		if(String.valueOf(name).length() == 0) {
			throw new NoInformationException("no keyword to search");
		}
		
		for (Bike b: listAllBikes) {
			if(b.getName().toLowerCase().contains(name.toLowerCase())) {
				return b;
			}
		}
		return null;
	}
	
	// should handle the case of more than 1 result;
	public Dock getDockInformationByName(String name) throws SQLException, EcoBikeException {
		if(String.valueOf(name) == null) {
			throw new NoInformationException("no keyword to search");
		}
		
		if(String.valueOf(name).length() == 0) {
			throw new NoInformationException("no keyword to search");
		}	
		
		for (Dock d: listAllDocks) {
			if (d.getName().toLowerCase().contains(name.toLowerCase())) {
				return d;
			}
		}
		return null;
	}
	
	public String getBikeLocation(String bikeBarcode) throws SQLException, EcoBikeException {
		if(String.valueOf(bikeBarcode) == null) {
			throw new NoInformationException("no keyword to search");
		}
		
		if(String.valueOf(bikeBarcode).length() == 0) {
			throw new NoInformationException("no keyword to search");
		}	
		
		for (Bike b: listAllBikes) {
			if (b.getBikeBarCode().equals(bikeBarcode)) {
				return b.getCurrentDock().getName();
			}
		}
		return null;
	}
	
	public float getBikeBattery(String bikeBarcode) throws SQLException, EcoBikeException {
		if(String.valueOf(bikeBarcode) == null) {
			throw new NoInformationException("no keyword to search");
		}
		
		if(String.valueOf(bikeBarcode).length() == 0) {
			throw new NoInformationException("no keyword to search");
		}	
		return DBUtils.getBikeBattery(bikeBarcode); // shoulda fix this
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		Object val = evt.getNewValue();
		if (val instanceof Configs.BIKE_STATUS) {
			Configs.BIKE_STATUS newBikeStatus = (Configs.BIKE_STATUS) val;
			Configs.BIKE_STATUS oldBikeStatus = (Configs.BIKE_STATUS) evt.getOldValue();
			Bike sourceBike = (Bike)evt.getSource();
			if (oldBikeStatus == Configs.BIKE_STATUS.FREE && newBikeStatus == Configs.BIKE_STATUS.RENTED) {
				this.listAllFreeBikes.remove(sourceBike);
				this.listAllRentedBikes.add(sourceBike);
			} else if (newBikeStatus == Configs.BIKE_STATUS.FREE) {
				this.listAllFreeBikes.add(sourceBike);
				this.listAllRentedBikes.remove(sourceBike);
			}
		}
		
	}
}
