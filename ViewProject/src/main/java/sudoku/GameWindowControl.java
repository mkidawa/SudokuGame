package sudoku;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;


public class GameWindowControl {
    private ResourceBundle bundle = ResourceBundle.getBundle("bundles.language");
    @FXML
    private GridPane sudokuGrid;
    private AlertWindow alertWindow = new AlertWindow();
    private SudokuBoard sudokuBoard = new SudokuBoard();
    final static Logger logger = Logger.getLogger(MenuWindowControl.class);
    private String Level = MenuWindowControl.getLevel();

    @FXML
    public void initialize(){
        try {
            solveBoard();
            checkCompability();
            eraseFields();
            fillFields();
        } catch (WrongBoardException e) {
            logger.error(bundle.getString("emptyBoardException"));
        } catch (UnknownLevelException e) {
            logger.error(bundle.getString("unknownLvl"));
        }
    }

    public void solveBoard(){
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        solver.solve(sudokuBoard);
    }

    public void eraseFields() throws UnknownLevelException {
        DifficultyLevel difficulty = new DifficultyLevel();
        difficulty.chooseLevel(sudokuBoard, Level);
    }

    public boolean checkSudoku(){
        return sudokuBoard.checkBoard();
    }

    public void checkCompability() throws WrongBoardException {
        if(!sudokuBoard.checkBoard()){
            throw new WrongBoardException(bundle.getString("emptyBoardException"));
        }
    }

    private void fillFields() {
        for (int i = 0; i < SudokuBoard.getSize(); i++) {
            for (int j = 0; j < SudokuBoard.getSize(); j++) {
                int row = i;
                int column = j;
                TextField textField = new TextField();
                customizeTextField(textField, row, column);
                changeBoardValue(textField,row,column);
                sudokuGrid.add(textField, j, i);
            }
        }
    }

    /**
     * @param textField - TextField that we want to customize and later add to GridPane
     * @param row - row number of TextField
     * @param column - column number of TextField
     */
    public void customizeTextField(TextField textField, int row, int column){
        textField.setMinSize(50, 56);
        textField.setFont(Font.font(18));
        textField.setAlignment(Pos.CENTER);
        textField.setStyle("-fx-text-color: black;");
        if (sudokuBoard.get(row, column) != 0) {
            textField.setDisable(true);
            textField.setText(String.valueOf(sudokuBoard.get(row, column)));
        }
    }

    /**
     * Method adds listener textField and changes value in sudokuBoard everytime value of textField is changed
     * @param textField - textField that will have a listener assigned to
     * @param row - row number
     * @param column - column number
     */
    public void changeBoardValue(TextField textField, int row, int column){
        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(!validateFieldValue(newValue)){
                    Platform.runLater(() -> textField.clear());
                }
                else{
                    sudokuBoard.set(row,column,Integer.parseInt(newValue));
                }
            }
        });
    }

    public void setSudokuBoard(int row, int column, int value){
        sudokuBoard.set(row, column, value);
    }

    /**
     * @param fieldValue - value of TextField
     * @return method returns true if given value is not against rules of sudoku (range 1-9, integer), else returns false
     */
    public boolean validateFieldValue(String fieldValue){
        if(fieldValue.length() == 1 && "123456789".contains(fieldValue))
            return true;
        else return false;
    }

    @FXML
    public void onActionButtonCheck(ActionEvent actionEvent) throws IOException {
        if (sudokuBoard.checkBoard()) {
            alertWindow.messageBox("", bundle.getString("won"), Alert.AlertType.INFORMATION);
            logger.info(bundle.getString("won"));
            FxmlStageSetup.buildStage("/GameWindow.fxml",bundle);
        } else {
            alertWindow.messageBox("", bundle.getString("lost"), Alert.AlertType.INFORMATION);
            logger.info(bundle.getString("lost"));
            FxmlStageSetup.buildStage("/MenuWindow.fxml",bundle);
        }
    }

    @FXML
    public void startNewGame(ActionEvent actionEvent) throws IOException {
        FxmlStageSetup.buildStage("/GameWindow.fxml",bundle);
    }

    @FXML
    public void saveGame() throws JdbcDaoException {
        JdbcSudokuBoardDao jdbcDao = new JdbcSudokuBoardDao("boardSave");
        jdbcDao.write(sudokuBoard);
    }

    private void saveGameTxt() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt")
        );
        try {
            File selectedFile = fileChooser.showSaveDialog(new Stage());
            FileSudokuBoardDao dao = new FileSudokuBoardDao(selectedFile.getAbsolutePath());
            dao.write(sudokuBoard);
        } catch (NullPointerException e) {
            logger.error(bundle.getString("fileException"), e);
        }
    }

    @FXML
    public void loadGame() throws JdbcDaoException {
        JdbcSudokuBoardDao jdbcDao = new JdbcSudokuBoardDao("boardSave");
        sudokuBoard = jdbcDao.read();
        reloadGrid();
    }

    private void loadGameTxt() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt")
        );
        try {
            File selectedFile = fileChooser.showOpenDialog(new Stage());
            FileSudokuBoardDao dao = new FileSudokuBoardDao(selectedFile.getAbsolutePath());
            sudokuBoard = dao.read();
        } catch (NullPointerException e) {
            logger.error(bundle.getString("fileException"), e);
        }
        reloadGrid();
    }

    /**
     * Method reloads the GridPane and fills it with new TextFields
     */
    void reloadGrid(){
        sudokuGrid.getChildren().clear();
        fillFields();
    }

    @FXML
    public void backToMenu(ActionEvent actionEvent) throws IOException {
        FxmlStageSetup.buildStage("/MenuWindow.fxml",bundle);
    }

    public int getEmptyField(){
        int zerosCount = 0;
        for(int i=0;i<SudokuBoard.getSize();i++){
            for(int j=0;j<SudokuBoard.getSize();j++){
                if(sudokuBoard.get(i,j) == 0){
                    zerosCount++;
                }
            }
        }
        return zerosCount;
    }

}
