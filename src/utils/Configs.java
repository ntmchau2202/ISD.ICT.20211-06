package utils;

import java.util.Dictionary;
import java.util.Enumeration;

/**
 * This class provides constants, literals and related functions for generation to use in classes
 * @author chauntm
 */
public class Configs {
    //renting service related constant
    public enum BikeType{
        STANDARDBIKE,
        STANDARDEBIKE,
        TWINBIKE
    }

    public static java.util.Map<BikeType, Float> chargeMultiplierDictionary = new java.util.HashMap<BikeType, Float>() {{
        put(BikeType.STANDARDBIKE, 1f);
        put(BikeType.STANDARDEBIKE, 1.5f);
        put(BikeType.TWINBIKE, 1.5f);
    }};

    public static float freeOfChargeTimeInMinute = 10f;

    public static float firstChargeTimeIntervalInMinute = 30f;
    public static float firstChargeTimeIntervalCost = 10000f;

    public static float chargeTimeIntervalInMinute = 15f;
    public static float chargeTimeIntervalCost = 3000f;

	public enum BIKE_STATUS {
		FREE,
		RENTED,		
	}
	
	public enum TransactionType {
		// transaction_detail = PAY_DEPOSIT: rent_id; PAY_RENTAL: rent_id; RETURN_DEPOSIT: rent_id;
		PAY_DEPOSIT("PAY_DEPOSIT"),
		PAY_RENTAL("PAY_RENTAL"),
		RETURN_DEPOSIT("RETURN_DEPOSIT");

		private String transactionType;
		TransactionType(String string) {
			this.transactionType = string;
		}
		
		public String toString() {
			return this.transactionType;
		}
	}
}
