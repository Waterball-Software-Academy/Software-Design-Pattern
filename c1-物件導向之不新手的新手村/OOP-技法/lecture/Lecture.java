package lecture;

import java.util.ArrayList;
import java.util.List;

/**
 * @author johnny@waterballsa.tw
 */
public class Lecture {
    private final String name;

    private final List<LectureAttendance> lectureAttendances = new ArrayList<>(); // 代表有哪些學生參與了這門課

    public Lecture(String name) {
        this.name = name;
    }

    public void signUp(Student student) {
        if (lectureAttendances.stream().anyMatch(l -> l.getStudent() == student)) {
            throw new IllegalStateException("Student has taken a lecture.");
        }

        LectureAttendance lectureAttendance = new LectureAttendance(student, this);
        lectureAttendances.add(lectureAttendance);
        student.addLectureAttendance(lectureAttendance);
    }

    public void signOff(Student student) {
        LectureAttendance attendance = lectureAttendances.stream().filter(l -> l.getStudent() == student).findFirst().orElse(null);
        if (attendance == null) {
            throw new IllegalStateException("The student didn't sign up this lecture.");
        }

        lectureAttendances.remove(attendance);
        student.removeLectureAttendance(attendance);
    }

    public String getName() {
        return name;
    }

    public List<LectureAttendance> getLectureAttendances() {
        return lectureAttendances;
    }
}
