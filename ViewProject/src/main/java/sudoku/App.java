package sudoku;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.stage.Stage;
import org.apache.log4j.Logger;


public class App extends Application {

    final static Logger logger = Logger.getLogger(MenuWindowControl.class);
    @Override
    public void start(Stage stage) throws IOException {
        ResourceBundle bundle = ResourceBundle.getBundle("bundles/language");
        FxmlStageSetup.buildStage(stage, "/MenuWindow.fxml", bundle.getString("app.menuTitle"), bundle);
        logger.debug(bundle.getString("app.menuOpened"));
    }

    public static void main(String[] args) {
        launch();
    }
}