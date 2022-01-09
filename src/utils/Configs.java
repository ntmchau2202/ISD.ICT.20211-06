package utils;


/**
 * This class provides constants, literals and related functions for generation to use in classes
 * @author chauntm
 */
public class Configs {
    //renting service related constant
    public enum BikeType{
        Bike,
        EBike,
        TwinBike,
        Others;
    }

    @SuppressWarnings("serial")
	public static java.util.Map<BikeType, Float> chargeMultiplierDictionary = new java.util.HashMap<BikeType, Float>() {{
        put(BikeType.Bike, 1f);
        put(BikeType.EBike, 1.5f);
        put(BikeType.TwinBike, 1.5f);
    }};
    
    public static BikeType getBikeType(String bikeType) {
    	if (bikeType.equalsIgnoreCase(BikeType.Bike.toString())) {
    		return BikeType.Bike;
    	} else if (bikeType.equalsIgnoreCase(BikeType.EBike.toString())) {
    		return BikeType.EBike;
    	} else if (bikeType.equalsIgnoreCase(BikeType.TwinBike.toString())) {
    		return BikeType.TwinBike;
    	} else {
    		return BikeType.Others;
    	}
    }

    public static float freeOfChargeTimeInMinute = 10f;

    public static float firstChargeTimeIntervalInMinute = 30f;
    public static float firstChargeTimeIntervalCost = 10000f;

    public static float chargeTimeIntervalInMinute = 15f;
    public static float chargeTimeIntervalCost = 3000f;
    
    public static String CURRENCY = "VND";
    public static float PERCENT_VAT = 10;
    
    // static resource
    public static final String IMAGE_PATH = "/lib/assets/icons";
    public static final String BIKE_IMAGE_LIB = "/lib/assets/bikes";
    public static final String DOCK_IMAGE_LIB = "/lib/assets/docks";
    public static final String LIST_DOCK_SCREEN_PATH = "/views/fxml/FXML_ListDockScreen.fxml";
    public static final String MAIN_SCREEN_PATH = "/views/fxml/FXML_MainScreen.fxml";
    public static final String PAYING_FOR_DEPOSIT_SCREEN_PATH = "/views/fxml/FXML_PayForDepositScreen.fxml";
    public static final String PAYING_FOR_RENTAL_SCREEN_PATH = "/views/fxml/FXML_PayForRentalScreen.fxml";
    public static final String PAYMENT_SCREEN_PATH = "/views/fxml/FXML_PaymentScreen.fxml";
    public static final String POPUP_SCREEN_PATH = "/views/fxml/FXML_PopupScreen.fxml";
    public static final String RETURN_BIKE_SCREEN_PATH = "/views/fxml/FXML_ReturnBikeScreen.fxml";
    public static final String SPLASH_SCREEN_PATH = "/views/fxml/FXML_SplashScreen.fxml";
    public static final String VIEW_BIKE_SCREEN_PATH = "/views/fxml/FXML_ViewBikeScreen.fxml";
    public static final String VIEW_DOCK_SCREEN_PATH = "/views/fxml/FXML_ViewDockScreen.fxml";
    public static final String INVOICE_SCREEN_PATH = "/views/fxml/FXML_InvoiceScreen.fxml";
	public static final String BIKE_IN_DOCK_PATH = "/views/fxml/FXML_BikeInDock.fxml";
	public static final String PAYMENT_METHOD_SCREEN_PATH = "/views/fxml/FXML_PayingMethodScreen.fxml";


	public enum BIKE_STATUS {
		FREE,
		RENTED,		
		PAUSED
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
	
	public static final String API_BASE_URL = "https://ecopark-system-api.herokuapp.com";
	public static final String API_TRANSACTION = "/api/card/processTransaction";
	public static final String API_RESET_BALANCE = "/api/card/reset-balance";
}
