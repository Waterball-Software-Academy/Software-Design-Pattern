package v2;

import java.util.List;

import static utils.ValidationUtils.shouldMatch;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public abstract class Item {
    protected final String name;
    protected Directory parent;

    public Item(String name) {
        this.name = shouldMatch("[A-Za-z0-9.\\-_]+", name);
    }

    public abstract long totalBytes();

    public abstract List<Item> search(String keyword);

    public void setParent(Directory parent) {
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public Directory getParent() {
        return parent;
    }
}
