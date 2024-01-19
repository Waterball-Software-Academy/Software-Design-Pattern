package v2;

import java.util.Collection;

/**
 * @author johnny@waterballsa.tw
 */
public abstract class RegionDecorator implements Region {
    protected Region next;

    public RegionDecorator(Region next) {
        this.next = next;
    }

    @Override
    public String getName() {
        return next.getName();
    }

    @Override
    public void addPortal(Portal portal) {
        next.addPortal(portal);
    }

    @Override
    public void removePortal(Portal portal) {
        next.removePortal(portal);
    }

    @Override
    public boolean hasSpaceForMorePortals() {
        return next.hasSpaceForMorePortals();
    }

    @Override
    public Collection<Portal> getPortals() {
        return next.getPortals();
    }

    @Override
    public int getNumber() {
        return next.getNumber();
    }

    @Override
    public Floor getFloor() {
        return next.getFloor();
    }

    @Override
    public boolean equals(Object obj) {
        return next.equals(obj);
    }

    @Override
    public int hashCode() {
        return next.hashCode();
    }
}
