package v2pull;

import common.ReadStudents;
import common.Student;

import java.io.IOException;
import java.util.*;

import static common.Utils.delay;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class StudentDataFile {
    private boolean monitoring;
    private final String studentDataFileName;
    private Set<Student> students = new HashSet<>();
    private final List<StudentDataObserver> observers = new ArrayList<>();

    public StudentDataFile(String studentDataFileName) {
        this.studentDataFileName = studentDataFileName;
    }

    public void startMonitoring() {
        monitoring = true;
        new Thread(this::monitoring).start();
    }

    private void monitoring() {
        while (monitoring) {
            delay(1000);
            try {
                watchStudentData();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public void register(StudentDataObserver observer) {
        this.observers.add(observer);
    }

    public void unregister(StudentDataObserver observer) {
        this.observers.remove(observer);
    }

    private void notifyObservers() throws IOException {
        for (StudentDataObserver observer : observers) {
            observer.update();
        }
    }

    private void watchStudentData() throws IOException {
        Set<Student> newStudents = new HashSet<>(ReadStudents.fromFile(studentDataFileName));
        if (!StudentDataFile.this.students.equals(newStudents)) {
            StudentDataFile.this.students = newStudents;
            notifyObservers();
        }
    }

    public Collection<Student> getStudents() {
        return students;
    }

    public void stopMonitoring() {
        monitoring = false;
    }
}
