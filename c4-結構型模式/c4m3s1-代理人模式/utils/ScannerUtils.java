package utils;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class ScannerUtils {
    private static final Scanner in = new Scanner(System.in);

    public static int inputIntegerBetween(int min, int max, String errorMessage) {
        return inputNumberBetween(in::nextInt, min, max, errorMessage);
    }

    public static double inputDoubleBetween(double min, double max, String errorMessage) {
        return inputNumberBetween(in::nextDouble, min, max, errorMessage);
    }

    public static <T extends Number> T inputNumberBetween(Supplier<T> nextNumberSupplier,
                                                          T min, T max, String errorMessage) {
        try {
            T choice = nextNumberSupplier.get();
            if (choice.doubleValue() < min.doubleValue() || choice.doubleValue() > max.doubleValue()) {
                System.err.println(errorMessage);
                return inputNumberBetween(nextNumberSupplier, min, max, errorMessage);
            }
            return choice;
        } catch (InputMismatchException err) {
            System.err.println(errorMessage);
            return inputNumberBetween(nextNumberSupplier, min, max, errorMessage);
        }
    }

    public static String inputLine() {
        String line = in.nextLine();
        if (line.isBlank()) {
            return inputLine();
        }
        return line;
    }

    public static String input() {
        String line = in.next();
        if (line.isBlank()) {
            return input();
        }
        return line;
    }
}
