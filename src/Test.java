import lenz.htw.gaap.Move;
import lenz.htw.gaap.net.NetworkClient;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Test {

    public static void main(String[] args) throws IOException {
        List<Integer> playersList = Arrays.asList(1, 2, 3, 4);

        int index = playersList.indexOf(1);
        if (index == 0) {
            index = playersList.size();
        }
        System.out.println(playersList.get(index - 1));

    }

    private static void rotate() {
        int[][] matrix = {{1, 2, 3, 3}, {4, 5, 6, 6}, {7, 8, 9, 9}, {0, 0, 0, 0}};

        int m = matrix.length;
        int n = matrix[0].length;
        if (m != n)
            return;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }

        int temp;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < i; j++) {
                temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        System.out.println("After the principal diagonal reversal:");
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n / 2; j++) {
                temp = matrix[i][j];
                matrix[i][j] = matrix[i][n - 1 - j];
                matrix[i][n - 1 - j] = temp;
            }
        System.out.println("After the reversal of the vertical midline:");

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
    }

    private static void mainn() throws IOException {
        NetworkClient client = new NetworkClient(null, "TOBIAS", ImageIO.read(new File("/User/...")));

        //Server.runOnceAndReturnTheWinner(8);

        client.getExpectedNetworkLatencyInMilliseconds();

        //Zeit einen eigenen Zug abzugeben
        client.getTimeLimitInSeconds();

        //Meine Nummer 0-3 (0=rot, 1=grün, ...)
        client.getMyPlayerNumber();

        //Endlosschleife bis Spiel durch Exception endet (elegantes Softwaredesign?)
//        for(;;)
        Move move;
        while ((move = client.receiveMove()) != null) {
            //zug von gegenspielerInnen erhalten
            //schieben durchführen?
            //punkte?
            //zug in brettrepräsentation einarbeiten

        }

//        move = findeGenialenZug();
        client.sendMove(move);
    }
}

