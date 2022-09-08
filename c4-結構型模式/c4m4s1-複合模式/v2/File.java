package v2;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class File extends Item {
    private final byte[] content;

    public File(String name, String content) {
        super(name);
        this.content = content.getBytes();
    }

    @Override
    public long bytes() {
        return content.length;
    }
}
