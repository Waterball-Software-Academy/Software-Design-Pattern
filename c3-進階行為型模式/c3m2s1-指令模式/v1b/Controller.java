package v1b;

import commons.AirConditioner;
import commons.Fan;
import commons.Television;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class Controller {
    private final AirConditioner ac;
    private final Fan fan;
    private final Television tv;

    public Controller(AirConditioner ac, Fan fan, Television tv) {
        this.ac = ac;
        this.fan = fan;
        this.tv = tv;
    }

    public void press(int button) {
        switch (button) {
            case 0:
                fan.nextLevel();
                break;
            case 1:
                fan.previousLevel();
                break;
            case 2:
                ac.turnOn();
                break;
            case 3:
                ac.turnOff();
                break;
            case 4:
                tv.turnOn();
                break;
            case 5:
                tv.turnOff();
                break;
            default:
                throw new IllegalArgumentException("Non-recognizable button.");
        }
    }
}
