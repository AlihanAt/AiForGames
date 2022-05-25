package Main;

import Main.Logic.AiLogic;
import Main.Logic.SimpleBoardAi;
import lenz.htw.gaap.Move;
import lenz.htw.gaap.net.NetworkClient;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Client implements Runnable{

    public Client(String name, AiLogic logic){
        this.name = name;
        this.logic = logic;
    }

    public Client(String name, AiLogic logic, BewertungsFunktion bewertungsFunktion){
        this.name = name;
        this.logic = logic;
        this.board = new Board(bewertungsFunktion);
    }

    public Client(AiLogic logic){
        this.logic = logic;
    }

    public Client(int playerNo){
        myNumber = playerNo;
        logic = new SimpleBoardAi();
    }

    public Client(int playerNo, AiLogic logic){
        myNumber = playerNo;
        this.logic = logic;
    }

    private String name;

    private Board board = new Board();
    private AiLogic logic;

    private int myNumber;
    private boolean init;
    private int lastPlayer = 0;

    public void start() throws IOException {
        NetworkClient client = new NetworkClient("localhost", name, ImageIO.read(new File("mc.png")));

        myNumber = client.getMyPlayerNumber() + 1;
        Player myself = board.getPlayerAndRegister(myNumber);
        System.out.println("MyNumber " + myNumber);
        Move move;

        try {
            while (true) {
                if ((move = client.receiveMove()) != null) {
                    updateOnMoveReceived(move);
                }
                else {
                    client.sendMove(doOwnTurn());
                }
            }
        }catch (Exception e){
            System.out.println(e);
//            System.out.println(board + "\n");
//            System.out.println("Scoreboard for PlayerNo " + myNumber + ": " + board.getPlayerScore(1) + ", " + board.getPlayerScore(2) + ", " + board.getPlayerScore(3) + ", " + board.getPlayerScore(4) +"\n");
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
        board.addMove(move.x, move.y);
    }

    public boolean areEqual(Client client){
       return board.toString().equals(client.board.toString());
    }

    public Board getBoard(){
        return board;
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