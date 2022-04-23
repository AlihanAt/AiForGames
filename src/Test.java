import lenz.htw.gaap.Move;
import lenz.htw.gaap.Server;
import lenz.htw.gaap.net.NetworkClient;

import javax.imageio.ImageIO;
import  java.io.File;
import java.io.IOException;

public class Test {

    public static void main(String[] args) throws IOException {
        NetworkClient client = new NetworkClient("ipadr", "Name", ImageIO.read(new File("C:\\Users\\wilde\\Pictures\\Wallpaper\\pb")));

        Server.runOnceAndReturnTheWinner(8);

        client.getExpectedNetworkLatencyInMilliseconds();
        client.getTimeLimitInSeconds();
        client.getMyPlayerNumber();
//        client.sendMove();
        client.receiveMove();

        Move move;
        move = client.receiveMove();
        int x = move.x;
        int y = move.y;

        client.sendMove(move);


    }

}
