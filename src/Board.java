import lenz.htw.gaap.Move;

import java.util.Arrays;
import java.util.List;

public class Board {

    private final BoardField[][] board = new BoardField[8][8];

    private final List<BoardField> boardFieldList;

    private final Player[] players;

    public Board(){
        players = new Player[]{
                new Player(1, false),
                new Player(2, false),
                new Player(3, false),
                new Player(4, false),
        };

        for (int x = 0; x < board.length; x++){
            for (int y = 0; y < board[x].length; y++){
                board[x][y] = new BoardField();
            }
        }

        boardFieldList = ListExtension.twoDArrayToList(board);

    }

    public Player getPlayerAndRegister(int playerNo){
        players[playerNo-1].registerSelf();
        return players[playerNo-1];
    }

    private boolean addStone(int x, int y, int playerNo) {
        return board[x][y].createStone(players[playerNo - 1]);
    }

    public void updateStonePositionsFrom(int playerNo) {
        int pushDirX = getPushDir(playerNo, true);
        int pushDirY = getPushDir(playerNo, false);

        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[x].length; y++) {

                if (board[x][y].isStoneFromPlayer(playerNo) && !board[x][y].hasStoneBeenPushed()) {
                    pushStone(x, y, pushDirX, pushDirY);
                }

            }
        }

        clearPushState();
    }

    private int getPushDir(int playerNo, boolean isXAxis){
        if ((isXAxis && playerNo == 2) || (!isXAxis && playerNo == 1))
            return 1;
        else if ((isXAxis && playerNo == 4) || (!isXAxis && playerNo == 3))
            return -1;
        else
            return 0;
    }

    private void pushStone(int currentPosX, int currentPosY, int pushDirX, int pushDirY) {

        int nextPosX = currentPosX + pushDirX;
        int nextPosY = currentPosY + pushDirY;

        if (isWithinBounds(nextPosX, nextPosY)) {
            board[currentPosX][currentPosY].clearField();
            return;
        }
        else if (board[nextPosX][nextPosY].isFieldInUse()) {
            boolean samePlayer = board[currentPosX][currentPosY].isSamePlayerAs(board[nextPosX][nextPosY]);

            if(!samePlayer)
                board[currentPosX][currentPosY].addPoint();

            pushStone(nextPosX, nextPosY, pushDirX, pushDirY);
        }

        board[currentPosX][currentPosY].pushStone(board[nextPosX][nextPosY]);
    }

    private void clearPushState(){
        for (BoardField field : boardFieldList) {
            field.clearPushState();
        }
    }

    private boolean isWithinBounds(int currentPosX, int currentPosY) {
        return currentPosX >= board.length || currentPosY >= board.length || currentPosX < 0 || currentPosY < 0;
    }

    public void addMove(int x, int y) {
        int playerNo = getPlayerFromMove(x, y);

        if(playerNo == -1)
            return;

        updateStonePositionsFrom(playerNo);

        addStone(x, y, playerNo);
    }

    public int getPlayerFromMove(int x, int y){

        if(y == 0){
            //p0
            return 1;
        }
        else if (x == 0){
            //p1
            return 2;
        }
        else if (y == 7){
            //p2
            return 3;
        }
        else if (x == 7){
            //p3
            return 4;
        }
        System.out.println("invalid move" +x +"," +y);
        return -1;
    }

    public void updateSkippedPlayers(int lastPlayer, int myNumber){
        //gucken ob spieler geskippt wurden, wenn ja dann deren steine updaten
        if ((lastPlayer + 1) % 4 != myNumber)
            for (int i = lastPlayer + 1; i < (myNumber % 4) + 4; i++)
                updateStonePositionsFrom(modToPlayerNumber(i % 4));
    }

    private int modToPlayerNumber(int mod){
        if (mod == 0)
            return 4;
        else
            return mod;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();

        for (int x = 0; x < board.length; x++){
            for (int y = 0; y < board[x].length; y++){

                str.append(board[x][y]);
                if(y+1 != board[x].length)
                    str.append(",");
            }
            if(x+1 != board.length)
                str.append("\n");
        }

        return str.toString();
    }
}
