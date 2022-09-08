package v1;

import static utils.ValidationUtils.shouldMatch;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class File {
    private Directory parent;
    private final String name;
    private final byte[] content;

    public File(String name, String content) {
        this.name = shouldMatch("[A-Za-z0-9.-_]", name);
        this.content = content.getBytes();
    }

    public String getName() {
        return name;
    }

    public long bytes() {
        return content.length;
    }

    public Directory getParent() {
        return parent;
    }

    public void setParent(Directory parent) {
        this.parent = parent;
    }
}
