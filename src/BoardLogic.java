import lenz.htw.gaap.Move;

import java.util.Random;

public abstract class BoardLogic {

    public abstract Move generateMove(Board board, int myNumber);

}

class SimpleBoardAi extends BoardLogic {

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

class AdvancedAi extends BoardLogic{

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

class RandomAiLogic extends BoardLogic{

    Random rand =  new Random();

    @Override
    public Move generateMove(Board board, int myself) {
        int value = rand.nextInt(5) + 1;

        if (myself == 1){
            return new Move(value, 0);
        }
        else if (myself == 2){
            return new Move(0, value);
        }
        else if (myself == 3){
            return new Move(value, 7);
        }
        else if (myself == 4){
            return new Move(7, value);
        }
        return null;
    }
}