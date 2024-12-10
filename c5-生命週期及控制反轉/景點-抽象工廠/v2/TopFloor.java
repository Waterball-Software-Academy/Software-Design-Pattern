package v2;

import v2.factories.PortalFactory;
import v2.factories.RegionFactory;

/**
 * @author johnny@waterballsa.tw
 */
public class TopFloor extends Floor {

    public TopFloor(Game game, RegionFactory regionFactory, PortalFactory portalFactory) {
        super(game, "Top Floor", null, 1, regionFactory, portalFactory);
    }

    @Override
    public void access(Player player) {
        System.out.println("You win this game! Congratulations!");
        game.over();
    }

}
