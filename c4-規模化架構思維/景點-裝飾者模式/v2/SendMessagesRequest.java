package v2;

import commons.Message;

import java.util.List;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class SendMessagesRequest {
    private final List<Message> messages;

    public SendMessagesRequest(List<Message> messages) {
        this.messages = messages;
    }

    public List<Message> getMessages() {
        return messages;
    }
}
