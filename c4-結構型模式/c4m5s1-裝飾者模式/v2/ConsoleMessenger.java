package v2;

import commons.Message;

import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class ConsoleMessenger implements Messenger {
    @Override
    public void send(List<Message> messages) {
        for (Message message : messages) {
            System.out.printf("[%s] %s: \n%s\n",
                    message.getCreatedTime().format(DateTimeFormatter.ofPattern("MM/dd hh:mm")),
                    message.getAuthor(), message.getContent());
        }
    }
}
