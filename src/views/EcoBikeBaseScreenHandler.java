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
	protected EcoBikeBaseScreenHandler(String screenTitle, EcoBikeBaseScreenHandler prevScreen) {
		this.screenTitle = screenTitle;
//		this.controller = controller;
		this.prevScreen = prevScreen;
	}
	
	/**
	 * Initialize the views and components of the screen
	 */
	protected abstract void initialize();
	
	/**
	 * Display a message box with error message and error code
	 * @param errMsg Message to be displayed
	 * @param errCode Error code of the failure
	 */
	public void notifyError(String errMsg, int errCode) {
		
	}
	
	/**
	 * Display a message box with notify message
	 * @param msg Message to be displayed
	 */
	public void notifySuccess(String msg) {
		
	}
}
