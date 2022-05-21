package Main.Logic;

import Main.Board;
import lenz.htw.gaap.Move;

import java.util.Random;

public class RandomAiLogic extends AiLogic {

    Random rand = new Random();

    @Override
    public Move generateMove(Board board, int myself) {
        int value = rand.nextInt(5) + 1;

        if (myself == 1) {
            return new Move(value, 0);
        } else if (myself == 2) {
            return new Move(0, value);
        } else if (myself == 3) {
            return new Move(value, 7);
        } else if (myself == 4) {
            return new Move(7, value);
        }
        return null;
    }
}
