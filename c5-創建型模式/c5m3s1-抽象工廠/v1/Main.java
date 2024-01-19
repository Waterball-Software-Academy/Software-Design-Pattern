package v1;
/**
 * @author johnny@waterballsa.tw
 */
public class Main {
    public static void main(String[] args) {
        Game game = new Game();

        TopFloor topFloor = new TopFloor(game); // TopFloor extends Floor
        Floor f3 = new Floor(game, "Floor 3", topFloor);
        Floor f2 = new Floor(game, "Floor 2", f3);
        Floor f1 = new Floor(game, "Floor 1", f2); // the next floor

        game.setFloor1(f1);
        game.start();
    }
}
