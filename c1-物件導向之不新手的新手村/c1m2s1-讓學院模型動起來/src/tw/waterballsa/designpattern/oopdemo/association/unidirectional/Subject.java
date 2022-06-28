package tw.waterballsa.designpattern.oopdemo.association.unidirectional;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class Subject {
    private final String name;

    public Subject(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
