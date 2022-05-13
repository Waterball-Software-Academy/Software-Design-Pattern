package v3;

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
public abstract class CutBasedGroupingStrategy implements GroupingStrategy {
    public final int GROUP_MIN_SIZE;

    protected CutBasedGroupingStrategy(int groupMinSize) {
        GROUP_MIN_SIZE = groupMinSize;
    }

    public List<Group> group(List<Student> students) {
        // 先用某種屬性將成員分組
        List<Group> firstCut = cutGroupByKey(students);

        // 再到各組中以最低成員數 N 員分組
        List<Group> secondCut = cutGroupByMinSize(firstCut);

        // 平衡各組大小：將不足 N 員的組別併到組
        return balanceGroupSizes(secondCut);
    }

    protected List<Group> cutGroupByKey(List<Student> students) {
        Map<Object, Group> firstCut = new HashMap<>();
        for (Student student : students) {
            Object key = cutGroupBy(student);
            if (!firstCut.containsKey(key)) {
                firstCut.put(key, new Group());
            }
            firstCut.get(key).addStudent(student);
        }
        return new ArrayList<>(firstCut.values());
    }

    protected abstract Object cutGroupBy(Student student);

    protected List<Group> cutGroupByMinSize(List<Group> firstCutGroups) {
        List<Group> secondCutGroups = new ArrayList<>();
        for (Group group : firstCutGroups) {
            secondCutGroups.addAll(group.splitBySize(GROUP_MIN_SIZE));
        }
        return secondCutGroups;
    }

    protected List<Group> balanceGroupSizes(List<Group> secondCut) {
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

    protected boolean meetMergeCriterial(Group nonFullGroup, Group fullGroup) {
        return true;  // 掛鉤 (Hook)：預設無條件
    }
}
