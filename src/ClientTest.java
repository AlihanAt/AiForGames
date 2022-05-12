import lenz.htw.gaap.Move;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

    private Client c1;
    private Client c2;
    private Client c3;
    private Client c4;

    @BeforeEach
    void setUp() {
        c1 = new Client(1);
        c2 = new Client(2);
        c3 = new Client(3);
        c4 = new Client(4);
    }

    @Test
    public void testClient(){

        for (int i=1; i<=12; i++){
            doTurn(i%4);
            assertEquals(c1.getBoard().toString(), c2.getBoard().toString());
            assertTrue(c1.areEqual(c2));
        }
    }

    private void doTurn(int playerNo){
        Move move;
        switch (playerNo){
            case 1:
                move = c1.doOwnTurn();
                break;
            case 2:
                move = c2.doOwnTurn();
                break;
            case 3:
                move = c3.doOwnTurn();
                break;
            default:
                move = c4.doOwnTurn();
                break;
        }

        c1.updateOnMoveReceived(move);
        c2.updateOnMoveReceived(move);
        c3.updateOnMoveReceived(move);
        c4.updateOnMoveReceived(move);
    }
}