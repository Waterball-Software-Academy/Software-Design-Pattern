package v2;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class Controller {
    private final Command[] commands = new Command[6];

    public void setCommand(int button, Command command) {
        commands[button] = command;
    }

    public void press(int button) {
        if (button >= 0 && button < commands.length) {
            commands[button].execute();
        } else {
            throw new IllegalArgumentException("Button " + button + " unsupported.");
        }
    }
}
