package common;

import com.github.javafaker.Faker;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.util.stream.Collectors.joining;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class StudentGenerations {
    private final static Random random = new Random();
    private final static Faker faker = new Faker();
    private final static String[] languages = {"C", "C++", "C#", "JAVA", "Javascript", "Python", "Kotlin", "Ruby", "Go", "php"};
    private final static String[] titles = {"軟體工程師", "PM", "資深軟體工程師", "維運工程師", "網路工程師", "學生", "接案工程師"};
    public static final int NUMER_OF_GENERATED_MEMBERS = 3000;

    public static void main(String[] args) throws IOException {
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < NUMER_OF_GENERATED_MEMBERS; i++) {
            students.add(new Student(faker.name().firstName(), random.nextInt(20),
                    languages[random.nextInt(languages.length)],
                    titles[random.nextInt(titles.length)], generateAvailableTimeSlots()));
        }

        Files.write(Paths.get("student.data"),
                students.stream().map(Student::toString)
                        .collect(joining("\n")).getBytes());

    }

    private static boolean[] generateAvailableTimeSlots() {
        boolean[] slots = new boolean[13];
        for (int i = 0; i < slots.length; i++) {
            slots[i] = random.nextBoolean();
        }
        return slots;
    }
}
