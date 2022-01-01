package views;

import controllers.EcoBikeBaseController;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This class is the base for all screen handler class of the EcoBike application
 * @author chauntm
 *
 */
public abstract class EcoBikeBaseScreenHandler extends FXMLScreenHandler {
	/**
	 * Title of the screen
	 */
	protected String screenTitle;
	private Scene scene;
	protected final Stage stage;
	
	/**
	 * The screen that called this current screen
	 */
	protected EcoBikeBaseScreenHandler prevScreen;
	
	protected EcoBikeBaseController controller;
	
	/**
	 * Constructs a screen handler base
	 * @param screenTitle Title of the screen to be created
	 */
	public EcoBikeBaseScreenHandler(Stage stage, String screenTitle, EcoBikeBaseController controller, EcoBikeBaseScreenHandler prevScreen, String screenPath) {
		super(screenPath);
		this.screenTitle = screenTitle;
//		this.controller = controller;
		this.prevScreen = prevScreen;
		this.stage = stage;
	}


	public void show() {
		if (this.scene == null) {
			this.scene = new Scene(this.content);
		}
		this.stage.setScene(this.scene);
		//initialize();
		System.out.println("hi");
		this.stage.show();
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
