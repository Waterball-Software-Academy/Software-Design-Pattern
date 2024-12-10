package v2;

import commons.Message;

import java.util.List;

import static java.util.Collections.singletonList;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public interface Messenger {
    default void send(Message message) {
        send(singletonList(message));
    }
    void send(List<Message> messages);
}
