package v2;

import commons.Message;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toUnmodifiableList;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class HideEmails extends MessageProcessor {
    public HideEmails(Messenger next) {
        super(next);
    }

    @Override
    public void send(List<Message> messages) {
        messages = messages
                .stream().map(this::hideEmails)
                .collect(toUnmodifiableList());

        next.send(messages);
    }

    private Message hideEmails(Message message) {
        return message.edit(Pattern.compile("[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}")
                .matcher(message.getContent())
                .replaceAll(res -> hideEmail(res.group())));
    }

    private String hideEmail(String email) {
        String[] parts = email.split("@");
        char[] chars = parts[0].toCharArray();
        int hiddenChars = (int) Math.ceil(chars.length / 4f);
        for (int i = 0; i < hiddenChars; i++) {
            chars[hiddenChars + i] = '*';
        }
        return String.format("%s@%s", new String(chars), parts[1]);
    }

}
