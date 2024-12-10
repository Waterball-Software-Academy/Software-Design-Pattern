package utils;

import static java.lang.String.format;

/**
 * @author johnny@waterballsa.tw
 */
public class ValidationUtils {

    public static int shouldBeGreaterThanOrEqual(String name, int num, int target) {
        if (num < target) {
            throw new IllegalArgumentException(format("'%s' must be greater than or equal %d.", name, target));
        }
        return num;
    }

    public static int shouldBeWithinRange(String name, int num, int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("Min must be <= Max!");
        }
        if (num < min || num > max) {
            throw new IllegalArgumentException(format("'%s' must be within 0~100.", name));
        }
        return num;
    }
}
