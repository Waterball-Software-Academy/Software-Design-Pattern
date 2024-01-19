package v2.factories;

import v2.Portable;
import v2.Portal;

/**
 * @author johnny@waterballsa.tw
 */
public interface PortalFactory {
    Portal createPortal(Portable p1, Portable p2);
}
