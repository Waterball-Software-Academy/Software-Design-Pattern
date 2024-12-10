package v2;

import common.Group;
import common.GroupingStrategy;
import common.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class LanguageBasedGroupingStrategy implements GroupingStrategy {

    public static final int GROUP_MIN_SIZE = 6;

    public List<Group> group(List<Student> students) {
        // 先用「Language」將成員分組
        List<Group> firstCut = cutGroupByLanguage(students);

        // 再到各組中以最低成員數 6 員分組
        List<Group> secondCut = cutGroupByMinSize(firstCut);

        // 平衡各組大小：將不足 6 員的組別併到組
        return balanceGroupSizes(secondCut);
    }

    private List<Group> cutGroupByLanguage(List<Student> students) {
        Map<String, Group> firstCut = new HashMap<>();
        for (Student student : students) {
            if (!firstCut.containsKey(student.getLanguage())) {
                firstCut.put(student.getLanguage(), new Group());
            }
            firstCut.get(student.getLanguage()).addStudent(student);
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

    private List<Group> balanceGroupSizes(List<Group> secondCut) {
        List<Group> nonFulLGroups = new ArrayList<>();
        List<Group> fullGroups = new ArrayList<>();
        for (Group group : secondCut) {
            if (group.size() < GROUP_MIN_SIZE) {
                nonFulLGroups.add(group);
            } else {
                fullGroups.add(group);
            }
        }

        for (Group nonFullGroup : nonFulLGroups) {
            for (Group fullGroup : fullGroups) {
                // 符合併組的條件的話才併組
                if (meetMergeCriterial(nonFullGroup, fullGroup)) {
                    System.out.printf("Merge group (%d) to (%d).\n", nonFullGroup.getNumber(), fullGroup.getNumber());
                    fullGroup.merge(nonFullGroup);
                    break;
                }
            }
        }
        return fullGroups;
    }

    private boolean meetMergeCriterial(Group nonFullGroup, Group fullGroup) {
        return fullGroup.get(0).getLanguage().equals(nonFullGroup.get(0).getLanguage());
    }
}
