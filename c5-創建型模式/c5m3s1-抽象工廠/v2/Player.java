package v2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

import static java.lang.String.format;
import static java.util.stream.Collectors.joining;

/**
 * @author johnny@waterballsa.tw
 */
public class Player {
    private final Scanner in = new Scanner(System.in);
    private boolean alive = true;
    private Floor currentFloor;
    private Region currentRegion;

    public Floor getCurrentFloor() {
        return currentFloor;
    }

    public Region getCurrentRegion() {
        return currentRegion;
    }

    public void setCurrentFloor(Floor currentFloor) {
        this.currentFloor = currentFloor;
    }

    public void setCurrentRegion(Region region) {
        this.currentRegion = region;
    }

    public boolean isAt(Portable portable) {
        if (portable instanceof Region) {
            return currentRegion.equals(portable);
        }
        if (portable instanceof Floor) {
            return currentFloor.equals(portable);
        }
        throw new IllegalStateException("Unexpected type");
    }

    public void loseGame() {
        alive = false;
    }

    public boolean isAlive() {
        return alive;
    }

    public Portal selectPortal(Collection<Portal> portals) {
        List<Portal> portalList = new ArrayList<>(portals);
        System.out.printf("Select a portal (You're at %s): %n%s%n",
                currentRegion.getName(),
                IntStream.range(0, portalList.size())
                        .mapToObj(i -> format("(%d) --> %s", i, portalList.get(i).getTarget(this).getName()))
                        .collect(joining("\n")));
        return portalList.get(in.nextInt());
    }

    public boolean isAt(Region region) {
        return currentRegion == region;
    }

    public String answer() {
        return in.next();
    }
}
