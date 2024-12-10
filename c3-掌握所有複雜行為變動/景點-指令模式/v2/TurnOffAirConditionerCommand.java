package v2;

import commons.AirConditioner;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class TurnOffAirConditionerCommand implements Command {
    private final AirConditioner ac;

    public TurnOffAirConditionerCommand(AirConditioner ac) {
        this.ac = ac;
    }

    @Override
    public void execute() {
        ac.turnOff();
    }
}
