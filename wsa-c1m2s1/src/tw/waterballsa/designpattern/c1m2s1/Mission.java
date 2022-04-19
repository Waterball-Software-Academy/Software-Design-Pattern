package tw.waterballsa.designpattern.c1m2s1;

import java.util.List;

import static java.util.Objects.requireNonNull;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class Mission {
    private int number;
    private String name;
    private Challenge challenge;
    private List<Scene> scenes;

    public Mission(int number, String name, Challenge challenge, List<Scene> scenes) {
        setScenes(scenes);
        setNumber(number);
        setName(name);
        setChallenge(challenge);
    }

    private void setScenes(List<Scene> scenes) {
        this.scenes = requireNonNull(scenes);
    }

    // 底下所有 Scene 的經驗值加總。
    public int calculateExpAward() {
        int sum = 0;
        for (Scene scene : scenes) {
            sum += scene.calculateExpAward(); // 多型
        }
        return sum;
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

    public Challenge getChallenge() {
        return challenge;
    }

    public void setChallenge(Challenge challenge) {
        this.challenge = requireNonNull(challenge);
    }
}
