package commons;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class Television {
    private boolean on;

    public void turnOn() {
        this.on = true;
        System.out.println("【 TV 】Turned ON.");
    }

    public void turnOff() {
        this.on = false;
        System.out.println("【 TV 】Turned OFF.");
    }

    public boolean isOn() {
        return on;
    }
}
