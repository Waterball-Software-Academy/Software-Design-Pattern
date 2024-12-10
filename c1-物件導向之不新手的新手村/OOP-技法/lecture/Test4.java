package lecture;

/**
 * @author johnny@waterballsa.tw
 */
public class Test4 {
    public static void main(String[] args) {
        Student student1 = new Student("Johnny");
        Student student2 = new Student("Peter");
        Lecture lecture1 = new Lecture("軟體設計模式精通之旅");
        Lecture lecture2 = new Lecture("GenAI 工程師產能爆發之路");

        lecture1.signUp(student1);
        lecture2.signUp(student1);
        lecture2.signUp(student2);

        try {
            lecture1.signUp(student1);
        } catch (Exception err) {
            System.out.println("不能重複註冊！");
        }

        for (LectureAttendance attendance : lecture1.getLectureAttendances()) {
            attendance.receiveGrade(60); // 打分數
        }

        for (LectureAttendance attendance : lecture2.getLectureAttendances()) {
            attendance.receiveGrade(100); // 打分數
        }

    }
}
