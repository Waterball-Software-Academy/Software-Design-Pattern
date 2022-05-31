package v2pull;

import common.Student;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static java.util.stream.Collectors.joining;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class StudentDataFileBackup implements StudentDataObserver {
    private final StudentDataFile dataFile;

    public StudentDataFileBackup(StudentDataFile dataFile) {
        this.dataFile = dataFile;
    }

    @Override
    public void update() throws IOException {
        String fileName = new SimpleDateFormat("yyyyMMdd-HH:mm:ss'.backup.data'", Locale.getDefault()).format(new Date());
        Files.writeString(Paths.get(fileName), dataFile.getStudents().stream()
                .map(Student::toString)
                .collect(joining("\n")));
    }
}
