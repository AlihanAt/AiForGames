package Test;

import Main.RatingFunction;
import Main.Board;
import Main.Logic.MaxnAi;
import Main.Logic.MinimaxAi;
import lenz.htw.gaap.Move;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @Test
    public void boardTest(){
        Board board = new Board();
        String expected =
                "-,-,-,-,-,-,-,-\n" +
                "-,-,-,-,-,-,-,-\n" +
                "-,-,-,-,-,-,-,-\n" +
                "-,-,-,-,-,-,-,-\n" +
                "-,-,-,-,-,-,-,-\n" +
                "-,-,-,-,-,-,-,-\n" +
                "-,-,-,-,-,-,-,-\n" +
                "-,-,-,-,-,-,-,-";
        Assertions.assertEquals(expected, board.toString());

        board.updateAndAddMove(6,0);
        expected =
                "-,-,-,-,-,-,-,-\n" +
                "-,-,-,-,-,-,-,-\n" +
                "-,-,-,-,-,-,-,-\n" +
                "-,-,-,-,-,-,-,-\n" +
                "-,-,-,-,-,-,-,-\n" +
                "-,-,-,-,-,-,-,-\n" +
                "1,-,-,-,-,-,-,-\n" +
                "-,-,-,-,-,-,-,-";
        Assertions.assertEquals(expected, board.toString());

        board.updateAndAddMove(0,6);
        expected =
                "-,-,-,-,-,-,2,-\n" +
                "-,-,-,-,-,-,-,-\n" +
                "-,-,-,-,-,-,-,-\n" +
                "-,-,-,-,-,-,-,-\n" +
                "-,-,-,-,-,-,-,-\n" +
                "-,-,-,-,-,-,-,-\n" +
                "1,-,-,-,-,-,-,-\n" +
                "-,-,-,-,-,-,-,-";
        Assertions.assertEquals(expected, board.toString());

        board.updateAndAddMove(6,0);
        expected =
                "-,-,-,-,-,-,2,-\n" +
                "-,-,-,-,-,-,-,-\n" +
                "-,-,-,-,-,-,-,-\n" +
                "-,-,-,-,-,-,-,-\n" +
                "-,-,-,-,-,-,-,-\n" +
                "-,-,-,-,-,-,-,-\n" +
                "1,1,-,-,-,-,-,-\n" +
                "-,-,-,-,-,-,-,-";
        Assertions.assertEquals(expected, board.toString());

        board.updateAndAddMove(4,0);
        expected =
                "-,-,-,-,-,-,2,-\n" +
                "-,-,-,-,-,-,-,-\n" +
                "-,-,-,-,-,-,-,-\n" +
                "-,-,-,-,-,-,-,-\n" +
                "1,-,-,-,-,-,-,-\n" +
                "-,-,-,-,-,-,-,-\n" +
                "-,1,1,-,-,-,-,-\n" +
                "-,-,-,-,-,-,-,-";
        Assertions.assertEquals(expected, board.toString());

        board.updateAndAddMove(6,0);
        board.updateAndAddMove(6,0);
        board.updateAndAddMove(6,0);
        expected =
                "-,-,-,-,-,-,2,-\n" +
                "-,-,-,-,-,-,-,-\n" +
                "-,-,-,-,-,-,-,-\n" +
                "-,-,-,-,-,-,-,-\n" +
                "-,-,-,1,-,-,-,-\n" +
                "-,-,-,-,-,-,-,-\n" +
                "1,1,1,-,1,1,-,-\n" +
                "-,-,-,-,-,-,-,-";
        Assertions.assertEquals(expected, board.toString());

        board.updateAndAddMove(6,0);
        board.updateAndAddMove(6,0);
        expected =
                "-,-,-,-,-,-,2,-\n" +
                "-,-,-,-,-,-,-,-\n" +
                "-,-,-,-,-,-,-,-\n" +
                "-,-,-,-,-,-,-,-\n" +
                "-,-,-,-,-,1,-,-\n" +
                "-,-,-,-,-,-,-,-\n" +
                "1,1,1,1,1,-,1,1\n" +
                "-,-,-,-,-,-,-,-";
        Assertions.assertEquals(expected, board.toString());

        board.updateAndAddMove(5,0);
        expected =
                "-,-,-,-,-,-,2,-\n" +
                "-,-,-,-,-,-,-,-\n" +
                "-,-,-,-,-,-,-,-\n" +
                "-,-,-,-,-,-,-,-\n" +
                "-,-,-,-,-,-,1,-\n" +
                "1,-,-,-,-,-,-,-\n" +
                "-,1,1,1,1,1,-,1\n" +
                "-,-,-,-,-,-,-,-";
        Assertions.assertEquals(expected, board.toString());

        board.updateAndAddMove(7,4);
        expected =
                "-,-,-,-,-,-,2,-\n" +
                "-,-,-,-,-,-,-,-\n" +
                "-,-,-,-,-,-,-,-\n" +
                "-,-,-,-,-,-,-,-\n" +
                "-,-,-,-,-,-,1,-\n" +
                "1,-,-,-,-,-,-,-\n" +
                "-,1,1,1,1,1,-,1\n" +
                "-,-,-,-,4,-,-,-";
        Assertions.assertEquals(expected, board.toString());

        board.updateAndAddMove(7,4);
        expected =
                "-,-,-,-,-,-,2,-\n" +
                "-,-,-,-,-,-,-,-\n" +
                "-,-,-,-,-,-,-,-\n" +
                "-,-,-,-,-,-,-,-\n" +
                "-,-,-,-,-,-,1,-\n" +
                "1,-,-,-,1,-,-,-\n" +
                "-,1,1,1,4,1,-,1\n" +
                "-,-,-,-,4,-,-,-";
        Assertions.assertEquals(expected, board.toString());

        board.updateAndAddMove(7,4);
        board.updateAndAddMove(7,4);
        board.updateAndAddMove(7,4);
        board.updateAndAddMove(7,4);
        board.updateAndAddMove(7,4);
        board.updateAndAddMove(7,4);
        expected =
                "-,-,-,-,4,-,2,-\n" +
                "-,-,-,-,4,-,-,-\n" +
                "-,-,-,-,4,-,-,-\n" +
                "-,-,-,-,4,-,-,-\n" +
                "-,-,-,-,4,-,1,-\n" +
                "1,-,-,-,4,-,-,-\n" +
                "-,1,1,1,4,1,-,1\n" +
                "-,-,-,-,4,-,-,-";
        Assertions.assertEquals(expected, board.toString());

        board.updateAndAddMove(0,5);
        expected =
                "-,-,-,-,4,2,-,-\n" +
                "-,-,-,-,4,-,2,-\n" +
                "-,-,-,-,4,-,-,-\n" +
                "-,-,-,-,4,-,-,-\n" +
                "-,-,-,-,4,-,1,-\n" +
                "1,-,-,-,4,-,-,-\n" +
                "-,1,1,1,4,1,-,1\n" +
                "-,-,-,-,4,-,-,-";
        Assertions.assertEquals(expected, board.toString());

        board.updateStonePositionsFrom(2);
        expected =
                "-,-,-,-,4,-,-,-\n" +
                "-,-,-,-,4,2,-,-\n" +
                "-,-,-,-,4,-,2,-\n" +
                "-,-,-,-,4,-,-,-\n" +
                "-,-,-,-,4,-,1,-\n" +
                "1,-,-,-,4,-,-,-\n" +
                "-,1,1,1,4,1,-,1\n" +
                "-,-,-,-,4,-,-,-";
        Assertions.assertEquals(expected, board.toString());

    }

    @Test
    public void testSkipping(){
        Board board = new Board();
        board.updateAndAddMove(6,0);
        board.updateAndAddMove(0,6);
        board.updateAndAddMove(6,7);
        board.updateAndAddMove(7,6);
        String expected =
                "-,-,-,-,-,-,2,-\n" +
                "-,-,-,-,-,-,-,-\n" +
                "-,-,-,-,-,-,-,-\n" +
                "-,-,-,-,-,-,-,-\n" +
                "-,-,-,-,-,-,-,-\n" +
                "-,-,-,-,-,-,-,-\n" +
                "1,-,-,-,-,-,-,3\n" +
                "-,-,-,-,-,-,4,-";
        Assertions.assertEquals(expected, board.toString());

        board.updateSkippedPlayers(1, 4);
        expected =
                "-,-,-,-,-,-,-,-\n" +
                "-,-,-,-,-,-,2,-\n" +
                "-,-,-,-,-,-,-,-\n" +
                "-,-,-,-,-,-,-,-\n" +
                "-,-,-,-,-,-,-,-\n" +
                "-,-,-,-,-,-,-,-\n" +
                "1,-,-,-,-,-,3,-\n" +
                "-,-,-,-,-,-,4,-";
        Assertions.assertEquals(expected, board.toString());

        board.updateSkippedPlayers(2, 1);
        expected =
                "-,-,-,-,-,-,-,-\n" +
                "-,-,-,-,-,-,2,-\n" +
                "-,-,-,-,-,-,-,-\n" +
                "-,-,-,-,-,-,-,-\n" +
                "-,-,-,-,-,-,-,-\n" +
                "-,-,-,-,-,-,-,-\n" +
                "1,-,-,-,-,3,4,-\n" +
                "-,-,-,-,-,-,-,-";
        Assertions.assertEquals(expected, board.toString());

        board.updateSkippedPlayers(3, 2);
        expected =
                "-,-,-,-,-,-,-,-\n" +
                "-,-,-,-,-,-,2,-\n" +
                "-,-,-,-,-,-,-,-\n" +
                "-,-,-,-,-,-,-,-\n" +
                "-,-,-,-,-,-,-,-\n" +
                "-,-,-,-,-,-,4,-\n" +
                "-,1,-,-,-,3,-,-\n" +
                "-,-,-,-,-,-,-,-";
        Assertions.assertEquals(expected, board.toString());

        board.updateSkippedPlayers(4, 3);
        expected =
                "-,-,-,-,-,-,-,-\n" +
                "-,-,-,-,-,-,-,-\n" +
                "-,-,-,-,-,-,2,-\n" +
                "-,-,-,-,-,-,-,-\n" +
                "-,-,-,-,-,-,-,-\n" +
                "-,-,-,-,-,-,4,-\n" +
                "-,-,1,-,-,3,-,-\n" +
                "-,-,-,-,-,-,-,-";
        Assertions.assertEquals(expected, board.toString());

    }

    @Test
    public void testFieldRating(){
        Board board = new Board();
        board.getPlayerAndRegister(1);
        board.updateAndAddMove(3,0);
        board.updateAndAddMove(2,0);
        board.updateAndAddMove(0,3);
        board.updateAndAddMove(2,7);
        board.updateAndAddMove(7,4);
        String expected =
                "-,-,-,2,-,-,-,-\n" +
                "-,-,-,-,-,-,-,-\n" +
                "1,-,-,-,-,-,-,3\n" +
                "-,1,-,-,-,-,-,-\n" +
                "-,-,-,-,-,-,-,-\n" +
                "-,-,-,-,-,-,-,-\n" +
                "-,-,-,-,-,-,-,-\n" +
                "-,-,-,-,4,-,-,-";
        assertEquals(expected, board.toString());

        board.updateStonePositionsFrom(1);
        board.updateStonePositionsFrom(1);
        board.updateStonePositionsFrom(2);
        board.updateStonePositionsFrom(2);
        board.updateStonePositionsFrom(3);
        board.updateStonePositionsFrom(3);
        board.updateStonePositionsFrom(3);
        board.updateStonePositionsFrom(4);
        board.updateStonePositionsFrom(4);

        expected =
                "-,-,-,-,-,-,-,-\n" +
                "-,-,-,-,-,-,-,-\n" +
                "-,-,1,2,3,-,-,-\n" +
                "-,-,-,1,-,-,-,-\n" +
                "-,-,-,-,-,-,-,-\n" +
                "-,-,-,-,4,-,-,-\n" +
                "-,-,-,-,-,-,-,-\n" +
                "-,-,-,-,-,-,-,-";
        Assertions.assertEquals(expected, board.toString());

        assertEquals(0, board.getFieldRatingForPlayer(1));

        expected =
                "-,-,-,-,-,-,-,-\n" +
                "-,-,-,-,-,-,-,-\n" +
                "-,-,-,1,3,-,-,-\n" +
                "-,-,-,2,-,-,-,-\n" +
                "-,-,-,-,1,4,-,-\n" +
                "-,-,-,-,-,-,-,-\n" +
                "-,-,-,-,-,-,-,-\n" +
                "-,-,-,-,-,-,-,-";
        Assertions.assertEquals(expected, board.toString());

    }

    @Test
    public void testPoints(){
        Board board = new Board();
        board.getPlayerAndRegister(1);

        board.addStone(1,1,3);
        board.addStone(1,0,1);
        board.addStone(1,2,1);
        board.addStone(1,3,1);
        board.updateStonePositionsFrom(1);

//        board.addMove(3,0);

        String expected =
                "-,-,-,-,-,-,-,-\n" +
                "-,1,3,1,1,-,-,-\n" +
                "-,-,-,-,-,-,-,-\n" +
                "-,-,-,-,-,-,-,-\n" +
                "-,-,-,-,-,-,-,-\n" +
                "-,-,-,-,-,-,-,-\n" +
                "-,-,-,-,-,-,-,-\n" +
                "-,-,-,-,-,-,-,-";
        assertEquals(expected, board.toString());

        assertEquals(1, board.getPlayerScore(1));
        assertEquals(0, board.getPlayerScore(3));

        board.addStone(1,0,3);
        board.updateStonePositionsFrom(3);
        expected =
                "-,-,-,-,-,-,-,-\n" +
                "1,3,-,1,1,-,-,-\n" +
                "-,-,-,-,-,-,-,-\n" +
                "-,-,-,-,-,-,-,-\n" +
                "-,-,-,-,-,-,-,-\n" +
                "-,-,-,-,-,-,-,-\n" +
                "-,-,-,-,-,-,-,-\n" +
                "-,-,-,-,-,-,-,-";

        assertEquals(expected, board.toString());
        assertEquals(1, board.getPlayerScore(3));

    }

    @Test
    public void placementTest(){
        Board board = new Board();
        board.getPlayerAndRegister(1);
        board.addStone(1,0, 3);
        board.addStone(2,0, 3);
        board.addStone(4,0, 3);
        board.addStone(5,0, 3);
        board.addStone(6,0, 3);

        String expected =
                "-,-,-,-,-,-,-,-\n" +
                "3,-,-,-,-,-,-,-\n" +
                "3,-,-,-,-,-,-,-\n" +
                "-,-,-,-,-,-,-,-\n" +
                "3,-,-,-,-,-,-,-\n" +
                "3,-,-,-,-,-,-,-\n" +
                "3,-,-,-,-,-,-,-\n" +
                "-,-,-,-,-,-,-,-";
        assertEquals(expected, board.toString());

        assertFalse(board.addStone(1,0, 1));

        expected =
                "-,-,-,-,-,-,-,-\n" +
                "3,-,-,-,-,-,-,-\n" +
                "3,-,-,-,-,-,-,-\n" +
                "-,-,-,-,-,-,-,-\n" +
                "3,-,-,-,-,-,-,-\n" +
                "3,-,-,-,-,-,-,-\n" +
                "3,-,-,-,-,-,-,-\n" +
                "-,-,-,-,-,-,-,-";
        assertEquals(expected, board.toString());

        board.updateStonePositionsFrom(1);
        expected =
                "-,-,-,-,-,-,-,-\n" +
                "3,-,-,-,-,-,-,-\n" +
                "3,-,-,-,-,-,-,-\n" +
                "-,-,-,-,-,-,-,-\n" +
                "3,-,-,-,-,-,-,-\n" +
                "3,-,-,-,-,-,-,-\n" +
                "3,-,-,-,-,-,-,-\n" +
                "-,-,-,-,-,-,-,-";
        assertEquals(expected, board.toString());

    }

    @Test
    public void deepCopyTest(){
        Board board = new Board();
        board.getPlayerAndRegister(1);
        board.addStone(1,0, 3);
        board.addStone(2,0, 3);
        board.addStone(4,0, 3);
        board.addStone(5,0, 3);
        board.addStone(6,0, 3);

        Board copy = board.deepCopy();


        assertEquals(copy.toString(), board.toString());

        copy.addStone(7, 1 ,4);
        Board copy2 = copy.deepCopy();

        assertEquals(copy.toString(), copy2.toString());
        assertNotEquals(copy.toString(), board.toString());

    }

    @Test
    public void AlgorithmPlacementTest(){
        Board board = new Board();
        board.getPlayerAndRegister(1);
        board.addStone(1,0, 3);
        board.addStone(2,0, 3);
        board.addStone(4,0, 3);
        board.addStone(5,0, 3);
        board.addStone(6,0, 3);

        String expected =
                "-,-,-,-,-,-,-,-\n" +
                "3,-,-,-,-,-,-,-\n" +
                "3,-,-,-,-,-,-,-\n" +
                "-,-,-,-,-,-,-,-\n" +
                "3,-,-,-,-,-,-,-\n" +
                "3,-,-,-,-,-,-,-\n" +
                "3,-,-,-,-,-,-,-\n" +
                "-,-,-,-,-,-,-,-";
        assertEquals(expected, board.toString());

        MaxnAi maxn = new MaxnAi(new RatingFunction(10,10,10));
        Move maxnMove = maxn.generateMove(board, 1);

        MinimaxAi minimax = new MinimaxAi(null);
        Move minimaxMove = maxn.generateMove(board, 1);

        assertEquals(maxnMove.x, 3);
        assertEquals(maxnMove.y, 0);
//        assertEquals(minimaxMove, new Move(3,0));
        assertEquals(minimaxMove.x, 3);
        assertEquals(minimaxMove.y, 0);
    }

    @Test
    public void stringToBoardTest(){
        String expected =
                "-,-,-,-,-,-,-,-\n" +
                "-,-,-,-,-,-,-,-\n" +
                "-,-,-,-,-,-,2,-\n" +
                "-,-,-,-,-,-,-,-\n" +
                "-,-,-,-,-,-,-,-\n" +
                "-,-,-,-,-,-,4,-\n" +
                "-,-,1,-,-,3,-,-\n" +
                "-,-,-,-,-,-,-,-";

        Board board = stringToBoard(expected);
        assertEquals(expected, board.toString());

        expected =
                "-,-,-,-,3,-,-,1\n"+
                "1,1,1,1,2,-,1,4\n"+
                "-,-,-,2,-,1,4,2\n"+
                "-,-,-,1,3,-,4,2\n"+
                "-,3,-,-,-,-,4,-\n"+
                "3,4,-,-,2,4,3,-\n"+
                "-,-,2,-,-,-,4,3\n"+
                "2,-,-,-,1,-,4,-";
        board = stringToBoard(expected);
        MinimaxAi minimaxAi = new MinimaxAi(null);
        Move move = minimaxAi.generateMove(board, 2);
        System.out.println(move.x + ", "  + move.y);


//        assertFalse(board.addStone(0,4,2));
//        assertEquals(expected, board.toString());

        expected =
                "-,-,-,3,-,-,-,1\n"+
                "-,1,1,1,1,-,4,-\n"+
                "-,-,-,-,-,-,1,4\n"+
                "-,-,-,3,2,-,4,-\n"+
                "3,4,2,-,1,4,3,2\n"+
                "-,-,-,-,-,4,3,2\n"+
                "-,-,-,-,-,-,4,-\n"+
                "-,-,-,-,2,1,-,-";
        board = stringToBoard(expected);
        MaxnAi maxnAi = new MaxnAi(new RatingFunction(10,10,10));
        move = maxnAi.generateMove(board, 3);
        System.out.println(move.x + ", "  + move.y);

//        assertFalse(board.addStone(4,7,3));
//        assertEquals(expected, board.toString());
    }

    public Board stringToBoard(String boardString){

        Board board  = new Board();
        char[] chars = boardString.toCharArray();

        int counterX = 0;
        int counterY = 0;

        for(int i=0; i<chars.length; i++){

            if(Character.isDigit(chars[i]) || chars[i] == '-'){

                if(Character.isDigit(chars[i])){
                    int x = Character.getNumericValue(chars[i]);
                    board.addStone(counterX, counterY, x);
                }

                counterY += 1;

                if(counterY >= 8){
                    counterX += 1;
                    counterY = 0;
                }
            }
        }

        return board;
    }

    @Test
    public void baselineTest(){
        Board board = new Board();
        board.addStone(1,0, 3);
        board.addStone(2,0, 3);
        board.addStone(4,0, 1);
        board.addStone(5,0, 2);

        board.addStone(0,2, 3);
        board.addStone(0,3, 2);
        board.addStone(0,4, 4);

        board.addStone(1,7, 1);
        board.addStone(6,7, 2);

        board.addStone(7,3, 1);
        board.addStone(7,5, 4);
        board.addStone(7,6, 1);
        board.addStone(7,1, 1);
        board.addStone(7,2, 1);

        String expected =
                "-,-,3,2,4,-,-,-\n" +
                "3,-,-,-,-,-,-,1\n" +
                "3,-,-,-,-,-,-,-\n" +
                "-,-,-,-,-,-,-,-\n" +
                "1,-,-,-,-,-,-,-\n" +
                "2,-,-,-,-,-,-,-\n" +
                "-,-,-,-,-,-,-,2\n" +
                "-,1,1,1,-,4,1,-";
        assertEquals(expected, board.toString());
        assertEquals(2, board.getFreeFieldsOnBaseline(1));
        assertEquals(3, board.getFreeFieldsOnBaseline(2));
        assertEquals(4, board.getFreeFieldsOnBaseline(3));
        assertEquals(1, board.getFreeFieldsOnBaseline(4));


    }

}