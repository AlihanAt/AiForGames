import lenz.htw.gaap.Move;
import lenz.htw.gaap.net.NetworkClient;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Client {

    private final Board board = new Board();

    private boolean init;
    private int lastPlayer = 0;

    public static void main(String[] args) throws IOException {
        Client client = new Client();
        client.start();
    }

    public void start() throws IOException {
        NetworkClient client = new NetworkClient("localhost", "alsi", ImageIO.read(new File("mc.png")));

        int myNumber = client.getMyPlayerNumber() + 1;
        Player myself = board.getPlayerAndRegister(myNumber);
        System.out.println("MyNumber " + myNumber);
        Move move;

        try {
            while (true) {

                if ((move = client.receiveMove()) != null) {
                    int currentPlayer = board.getPlayerFromMove(move.x, move.y);
                    //TODO skipplayers updaten aus der sicht von anderen spielern
                    //if (currentPlayer == myNumber)
                        // board.updateSkippedPlayers(lastPlayer, currentPlayer);
                    lastPlayer = currentPlayer;
                    board.addMove(move.x, move.y);
                }
                //my turn
                else {
                    if (myNumber == 1 && !init) {
                        client.sendMove(generateRandomMove(myNumber));
                        init = true;
                    } else {
                        board.updateSkippedPlayers(lastPlayer, myNumber);

                        move = generateRandomMove(myNumber);
                        client.sendMove(move);
                    }
                }

//            while ((move = client.receiveMove()) != null) {
//                    client.sendMove(sendRandomMove(myNumber));
//
//
////                //zug von gegenspielerInnen erhalten
////                int playerNo = checkMovePosition(move);
////
////                if (myself.getNumber() != playerNo) {
////                    //in board speichern
////                    board.addStone(move.x, move.y, playerNo);
////
////                    //schieben durchführen?
////                    board.updateStonePositionsFrom(playerNo);
////                }
////
////                if(playerNo == myNumber-1 || (myNumber == 1 && playerNo==4)){
////                    move = sendRandomMove(myNumber);
////                    client.sendMove(move);
////                }
//
//            }
            }
        }catch (Exception e){
            System.out.println(board);
        }
    }



    Random rand =  new Random();
    private Move generateRandomMove(int myself){
        int value = rand.nextInt(5) + 1;

        if (myself == 1){
            return new Move(value,0);
        }
        else if (myself == 2){
            return new Move(0,value);
        }
        else if (myself == 3){
            return new Move(value,7);
        }
        else if (myself == 4){
            return new Move(7,value);
        }
        return null;
    }


}
