package Test;

import Main.Board;
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

        board.addMove(6,0);
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

        board.addMove(0,6);
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

        board.addMove(6,0);
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

        board.addMove(4,0);
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

        board.addMove(6,0);
        board.addMove(6,0);
        board.addMove(6,0);
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

        board.addMove(6,0);
        board.addMove(6,0);
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

        board.addMove(5,0);
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

        board.addMove(7,4);
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

        board.addMove(7,4);
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

        board.addMove(7,4);
        board.addMove(7,4);
        board.addMove(7,4);
        board.addMove(7,4);
        board.addMove(7,4);
        board.addMove(7,4);
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

        board.addMove(0,5);
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
        board.addMove(6,0);
        board.addMove(0,6);
        board.addMove(6,7);
        board.addMove(7,6);
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
        board.addMove(3,0);
        board.addMove(2,0);
        board.addMove(0,3);
        board.addMove(2,7);
        board.addMove(7,4);
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
//        board.updateStonePositionsFrom(1);
        board.updateStonePositionsFrom(2);
        board.updateStonePositionsFrom(2);
        board.updateStonePositionsFrom(3);
        board.updateStonePositionsFrom(3);
        board.updateStonePositionsFrom(3);
        board.updateStonePositionsFrom(4);
        board.updateStonePositionsFrom(4);
//        board.updateStonePositionsFrom(4);
//        board.updateStonePositionsFrom(4);

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

        assertEquals(-1, board.fieldRating(1));

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

}