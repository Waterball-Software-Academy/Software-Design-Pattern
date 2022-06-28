package v3;

import commons.Television;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class TurnOffTvCommand implements Command {
    private final Television tv;

    public TurnOffTvCommand(Television tv) {
        this.tv = tv;
    }

    @Override
    public void execute() {
        tv.turnOff();
    }

    @Override
    public void undo() {
        tv.turnOn();
    }
}
