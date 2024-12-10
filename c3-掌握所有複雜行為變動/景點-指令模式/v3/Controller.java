package v3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class Controller {
    private final Command[] commands = new Command[6];
    private final Stack<Command> s1 = new Stack<>();
    private final Stack<Command> s2 = new Stack<>();

    public void setCommand(int button, Command command) {
        commands[button] = command;
    }

    public void press(int button) {
        if (button >= 0 && button < commands.length) {
            Command command = commands[button];
            // 每當執行完新的指令後，就把此指令 push 到 S1 中，並且清空 S2。
            command.execute();
            s1.push(command);
            s2.clear();
        } else {
            throw new IllegalArgumentException("Button " + button + " unsupported.");
        }
    }

    public void undo() {
        if (!s1.isEmpty()) {
            // 在 Undo 時，把 S1 最上層的指令 pop 出來，並執行此指令的 undo 操作。undo 完將此指令 Push 到 S2 中。
            Command previousCommand = s1.pop();
            previousCommand.undo();
            s2.push(previousCommand);
        }
    }

    public void redo() {
        if (!s2.isEmpty()) {
            // 而在 Redo 時，把 S2 最上層的指令 pop 出來，呼叫此指令的 execute 操作，呼叫完之後將此指令 Push 到 S1 中。
            Command nextCommand = s2.pop();
            nextCommand.execute();
            s1.push(nextCommand);
        }
    }
}
