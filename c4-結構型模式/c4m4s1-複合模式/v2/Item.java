package v2;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.matches;
import static utils.ValidationUtils.shouldMatch;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public abstract class Item {
    protected final String name;
    protected Item parent;
    protected final List<Item> children = new ArrayList<>();

    public Item(String name) {
        this.name = shouldMatch("[A-Za-z0-9.-_]", name);
    }

    public Item getChild(String name) {
        for (Item child : children) {
            if (name.equals(child.name)) {
                return child;
            }
        }
        return null;
    }

    public void addChild(Item item) {
        children.add(item);
        item.setParent(this);
    }

    public Item getParent() {
        return parent;
    }

    public void setParent(Item parent) {
        this.parent = parent;
    }

    public abstract long bytes();

    public String getName() {
        return name;
    }
}
