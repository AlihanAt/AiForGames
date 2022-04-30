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
        assertEquals(expected, board.toString());

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
        assertEquals(expected, board.toString());

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
        assertEquals(expected, board.toString());

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
        assertEquals(expected, board.toString());

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
        assertEquals(expected, board.toString());

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
        assertEquals(expected, board.toString());

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
        assertEquals(expected, board.toString());

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
        assertEquals(expected, board.toString());

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
        assertEquals(expected, board.toString());

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
        assertEquals(expected, board.toString());

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
        assertEquals(expected, board.toString());

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
        assertEquals(expected, board.toString());

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
        assertEquals(expected, board.toString());

    }

}