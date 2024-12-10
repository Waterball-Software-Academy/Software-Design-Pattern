package v2;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public abstract class MessageProcessor implements Messenger {
    protected Messenger next;
    public MessageProcessor(Messenger next) {
        this.next = next;
    }
}
