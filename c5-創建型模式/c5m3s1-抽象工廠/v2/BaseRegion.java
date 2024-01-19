package v2;

import v2.factories.StageFactory;

import java.util.*;

/**
 * @author johnny@waterballsa.tw
 */
public class BaseRegion implements Region {
    private final int number;
    private final Floor floor;
    private final Set<Portal> portals = new HashSet<>(4);
    private final StageFactory stageFactory;

    public BaseRegion(int number, Floor floor, StageFactory stageFactory) {
        this.number = number;
        this.floor = floor;
        this.stageFactory = stageFactory;
    }

    @Override
    public String getName() {
        return String.format("Region %d", number);
    }

    @Override
    public void access(Player player) {
        System.out.printf("Enter %s%n.", getName());
        player.setCurrentRegion(this);
        Stage stage = stageFactory.createStage(this);
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

    @Override
    public boolean hasSpaceForMorePortals() {
        return portals.size() < 4;
    }

    @Override
    public Collection<Portal> getPortals() {
        return portals;
    }

    @Override
    public int getNumber() {
        return number;
    }

    @Override
    public Floor getFloor() {
        return floor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        Region that = (Region) o;
        return number == that.getNumber();
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
