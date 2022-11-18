package v2;

import java.util.Scanner;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class CLI {
    private final Scanner in = new Scanner(System.in);
    private Directory current;
    public CLI(Directory current) {
        this.current = current;
    }

    public void start() {
        while (true) {
            System.out.printf("%s> ", current.getName());
            String command = in.nextLine();
            executeCommand(command);
        }
    }

    private void executeCommand(String command) {
        String[] parts = command.split(" ");
        if ("cd".equals(parts[0])) {
            changDirectory(parts[1]);
        } else if ("size".equals(parts[0])) {
            printSize(parts[1]);
        } else {
            System.err.println("Unrecognizable Command.");
        }
    }

    private void changDirectory(String name) {
        if ("..".equals(name)) {
            current = current.getParent() == null /*root*/?
                    current : current.getParent();
        } else {
            Item target = current.getItem(name);
            if (target == null) {
                System.err.printf("Can't find the item '%s'.\n", name);
            } else if (!(target instanceof Directory)) {
                System.err.printf("%s is not a directory.\n", name);
            } else {
                current = (Directory) target;
            }
        }
    }

    private void printSize(String name) {
        Item item = current.getItem(name);
        if (item == null) {
            System.out.printf("Can't find the item '%s'.\n", name);
        } else {
            System.out.printf("Size: %dB\n", item.bytes());
        }
    }
}
