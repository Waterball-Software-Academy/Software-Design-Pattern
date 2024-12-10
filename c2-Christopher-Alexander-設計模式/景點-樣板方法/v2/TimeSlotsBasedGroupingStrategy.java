package v2;

import common.Group;
import common.GroupingStrategy;
import common.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.String.format;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class TimeSlotsBasedGroupingStrategy implements GroupingStrategy {

    public static final int GROUP_MIN_SIZE = 6;

    public List<Group> group(List<Student> students) {
        // 先用「成員有空的時間點」將成員分組
        List<Group> firstCut = cutGroupByTimeSlots(students);

        // 再到各組中以最低成員數 6 員分組
        List<Group> secondCut = cutGroupByMinSize(firstCut);

        // 平衡各組大小：將不足 6 員的組別併到組
        return balanceGroupSizes(secondCut);
    }

    private List<Group> cutGroupByTimeSlots(List<Student> students) {
        Map<String, Group> firstCut = new HashMap<>();
        for (Student student : students) {
            String timeSlotsHash = hashMemberTimeSlots(student);
            if (!firstCut.containsKey(timeSlotsHash)) {
                firstCut.put(timeSlotsHash, new Group());
            }
            firstCut.get(timeSlotsHash).addStudent(student);
        }
        return new ArrayList<>(firstCut.values());
    }

    private List<Group> cutGroupByMinSize(List<Group> firstCutGroups) {
        List<Group> secondCutGroups = new ArrayList<>();
        for (Group group : firstCutGroups) {
            secondCutGroups.addAll(group.splitBySize(GROUP_MIN_SIZE));
        }
        return secondCutGroups;
    }

    private List<Group> balanceGroupSizes(List<Group> secondCutGroups) {
        List<Group> nonFulLGroups = new ArrayList<>();
        List<Group> fullGroups = new ArrayList<>();
        for (Group group : secondCutGroups) {
            if (group.size() < GROUP_MIN_SIZE) {
                nonFulLGroups.add(group);
            } else {
                fullGroups.add(group);
            }
        }

        for (Group nonFullGroup : nonFulLGroups) {
            for (Group fullGroup : fullGroups) {
                // 直接併組，沒有併組的條件判斷
                System.out.printf("Merge group (%d) to (%d).\n", nonFullGroup.getNumber(), fullGroup.getNumber());
                fullGroup.merge(nonFullGroup);
                break;
            }
        }
        return fullGroups;
    }

    private String hashMemberTimeSlots(Student student) {
        // Hash to four s 9AM-12AM 1PM-4PM 5PM-7PM 8PM-9PM
        boolean[] s = student.getAvailableTimeSlots();
        return format("%d%d%d%d", convertBooleanToNumber(s[0] && s[1] && s[2] && s[3]),
                convertBooleanToNumber(s[4] & s[5] & s[6] & s[7]),
                convertBooleanToNumber(s[8] && s[9] && s[10]),
                convertBooleanToNumber(s[11] && s[12]));
    }

    private int convertBooleanToNumber(boolean b) {
        return b ? 1 : 0;
    }
}
