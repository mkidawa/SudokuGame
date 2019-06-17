package sudoku;
import java.io.*;

public class FileSudokuBoardDao implements Dao<SudokuBoard> {

    private String filename;

    public FileSudokuBoardDao(String filename) {
        this.filename = filename;
    }

    @Override
    public SudokuBoard read() {
        SudokuBoard obj = null;
        // instrukcja try-with-resources
        try (FileInputStream fIS = new FileInputStream(filename);
             ObjectInputStream objectInputStream = new ObjectInputStream(fIS)) {
             obj = (SudokuBoard) objectInputStream.readObject();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return obj;
    }

    @Override
    public void write(SudokuBoard obj) {
        try (FileOutputStream fOS = new FileOutputStream(filename);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fOS)) {
            objectOutputStream.writeObject(obj);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
