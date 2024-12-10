package v1and2.crawler;

import java.util.List;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class SuperWORD {
    public String raw;
    public List<String> definitions; // format: <part of speech> <description>

    public SuperWORD(String raw, List<String> definitions) {
        this.raw = raw;
        this.definitions = definitions;
    }
}
