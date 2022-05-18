package Main;

import java.util.List;

public class Board {

    private final BoardField[][] boardFields;
    private final List<BoardField> boardFieldList;
    private final Player[] players;

    private int myNumber;
    public int enemyPointsThisRound = 0;
    public int myPointsThisRound = 0;

    public Board() {
        players = new Player[]{
                new Player(1, false),
                new Player(2, false),
                new Player(3, false),
                new Player(4, false),
        };

        boardFields = new BoardField[8][8];

        for (int x = 0; x < boardFields.length; x++) {
            for (int y = 0; y < boardFields[x].length; y++) {
                boardFields[x][y] = new BoardField(this);
            }
        }

        boardFieldList = ListExtension.twoDArrayToList(boardFields);
    }

    private Board(Player[] playersToClone, BoardField[][] boardToClone) {
        players = getPlayersDeepCopy(playersToClone);
        boardFields = getBoardFieldDeepCopy(boardToClone);
        boardFieldList = ListExtension.twoDArrayToList(boardFields);
    }

    public Player getPlayerAndRegister(int playerNo) {
        myNumber = playerNo;
        players[playerNo - 1].registerSelf();
        return players[playerNo - 1];
    }

    public boolean addStone(int x, int y, int playerNo) {
        return boardFields[x][y].createStone(players[playerNo - 1]);
    }

    public void clearStone(int x, int y) {
        boardFields[x][y].clearField();
    }

    public void updateStonePositionsFrom(int playerNo) {
        int pushDirX = getPushDir(playerNo, true);
        int pushDirY = getPushDir(playerNo, false);

        for (int x = 0; x < boardFields.length; x++) {
            for (int y = 0; y < boardFields[x].length; y++) {

                if (boardFields[x][y].isStoneFromPlayer(playerNo) && !boardFields[x][y].hasStoneBeenPushed()) {
                    pushStone(x, y, pushDirX, pushDirY, playerNo);
                }

            }
        }

        clearPushState();
    }

    private int getPushDir(int playerNo, boolean isXAxis) {
        if ((isXAxis && playerNo == 2) || (!isXAxis && playerNo == 1))
            return 1;
        else if ((isXAxis && playerNo == 4) || (!isXAxis && playerNo == 3))
            return -1;
        else
            return 0;
    }

    private void pushStone(int currentPosX, int currentPosY, int pushDirX, int pushDirY, int playerNo) {

        int nextPosX = currentPosX + pushDirX;
        int nextPosY = currentPosY + pushDirY;

        if (isWithinBounds(nextPosX, nextPosY)) {
            boardFields[currentPosX][currentPosY].clearField();
            return;
        } else if (boardFields[nextPosX][nextPosY].isFieldInUse()) {
            boolean samePlayer = boardFields[currentPosX][currentPosY].isSamePlayerAs(boardFields[nextPosX][nextPosY]);

            if (!samePlayer && !boardFields[nextPosX][nextPosY].isStoneFromPlayer(playerNo)) {
                boardFields[currentPosX][currentPosY].addPoint();
            }

            pushStone(nextPosX, nextPosY, pushDirX, pushDirY, playerNo);
        }

        boardFields[currentPosX][currentPosY].pushStone(boardFields[nextPosX][nextPosY]);
    }

    private void clearPushState() {
        for (BoardField field : boardFieldList) {
            field.clearPushState();
        }
    }

    private void clearPointsThisRound() {
        myPointsThisRound = 0;
        enemyPointsThisRound = 0;
    }

    private boolean isWithinBounds(int currentPosX, int currentPosY) {
        return currentPosX >= boardFields.length || currentPosY >= boardFields.length || currentPosX < 0 || currentPosY < 0;
    }

    public void addMove(int x, int y) {
        int playerNo = getPlayerFromMove(x, y);

        if (playerNo == -1)
            return;

        updateStonePositionsFrom(playerNo);
        addStone(x, y, playerNo);
    }

