public class BoardField {
    private Stone stone;

    private Stone getStone() {
        return stone;
    }

    private void setStone(Stone stone) {
        this.stone = stone;
    }

    public boolean createStone(Player player){
        if(stone == null){
            stone = new Stone(player);
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
        return stone != null && stone.getPlayer().getNumber() == playerNo;
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
        return this.getStone().getPlayer().getNumber() == next.getStone().getPlayer().getNumber();
    }

    public void addPoint() {
        this.getStone().getPlayer().addPoints(1);
    }

    @Override
    public String toString() {
        if(stone == null || stone.getPlayer() == null)
            return "-";

        return "" + stone.getPlayer().getNumber();
    }

    //TODO static vllt weg, mal schauen
    private static final class Stone {
        private final Player player;
        private boolean wasPushed;

        public Stone(Player player) {
            this.player = player;
        }

        public Player getPlayer() {
            return player;
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
    }

}


