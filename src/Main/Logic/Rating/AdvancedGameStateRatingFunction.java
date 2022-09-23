package Main.Logic.Rating;

import Main.Board;

import java.util.Random;

public class AdvancedGameStateRatingFunction extends RatingFunction {

    private final float ownPointsMultiplier;
    private final float fieldRatingMultiplier;
    private final float stonesOnLineMultiplier;

    private final float playerWonEvaluation = 10000;
    private final float playerLostEvaluation = -10000;
    private final float noFreeFieldEvaluation = -100;

    public AdvancedGameStateRatingFunction(float ownPoints, float fieldRating, float stonesOnLine) {
        this.ownPointsMultiplier = ownPoints;
        this.fieldRatingMultiplier = fieldRating;
        this.stonesOnLineMultiplier = stonesOnLine;
    }

    public float evaluateGame(Board board, int playerNo) {
        float evaluation = (ownPointsMultiplier * board.getPlayerScore(playerNo))
                - (fieldRatingMultiplier * board.getFieldRatingForEnemies(playerNo))
                + (stonesOnLineMultiplier * board.getFreeFieldsOnBaseline(playerNo));

        if(board.getFreeFieldsOnBaseline(playerNo) == 0){
            evaluation += noFreeFieldEvaluation;
        }

        if (board.hasPlayerWon(playerNo))
            evaluation += playerWonEvaluation;
        else if (board.hasEnemyWon(playerNo))
            evaluation += playerLostEvaluation;

        return evaluation;
    }

    public AdvancedGameStateRatingFunction deepCopyMutate() {
        Random random = new Random();
        int mod = random.nextInt(10) + 1;
        float modifier = random.nextFloat() * mod;
        return new AdvancedGameStateRatingFunction(ownPointsMultiplier + modifier, fieldRatingMultiplier + modifier, stonesOnLineMultiplier + modifier);
    }

    @Override
    public String toString() {
        return String.format("Rating: %f | %f | %f", ownPointsMultiplier, fieldRatingMultiplier, stonesOnLineMultiplier);
    }
}