    public int getPlayerFromMove(int x, int y) {

        if (y == 0) {
            //p0
            return 1;
        } else if (x == 0) {
            //p1
            return 2;
        } else if (y == 7) {
            //p2
            return 3;
        } else if (x == 7) {
            //p3
            return 4;
        }
        System.out.println("invalid move" + x + "," + y);
        return -1;
    }

    public void updateSkippedPlayers(int lastPlayer, int myNumber) {
        //gucken ob spieler geskippt wurden, wenn ja dann deren steine updaten
        if ((lastPlayer + 1) % 4 != myNumber)
            for (int i = lastPlayer + 1; i < (myNumber % 4) + 4; i++)
                updateStonePositionsFrom(modToPlayerNumber(i % 4));
    }

    private int modToPlayerNumber(int mod) {
        if (mod == 0)
            return 4;
        else
            return mod;
    }

    public int getMyNumber() {
        return myNumber;
    }

    public int fieldRating(int playerNo){

        int tempPlayerNo;
        if(playerNo == 4)
            tempPlayerNo = 1;
        else
            tempPlayerNo = playerNo+1;

        while (playerNo != tempPlayerNo){
            updateStonePositionsFrom(tempPlayerNo);
            tempPlayerNo++;

            if(tempPlayerNo == 5)
                tempPlayerNo = 1;
        }

        updateStonePositionsFrom(playerNo);

        int rating = enemyPointsThisRound-myPointsThisRound;
        clearPointsThisRound();
        return rating;
    }

    public Board deepCopy() {
        return new Board(this.players, this.boardFields);
    }

    private BoardField[][] getBoardFieldDeepCopy(BoardField[][] boardToClone) {
        BoardField[][] boardField = new BoardField[boardToClone.length][boardToClone.length];
        for (int i=0; i<boardField.length; i++){
            for (int j=0; j<boardField.length; j++){
                boardField[i][j] = boardToClone[i][j].deepCopy(this);
            }
        }
        return boardField;
    }

    private Player[] getPlayersDeepCopy(Player[] playersToClone) {
        return new Player[]{
                playersToClone[0].deepCopy(),
                playersToClone[1].deepCopy(),
                playersToClone[2].deepCopy(),
                playersToClone[3].deepCopy(),
        };
    }

    public BoardField[][] getBoardFields() {
        return boardFields;
    }

    public Player getPlayer(int playerNo) {
        return players[playerNo-1];
    }

    public int getPlayerScore(int playerNo){
        return getPlayer(playerNo).getPoints();
    }

    public int getFieldRatingForPlayer(int playerNo) {
        int enemyPoints = getEnemyPoints(playerNo);
        return getPlayerScore(playerNo) - enemyPoints;
    }

    public int getFieldRatingForEnemies(int playerNo){
        return getEnemyPoints(playerNo) - getPlayerScore(playerNo);
    }

    private int getEnemyPoints(int playerNo) {
        int tempPlayerNo;
        if(playerNo == 4)
            tempPlayerNo = 1;
        else
            tempPlayerNo = playerNo +1;

        int enemyPoints = 0;

        while (playerNo != tempPlayerNo) {
            enemyPoints = getPlayerScore(tempPlayerNo);
            tempPlayerNo++;

            if(tempPlayerNo == 5)
                tempPlayerNo = 1;
        }
        return enemyPoints;
    }

    public boolean hasPlayerWon(int playerNo){
        return getPlayerScore(playerNo) >= 44;
    }

    public boolean hasEnemyWon(int playerNo){
        int tempPlayerNo;
        if(playerNo == 4)
            tempPlayerNo = 1;
        else
            tempPlayerNo = playerNo +1;

        while (playerNo != tempPlayerNo) {

            if(getPlayerScore(tempPlayerNo) >= 44)
                return true;

            tempPlayerNo++;
            if(tempPlayerNo == 5)
                tempPlayerNo = 1;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();

        for (int x = 0; x < boardFields.length; x++) {
            for (int y = 0; y < boardFields[x].length; y++) {

                str.append(boardFields[x][y]);
                if (y + 1 != boardFields[x].length)
                    str.append(",");
            }
            if (x + 1 != boardFields.length)
                str.append("\n");
        }

        return str.toString();
    }


}
