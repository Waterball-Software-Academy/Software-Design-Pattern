package v2.defaults;

import v2.BasePortal;
import v2.Portable;
import v2.Portal;
import v2.factories.PortalFactory;

/**
 * @author johnny@waterballsa.tw
 */
public class BasePortalFactory implements PortalFactory {
    @Override
    public Portal createPortal(Portable p1, Portable p2) {
        return BasePortal.makePortal(p1, p2);
    }
}
