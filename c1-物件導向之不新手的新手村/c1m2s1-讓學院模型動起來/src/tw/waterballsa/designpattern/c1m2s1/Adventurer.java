package tw.waterballsa.designpattern.c1m2s1;

import static java.util.Objects.requireNonNull;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class Adventurer {
    private int number;
    private Student student;
    private Journey journey;
    private TourGroup tourGroup;

    public Adventurer(int number, Student student, Journey journey) {
        setNumber(number);
        setStudent(student);
        setJourney(journey);
    }

    public TourGroup getTourGroup() {
        return tourGroup;
    }

    public void setTourGroup(TourGroup tourGroup) {
        this.tourGroup = requireNonNull(tourGroup);
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = ValidationUtils.shouldBePositive(number);
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = requireNonNull(student);
    }

    public Journey getJourney() {
        return journey;
    }

    public void setJourney(Journey journey) {
        this.journey = requireNonNull(journey);
    }

    public void carryOn(Mission mission) {
        student.carryOn(mission);
    }
}
