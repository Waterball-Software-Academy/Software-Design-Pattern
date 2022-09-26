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
    protected Directory parent;

    public Item(String name) {
        this.name = shouldMatch("[A-Za-z0-9.\\-_]+", name);
    }

    public Directory getParent() {
        return parent;
    }

    public void setParent(Directory parent) {
        this.parent = parent;
    }

    public abstract long bytes();

    public String getName() {
        return name;
    }
}
