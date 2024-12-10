package lecture;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.unmodifiableList;

/**
 * @author johnny@waterballsa.tw
 */
public class Student {
    private final String name;

    private final List<LectureAttendance> lectureAttendances = new ArrayList<>(); // 代表他修了哪些課 (0..*)

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<LectureAttendance> getLectureAttendances() {
        return unmodifiableList(lectureAttendances);
    }

    public void addLectureAttendance(LectureAttendance lectureAttendance) {
        lectureAttendances.add(lectureAttendance);
    }

    public void removeLectureAttendance(LectureAttendance lectureAttendance) {
        lectureAttendances.remove(lectureAttendance);
    }
}
