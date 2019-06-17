package sudoku;
import java.util.*;
import org.apache.log4j.Logger;

public class DifficultyLevel {

    private Random rand = new Random();
    private int[] erased = {9,21,48,64,80};
    private Set<FieldPosition> randomPositions = new HashSet<>();
    private enum Level {Noob, Easy, Medium,Hard,Pro}
    private Level diffLevel;
    private ResourceBundle bundle = ResourceBundle.getBundle("bundles.language");

    final static Logger logger = Logger.getLogger(MenuWindowControl.class);

    private void fillRandomPositionsList(int number_of_fields_to_erase) {
        for (int i = 0; i < number_of_fields_to_erase; i++) {
            boolean isAdded = false;
            while (!isAdded) {
                int axisX = rand.nextInt(9);
                int axisY = rand.nextInt(9);
                isAdded = randomPositions.add(new FieldPosition(axisX, axisY));
            }
        }
    }

    public SudokuBoard chooseLevel(SudokuBoard sudokuBoard, String level) throws UnknownLevelException {
        setLevel(level);
        containsLevel();
        if (diffLevel.equals(Level.Noob)) {
            fillRandomPositionsList(erased[0]);
            logger.info(bundle.getString("chosenDifficulty") + bundle.getString("combo.noob"));
        } else if (diffLevel.equals(Level.Easy)) {
            fillRandomPositionsList(erased[1]);
            logger.info(bundle.getString("chosenDifficulty") + bundle.getString("combo.easy"));
        } else if (diffLevel.equals(Level.Medium)) {
            fillRandomPositionsList(erased[2]);
            logger.info(bundle.getString("chosenDifficulty") + bundle.getString("combo.medium"));
        } else if (diffLevel.equals(Level.Hard)) {
            fillRandomPositionsList(erased[3]);
            logger.info(bundle.getString("chosenDifficulty") + bundle.getString("combo.hard"));
        } else if (diffLevel.equals(Level.Pro)) {
            fillRandomPositionsList(erased[4]);
            logger.info(bundle.getString("chosenDifficulty") + bundle.getString("combo.pro"));
        }
        for (FieldPosition it : randomPositions) {
            sudokuBoard.set(it.getFieldX(), it.getFieldY(), 0);
        }
        return sudokuBoard;
    }


    public void setLevel(String level) {
        switch (level) {
            case "Noob":
            case "Świeżak": {
                this.diffLevel = Level.Noob;
                break;
            }
            case "Easy":
            case "Łatwy": {
                this.diffLevel = Level.Easy;
                break;
            }
            case "Medium":
            case "Średni": {
                this.diffLevel = Level.Medium;
                break;
            }
            case "Hard":
            case "Trudny": {
                this.diffLevel = Level.Hard;
                break;
            }
            case "Sudoku Pro":
            case "Profesjonalista": {
                this.diffLevel = Level.Pro;
                break;
            }
        }
    }

    public void containsLevel() throws UnknownLevelException{
        if (diffLevel != Level.Noob && diffLevel != Level.Easy && diffLevel != Level.Medium && diffLevel != Level.Hard && diffLevel != Level.Pro)
            throw new UnknownLevelException(bundle.getString("unknownLvl"));
    }


}
