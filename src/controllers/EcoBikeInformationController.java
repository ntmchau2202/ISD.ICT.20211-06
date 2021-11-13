package controllers;

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
	/**
	 * Gets information about a given dock
	 * @param dock The dock having information to be queried
	 * @throws NoInformationException If there is no information about the entity
	 * @throws EcoBikeUndefinedException If there is an unexpected error when querying for information
	 * @return a JSON contains dock information
	 */
	public String getDockInformation(Dock dock) throws NoInformationException, EcoBikeUndefinedException {
		return null;
	}

	/**
	 * Gets information about a given bike
	 * @param dock The bike having information to be queried
	 * @throws NoInformationException If there is no information about the entity
	 * @throws EcoBikeUndefinedException If there is an unexpected error when querying for information
	 * @return a JSON contains bike information
	 */
	public String getBikeInformation(Bike bike) throws NoInformationException, EcoBikeUndefinedException {
		return null;
	}
}
