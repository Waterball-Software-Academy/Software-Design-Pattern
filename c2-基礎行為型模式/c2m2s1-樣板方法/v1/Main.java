package v1;

import common.Group;
import common.GroupingStrategy;
import common.ReadStudents;
import common.Student;
import v2.JobTitleBasedGroupingStrategy;

import java.io.IOException;
import java.util.List;

import static common.Utils.printGroups;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class Main {
    public static void main(String[] args) throws IOException {
        GroupingStrategy groupingStrategy = new JobTitleBasedGroupingStrategy();
        List<Student> students = ReadStudents.fromFile("student.data");
        List<Group> groups = groupingStrategy.group(students);

        printGroups(groups);
    }
}
