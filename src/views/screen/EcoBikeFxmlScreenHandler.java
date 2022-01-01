package views.screen;

import java.io.File;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * This is the parent class handler for other class handlers to extend.
 * <br>@author duong
 *
 */
public class EcoBikeFxmlScreenHandler {
	protected FXMLLoader loader;
	protected Pane content;
	
	public EcoBikeFxmlScreenHandler(String screenPath) throws IOException {
		this.loader = new FXMLLoader(getClass().getResource(screenPath));
		this.loader.setController(this);
		this.content = (Pane) loader.load();
	}
	
	public Pane getContent() {
		return this.content;
	}
	
	public FXMLLoader getLoader() {
		return this.loader;
	}
	
	public void setImage(ImageView imv, String path) {
	    File file = new File(path);
	    Image img = new Image(file.toURI().toString());
	    imv.setImage(img);
	  }
}
