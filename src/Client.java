import lenz.htw.gaap.Move;
import lenz.htw.gaap.net.NetworkClient;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Client {

    public Client(){}

    public Client(int playerNo){
        myNumber = playerNo;
    }

    private final Board board = new Board();
    private int myNumber;
    private boolean init;
    private int lastPlayer = 0;

    public static void main(String[] args) throws IOException {
        Client client = new Client();
        client.start();
    }

    public void start() throws IOException {
        NetworkClient client = new NetworkClient("localhost", "alsi", ImageIO.read(new File("mc.png")));

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
            System.out.println(board);
        }
    }

    public Move doOwnTurn() {
        if (myNumber == 1 && !init) {
            init = true;
            return generateRandomMove(myNumber);
        } else {
            board.updateSkippedPlayers(lastPlayer, myNumber);
            return generateGoodMove(myNumber);
        }
    }

    public void updateOnMoveReceived(Move move) {
        int currentPlayer = board.getPlayerFromMove(move.x, move.y);

        if (currentPlayer != myNumber)
         board.updateSkippedPlayers(lastPlayer, currentPlayer);

        lastPlayer = currentPlayer;
        board.addMove(move.x, move.y);
    }


    Random rand =  new Random();
    private Move generateRandomMove(int myself){
        int value = rand.nextInt(5) + 1;

        if (myself == 1){
            return new Move(value, 0);
        }
        else if (myself == 2){
            return new Move(0, value);
        }
        else if (myself == 3){
            return new Move(value, 7);
        }
        else if (myself == 4){
            return new Move(7, value);
        }
        return null;
    }

    private Move generateGoodMove(int myself){
        if (myself == 1){

            for (int i=1; i<board.getBoard().length; i++){
                if(!board.getBoard()[i][0].isFieldInUse())
                    return new Move(i,0);
            }
        }
        else if (myself == 2){

            for (int i=1; i<board.getBoard().length; i++){
                if(!board.getBoard()[0][i].isFieldInUse())
                    return new Move(0,i);
            }
        }
        else if (myself == 3){

            for (int i=1; i<board.getBoard().length; i++){
                if(!board.getBoard()[i][7].isFieldInUse())
                    return new Move(i,7);
            }

        }
        else if (myself == 4){

            for (int i=1; i<board.getBoard().length; i++){
                if(!board.getBoard()[7][i].isFieldInUse())
                    return new Move(7,i);
            }

        }
        return null;
    }

    public boolean areEqual(Client client){
       return board.toString().equals(client.board.toString());
    }

    public Board getBoard(){
        return board;
    }

}