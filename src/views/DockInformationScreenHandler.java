package views;

import controllers.EcoBikeBaseController;

/**
 * This class creates a handler for getting behaviors of customer on dock information screen 
 * @author chauntm
 *
 */
public class DockInformationScreenHandler extends EcoBikeBaseScreenHandler {

	/**
	 * Initialize handler for dock information screen
	 * @param screenTitle Title of the screen
	 * @param controller Controller for handling request from the screen
	 * @param prevScreen An instance to the screen that called this screen
	 */
	protected DockInformationScreenHandler(String screenTitle, EcoBikeBaseController controller, EcoBikeBaseScreenHandler prevScreen) {
		super(screenTitle, controller, prevScreen);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Request the controller to return information about the bike selected and call the screen for displaying data
	 */
	public void viewBikeInformation() {
		
	}
	
	
	
	
}
