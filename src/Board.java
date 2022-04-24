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

        boardFieldList = ListExtension.twoDArrayToList(board);
    }

    public Player getPlayerAndRegister(int playerNo){
        players[playerNo-1].registerSelf();
        return players[playerNo-1];
    }

    public boolean addStone(int x, int y, int playerNo) {
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

    private void clearPushState(){
        for (BoardField field : boardFieldList) {
            field.clearPushState();
        }
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

    private boolean isWithinBounds(int currentPosX, int currentPosY) {
        return currentPosX >= board.length || currentPosY >= board.length || currentPosX < 0 || currentPosY < 0;
    }

}
