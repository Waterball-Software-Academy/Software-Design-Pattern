package v3.thirdparty;

import v3.*;

/**
 * @author johnny@waterballsa.tw
 */
public class SuperPortal extends PortalDecorator {

    public SuperPortal(Portal next) {
        super(next);
    }

    @Override
    public void access(Player player) {
        System.out.println("<有機率打廣告>");
        next.access(player);
    }
}
