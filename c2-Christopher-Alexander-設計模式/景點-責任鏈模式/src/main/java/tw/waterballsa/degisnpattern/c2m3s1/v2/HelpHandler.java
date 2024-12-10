package tw.waterballsa.degisnpattern.c2m3s1.v2;

import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class HelpHandler extends MessageHandler {

    public HelpHandler(MessageHandler next) {
        super(next);
    }

    @Override
    public void handle(Message message, MessageChannel channel) {
        if ("help".equalsIgnoreCase(message.getContent())) {
            channel.createMessage("▋ HELP ▋").block();
            channel.createMessage("Commands: dcard, currency").block();
        } else if (next != null) {
            next.handle(message, channel);
        }
    }
}
