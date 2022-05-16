package Main;

public class ScoreBoard {

    private Player[] players = new Player[4];

    private Player playerA;
    private Player playerB;
    private Player playerC;
    private Player playerD;

    private ScoreBoard(){
//        this.playerA = new Main.Player();
//        this.playerB = new Main.Player();
//        this.playerC = new Main.Player();
//        this.playerD = new Main.Player();
    }

    public Player getPlayerA(){
        return playerA;
    }

    public Player getPlayerB() {
        return playerB;
    }

    public Player getPlayerC() {
        return playerC;
    }

    public Player getPlayerD() {
        return playerD;
    }

    public void addPoints(Player player, int count){
        player.addPoints(count);
    }

}
