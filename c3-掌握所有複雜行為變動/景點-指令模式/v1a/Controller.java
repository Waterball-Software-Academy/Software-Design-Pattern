package v1a;

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

    public void press0() {
        fan.nextLevel();
    }

    public void press1() {
        fan.previousLevel();
    }

    public void press2() {
        ac.turnOn();
    }

    public void press3() {
        ac.turnOff();
    }

    public void press4() {
        tv.turnOn();
    }

    public void press5() {
        tv.turnOff();
    }

}
