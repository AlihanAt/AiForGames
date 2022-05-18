package Main;

import Main.Logic.AdvancedAi;
import Main.Logic.BoardLogic;
import Main.Logic.SimpleBoardAi;
import lenz.htw.gaap.Move;
import lenz.htw.gaap.net.NetworkClient;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Client implements Runnable{

    public Client(String name, BoardLogic logic){
        this.name = name;
        this.logic = logic;
    }

    public Client(BoardLogic logic){
        this.logic = logic;
    }

    public Client(int playerNo){
        myNumber = playerNo;
        logic = new SimpleBoardAi();
    }

    public Client(int playerNo, BoardLogic logic){
        myNumber = playerNo;
        this.logic = logic;
    }

    private String name;

    private final Board board = new Board();
    private BoardLogic logic;

    private int myNumber;
    private boolean init;
    private int lastPlayer = 0;

//    public static void main(String[] args) throws IOException {
//        Client client = new Client();
//        client.start();
//    }

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