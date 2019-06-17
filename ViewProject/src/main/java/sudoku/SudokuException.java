package sudoku;

public class SudokuException extends IllegalArgumentException {
    public SudokuException(String message){
        super(message);
    }
}