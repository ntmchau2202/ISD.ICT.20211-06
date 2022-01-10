package entities;

import java.text.DateFormat; 
import java.text.SimpleDateFormat;

import org.json.JSONException;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
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
public class NormalBike extends Bike{
	
	public NormalBike(String name, String licensePlateCode, String bikeImage, 
			String bikeBarcode, double bikeRentalPrice, String currencyUnit, double deposit, 
			String createDate) throws InvalidEcoBikeInformationException {
		super(name, licensePlateCode, bikeImage, bikeBarcode, bikeRentalPrice, currencyUnit, deposit, createDate);
		this.setBikeType(Configs.BikeType.NormalBike);
	}		
}

	