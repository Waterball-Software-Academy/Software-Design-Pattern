package lib.commands;

import lib.ioc.Command;

import java.util.List;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class RepeatCommandTask implements Command {
    private int times;
    private Command child;

    @Override
    public int execute() {
        for (int i = 0; i < times; i++) {
            int status = child.execute();
            if (status != 0) {
                return status;
            }
        }
        return 0;
    }

    @Override
    public void setChildren(List<Command> children) {
        child = children.get(0);
    }

    @Override
    public void setArguments(List<Object> arguments) {
        times = (int) arguments.get(0);
    }
}
