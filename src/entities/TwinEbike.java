package entities;

import exceptions.ecobike.InvalidEcoBikeInformationException;

public class TwinEbike extends TwinBike {

	public TwinEbike(String name, String licensePlateCode, String bikeImage, String bikeBarcode, String currencyUnit,
			double deposit, String createDate) throws InvalidEcoBikeInformationException {
		super(name, licensePlateCode, bikeImage, bikeBarcode, currencyUnit, deposit, createDate);
	}

}
