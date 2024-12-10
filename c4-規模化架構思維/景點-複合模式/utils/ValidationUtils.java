package utils;

import java.util.regex.Pattern;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class ValidationUtils {
    public static String shouldMatch(String regex, String content) {
        if (!Pattern.matches(regex, content)) {
            throw new IllegalArgumentException(
                    String.format("Content '%s' doesn't match '%s'",
                            content, regex));
        }
        return content;
    }
}
