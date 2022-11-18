package lib.ioc;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class DuplicateInstanceTypeException extends IllegalStateException {
    private final Object instance;

    public DuplicateInstanceTypeException(Object instance) {

        this.instance = instance;
    }
}
