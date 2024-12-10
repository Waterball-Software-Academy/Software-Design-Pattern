package lecture;

import org.jetbrains.annotations.Nullable;

import static utils.ValidationUtils.shouldBeWithinRange;

/**
 * @author johnny@waterballsa.tw
 */
public class LectureAttendance {
    private final Student student;
    private final Lecture lecture;

    @Nullable
    private Integer grade;

    public LectureAttendance(Student student, Lecture lecture) {
        this.student = student;
        this.lecture = lecture;
    }

    public void receiveGrade(int grade) {
        this.grade = shouldBeWithinRange("Grade", grade, 0, 100);
        System.out.printf("在 '%s' 課程中，學生 '%s' 拿了 '%d' 分。\n",
                lecture.getName(), student.getName(), grade);
    }

    @Nullable
    public Integer getGrade() {
        return grade;
    }

    public Student getStudent() {
        return student;
    }

    public Lecture getLecture() {
        return lecture;
    }
}
