package v2;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.concat;

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

    public Optional<Item> getItem(String name) {
        return children.stream()
                .filter(c -> c.getName().equals(name))
                .findFirst();
    }

    public void addItem(Item item) {
        children.add(item);
        item.setParent(this);
    }

    @Override
    public long totalBytes() {
        return children.stream().mapToLong(Item::totalBytes).sum();
    }

    @Override
    public List<Item> search(String keyword) {
        return children.stream()
                .filter(c -> c.getName().contains(keyword))
                .flatMap(c -> concat(Stream.of(c), c.search(keyword).stream()))
                .collect(toList());
    }
}
