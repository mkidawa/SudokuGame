package sudoku;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

public class MenuWindowControl {

    private static Stage stage;
    private ResourceBundle bundle = ResourceBundle.getBundle("bundles.language");
    @FXML
    private ComboBox<String> difficulty;
    private static String level;
    @FXML
    private Button btnPL;
    @FXML
    private Button btnENG;
    final static Logger logger = Logger.getLogger(MenuWindowControl.class);

    @FXML
    private void initialize() {
        btnPL.setOnAction(event -> {
            try {
                changeLanguagePL();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        btnENG.setOnAction(event -> {
            try {
                changeLanguageENG();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        difficulty.getItems().addAll(
                bundle.getString("combo.noob"),
                bundle.getString("combo.easy"),
                bundle.getString("combo.medium"),
                bundle.getString("combo.hard"),
                bundle.getString("combo.pro")
        );
    }

    public static String getLevel() {
        return level;
    }

    public void setLevel(String level){
        this.level = level;
    }

    @FXML
    public void onActionButtonStartGame(ActionEvent actionEvent) throws IOException {
        this.level = difficulty.getSelectionModel().getSelectedItem();
        try{
            if(level == null){
                level = bundle.getString("combo.noob");
                logger.info(bundle.getString("wrongLvl"));
            }
            FxmlStageSetup.buildStage("/GameWindow.fxml",bundle);
        } catch (NullPointerException e){
            AlertWindow alert = new AlertWindow();
            alert.messageBox("",bundle.getString("appError"), Alert.AlertType.INFORMATION);
        }
    }

    @FXML
    public void changeLanguagePL() throws IOException {
        Locale.setDefault(new Locale("pl"));
        FxmlStageSetup.buildStage("/MenuWindow.fxml", bundle.getString("app.menuTitle"), bundle);
        logger.info(bundle.getString("chosenLanguage") + bundle.getString("lang.pl"));
    }

    @FXML
    public void changeLanguageENG() throws IOException {
        Locale.setDefault(new Locale("en"));
        FxmlStageSetup.buildStage("/MenuWindow.fxml", bundle.getString("app.menuTitle"), bundle);
        logger.info(bundle.getString("chosenLanguage") + bundle.getString("lang.eng"));
    }

    Authors authors = new Authors();

    @FXML
    public void getAuthors(ActionEvent actionEvent){
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Autorzy");
    alert.setHeaderText(null);
    alert.setContentText(authors.getObject("1. ") + "\n" + authors.getObject("2. "));
    alert.showAndWait();
    }
}
