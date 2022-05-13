package common;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.min;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class Group {
    private static int count = 0;
    private final int number;
    private final List<Student> students;

    public Group() {
        this(new ArrayList<>());
    }

    public Group(List<Student> students) {
        this.number = ++count;
        this.students = new ArrayList<>(students);
    }

    public Student get(int index) {
        return students.get(index);
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public List<Group> splitBySize(int groupSize) {
        List<Group> groups = new ArrayList<>();
        // 0~5
        for (int i = 0; i < size(); i += groupSize) {
            groups.add(new Group(students.subList(i, min(i + groupSize, students.size()))));
        }
        return groups;
    }

    public void merge(Group group) {
        students.addAll(group.getStudents());
    }

    public List<Student> getStudents() {
        return students;
    }

    public int size() {
        return students.size();
    }

    public int getNumber() {
        return number;
    }
}
