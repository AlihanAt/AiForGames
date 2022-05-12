public class Player {

    private final int number;

    private int points = 0;

    private boolean isMe;

    public Player(int number, boolean isMe){
        this.number = number;
        this.isMe = isMe;
    }

    public Player deepCopy(){
        Player p = new Player(this.number, this.isMe);
        p.points = this.points;
        return p;
    }

    public void addPoints(int count){
        this.points += count;
    }

    public boolean isMe() {
        return isMe;
    }

    public int getNumber() {
        return number;
    }

    public void registerSelf() {
        isMe = true;
    }
}