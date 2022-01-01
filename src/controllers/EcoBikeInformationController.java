package controllers;

import java.sql.SQLException;
import java.util.ArrayList;

import entities.Bike;
import entities.Dock;
import exceptions.ecobike.EcoBikeException;
import exceptions.ecobike.EcoBikeUndefinedException;
import exceptions.ecobike.NoInformationException;
import utils.DBUtils;

/**
 * This class is in charge of getting information from the database and returns it to the screen handler for displaying
 * @author chauntm
 *
 */
public class EcoBikeInformationController extends EcoBikeBaseController {
	private static EcoBikeInformationController ecoBikeInformationController;

	public static EcoBikeInformationController getEcoBikeInformationController() {
		if (ecoBikeInformationController == null)
			ecoBikeInformationController = new EcoBikeInformationController();
		return ecoBikeInformationController;
	}

	public EcoBikeInformationController() {

	}
	
	
	/**
	 * Gets information about a given dock
	 * @param dock The dock having information to be queried
	 * @throws NoInformationException If there is no information about the entity
	 * @return a JSON contains dock information
	 * @throws EcoBikeException 
	 * @throws SQLException 
	 */
	public Dock getDockInformation(String dockID) throws SQLException, EcoBikeException {
		if (dockID == null) {
			throw new NoInformationException("no keyword to search");
		}
		
		if (dockID.length() == 0) {
			throw new NoInformationException("no keyword to search");
		}

		return DBUtils.getDockInformation(dockID);
	}

	/**
	 * Gets information about a given bike
	 * @param dock The bike having information to be queried
	 * @throws EcoBikeUndefinedException If there is an unexpected error when querying for information
	 * @return a JSON contains bike information
	 * @throws SQLException 
	 * @throws EcoBikeException 
	 */
	public Bike getBikeInformation(String bikeID) throws EcoBikeException, SQLException {
		if (bikeID == null) {
			throw new NoInformationException("no keyword to search");
		}
		
		if (bikeID.length() == 0) {
			throw new NoInformationException("no keyword to search");
		}

		return DBUtils.getBikeInformation(bikeID);
	}
	
}
