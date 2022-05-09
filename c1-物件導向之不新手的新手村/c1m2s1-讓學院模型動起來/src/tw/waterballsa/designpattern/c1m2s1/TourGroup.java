package tw.waterballsa.designpattern.c1m2s1;

import java.util.List;

import static java.util.Objects.requireNonNull;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class TourGroup {
    private int number;
    private List<Adventurer> adventurers;

    public TourGroup(int number, List<Adventurer> adventurers) {
        this.number = number;
        setAdventurers(adventurers);
    }

    public void add(Adventurer adventurer) {
        adventurers.add(adventurer);
        adventurer.setTourGroup(this);
    }

    private void setAdventurers(List<Adventurer> adventurers) {
        this.adventurers = requireNonNull(adventurers);
        for (Adventurer adventurer : adventurers) {
            adventurer.setTourGroup(this);
        }
    }

    public List<Adventurer> getAdventurers() {
        return adventurers;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = ValidationUtils.shouldBePositive(number);
    }
}
