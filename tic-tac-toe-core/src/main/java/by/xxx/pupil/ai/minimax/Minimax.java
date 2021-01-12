package by.xxx.pupil.ai.minimax;

import by.xxx.pupil.WinnerFinder;
import by.xxx.pupil.model.Board;
import by.xxx.pupil.model.CellType;
import by.xxx.pupil.model.GameState;
import by.xxx.pupil.model.Move;

import java.util.List;


public class Minimax {

    private final int depthLimit;
    private final WinnerFinder winnerFinder;
    private final PossibleMovesFinder possibleMovesFinder;
    private final Evaluator evaluator;

    public Minimax(int depthLimit, PossibleMovesFinder possibleMovesFinder, Evaluator evaluator, WinnerFinder winnerFinder) {
        this.depthLimit = depthLimit;
        this.possibleMovesFinder = possibleMovesFinder;
        this.evaluator = evaluator;
        this.winnerFinder = winnerFinder;
    }

    /**
     * @param board - presents current game state
     * @param currDepth - current level in game tree which is build using minimax algorithm
     * @param isMaximizingPlayer - is current player maximize score or minimize
     * @param alpha - the best value that the maximizer currently can guarantee at that level or above.
     * @param beta - the best value that the minimizer currently can guarantee at that level or above.
     * @return min or max (depends on player type) value that minimax can achieve on that level
     */
    public int minimax(Board board, int currDepth, boolean isMaximizingPlayer, int alpha, int beta) {
        if (currDepth > depthLimit) {
            return evaluator.evaluate(board, GameState.GAME_CONTINUES);
        }

        int bestValue;
        if (isMaximizingPlayer) {
            bestValue = Integer.MIN_VALUE;
            List<Move> possibleMoves = possibleMovesFinder.getAvailableMoves(board);
            for (Move currentMove : possibleMoves) {
                board.updateCellToPossibleValue(currentMove.getI(), currentMove.getJ(), CellType.NOUGHT);
                int value;
                if (winnerFinder.isMoveLeadToWin(board, currentMove)) {
                    value = evaluator.evaluate(board, GameState.NOUGHT_WIN);
                } else {
                    value = minimax(board, currDepth + 1, false, alpha, beta);
                }
                board.updateCellValue(currentMove.getI(), currentMove.getJ(), CellType.EMPTY);
                bestValue = Math.max(bestValue, value);
                alpha = Math.max(alpha, bestValue);
                if (beta <= alpha) {
                    break;
                }
            }
        } else {
            bestValue = Integer.MAX_VALUE;
            List<Move> possibleMoves = possibleMovesFinder.getAvailableMoves(board);
            for (Move currentMove : possibleMoves) {
                board.updateCellToPossibleValue(currentMove.getI(), currentMove.getJ(), CellType.CROSS);
                int value;
                if (winnerFinder.isMoveLeadToWin(board, currentMove)) {
                    value = evaluator.evaluate(board, GameState.CROSS_WIN);
                } else {
                    value = minimax(board, currDepth + 1, true, alpha, beta);
                }
                board.updateCellValue(currentMove.getI(), currentMove.getJ(), CellType.EMPTY);
                bestValue = Math.min(bestValue, value);
                beta = Math.min(beta, bestValue);
                if (beta <= alpha) {
                    break;
                }
            }
        }

        return bestValue;
    }

}
