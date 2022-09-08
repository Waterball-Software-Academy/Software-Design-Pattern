package v2;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class Directory extends Item {

    public static Directory root() {
        return new Directory("");
    }

    public Directory(String name) {
        super(name);
    }

    @Override
    public long bytes() {
        long total = 0;
        for (Item child : children) {
            total += child.bytes();
        }
        return total;
    }

}
