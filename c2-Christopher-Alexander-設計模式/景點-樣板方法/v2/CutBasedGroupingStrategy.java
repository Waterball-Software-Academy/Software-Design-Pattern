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
public abstract class CutBasedGroupingStrategy implements GroupingStrategy {

    private static final int GROUP_MIN_SIZE = 6;

    @Override
    public List<Group> group(List<Student> students) {
        // 第一刀：程式語言專長
        Map<Object, Group> firstCut = new HashMap<>();
        for (Student student : students) {
            Object key = cutBy(student);
            if (!firstCut.containsKey(key)) {
                firstCut.put(key, new Group());
            }
            firstCut.get(key).addStudent(student);
        }
        List<Group> firstCutGroups = new ArrayList<>(firstCut.values());

        // 第二刀：人數 (6人一組)
        List<Group> secondCutGroups = new ArrayList<>();
        for (Group group : firstCutGroups) {
            secondCutGroups.addAll(group.splitBySize(GROUP_MIN_SIZE));
        }

        // 將不足 6 員的組別併到別組
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
                // 如果兩個組別使用的程式語言一樣，才併組
                if (meetMergeCriteria(nonFullGroup, fullGroup)) {
                    System.out.printf("Merge group (%d) to (%d).\n", nonFullGroup.getNumber(), fullGroup.getNumber());
                    fullGroup.merge(nonFullGroup);
                    break;
                }
            }
        }

        return fullGroups;
    }

    protected boolean meetMergeCriteria(Group nonFullGroup, Group fullGroup) {
        return true;
    }

    protected abstract Object cutBy(Student student);
}
