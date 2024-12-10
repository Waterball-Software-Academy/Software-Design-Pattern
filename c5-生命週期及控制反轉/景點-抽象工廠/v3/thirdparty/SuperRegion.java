package v3.thirdparty;

import v3.*;

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
