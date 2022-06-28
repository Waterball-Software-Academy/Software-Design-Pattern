package tw.waterballsa.designpattern.oopdemo.association.bidirectional;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class Subject {
    private final String name;

    // Many(Subject) to "One(Student)" association
    private Student learner;

    public Subject(String name) {
        this.name = name;
    }

    public Student getLearner() {
        return learner;
    }

    public void setLearner(Student learner) {
        this.learner = learner;
    }

    public String getName() {
        return name;
    }
}
