package v2;

import commons.Message;

import java.util.List;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toUnmodifiableList;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class Paragraph extends MessageProcessor {
    public Paragraph(Messenger next) {
        super(next);
    }

    @Override
    public void send(List<Message> messages) {
        messages = messages
                .stream().flatMap(msg -> paragraph(msg).stream())
                .collect(toUnmodifiableList());

        next.send(messages);
    }

    private List<Message> paragraph(Message message) {
        return stream(message.getContent().split("\n\n"))
                .map(message::edit)
                .collect(toList());
    }
}
