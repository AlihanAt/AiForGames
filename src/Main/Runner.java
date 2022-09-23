package Main;

import Main.Logic.MaxnAi;
import Main.Logic.MinimaxAi;
import Main.Logic.RandomAiLogic;
import Main.Logic.Rating.AdvancedGameStateRatingFunction;
import lenz.htw.gaap.Server;

import java.util.Random;

public class Runner {

    public static void main(String[] args) {
        runWithServer();
//        runWithGameMock();
    }

    private static void runWithGameMock() {

        int minimaxPoints = 0;
        int minimaxIndex = -1;
        int maxnPoints = 0;
        int maxnIndex = -1;

        int iterations = 100;

        for(int j = 0; j<iterations; j++) {
            System.out.println("Iteration: " + j);

            GameMock game = new GameMock();

            Client c1 = new Client("c1", new MinimaxAi(new AdvancedGameStateRatingFunction(5, 1, 3)));
            Client c2 = new Client("c2", new RandomAiLogic());
            Client c3 = new Client("c3", new RandomAiLogic());
            Client c4 = new Client("c4", new MaxnAi(new AdvancedGameStateRatingFunction(5, 1, 3)));
            Client[] clients = new Client[]{c1,c2,c3,c4};
            Client[] clientsRandom = new Client[4];

            int count = 0;
            while (count < 4) {

                Random r = new Random();
                int x = r.nextInt(4);
                if (clientsRandom[x] == null) {
                    if (count == 0)
                        minimaxIndex = x;
                    if (count == 3)
                        maxnIndex = x;

                    clients[count].setMyNumber(x + 1);
                    clientsRandom[x] = clients[count];
                    count++;
                }
            }

            game.setUpClients(clientsRandom);
            int[] results = game.startGameSimulation();

//            System.out.println("Minimax Index = " + minimaxIndex + ", Maxn index = " + maxnIndex);
//            System.out.println("Minimax: " + clientsRandom[minimaxIndex].getMyNumber() + ", " + results[minimaxIndex] + " / Maxn: " + clientsRandom[maxnIndex].getMyNumber() + ", " + results[maxnIndex] );

            minimaxPoints += results[minimaxIndex];
            maxnPoints += results[maxnIndex];
            System.out.println("Minimax gesamt: " + minimaxPoints + ", Maxn Gesamt: " + maxnPoints);
        }

        System.out.println("Iterations: " + iterations + "\nMinimax Points: " + minimaxPoints + ", Maxn Points: " + maxnPoints);
    }


    public static void runWithServer(){

//        TestThread testThread = new TestThread();
//        testThread.start();

        Client c1 = new Client("c1", new MinimaxAi(new AdvancedGameStateRatingFunction(5, 1, 3)));
        Client c2 = new Client("c2", new RandomAiLogic());
        Client c3 = new Client("c3", new RandomAiLogic());
        Client c4 = new Client("c4", new MaxnAi(new AdvancedGameStateRatingFunction(5, 1, 3)));

        Thread t1 = new Thread(c1);
        t1.start();
        Thread t2 = new Thread(c2);
        t2.start();
        Thread t3 = new Thread(c3);
        t3.start();
        Thread t4 = new Thread(c4);
        t4.start();
    }

}

class TestThread extends Thread {
    public void run() {
        Server.runOnceAndReturnTheWinner(5);
    }
}
