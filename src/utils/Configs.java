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
    
    public static String CURRENCY = "VND";
    public static float PERCENT_VAT = 10;
    
    // static resource
    public static final String IMAGE_PATH = "assets/images";
    public static final String DEPOSIT_SCREEN_PATH = "/views/fxml/FXML_DepositScreen.fxml";
    public static final String LIST_DOCK_SCREEN_PATH = "/views/fxml/FXML_ListDockScreen.fxml";
    public static final String MAIN_SCREEN_PATH = "/views/fxml/FXML_MainScreen.fxml";
    public static final String PAYING_METHOD_SCREEN_PATH = "/views/fxml/FXML_PayingMethodScreen.fxml";
    public static final String PAYMENT_SCREEN_PATH = "/views/fxml/FXML_PaymentScreen.fxml";
    public static final String POPUP_SCREEN_PATH = "/views/fxml/FXML_PopupScreen.fxml";
    public static final String RETURN_BIKE_SCREEN_PATH = "/views/fxml/FXML_ReturnBikeScreen.fxml";
    public static final String SPLASH_SCREEN_PATH = "/views/fxml/FXML_SplashScreen.fxml";
    public static final String VIEW_BIKE_SCREEN_PATH = "/views/fxml/FXML_ViewBikeScreen.fxml";
    public static final String VIEW_DOCK_SCREEN_PATH = "/views/fxml/FXML_ViewDockScreen.fxml";
    public static final String INVOICE_SCREEN_PATH = "/views/fxml/FXML_InvoiceScreen.fxml";


	public static final String BIKE_INFORMATION_SCREEN_PATH = "/views/screens/FXML_ViewBikeScreen.fxml";
	public static final String DOCK_INFORMATION_SCREEN_PATH = "/views/screens/FXML_ViewDockScreen.fxml";
	public static final String BIKE_IN_DOCK_PATH = "/views/screens/BikeInDock.fxml";
	public static final String PAYMENT_METHOD_SCREEN_PATH = "/views/screens/FXML_PayingMethodScreen.fxml";


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
