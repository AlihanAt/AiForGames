package Main;

import Main.Logic.AdvancedAi;
import Main.Logic.MaxnAi;
import Main.Logic.MinimaxAi;
import Main.Logic.RandomAiLogic;

public class Runner {

    public static void main(String[] args) {

        Client c1 = new Client("Alsi", new MaxnAi());
        Client c2 = new Client("Oli", new MaxnAi());
        Client c3 = new Client("Dennoos", new MaxnAi());
        Client c4 = new Client("jann", new MaxnAi());

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
