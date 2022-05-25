package Main;

import java.util.ArrayList;
import java.util.List;

public class AutoTrainer {

    final int ITERATIONS = 3;
    List<int[]> results = new ArrayList<>();
    int[] tempResults = new int[4];

    public void startTraining(){
        for(int i=0; i<ITERATIONS; i++){
            System.out.println("ITERATION: " + i);
            GameMock game = new GameMock();
            tempResults = game.startGameSimulation();
            results.add(tempResults);
            printPoints();
        }
    }

    private void printPoints(){
        System.out.println("Player 1: " + tempResults[0] + ", Player 2: " + tempResults[1] + ", Player 3: " + tempResults[2] + ", Player 4: " + tempResults[3]);
    }

    public static void main(String[] args) {
        AutoTrainer trainer = new AutoTrainer();
        trainer.startTraining();
    }

}
