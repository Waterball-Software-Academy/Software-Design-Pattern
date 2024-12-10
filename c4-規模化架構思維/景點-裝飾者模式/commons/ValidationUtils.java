package commons;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class ValidationUtils {
    public static String lengthShouldBeWithin(String content, int min, int max) {
        if (content.length() < min || content.length() > max) {
            throw new IllegalArgumentException(String.format("The length must be within %d~%d.", min, max));
        }
        return content;
    }
}
