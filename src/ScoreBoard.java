public class ScoreBoard {

    private Player[] players = new Player[4];

    private Player playerA;
    private Player playerB;
    private Player playerC;
    private Player playerD;

    private ScoreBoard(){
//        this.playerA = new Player();
//        this.playerB = new Player();
//        this.playerC = new Player();
//        this.playerD = new Player();
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
