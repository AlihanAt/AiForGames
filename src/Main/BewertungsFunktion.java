package Main;

public class BewertungsFunktion {
    /**
     * Merkmale
     * - eigene punkte
     * - alle punkte zusammenrechnen
     * - steine auf grundlinie
     */

    private int ownPointsMultiplier;
    private int fieldRatingMultiplier;
    private int stonesOnLineMultiplier;
    private int isWinningMoveMultiplier;

    public BewertungsFunktion(int ownPoints, int fieldRating, int stonesOnLine, int isWinningMove) {
        this.ownPointsMultiplier = ownPoints;
        this.fieldRatingMultiplier = fieldRating;
        this.stonesOnLineMultiplier = stonesOnLine;
        this.isWinningMoveMultiplier = isWinningMove;
    }

    public int getOwnPointsMultiplier() {
        return ownPointsMultiplier;
    }

    public int getFieldRatingMultiplier() {
        return fieldRatingMultiplier;
    }

    public int getStonesOnLineMultiplier() {
        return stonesOnLineMultiplier;
    }

    public int getIsWinningMoveMultiplier() {
        return isWinningMoveMultiplier;
    }
}
