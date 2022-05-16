package Test;

import Main.Client;
import Main.Logic.AdvancedAi;
import Main.Logic.RandomAiLogic;
import Main.Logic.SimpleBoardAi;
import lenz.htw.gaap.Move;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ClientTest {

    private Client c1;
    private Client c2;
    private Client c3;
    private Client c4;

    @BeforeEach
    void setUp() {
        c1 = new Client(1, new AdvancedAi());
        c2 = new Client(2, new RandomAiLogic());
        c3 = new Client(3, new SimpleBoardAi());
        c4 = new Client(4, new RandomAiLogic());
    }

    @Test
    public void testClient(){

        for (int i=1; i<=12; i++){
            doTurn(i%4);
            Assertions.assertEquals(c1.getBoard().toString(), c2.getBoard().toString());
            Assertions.assertTrue(c1.areEqual(c2));
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