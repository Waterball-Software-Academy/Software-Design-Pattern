package tw.waterballsa.designpattern.oopdemo.association.associationclass;

import java.util.Collection;
import java.util.HashSet;

/**
 * @author johnny@waterballsa.tw
 */
public class Student {
    private final Collection<Registration> registrations = new HashSet<>();

    public Collection<Registration> getRegistrations() {
        return registrations;
    }
}
