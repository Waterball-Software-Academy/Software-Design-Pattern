package v1;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class Main {
    public static void main(String[] args) {
        StudentDataFile studentDataFile = new StudentDataFile("student.data");
        studentDataFile.startMonitoring();
    }
}
