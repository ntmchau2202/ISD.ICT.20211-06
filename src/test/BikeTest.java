package test;

import static org.junit.Assert.*;

import java.sql.Timestamp;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.rules.ExpectedException;

import entities.Bike;
import exceptions.ecobike.InvalidEcoBikeInformationException;

public class BikeTest {
	
	@Before
	public void setUp() throws Exception {

	}


	@ParameterizedTest
	@CsvSource({
		"ABC, normal, laksjfhbg/alkjfhg, 135afg21, 10, 300, VND, 12/03/2021",
		"A BC, normal, laksjfhbg/alkjfhg, 135afg21, 10, 300, VND, 12/03/2021",
		"AB-C, normal, laksjfhbg/alkjfhg, 135afg21, 10, 300, VND, 12/03/2021",
		"ABC, norm_al, laksjfhbg/alkjfhg, 135afg21, 10, 300, VND, 12/03/2021",
		"ABC, normal, , 135afg21, 10, 300, VND, 12/03/2021",
	})
	public void BikeConstructorTestOK(String name, String bikeType, String licensePlateCode, String bikeImage, 
			String bikeBarcode, double bikeRentalPrice, String currencyUnit, double deposit, 
			String createDate) {
		boolean isValid = true;
		try {
			Bike bike = new Bike(name, bikeType, licensePlateCode, bikeImage, bikeBarcode, bikeRentalPrice
					, currencyUnit, deposit, createDate);					
		} catch (Exception e) {
			isValid = false;
		} finally {
			assertTrue(isValid == true);
		}
		
	}
	
	@ParameterizedTest
	@CsvSource({
		"1ABC, normal, laksjfhbg/alkjfhg, 135afg21, 10, 300, VND, 12/03/2021",
		"AB?C, normal, laksjfhbg/alkjfhg, 135afg21, 10, 300, VND, 12/03/2021",
		"ABC, nor?mal, laksjfhbg/alkjfhg, 135afg21, 10, 300, VND, 12/03/2021",
		"ABC, normal, laksjfhbg/alkjfhg, 135afg21, 0, 300, VND, 12/03/2021",
		"ABC, normal, laksjfhbg/alkjfhg, 135afg21, 10, 0, VND, 12/03/2021",
		"ABC, normal, laksjfhbg/alkjfhg, 135afg21, 10, 300, VND, 32/03/2021",
		"ABC, normal, laksjfhbg/alkjfhg, 135afg21, 10, 300, VND, 12/3abc/2021",
		", normal, laksjfhbg/alkjfhg, 135afg21, 10, 300, VND, 12/03/2021",
		"ABC, , laksjfhbg/alkjfhg, 135afg21, 10, 300, VND, 12/03/2021",
		"ABC, normal, laksjfhbg/alkjfhg, , 0, 300, VND, 12/03/2021",
	})
	public void BikeConstructorTestException(String name, String bikeType, String licensePlateCode, String bikeImage, 
			String bikeBarcode, double bikeRentalPrice, String currencyUnit, double deposit, 
			String createDate) {
		try { 
			Bike bike = new Bike(name, bikeType, licensePlateCode, bikeImage, bikeBarcode, bikeRentalPrice
					, currencyUnit, deposit, createDate);
		} catch (Exception e) {
			assertTrue(e instanceof InvalidEcoBikeInformationException);
		}
	}

	@ParameterizedTest
	@CsvSource({
		", normal, laksjfhbg/alkjfhg, 135afg21, 10, 300, VND, 12/03/2021",
		"ABC, , laksjfhbg/alkjfhg, 135afg21, 10, 300, VND, 12/03/2021",
		"ABC, normal, , 135afg21, 10, 300, VND, 12/03/2021",
		"ABC, normal, laksjfhbg/alkjfhg, , 0, 300, VND, 12/03/2021",
	})
	public void BikeConstructorTestNullException(String name, String bikeType, String licensePlateCode, String bikeImage, 
			String bikeBarcode, double bikeRentalPrice, String currencyUnit, double deposit, 
			String createDate) {
		try { 
			Bike bike = new Bike(name, bikeType, licensePlateCode, bikeImage, bikeBarcode, bikeRentalPrice
					, currencyUnit, deposit, createDate);
		} catch (Exception e) {
			assertTrue(e instanceof InvalidEcoBikeInformationException);
		}
	}
}
