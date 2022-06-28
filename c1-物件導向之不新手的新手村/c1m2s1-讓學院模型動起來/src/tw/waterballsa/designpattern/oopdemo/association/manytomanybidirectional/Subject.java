package tw.waterballsa.designpattern.oopdemo.association.manytomanybidirectional;

import java.util.Collection;
import java.util.HashSet;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class Subject {
    private final String name;

    // Many(Subject) to "Many(Student)" association
    // Use whatever data structure that fit your case
    private final Collection<Student> learners = new HashSet<>();

    public Subject(String name) {
        this.name = name;
    }

    public void addLearner(Student learner) {
        this.learners.add(learner);
    }

    public Collection<Student> getLearners() {
        return learners;
    }

    public String getName() {
        return name;
    }
}
