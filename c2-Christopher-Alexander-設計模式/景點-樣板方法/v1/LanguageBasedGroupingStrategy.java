package v1;

import common.Group;
import common.GroupingStrategy;
import common.Student;
import v2.CutBasedGroupingStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class LanguageBasedGroupingStrategy extends CutBasedGroupingStrategy {

    @Override
    protected Object cutBy(Student student) {
        return student.getLanguage();
    }

    @Override
    protected boolean meetMergeCriteria(Group nonFullGroup, Group fullGroup) {
        return nonFullGroup.get(0).getLanguage().equals(fullGroup.get(0).getLanguage());
    }
}
