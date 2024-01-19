package v2;

import java.util.Objects;

import static java.lang.String.format;

/**
 * @author johnny@waterballsa.tw
 */
public class BasePortal implements Portal {
    private static int count = 0;
    private int id;
    private final Portable p1;
    private final Portable p2;
    private int life = 3;

    public static BasePortal makePortal(Portable p1, Portable p2) {
        System.out.printf("[New portal connects %s <-> %s]%n", p1.getName(), p2.getName());
        return new BasePortal(p1, p2);
    }

    public BasePortal(Portable p1, Portable p2) {
        this.id = count++;
        this.p1 = p1;
        this.p2 = p2;
        p1.addPortal(this);
        p2.addPortal(this);
    }

    @Override
    public int id() {
        return id;
    }

    @Override
    public void access(Player player) {
        Portable target = getTarget(player);

        target.access(player);
        if (--life <= 0) {
            destroy();
        }
    }

    @Override
    public Portable getTarget(Player player) {
        if (player.isAt(p1)) {
            return p2;
        } else if (player.isAt(p2)) {
            return p1;
        } else {
            throw new IllegalStateException("The player is not in one of the region that can access this portal.");
        }
    }

    @Override
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
        return id == that.id();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
