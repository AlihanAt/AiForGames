package Main;

import Main.Logic.MaxnAi;
import lenz.htw.gaap.Move;

public class GameMock {

    // error catchen, worng move und game vorbei
    // game reproduzieren

    Board board;
    MaxnAi maxn = new MaxnAi();
    boolean[] thrownPlayers = new boolean[4];
    int[] results = new int[4];

    int counter = 0;

    public int[] startGameSimulation(){

        Player p1 = new Player(1,false);
        Player p2 = new Player(2, false);
        Player p3 = new Player(3, false);
        Player p4 = new Player(4, false);

        Player[] players =  new Player[4];
        players[0] = p1;
        players[1] = p2;
        players[2] = p3;
        players[3] = p4;

        board = new Board(players);
        board.getPlayerAndRegister(1);

        while (true){
            for(int i=1; i<5; i++){

                if(!thrownPlayers[i - 1]){
                    doTurn(i);
                    System.out.println(board + "\n");
                }
                else
                    board.updateStonePositionsFrom(i);

                if (board.hasAnyoneWon())
                    break;
            }

            if (board.hasAnyoneWon())
                break;
        }

        fillResults(players);
        return results;
    }

    private void fillResults(Player[] players) {
        results[0] = players[0].getPoints();
        results[1] = players[1].getPoints();
        results[2] = players[2].getPoints();
        results[3] = players[3].getPoints();
    }

    private void doTurn(int playerNo) {

        Move move = maxn.generateMove(board, playerNo);
        board.updateStonePositionsFrom(playerNo);

        if(move == null || !board.checkMoveIsLegal(move.x, move.y)){
            thrownPlayers[playerNo - 1] = true;
            System.out.println("Illegal Move of Player " + playerNo + ", Move: " + move.x + ", " + move.y);
        }
        else {
            board.addStone(move.x, move.y, playerNo);
            System.out.println("Player " + playerNo + " Move: " + move.x + ", " + move.y);
        }
    }

    private void drawPosition(int counter){

    }

}
