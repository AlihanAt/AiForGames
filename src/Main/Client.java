package Main;

import Main.Logic.AiLogic;
import lenz.htw.gaap.Move;
import lenz.htw.gaap.net.NetworkClient;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Client implements Runnable {

    private final AiLogic logic;
    private final String name;
    private final Board board;
    private int myNumber;
    private boolean init;
    private int lastPlayer = 0;

    public Client(String name, AiLogic logic) {
        this.name = name;
        this.logic = logic;
        board = new Board();
    }

    public Client(String name, int playerNo, AiLogic logic) {
        myNumber = playerNo;
        this.name = name;
        this.logic = logic;
        board = new Board();
    }

    public void start() throws IOException {
        NetworkClient client = new NetworkClient("localhost", name, ImageIO.read(new File("mc.png")));

        myNumber = client.getMyPlayerNumber() + 1;
        board.getPlayerAndRegister(myNumber);
        System.out.println("MyNumber " + myNumber);
        Move move;

        try {
            while (true) {
                if ((move = client.receiveMove()) != null) {
                    updateOnMoveReceived(move);
                } else {
                    client.sendMove(doOwnTurn());
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Move doOwnTurn() {
        if (myNumber == 1 && !init) {
            init = true;
        } else {
            board.updateSkippedPlayers(lastPlayer, myNumber);
        }
        return logic.generateMove(board, myNumber);
    }

    public void updateOnMoveReceived(Move move) {
        int currentPlayer = board.getPlayerFromMove(move.x, move.y);

        if (currentPlayer != myNumber)
            board.updateSkippedPlayers(lastPlayer, currentPlayer);

        lastPlayer = currentPlayer;
        board.updateAndAddMove(move.x, move.y);
    }

    public Board getBoard() {
        return board;
    }

    public void setMyNumber(int number){
        myNumber = number;
    }

    @Override
    public void run() {
        try {
            start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}