package v1;
/**
 * @author johnny@waterballsa.tw
 */
public class TopFloor extends Floor {
    public TopFloor(Game game) {
        super(game, "Top Floor", null, 1);
    }

    @Override
    public void access(Player player) {
        System.out.println("You win this game! Congratulations!");
        game.over();
    }

}
