package Main.Logic;

import Main.Board;
import Main.Logic.Rating.RatingFunction;
import lenz.htw.gaap.Move;

public class MaxnAi extends AiLogic {
    private final int DEPTH = 2;
    private int myNumber;
    private Move bestMove;

    public MaxnAi(RatingFunction ratingFunction) {
        super(ratingFunction);
    }

    @Override
    public Move generateMove(Board board, int myNumber) {
        this.myNumber = myNumber;
        maxn(DEPTH, myNumber, board);
        return bestMove;
    }

    private Tuple maxn(int depth, int playerNo, Board board) {

        if (board.hasEnemyWon(myNumber))
            return finalScore(board);
        else if (board.hasPlayerWon(myNumber))
            return finalScore(board);

        if (depth == 0)
            return finalScore(board);

        Tuple maxTuple = new Tuple(-1, -1, -1, -1);
        Board copy = board.deepCopy();
        copy.updateStonePositionsFrom(playerNo);

        for (int i = 1; i < 7; i++) {
            Move move = getMoveFromPlayerNumber(playerNo, i);

            if (!copy.addStone(move.x, move.y, playerNo))
                continue;

            Tuple rating = maxn(depth - 1, getNextPlayer(playerNo), copy);

            if (rating.getPointsOfPlayer(playerNo) > maxTuple.getPointsOfPlayer(playerNo)) {
                maxTuple.replace(rating);

                if (playerNo == myNumber && depth == DEPTH) {
                    bestMove = move;
                }
            }

            copy.clearStone(move.x, move.y);
        }
        return maxTuple;
    }

    private Tuple finalScore(Board board) {
        Tuple score = new Tuple();
        float points;
        for (int i = 0; i < 4; i++) {
//            points = board.getPlayerScore(i+1);
            points = ratingFunction.evaluateGame(board, i + 1);
            score.setPointsOfPlayer(i + 1, points);
        }
        return score;
    }

    private Move getMoveFromPlayerNumber(int playerNo, int i) {
        if (playerNo == 1) {
            return new Move(i, 0);
        } else if (playerNo == 2) {
            return new Move(0, i);
        } else if (playerNo == 3) {
            return new Move(i, 7);
        } else {
            return new Move(7, i);
        }
    }

    private int getNextPlayer(int playerNo) {
        int tempPlayerNo;
        if (playerNo == 4)
            tempPlayerNo = 1;
        else
            tempPlayerNo = playerNo + 1;

        return tempPlayerNo;
    }

    private static class Tuple {
        float[] pointsTuple = new float[4];

        public Tuple() {
        }

        public Tuple(float p1, float p2, float p3, float p4) {
            pointsTuple = new float[]{p1, p2, p3, p4};
        }

        public void setPointsOfPlayer(int playerNo, float points) {
            pointsTuple[playerNo - 1] = points;
        }

        public float getPointsOfPlayer(int playerNo) {
            return pointsTuple[playerNo - 1];
        }

        public void replace(Tuple rating) {
            for (int i = 0; i < 4; i++) {
                this.pointsTuple[i] = rating.getPointsOfPlayer(i + 1);
            }
        }

        @Override
        public String toString() {
            return "[" + pointsTuple[0] + "," + pointsTuple[1] + "," + pointsTuple[2] + "," + pointsTuple[3] + "]";
        }
    }
}