package tw.waterballsa.degisnpattern.c2m3s1.v3;

import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class HelpMessageHandler extends MessageHandler {
    public HelpMessageHandler(MessageHandler next) {
        super(next);
    }

    @Override
    protected boolean match(Message message) {
        return "help".equalsIgnoreCase(message.getContent());
    }

    @Override
    protected void doHandling(Message message, MessageChannel channel) {
        channel.createMessage("▋ HELP ▋").block();
        channel.createMessage("Commands: dcard, currency").block();
    }
}
