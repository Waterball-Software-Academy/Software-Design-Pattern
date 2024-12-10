package v2;

import commons.Fan;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class FanNextLevelCommand implements Command {
    private final Fan fan;

    public FanNextLevelCommand(Fan fan) {
        this.fan = fan;
    }

    @Override
    public void execute() {
        fan.nextLevel();
    }
}
