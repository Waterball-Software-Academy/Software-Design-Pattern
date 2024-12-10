package common;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static java.lang.Integer.parseInt;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class ReadStudents {

    public static List<Student> fromFile(String fileName) throws IOException {
        return Files.lines(Paths.get(fileName))
                .map(ReadStudents::dataLineToStudent)
                .collect(toList());
    }

    // Example line: Cortez 1y Go 軟體工程師 [9 10 11 12 14 15 16 19]
    private static Student dataLineToStudent(String line) {
        String[] s = line.split("\\[");
        String[] left = s[0].split(" ");
        String name = left[0];
        int experience = parseInt(left[1].substring(0, left[1].length()-1));
        String language = left[2];
        String title = left[3];
        List<Integer> slots = stream(s[1].split("(\\s|])"))
                .map(Integer::parseInt).collect(toList());
        boolean[] availableTimeSlots = new boolean[13];
        for (Integer slot : slots) {
            availableTimeSlots[slot-9] = true;
        }
        return new Student(name, experience, language, title, availableTimeSlots);
    }
}
