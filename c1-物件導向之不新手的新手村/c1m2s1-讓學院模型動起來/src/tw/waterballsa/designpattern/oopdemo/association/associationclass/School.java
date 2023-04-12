package tw.waterballsa.designpattern.oopdemo.association.associationclass;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

/**
 * @author johnny@waterballsa.tw
 */
public class School {
    private final Collection<Registration> registrations = new HashSet<>();

    public Registration register(Student student, int score) {
        Registration registration = new Registration(this, student, score);
        registrations.add(registration);
        student.getRegistrations().add(registration);
        return registration;
    }

    public Collection<Registration> getRegistrations() {
        return registrations;
    }
}
