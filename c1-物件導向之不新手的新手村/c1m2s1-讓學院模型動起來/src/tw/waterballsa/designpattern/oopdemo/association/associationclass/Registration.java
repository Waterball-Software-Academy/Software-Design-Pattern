package tw.waterballsa.designpattern.oopdemo.association.associationclass;

/**
 * @author johnny@waterballsa.tw
 */
public class Registration {
    private final School school;
    private final Student student;
    private final int score;

    public Registration(School school, Student student, int score) {
        this.school = school;
        this.student = student;
        this.score = score;
    }

    public School getSchool() {
        return school;
    }

    public Student getStudent() {
        return student;
    }

    public int getScore() {
        return score;
    }
}
