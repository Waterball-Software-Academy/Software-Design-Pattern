package v2;

import commons.Message;

import java.util.List;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public interface Messenger {
    void send(List<Message> messages);
}
