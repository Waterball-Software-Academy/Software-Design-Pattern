package common;

import java.util.List;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public interface GroupingStrategy {
    List<Group> group(List<Student> students);
}
