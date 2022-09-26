package v2;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class Main {
    public static void main(String[] args) {
        Messenger messenger = new HideEmails(new EmailFormatting(new Paragraph(new ConsoleMessenger())));
    }
}
