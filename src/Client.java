import lenz.htw.gaap.Move;
import lenz.htw.gaap.net.NetworkClient;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Client {

    //fragen ob ich eigenen move zurpckbekomme

    private final Board board = new Board();

    public static void main(String[] args) throws IOException {
        Client client = new Client();
        client.start();
    }

    private void start() throws IOException {
        NetworkClient client = new NetworkClient("ipadr", "Name", ImageIO.read(new File("C:\\Users\\wilde\\Pictures\\Wallpaper\\pb")));

        int myNumber = client.getMyPlayerNumber() + 1;

        Player myself = board.getPlayerAndRegister(myNumber);

        Move move;
        while ((move=client.receiveMove()) !=null) {
            //zug von gegenspielerInnen erhalten
            int playerNo = checkMovePosition(move);

            if(myself.getNumber() != playerNo){
                //in board speichern
                board.addStone(move.x, move.y, playerNo);

                //schieben durchführen?
                board.updateStonePositionsFrom(playerNo);
            }

            //zug in brettrepräsentation einarbeiten

        }
    }


    private int checkMovePosition(Move move){
        int x = move.x;
        int y = move.y;

        if(y == 0 ){
            //p0
            return 1;
        }
        else if (x == 0){
            //p1
            return 2;
        }
        else if (y == 8){
            //p2
            return 3;
        }
        else if (x == 8){
            //p3
            return 4;
        }
        return -1;
    }

}
