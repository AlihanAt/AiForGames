package Main;

import java.sql.SQLOutput;

public class BoardField {
    private Stone stone;
    private final Board board;

    public BoardField(Board board){
        this.board = board;
    }

    public BoardField deepCopy(Board board){
        BoardField b =  new BoardField(board);
        if(this.stone != null)
            b.stone = this.stone.deepCopy();
        else
            b.stone = null;

        return b;
    }

    private Stone getStone() {
        return stone;
    }

    private void setStone(Stone stone) {
        this.stone = stone;
    }

    public boolean createStone(Player player){
        if(stone == null){
            stone = new Stone(player.getNumber());
            return true;
        }
        return false;
    }

    public void clearField() {
        stone = null;
    }

    public boolean isFieldInUse() {
        return stone != null;
    }

    public boolean isStoneFromPlayer(int playerNo) {
        return stone != null && stone.getPlayerNo() == playerNo;
    }

    public void pushStone(BoardField next){
        next.setStone(this.getStone());
        next.getStone().push();
        this.clearField();
    }

    public boolean hasStoneBeenPushed() {
        return stone.wasPushed();
    }

    public void clearPushState() {
        if(stone != null)
            stone.resetPush();
    }

    public boolean isSamePlayerAs(BoardField next) {
        return this.getStone().getPlayerNo() == next.getStone().getPlayerNo();
    }

    public void addPoint() {
        if(this.getStone().playerNo == 0)
            return;

        board.getPlayer(this.getStone().getPlayerNo()).addPoints(1);

        if(this.getStone().getPlayerNo() == board.getMyNumber()){
            board.myPointsThisRound++;
        }
        else {
            board.enemyPointsThisRound++;
        }
    }

    @Override
    public String toString() {
        if(stone == null || stone.getPlayerNo() == 0)
            return "-";

        return "" + stone.getPlayerNo();
    }

    //TODO static vllt weg, mal schauen
    private static final class Stone {
        private final int playerNo;
        private boolean wasPushed;

        public Stone(int player) {
            this.playerNo = player;
        }

        public int getPlayerNo() {
            return playerNo;
        }

        public boolean wasPushed() {
            return wasPushed;
        }

        public void push() {
            wasPushed = true;
        }

        public void resetPush() {
            wasPushed = false;
        }

        public Stone deepCopy() {
            return new Stone(playerNo);
        }
    }

}


