package Main;

import Main.Logic.MaxnAi;
import lenz.htw.gaap.Move;

public class GameMock {

    public GameMock(RatingFunction[] ratingFunctions){
        clients[0] = new Client("c1", 1, new MaxnAi(ratingFunctions[0]));
        clients[1] = new Client("c2", 2, new MaxnAi(ratingFunctions[1]));
        clients[2] = new Client("c3", 3, new MaxnAi(ratingFunctions[2]));
        clients[3] = new Client("c4", 4, new MaxnAi(ratingFunctions[3]));
    }

    Board board;
    boolean[] thrownPlayers = new boolean[4];
    Client[] clients = new Client[4];

    public int[] startGameSimulation(){
        Move move;
        board = new Board();

        while (true){
            for(int i=0; i<4; i++){
                board.updateStonePositionsFrom(i+1);

                if(thrownPlayers[i])
                    continue;

                move = clients[i].doOwnTurn();

                if (board.checkMoveIsLegal(move.x, move.y)){
                    board.addStone(move.x, move.y, i+1);
                    for (Client client : clients) {
                        client.updateOnMoveReceived(move);
                    }
                }
                else{
                    thrownPlayers[i] = true;
                }

                if(board.hasAnyoneWon())
                    break;
            }
            if(board.hasAnyoneWon())
                break;
        }

        return fillResults();
    }

    private int[] fillResults() {
        int[] results = new int[4];
        results[0] = board.getPlayerScore(1);
        results[1] = board.getPlayerScore(2);
        results[2] = board.getPlayerScore(3);
        results[3] = board.getPlayerScore(4);
        return results;
    }

}
