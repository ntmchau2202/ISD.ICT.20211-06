package entities;

import exceptions.ecobike.InvalidEcoBikeInformationException;
import utils.Configs;

public class EBike extends Bike {
	
	private double battery;
	
	public EBike(String name, String licensePlateCode, String bikeImage, 
			String bikeBarcode, String currencyUnit, double deposit, 
			String createDate) throws InvalidEcoBikeInformationException {
		super(name, licensePlateCode, bikeImage, bikeBarcode, currencyUnit, deposit, createDate);
		this.setBikeType(Configs.BikeType.EBike);
	}	

	public double getBattery() {
		return this.battery;
	}
	
	public void setBattery(double battery) {
		if (battery < 0 || battery > 100) {
			return;
		}
		this.battery = battery;
	}
}
