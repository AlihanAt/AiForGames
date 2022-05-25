package Main;

import Main.Logic.MaxnAi;
import Main.Logic.MinimaxAi;
import lenz.htw.gaap.Server;

public class Runner {

    public static void main(String[] args) {

//        TestThread testThread = new TestThread();
//        testThread.start();

        Client c1 = new Client("Alsi", new MinimaxAi(null));
        Client c2 = new Client("Oli", new MinimaxAi(null));
        Client c3 = new Client("Dennoos", new MaxnAi(new RatingFunction(10,10,10)));
        Client c4 = new Client("jann", new MaxnAi(new RatingFunction(10,5,10)));

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
class TestThread extends Thread{
        public void run(){
            Server.runOnceAndReturnTheWinner(5);
        }
}
