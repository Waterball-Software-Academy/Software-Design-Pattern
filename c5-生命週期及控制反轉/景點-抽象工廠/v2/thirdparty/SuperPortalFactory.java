package v2.thirdparty;

import v2.BasePortal;
import v2.Portable;
import v2.Portal;
import v2.factories.PortalFactory;

/**
 * @author johnny@waterballsa.tw
 */
public class SuperPortalFactory implements PortalFactory {
    // 裝飾者模式
    @Override
    public Portal createPortal(Portable p1, Portable p2) {
        return new SuperPortal(BasePortal.makePortal(p1, p2));
    }
}
