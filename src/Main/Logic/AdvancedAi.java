package Main.Logic;

import Main.Board;
import lenz.htw.gaap.Move;

public class AdvancedAi extends BoardLogic {

    @Override
    public Move generateMove(Board board, int myNumber) {
        Board clone = board.deepCopy();
        clone.updateStonePositionsFrom(myNumber);

        if (myNumber == 1) {
            for (int i = 1; i < clone.getBoard().length; i++) {
                if (!clone.getBoard()[i][0].isFieldInUse())
                    return new Move(i, 0);
            }
        } else if (myNumber == 2) {
            for (int i = 1; i < clone.getBoard().length; i++) {
                if (!clone.getBoard()[0][i].isFieldInUse())
                    return new Move(0, i);
            }
        } else if (myNumber == 3) {
            for (int i = 1; i < clone.getBoard().length; i++) {
                if (!clone.getBoard()[i][7].isFieldInUse())
                    return new Move(i, 7);
            }
        } else if (myNumber == 4) {
            for (int i = 1; i < clone.getBoard().length; i++) {
                if (!clone.getBoard()[7][i].isFieldInUse())
                    return new Move(7, i);
            }
        }

        return null;
    }
}
