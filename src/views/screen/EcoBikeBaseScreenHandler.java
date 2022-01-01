package views.screen;

import java.io.IOException;
import java.util.Hashtable;

import controllers.EcoBikeBaseController;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This class is the base for all screen handler class of the EcoBike
 * application
 * 
 * @author chauntm
 *
 */
public abstract class EcoBikeBaseScreenHandler extends EcoBikeFxmlScreenHandler {

	private Scene scene;
	private EcoBikeBaseScreenHandler prev;
	protected final Stage stage;
	protected EcoBikeMainScreenHandler mainScreenHandler;
	protected Hashtable<String, String> messages;
	private EcoBikeBaseController baseController;

	private EcoBikeBaseScreenHandler(String screenPath) throws IOException {
		super(screenPath);
		this.stage = new Stage();
	}

	public void setPreviousScreen(EcoBikeBaseScreenHandler prevScreen) {
		this.prev = prevScreen;
	}

	public EcoBikeBaseScreenHandler(Stage newStage, String screenPath) throws IOException {
		super(screenPath);
		this.stage = newStage;
	}

	/**
	 * This is the method to show the screen.
	 */
	public void show() {
		if (this.scene == null) {
			this.scene = new Scene(this.content);
		}
		this.stage.setScene(this.scene);
		this.stage.show();
	}

	public void setScreenTitle(String string) {
		this.stage.setTitle(string);
	}
	
	public void setbController(EcoBikeBaseController newBaseController) {
		this.baseController = newBaseController;
	}
	
	public EcoBikeBaseController getbController() {
		return this.baseController;
	}
	
	@SuppressWarnings("rawtypes")
	public void forward(Hashtable message) {
		this.messages = message;
	}
	
	public void setMainScreenHandler(EcoBikeMainScreenHandler mainScreenHandler) {
		this.mainScreenHandler = mainScreenHandler;
	}
}
