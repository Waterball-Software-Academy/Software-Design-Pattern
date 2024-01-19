package v3;

/**
 * @author johnny@waterballsa.tw
 */
public class Game {
    private Floor floor1;
    private boolean over = false;

    public void start() {
        Player player = new Player();
        floor1.access(player);
    }

    public void over() {
        over = true;
        System.out.println("The game is over.");
    }

    public boolean isGameOver() {
        return over;
    }

    public void setFloor1(Floor floor1) {
        this.floor1 = floor1;
    }
}
