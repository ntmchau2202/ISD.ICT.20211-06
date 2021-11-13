package views;

import controllers.EcoBikeBaseController;

/**
 * This class is the base for all screen handler class of the EcoBike application
 * @author chauntm
 *
 */
public abstract class EcoBikeBaseScreenHandler {
	/**
	 * Title of the screen
	 */
	protected String screenTitle;
	
	/**
	 * The screen that called this current screen
	 */
	protected EcoBikeBaseScreenHandler prevScreen;
	
	protected EcoBikeBaseController controller;
	
	/**
	 * Constructs a screen handler base
	 * @param screenTitle Title of the screen to be created
	 */
	protected EcoBikeBaseScreenHandler(String screenTitle, EcoBikeBaseController controller, EcoBikeBaseScreenHandler prevScreen) {
		this.screenTitle = screenTitle;
		this.controller = controller;
		this.prevScreen = prevScreen;
	}
	
	/**
	 * Initialize the views and components of the screen
	 */
	protected abstract void initialize();
}
