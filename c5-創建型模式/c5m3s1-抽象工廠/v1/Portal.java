package v1;

import java.util.List;
import java.util.Objects;

import static java.lang.String.format;

/**
 * @author johnny@waterballsa.tw
 */
public class Portal {
    private static int count = 0;
    private final int id;
    private final Portable p1;
    private final Portable p2;
    private int life = 3;

    public static Portal makePortal(List<? extends Portable> portables) {
        return makePortal(portables.get(0), portables.get(1));
    }

    public static Portal makePortal(Portable p1, Portable p2) {
        System.out.printf("[New portal connects %s <-> %s]%n", p1.getName(), p2.getName());
        return new Portal(p1, p2);
    }

    public Portal(Portable p1, Portable p2) {
        this.id = count++;
        this.p1 = p1;
        this.p2 = p2;
        p1.addPortal(this);
        p2.addPortal(this);
    }

    public void access(Player player) {
        // 找到另一端點
        Portable target = getTarget(player);

        // Region or Floor
        target.access(player);
        if (--life <= 0) {
            destroy();
        }
    }

    public Portable getTarget(Player player) {
        if (player.isAt(p1)) {
            return p2;
        } else if (player.isAt(p2)) {
            return p1;
        } else {
            throw new IllegalStateException("The player is not in one of the region that can access this portal.");
        }
    }

    public void destroy() {
        System.out.printf("[Portal (%s) is destroyed]%n", this);
        p1.removePortal(this);
        p2.removePortal(this);
    }

    @Override
    public String toString() {
        return format("%s <--> %s", p1.getName(), p2.getName());
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        Portal that = (Portal) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
