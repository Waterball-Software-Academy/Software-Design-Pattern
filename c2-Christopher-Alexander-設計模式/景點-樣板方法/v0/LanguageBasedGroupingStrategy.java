package v0;

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
public class LanguageBasedGroupingStrategy {

    public static final int GROUP_MIN_SIZE = 6;

    public List<Group> group(List<Student> students) {
        // 第一刀：程式語言專長
        Map<String, Group> firstCut = new HashMap<>();
        for (Student student : students) {
            if (!firstCut.containsKey(student.getLanguage())) {
                firstCut.put(student.getLanguage(), new Group());
            }
            firstCut.get(student.getLanguage()).addStudent(student);
        }
        List<Group> firstCutGroups = new ArrayList<>(firstCut.values());

        // 第二刀：人數 (6人一組)
        List<Group> secondCutGroups = new ArrayList<>();
        for (Group group : firstCutGroups) {
            secondCutGroups.addAll(group.splitBySize(GROUP_MIN_SIZE));
        }

        // 將不足 6 員的組別併到人滿的組別
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
                if (fullGroup.get(0).getLanguage().equals(nonFullGroup.get(0).getLanguage())) {
                    System.out.printf("Merge group (%d) to (%d).\n", nonFullGroup.getNumber(), fullGroup.getNumber());
                    fullGroup.merge(nonFullGroup);
                    break;
                }
            }
        }

        return fullGroups;
    }
}
