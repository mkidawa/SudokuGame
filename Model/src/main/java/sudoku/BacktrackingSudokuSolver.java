package sudoku;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import static java.lang.Character.UNASSIGNED;

public class BacktrackingSudokuSolver implements SudokuSolver {
    private static final int SIZE = 9;

    public final boolean solve(final SudokuBoard board) {
        List<Integer> array = randomize();
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (board.get(row, col) == UNASSIGNED) {
                    for (int i = 0; i < SIZE; i++) {
                        if (isCorrect(row, col, array.get(i), board)) {
                            board.set(row, col, array.get(i));
                            if (solve(board)) {
                                return true;
                            } else {
                                board.set(row, col, UNASSIGNED);
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private List<Integer> randomize() {
        List<Integer> array =
                new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        Collections.shuffle(array);
        return array;
    }

    private boolean isCorrect(final int row, final int col,
                              final int number, final SudokuBoard board) {
        boolean flagRow = false;
        boolean flagCol = false;
        boolean flagBox = false;
        for (int i = 0; i < SIZE; i++) {
            if (board.get(row, i) == number) {
                flagRow = true;
            }
            if (board.get(i, col) == number) {
                flagCol = true;
            }
        }
        int r = row - row % 3;
        int c = col - col % 3;
        for (int i = r; i < r + 3; i++) {
            for (int j = c; j < c + 3; j++) {
                if (board.get(i, j) == number) {
                    flagBox = true;
                }
            }
        }
        return !(flagRow || flagCol || flagBox);
    }
}
