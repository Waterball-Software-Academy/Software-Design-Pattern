package v3;

import commons.Television;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class TurnOnTvCommand implements Command {
    private final Television tv;

    public TurnOnTvCommand(Television tv) {
        this.tv = tv;
    }

    @Override
    public void execute() {
        tv.turnOn();
    }

    @Override
    public void undo() {
        tv.turnOff();
    }
}
