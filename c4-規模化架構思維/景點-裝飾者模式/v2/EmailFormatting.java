package v2;

import commons.Message;

import java.util.List;

import static java.lang.String.format;
import static java.util.stream.Collectors.toUnmodifiableList;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class EmailFormatting extends MessageProcessor {
    public EmailFormatting(Messenger next) {
        super(next);
    }

    @Override
    public void send(List<Message> messages) {
        messages = messages
                .stream().map(this::mailFormatting)
                .collect(toUnmodifiableList());

        next.send(messages);
    }


    private Message mailFormatting(Message message) {
        return message.edit(format("Dear %s,\n\n%s\n\nBest regards,\n%s",
                message.getTargetName(), message.getContent(), message.getAuthorName()));
    }
}
