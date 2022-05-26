package Main.Logic;

import Main.Board;
import Main.Logic.Rating.RatingFunction;
import lenz.htw.gaap.Move;

public abstract class AiLogic {
    protected RatingFunction ratingFunction;

    public AiLogic() {
    }

    protected AiLogic(RatingFunction ratingFunction) {
        this.ratingFunction = ratingFunction;
    }

    public abstract Move generateMove(Board board, int myNumber);
}