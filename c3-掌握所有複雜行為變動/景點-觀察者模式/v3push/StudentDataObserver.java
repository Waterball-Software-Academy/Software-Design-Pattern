package v3push;

import common.Student;

import java.io.IOException;
import java.util.Collection;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public interface StudentDataObserver {
    void update(Collection<Student> students) throws IOException;
}
