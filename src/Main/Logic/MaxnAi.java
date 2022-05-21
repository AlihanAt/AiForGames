package Main.Logic;

import Main.Board;
import lenz.htw.gaap.Move;

public class MaxnAi extends AiLogic {

    final int DEPTH = 8;

    private int myNumber;
    private Move bestMove;

    @Override
    public Move generateMove(Board board, int myNumber) {
        this.myNumber = myNumber;
        maxn(DEPTH, myNumber, board);
        return bestMove;
    }

    private Tupel maxn(int depth, int playerNo, Board board) {

        if(board.hasEnemyWon(myNumber))
            return new Tupel(0,45,45,45);   //zu ändern
        else if(board.hasPlayerWon(myNumber))
            return new Tupel(45,0,0,0);

        if (depth == 0)
            return finalScore(board);

        Tupel maxTupel = new Tupel(-1,-1,-1,-1);
        Board copy = board.deepCopy();
        copy.updateStonePositionsFrom(playerNo);

        for(int i=1; i<7; i++){
            Move move = getMoveFromPlayerNumber(playerNo, i);

            if(!copy.addStone(move.x, move.y, playerNo))
                continue;

            Tupel rating = maxn(depth-1, getNextPlayer(playerNo), copy);

            if(rating.getPointsOfPlayer(playerNo) > maxTupel.getPointsOfPlayer(playerNo)){
                System.out.println("Best Tupel: " + rating + " - davor: " + maxTupel);
                maxTupel.replace(rating);
                if(playerNo == myNumber){
                    bestMove = move;
                }
            }
            else if(rating.getPointsOfPlayer(playerNo) == maxTupel.getPointsOfPlayer(playerNo) && Math.random() > 0.5){
                System.out.println("Best Tupel: " + rating + " - davor: " + maxTupel);
                maxTupel.replace(rating);
                if(playerNo == myNumber){
                    bestMove = move;
                }
            }

            board.clearStone(move.x, move.y);
        }
        return maxTupel;
    }

    private Tupel finalScore(Board board) {
        Tupel score = new Tupel();
        int points;

        for(int i=0; i<4; i++){
            points = board.getPlayerScore(i+1);
            score.setPointsOfPlayer(i+1, points);
        }
        return score;
    }

    private Move getMoveFromPlayerNumber(int playerNo, int i){
        if (playerNo == 1){
            return new Move(i,0);
        }
        else if (playerNo == 2){
            return new Move(0,i);
        }
        else if (playerNo == 3){
            return new Move(i,7);
        }
        else{
            return new Move(7,i);
        }
    }

    private int getNextPlayer(int playerNo){
        int tempPlayerNo;
        if(playerNo == 4)
            tempPlayerNo = 1;
        else
            tempPlayerNo = playerNo +1;

        return tempPlayerNo;
    }
}

class Tupel{

    int[] pointsTupel = new int[4];

    public Tupel(){}

    public Tupel(int p1, int p2, int p3, int p4){
        pointsTupel = new int[] {p1, p2, p3, p4};
    }

    public int[] getTupel(){
        return pointsTupel;
    }

    public void setPointsOfPlayer(int playerNo, int points){
        pointsTupel[playerNo-1] = points;
    }

    public int getPointsOfPlayer(int playerNo) {
        return pointsTupel[playerNo-1];
    }

    public void replace(Tupel rating) {
        for (int i=0; i<4; i++){
            this.pointsTupel[i] = rating.getPointsOfPlayer(i+1);
        }
    }

    @Override
    public String toString(){
        return "[" + pointsTupel[0] + "," + pointsTupel[1] + "," + pointsTupel[2] + "," + pointsTupel[3] + "]";
    }
}
