package v3;

import common.Group;
import common.Student;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class JobTitleBasedGroupingStrategy extends CutBasedGroupingStrategy {

    protected JobTitleBasedGroupingStrategy(int groupMinSize) {
        super(groupMinSize);
    }

    @Override
    protected Object cutGroupBy(Student student) {
        return student.getJobTitle();
    }

    @Override
    protected boolean meetMergeCriterial(Group nonFullGroup, Group fullGroup) {
        return nonFullGroup.get(0).getJobTitle().equals(fullGroup.get(0).getJobTitle());
    }
}
