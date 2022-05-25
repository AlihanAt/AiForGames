package Main;

import java.util.ArrayList;
import java.util.List;

public class AutoTrainer {

    /**
     *  kriegst results vom game zurück:
     *  speicher in liste die results
     *  nach x spielen nach den ergebnissen schauen
     *      wer hat am meisten gewonnen?
     *  den besten spieler bestimmen
     *  kinder davon erzeugen
     *  kinder gegen eltern spielen lassen
     */

    final int ITERATIONS = 1;
    List<int[]> results = new ArrayList<>();
    
    final RatingFunction[] ratingFunctions = new RatingFunction[]{
            new RatingFunction(1, 1, 1),
            new RatingFunction(10,2, 5),
            new RatingFunction(1,10, 2),
            new RatingFunction(2,2, 10)
    };

    public void startTraining(){

        generateRandomRatings();

        for(int i=0; i<ITERATIONS; i++){
            System.out.println("ITERATION: " + i);
            simulateARun();
        }

        evaluateSimulations();
    }

    private void generateRandomRatings() {

    }

    private void evaluateSimulations() {
        int[] winCounter = new int[4];
        int winningPlayer;

        for (int[] result : results) {
            winningPlayer = getWinningPlayer(result);
            winCounter[winningPlayer]++;
        }

        winningPlayer = getWinningPlayer(winCounter);

        System.out.println(winningPlayer+1);

    }

    private int getWinningPlayer(int[] result) {
        int winningPlayer = 0;

        for (int i = 1; i < result.length; i++) {
            if (!(result[winningPlayer] >= result[i]))
                winningPlayer = i;
        }
        return winningPlayer;
    }

    private void simulateARun() {
        GameMock game = new GameMock(ratingFunctions);
        int[] tempResults = game.startGameSimulation();
        results.add(tempResults);
        printPoints(tempResults);
    }

    private void printPoints(int[] tempResults){
        System.out.println("Player 1: " + tempResults[0] + ", Player 2: " + tempResults[1] + ", Player 3: " + tempResults[2] + ", Player 4: " + tempResults[3]);
    }

    public static void main(String[] args) {
        AutoTrainer trainer = new AutoTrainer();
        trainer.startTraining();
    }

}
