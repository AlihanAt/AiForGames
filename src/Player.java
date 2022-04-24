public class Player {

    private final int number;

    private int points;

    private boolean isMe;

    public Player(int number, boolean isMe){
        this.number = number;
        this.isMe = isMe;
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