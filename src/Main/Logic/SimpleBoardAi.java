package Main.Logic;

import Main.Board;
import lenz.htw.gaap.Move;

public class SimpleBoardAi extends BoardLogic {

    @Override
    public Move generateMove(Board board, int myNumber) {
        if (myNumber == 1) {
            for (int i = 1; i < board.getBoard().length; i++) {
                if (!board.getBoard()[i][0].isFieldInUse())
                    return new Move(i, 0);
            }
        } else if (myNumber == 2) {
            for (int i = 1; i < board.getBoard().length; i++) {
                if (!board.getBoard()[0][i].isFieldInUse())
                    return new Move(0, i);
            }
        } else if (myNumber == 3) {
            for (int i = 1; i < board.getBoard().length; i++) {
                if (!board.getBoard()[i][7].isFieldInUse())
                    return new Move(i, 7);
            }
        } else if (myNumber == 4) {
            for (int i = 1; i < board.getBoard().length; i++) {
                if (!board.getBoard()[7][i].isFieldInUse())
                    return new Move(7, i);
            }
        }
        return null;
    }
}
