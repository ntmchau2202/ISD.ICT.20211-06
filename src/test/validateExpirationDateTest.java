package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import controllers.PaymentController;

class validateExpirationDateTest {

	private PaymentController controllers;
	@BeforeEach
	void setUp() throws Exception {
		controllers = PaymentController.getPaymentController();
	}

	@ParameterizedTest
	@CsvSource({
		"09/25,true",
		"09-25,false"
	})
	public void test(String expirationDate, boolean expected) {
		//when
		boolean isValid = controllers.validateExprirationDate(expirationDate);
		
		//then
		assertEquals(expected, isValid);
	}

}
