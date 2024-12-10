package lib.commands;

import lib.ioc.Command;

import java.util.List;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class AndCommand implements Command {
    private List<Command> children;

    @Override
    public int execute() {
        for (Command child : children) {
            int status = child.execute();
            if (status != 0) {
                return status;
            }
        }
        return 0;
    }

    @Override
    public void setChildren(List<Command> children) {
        this.children = children;
    }

    @Override
    public void setArguments(List<Object> arguments) {

    }
}
