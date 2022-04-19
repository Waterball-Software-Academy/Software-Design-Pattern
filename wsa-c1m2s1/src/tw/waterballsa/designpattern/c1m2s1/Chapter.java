package tw.waterballsa.designpattern.c1m2s1;

import java.util.List;

import static java.util.Objects.requireNonNull;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class Chapter {
    private int number;
    private String name;
    private List<Mission> missions;

    public Chapter(int number, String name, List<Mission> missions) {
        setMissions(missions);
        setNumber(number);
        setName(name);
    }

    public List<Mission> getMissions() {
        return missions;
    }

    private void setMissions(List<Mission> missions) {
        this.missions = requireNonNull(missions);
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = ValidationUtils.shouldBePositive(number);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = ValidationUtils.lengthShouldBe(name, 1, 30);
    }

    // 提供一些好用的方法：拿第一個任務
    public Mission getFirstMission() {
        return missions.get(0);
    }
}
