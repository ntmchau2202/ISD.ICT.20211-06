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
	 */
	public void getDockInformation(Dock dock) throws NoInformationException, EcoBikeUndefinedException {
		
	}

	/**
	 * Gets information about a given bike
	 * @param dock The bike having information to be queried
	 * @throws NoInformationException If there is no information about the entity
	 * @throws EcoBikeUndefinedException If there is an unexpected error when querying for information
	 */
	public void getBikeInformation(Bike bike) throws NoInformationException, EcoBikeUndefinedException {
		
	}
}
