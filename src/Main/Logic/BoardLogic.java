package Main.Logic;

import Main.Board;
import lenz.htw.gaap.Move;

import java.util.Random;

public abstract class BoardLogic {

    public abstract Move generateMove(Board board, int myNumber);

}

