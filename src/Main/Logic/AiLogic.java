package Main.Logic;

import Main.RatingFunction;
import Main.Board;
import lenz.htw.gaap.Move;

public abstract class AiLogic {

    protected RatingFunction bewertungsFunktion;

    public abstract Move generateMove(Board board, int myNumber);

    public AiLogic(){}

    protected AiLogic(RatingFunction bewertungsFunktion){
        this.bewertungsFunktion = bewertungsFunktion;
    }

}

