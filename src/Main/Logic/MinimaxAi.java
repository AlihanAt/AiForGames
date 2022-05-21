package Main.Logic;

import Main.Board;
import lenz.htw.gaap.Move;

public class MinimaxAi extends AiLogic {

    final int DEPTH = 8;

    private int myNumber;
    private Move bestMove;

    @Override
    public Move generateMove(Board board, int myNumber) {
        this.myNumber = myNumber;
        minimax(DEPTH, myNumber, board);
        return bestMove;
    }

    private int minimax(int depth, int playerNo, Board board){

        if(board.hasEnemyWon(myNumber))
            return -1000;
        else if(board.hasPlayerWon(myNumber))
            return 1000;

        if (depth == 0) {
            if (playerNo == myNumber){
                return board.getFieldRatingForPlayer(playerNo);
            }
            else return board.getFieldRatingForEnemies(myNumber);
        }

        Board copy = board.deepCopy();

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        copy.updateStonePositionsFrom(playerNo);

        for(int i=1; i<7; i++){

            Move move = getMoveFromPlayerNumber(playerNo, i);

            if(!copy.addStone(move.x, move.y, playerNo))
                continue;

            int rating = minimax(depth-1, getNextPlayer(playerNo), copy);

            if(playerNo == myNumber){

                if(rating > max){
                    max = rating;
                    bestMove = move;
                }
                else  if(rating == max && Math.random() > 0.5){
                    max = rating;
                    bestMove = move;
                }
            }
            else{
                min = Math.min(rating,min);

            }
            copy.clearStone(move.x, move.y);
        }

        if (playerNo == myNumber){
            return Math.max(copy.getFieldRatingForPlayer(playerNo), max);
        }
        else return Math.min(copy.getFieldRatingForEnemies(myNumber), min);
    }

    private int getNextPlayer(int playerNo){
        int tempPlayerNo;
        if(playerNo == 4)
            tempPlayerNo = 1;
        else
            tempPlayerNo = playerNo +1;

        return tempPlayerNo;
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

}
