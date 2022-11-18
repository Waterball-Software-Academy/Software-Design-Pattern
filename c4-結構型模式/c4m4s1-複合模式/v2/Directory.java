package v2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class Directory extends Item {
    private final List<Item> children = new ArrayList<>();

    public static Directory root() {
        return new Directory("root");
    }

    public Directory(String name) {
        super(name);
    }

    public Item getItem(String name) {
        for (Item child : children) {
            if (name.equals(child.getName())) {
                return child;
            }
        }
        return null;
    }

    public void addItem(Item item) {
        children.add(item);
        item.setParent(this);
    }

    @Override
    public long bytes() {
        return children.stream().mapToLong(Item::bytes).sum();
    }
}
