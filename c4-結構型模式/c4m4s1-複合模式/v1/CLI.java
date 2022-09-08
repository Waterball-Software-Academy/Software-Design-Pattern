package v1;

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

    private void printSize(String name) {
        File file = current.getFile(name);
        if (file != null) {
            System.out.printf("Size: %dB\n", file.bytes());
        } else {
            Directory directory = current.getDirectory(name);
            if (directory != null) {
                System.out.printf("Size: %dB\n", directory.totalBytes());
            } else {
                System.err.printf("Can't find the item '%s'.\n", name);
            }
        }
    }

    private void changDirectory(String name) {
        if ("..".equals(name)) {
            current = current.getParent() == null ?
                    current : current.getParent();
        } else {
            Directory target = current.getDirectory(name);
            if (target == null) {
                System.err.printf("Can't find the item '%s'.\n", name);
            } else {
                current = target;
            }
        }
    }
}
