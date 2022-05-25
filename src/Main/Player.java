package Main;

public class Player {

    private final int number;
    private int points = 0;
    private boolean isMe;

    private BewertungsFunktion bewertung;

    public Player(int number, boolean isMe, BewertungsFunktion bewertung){
        this.number = number;
        this.isMe = isMe;
        this.bewertung = bewertung;
    }

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

    public int getPoints() {
        return points;
    }

    public void registerSelf() {
        isMe = true;
    }

    public BewertungsFunktion getBewertung(){
        return bewertung;
    }
}