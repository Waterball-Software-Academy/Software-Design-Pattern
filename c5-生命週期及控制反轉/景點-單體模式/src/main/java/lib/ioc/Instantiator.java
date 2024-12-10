package lib.ioc;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public interface Instantiator<T> {
    T instantiate(Object ...args);
}
