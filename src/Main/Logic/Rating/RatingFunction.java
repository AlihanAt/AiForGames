package Main.Logic.Rating;

import Main.Board;

public abstract class RatingFunction {

    public abstract float evaluateGame(Board board, int playerNo);

    public abstract RatingFunction deepCopyMutate();
}
