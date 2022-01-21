package entities;

import exceptions.ecobike.InvalidEcoBikeInformationException;
import utils.Configs;

public class TwinBike extends Bike {
	protected int motor;
	public TwinBike(String name, String licensePlateCode, String bikeImage, String bikeBarcode, String currencyUnit,
			double deposit, String createDate) throws InvalidEcoBikeInformationException {
		super(name, licensePlateCode, bikeImage, bikeBarcode, currencyUnit, deposit, createDate);
		
	}

	protected void setMotor() {
		this.motor = Configs.numOfMotor.get(Configs.BikeType.toBikeType(getBikeType()));
	}
	
	public int getNumberOfMotor() {
		return this.motor;
	}
}
