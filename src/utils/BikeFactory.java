package utils;

import entities.Bike;
import entities.EBike;
import entities.NormalBike;
import entities.TwinBike;
import entities.TwinEbike;

public class BikeFactory {
	public static Bike getBikeWithInformation(String name, String bikeType, String licensePlateCode, String bikeImage, 
			String bikeBarcode, String currencyUnit, double deposit, 
			String createDate) {
		Bike b = null;
		try {			
			if (bikeType.equalsIgnoreCase(Configs.BikeType.NormalBike.toString())) {
				b = new NormalBike(name, licensePlateCode, bikeImage, bikeBarcode, currencyUnit, deposit, createDate);
				return b;
			} 
			
			if (bikeType.equalsIgnoreCase(Configs.BikeType.EBike.toString())) {
				b = new EBike(name, licensePlateCode, bikeImage, bikeBarcode, currencyUnit, deposit, createDate);
				return b;
			}
			
			if (bikeType.equalsIgnoreCase(Configs.BikeType.TwinBike.toString())) {
				b = new TwinBike(name, licensePlateCode, bikeImage, bikeBarcode, currencyUnit, deposit, createDate);
			}
			
			if (bikeType.equalsIgnoreCase(Configs.BikeType.TwinEbike.toString())) {
				b = new TwinEbike(name, licensePlateCode, bikeImage, bikeBarcode, currencyUnit, deposit, createDate);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}
}
