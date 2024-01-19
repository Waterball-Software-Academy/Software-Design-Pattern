package v1;

import v2.BaseRegion;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author johnny@waterballsa.tw
 */
public class Region implements Portable {
    private final int number;
    private final Floor floor;
    private final Set<Portal> portals = new HashSet<>(4);

    public Region(int number, Floor floor) {
        this.number = number;
        this.floor = floor;
    }

    @Override
    public String getName() {
        return String.format("Region %d", number);
    }

    @Override
    public void access(Player player) {
        System.out.printf("Enter %s%n.", getName());
        player.setCurrentRegion(this);
        Stage stage = new Stage(floor);
        stage.play(player);
    }

    @Override
    public void addPortal(Portal portal) {
        if (hasSpaceForMorePortals()) {
            portals.add(portal);
        } else {
            throw new IllegalStateException("The number of portals in a region must not exceed 4.");
        }
    }

    @Override
    public void removePortal(Portal portal) {
        portals.remove(portal);
    }

    public boolean hasSpaceForMorePortals() {
        return portals.size() < 4;
    }

    public Collection<Portal> getPortals() {
        return portals;
    }

    public int getNumber() {
        return number;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        Region that = (Region) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
