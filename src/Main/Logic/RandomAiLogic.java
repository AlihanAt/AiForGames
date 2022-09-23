package Main.Logic;

import Main.Board;
import lenz.htw.gaap.Move;

import java.util.Random;

public class RandomAiLogic extends AiLogic {

    Random rand = new Random();

    @Override
    public Move generateMove(Board board, int myself) {
        boolean legalMove = false;
        Move move = new Move(0,0);

        while(!legalMove) {
            int value = rand.nextInt(5) + 1;

            if (myself == 1) {
                move = new Move(value, 0);
            } else if (myself == 2) {
                move = new Move(0, value);
            } else if (myself == 3) {
                move = new Move(value, 7);
            } else if (myself == 4) {
                move = new Move(7, value);
            }

            if(board.checkMoveIsLegal(move.x, move.y)){
                legalMove = true;
            }
        }
        return move;
    }
}
