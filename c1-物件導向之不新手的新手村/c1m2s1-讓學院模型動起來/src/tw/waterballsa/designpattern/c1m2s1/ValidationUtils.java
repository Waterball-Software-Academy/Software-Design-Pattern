package tw.waterballsa.designpattern.c1m2s1;

import java.math.BigDecimal;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class ValidationUtils {
    public static String lengthShouldBe(String text, int minLength, int maxLength) {
        if (text.length() < minLength || text.length() > maxLength) {
            throw new IllegalArgumentException(
                    String.format("The length of '%s' should be within the range %d ~ %d.", text, minLength, maxLength));
        }
        return text;
    }

    public static BigDecimal shouldBeBiggerThan(BigDecimal value, int target) {
        int val = value.intValueExact();
        if (val <= target) {
            throw new IllegalArgumentException(
                    String.format("The value %d should be bigger than %d.", val, target));
        }
        return value;
    }

    public static int shouldBePositive(int value) {
        if (value <= 0) {
            throw new IllegalArgumentException("The value should be positive.");
        }
        return value;
    }
}
