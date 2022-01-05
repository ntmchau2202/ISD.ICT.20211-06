package views.screen;
import controllers.EcoBikeBaseController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

/**
 * This class is the base for all screen handler class of the EcoBike
 * application
 *
 * @author chauntm
 */
public abstract class EcoBikeBaseScreenHandler {

    protected final Stage stage;
    protected EcoBikeMainScreenHandler mainScreenHandler;
    protected Hashtable<String, String> messages;
    protected FXMLLoader loader;
    protected AnchorPane content;
    private Scene scene;
    private EcoBikeBaseScreenHandler prev;
    private EcoBikeBaseController baseController;


    public EcoBikeBaseScreenHandler(Stage newStage, String screenPath) throws IOException {
        this.loader = new FXMLLoader(getClass().getResource(screenPath));
        this.loader.setController(this);
        this.content = (AnchorPane) loader.load();
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

    public AnchorPane getContent() {
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

    public void setScreenTitle(String string) {
        this.stage.setTitle(string);
    }

    public EcoBikeBaseController getbController() {
        return this.baseController;
    }

    public void setbController(EcoBikeBaseController newBaseController) {
        this.baseController = newBaseController;
    }

    public void setPreviousScreen(EcoBikeBaseScreenHandler prev) {
        this.prev = prev;
    }

    public EcoBikeBaseScreenHandler getPreviousScreen() {
        return this.prev;
    }

    @SuppressWarnings("rawtypes")
    public void forward(Hashtable message) {
        this.messages = message;
    }

    public void setMainScreenHandler(EcoBikeMainScreenHandler mainScreenHandler) {
        this.mainScreenHandler = mainScreenHandler;
    }
}
