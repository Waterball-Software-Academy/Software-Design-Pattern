package tw.waterballsa.designpattern.c1m2s1;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public abstract class Scene {
    private String name;
    private int number;
    protected int expAward;

    public Scene(String name, int number, int expAward) {
        setName(name);
        setNumber(number);
        setExpAward(expAward);
    }

    public abstract int calculateExpAward();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = ValidationUtils.lengthShouldBe(name, 1, 30);
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = ValidationUtils.shouldBePositive(number);
    }

    public int getExpAward() {
        return expAward;
    }

    public void setExpAward(int expAward) {
        this.expAward = expAward;
    }
}
