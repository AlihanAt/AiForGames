package Main;

import Main.Logic.AdvancedAi;
import Main.Logic.MinimaxAi;

public class Runner {

    public static void main(String[] args) {

        Client c1 = new Client("Alsi", new MinimaxAi());
        Client c2 = new Client("Oli", new AdvancedAi());
        Client c3 = new Client("Dennoos", new AdvancedAi());
        Client c4 = new Client("jann", new AdvancedAi());

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
