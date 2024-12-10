package lib.ioc;

import java.util.List;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public interface Command {
    int execute();
    void setChildren(List<Command> children);
    void setArguments(List<Object> arguments);


    Command NO_OP = new Command() {
        @Override
        public int execute() {
            return 0;
        }

        @Override
        public void setChildren(List<Command> children) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void setArguments(List<Object> arguments) {
            throw new UnsupportedOperationException();
        }
    };
}
