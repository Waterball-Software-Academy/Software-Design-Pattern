package v2;

import java.util.Collections;
import java.util.List;

import static java.util.Collections.emptyList;
import static utils.ValidationUtils.shouldMatch;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class File extends Item {
    private final byte[] content;

    public File(String name, String content) {
        super(name);
        this.content = content.getBytes();
    }

    public String getName() {
        return name;
    }

    @Override
    public long totalBytes() {
        return content.length;
    }

    @Override
    public List<Item> search(String keyword) {
        return emptyList();
    }
}
