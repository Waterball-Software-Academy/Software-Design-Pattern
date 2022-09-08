package v2;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class Main {
    public static void main(String[] args) {
        Directory root = Directory.root();
        Directory document = new Directory("document");
        Directory notes = new Directory("notes");
        root.addChild(document);
        document.addChild(notes);
        root.addChild(new File("me.txt", "Hello, I'm Waterball."));
        notes.addChild(new File("meeting-0825.txt", "Lauren: To be or not to be."));
        notes.addChild(new File("meeting-0824.txt", "Waterball: Composition over Inheritance."));

        CLI cli = new CLI(root);
        cli.start();
    }
}
