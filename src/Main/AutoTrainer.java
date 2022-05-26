package Main;

import Main.Logic.Rating.AdvancedGameStateRatingFunction;
import Main.Logic.Rating.RatingFunction;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AutoTrainer {

    final int RUN_ITERATIONS = 1;
    final int TRAINING_ITERATIONS = 20;
    List<int[]> results = new ArrayList<>();
    RatingFunction[] ratingFunctions;
    RatingFunction[] evaluatedFunctions;

    public static void main(String[] args) {
        AutoTrainer trainer = new AutoTrainer();
        trainer.startTraining(160);
    }

    public void startTraining(int amount) {
        ratingFunctions = generateRandomRatings(amount);
        evaluatedFunctions = new RatingFunction[ratingFunctions.length / 4];

        for (int i = 0; i < TRAINING_ITERATIONS; i++) {
            System.out.println("\nNext Training Queue\n");

            simulateRuns();
            copyWinners();
            createMutation();
            createRandoms();
        }

        System.out.println("\nBest Function Parameters\n");
        System.out.println("Rating: OwnPoints | Field | StoneInField");

        for (RatingFunction r : evaluatedFunctions) {
            System.out.println(r.toString());
        }
    }

    private void createRandoms() {
        RatingFunction[] randomFunctions = generateRandomRatings(ratingFunctions.length / 4);

        for (int i = 0; i < randomFunctions.length; i++) {
            ratingFunctions[i + ratingFunctions.length / 4 * 3] = randomFunctions[i];
        }
    }

    private void createMutation() {
        for (int i = 0; i < evaluatedFunctions.length * 2; i++) {
            ratingFunctions[i + ratingFunctions.length / 4] = evaluatedFunctions[i % evaluatedFunctions.length].deepCopyMutate();
        }
    }

    private void copyWinners() {
        for (int i = 0; i < evaluatedFunctions.length; i++) {
            ratingFunctions[i] = evaluatedFunctions[i];
        }
    }

    private void simulateRuns() {
        for (int i = 0, j = 0; i < ratingFunctions.length; i += 4, j++) {
            for (int k = 0; k < RUN_ITERATIONS; k++) {
                simulateARun(ratingFunctions[i], ratingFunctions[i + 1], ratingFunctions[i + 2], ratingFunctions[i + 3]);
            }
            evaluatedFunctions[j] = evaluateSimulations(i);
            results.clear();
        }
    }

    private RatingFunction[] generateRandomRatings(int amount) {
        RatingFunction[] randomRatings = new RatingFunction[amount];
        Random random = new Random();
        int mod1 = random.nextInt(10) + 1;
        int mod2 = random.nextInt(10) + 1;
        int mod3 = random.nextInt(10) + 1;
        for (int i = 0; i < randomRatings.length; i++) {
            randomRatings[i] = new AdvancedGameStateRatingFunction(random.nextFloat() * mod1,
                    random.nextFloat() + mod2,
                    random.nextFloat() * mod3);
            //TODO use reflection to grab a new instance of the child through the abstract class
        }
        return randomRatings;
    }

    private RatingFunction evaluateSimulations(int i) {
        int[] winCounter = new int[4];
        int winningPlayer;

        for (int[] result : results) {
            winningPlayer = getWinningPlayer(result);
            winCounter[winningPlayer]++;
        }

        winningPlayer = getWinningPlayer(winCounter);

        return ratingFunctions[i + winningPlayer];
    }

    private int getWinningPlayer(int[] result) {
        int winningPlayer = 0;

        for (int i = 1; i < result.length; i++) {
            if (!(result[winningPlayer] >= result[i]))
                winningPlayer = i;
        }
        return winningPlayer;
    }

    private void simulateARun(RatingFunction ratingFunction1, RatingFunction ratingFunction2, RatingFunction ratingFunction3, RatingFunction ratingFunction4) {
        GameMock game = new GameMock(ratingFunction1, ratingFunction2, ratingFunction3, ratingFunction4);
        int[] tempResults = game.startGameSimulation();
        results.add(tempResults);
        printPoints(tempResults);
    }

    private void printPoints(int[] tempResults) {
        System.out.println("Player 1: " + tempResults[0] + ", Player 2: " + tempResults[1] + ", Player 3: " + tempResults[2] + ", Player 4: " + tempResults[3]);
    }

}
