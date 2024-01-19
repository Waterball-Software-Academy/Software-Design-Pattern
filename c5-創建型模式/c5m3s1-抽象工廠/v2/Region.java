package v2;

import java.util.Collection;

/**
 * @author johnny@waterballsa.tw
 */
public interface Region extends Portable {
    String getName();

    void addPortal(Portal portal);

    void removePortal(Portal portal);

    boolean hasSpaceForMorePortals();

    Collection<Portal> getPortals();

    int getNumber();

    Floor getFloor();
}
