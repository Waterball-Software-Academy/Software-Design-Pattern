package abstractclass;

/**
 * @author johnny@waterballsa.tw
 */
public class Test {
    public static void main(String[] args) {
        Game game = new Game(new Human(2), new AI(2));
        game.start();
    }
}
