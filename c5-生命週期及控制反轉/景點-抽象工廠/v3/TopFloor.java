package v3;

import v3.factories.FloorAbstractFactory;

/**
 * @author johnny@waterballsa.tw
 */
public class TopFloor extends Floor {

    public TopFloor(Game game, FloorAbstractFactory factory) {
        super(game, "Top Floor", null, 1, factory);
    }

    @Override
    public void access(Player player) {
        System.out.println("You win this game! Congratulations!");
        game.over();
    }

}
