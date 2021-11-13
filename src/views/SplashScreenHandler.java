package views;

import controllers.EcoBikeBaseController;

/**
 * This class spawns a handler for the first splash screen of the EcoBike application
 * @author Hikaru
 *
 */

public class SplashScreenHandler extends EcoBikeBaseScreenHandler {
	/**
	 * Initialize handler for splash screen of EcoBike application
	 * @param screenTitle Title of the screen
	 * @param controller Controller for handling request from the screen
	 * @param prevScreen An instance to the screen that called this screen
	 */
	protected SplashScreenHandler(String screenTitle, EcoBikeBaseController controller, EcoBikeBaseScreenHandler prevScreen) {
		super(screenTitle, controller, prevScreen);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}

}
