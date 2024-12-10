package v2;

import commons.AirConditioner;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class TurnOnAirConditionerCommand implements Command {
    private final AirConditioner ac;

    public TurnOnAirConditionerCommand(AirConditioner ac) {
        this.ac = ac;
    }

    @Override
    public void execute() {
        ac.turnOn();
    }
}
