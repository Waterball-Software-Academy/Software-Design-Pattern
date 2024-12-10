package v2;

import commons.Fan;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class FanPreviousLevelCommand implements Command {
    private final Fan fan;

    public FanPreviousLevelCommand(Fan fan) {
        this.fan = fan;
    }

    @Override
    public void execute() {
        fan.previousLevel();
    }
}
