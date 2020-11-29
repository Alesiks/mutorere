package by.xxx.pupil.ai;


import by.xxx.pupil.Board;
import by.xxx.pupil.Cell;

import static by.xxx.pupil.Constants.DEFAULT_AI_CELL_TYPE;

public interface NextMoveFinder {

    Move findNextMove(Board board, Cell cell);

    default Move findNextMove(Board board) {
        return findNextMove(board, DEFAULT_AI_CELL_TYPE);
    }

}
