package controllers;

import java.util.ArrayList;

import entities.Bike;
import entities.Dock;
import exceptions.ecobike.EcoBikeUndefinedException;
import exceptions.ecobike.NoInformationException;

/**
 * This class is in charge of getting information from the database and returns it to the screen handler for displaying
 * @author chauntm
 *
 */
public class EcoBikeInformationController extends EcoBikeBaseController {
	private ArrayList<Bike> listBike;
	
	public EcoBikeInformationController() {
		this.listBike = new ArrayList<Bike>();
	}
	/**
	 * Gets information about a given dock
	 * @param dock The dock having information to be queried
	 * @throws NoInformationException If there is no information about the entity
	 * @throws EcoBikeUndefinedException If there is an unexpected error when querying for information
	 * @return a JSON contains dock information
	 */
	public String getDockInformation(String dockID) throws EcoBikeUndefinedException {
		try {

		} catch (Exception e){
			throw new EcoBikeUndefinedException(e.getMessage());
		}
	}

	/**
	 * Gets information about a given bike
	 * @param dock The bike having information to be queried
	 * @throws NoInformationException If there is no information about the entity
	 * @throws EcoBikeUndefinedException If there is an unexpected error when querying for information
	 * @return a JSON contains bike information
	 */
	public String getBikeInformation(String bikeID) throws NoInformationException {
		try {
			if (bikeID == null) {
				throw new NoInformationException("no keyword to search");
			}
			
			if (bikeID.length() == 0) {
				throw new NoInformationException("no keyword to search");
			}
			for (Bike b : this.listBike) {
				if(b.getBarCode().compareTo(bikeID) == 0) {
					return b.getName();
				}
			}
			return "";
		} catch (Exception e){
			throw new NoInformationException(e.getMessage());
		}
	}
	
	public void setBikeList(ArrayList<Bike> listBike) {
		this.listBike = listBike;
	}
}
