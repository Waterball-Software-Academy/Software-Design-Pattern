package v2.thirdparty;

import v2.Player;
import v2.Region;
import v2.RegionDecorator;

/**
 * @author johnny@waterballsa.tw
 */
public class SuperRegion extends RegionDecorator {

    public SuperRegion(Region next) {
        super(next);
    }

    @Override
    public void access(Player player) {
        System.out.println("<Waterball Region>");
        next.access(player);
    }
}
