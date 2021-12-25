package views;

import controllers.EcoBikeBaseController;
import controllers.EcoBikeInformationController;

/**
 * This class spawns a handler for the first splash screen of the EcoBike application
 * @author chauntm
 *
 */

public class SplashScreenHandler extends EcoBikeBaseScreenHandler {
	protected SplashScreenHandler(String screenTitle, EcoBikeBaseScreenHandler prevScreen) {
		super(screenTitle, prevScreen);
		// TODO Auto-generated constructor stub
		EcoBikeInformationController.getEcoBikeInformationController();
	}

	/**
	 * Initialize handler for splash screen of EcoBike application
	 * @param screenTitle Title of the screen
	 * @param controller Controller for handling request from the screen
	 * @param prevScreen An instance to the screen that called this screen
	 */

	@Override
	protected void initialize() {
		// TODO setup and display the screen here
		// then flash to the main screen
		
		
	}

}
