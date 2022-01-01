package views;

import controllers.EcoBikeBaseController;
import javafx.stage.Stage;

/**
 * This class spawns a handler for the first splash screen of the EcoBike application
 * @author chauntm
 *
 */

public class SplashScreenHandler extends EcoBikeBaseScreenHandler {
	/**
	 * Initialize handler for splash screen of EcoBike application
	 * @param screenTitle Title of the screen
	 * @param controller Controller for handling request from the screen
	 * @param prevScreen An instance to the screen that called this screen
	 */
	protected SplashScreenHandler(Stage stage, String screenTitle, EcoBikeBaseController controller, EcoBikeBaseScreenHandler prevScreen, String screenPath) {
		super(stage, screenTitle, controller, prevScreen, screenPath);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}

}
