package v3;

import common.Student;

import static java.lang.String.format;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class TimeSlotsBasedGroupingStrategy extends CutBasedGroupingStrategy {

    protected TimeSlotsBasedGroupingStrategy() {
        super(6);
    }

    @Override
    protected Object cutGroupBy(Student student) {
        return hashMemberTimeSlots(student);
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
