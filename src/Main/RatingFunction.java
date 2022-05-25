package Main;

public class RatingFunction {
    /**
     * Merkmale
     * - eigene punkte
     * - alle punkte zusammenrechnen
     * - steine auf grundlinie
     */

    private final int ownPointsMultiplier;
    private final int fieldRatingMultiplier;
    private final int stonesOnLineMultiplier;

    public RatingFunction(int ownPoints, int fieldRating, int stonesOnLine) {
        this.ownPointsMultiplier = ownPoints;
        this.fieldRatingMultiplier = fieldRating;
        this.stonesOnLineMultiplier = stonesOnLine;
    }

    public int evaluateGame(Board board, int playerNo){
        int evaluation = (ownPointsMultiplier * board.getPlayerScore(playerNo))
                + (fieldRatingMultiplier * board.getFieldRatingForEnemies(playerNo))
                + (stonesOnLineMultiplier * board.getFreeFieldsOnBaseline(playerNo));

        if(board.hasPlayerWon(playerNo))
            evaluation += 1000;
        else if(board.hasEnemyWon(playerNo))
            evaluation *= -1;

        return evaluation;
    }
}
