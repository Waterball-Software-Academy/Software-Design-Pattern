package tw.waterballsa.designpattern.oopdemo.dependency;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class Book {
    private final String name;

    public Book(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
