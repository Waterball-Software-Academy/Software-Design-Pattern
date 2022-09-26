package v1;

import commons.Message;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Pattern;

import static java.lang.String.format;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class EmailHidingAndParagraphingMessenger extends Messenger {
    @Override
    public void sendMessage(Message message) {
        message = hideEmails(message);
        List<Message> messages = paragraph(message);
        printMessage(messages);
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

    private List<Message> paragraph(Message message) {
        return stream(message.getContent().split("\n\n"))
                .map(message::edit)
                .collect(toList());
    }


    private void printMessage(List<Message> messages) {
        for (Message message : messages) {
            System.out.printf("[%s] %s: \n%s\n",
                    message.getCreatedTime().format(DateTimeFormatter.ofPattern("MM/dd hh:mm")),
                    message.getAuthor(), message.getContent());
        }
    }

}
