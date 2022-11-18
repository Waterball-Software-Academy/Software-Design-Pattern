package lib.commands;

import lib.ioc.Command;

import java.util.List;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class EchoCommandTask implements Command {
    private String content;

    @Override
    public int execute() {
        System.out.println(content);
        return 0;
    }

    @Override
    public void setChildren(List<Command> children) {
    }

    @Override
    public void setArguments(List<Object> arguments) {
        content = (String) arguments.get(0);
    }
}
