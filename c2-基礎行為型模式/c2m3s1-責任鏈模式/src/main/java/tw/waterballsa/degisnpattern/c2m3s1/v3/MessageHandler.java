package tw.waterballsa.degisnpattern.c2m3s1.v3;

import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public abstract class MessageHandler {
    protected MessageHandler next;

    public MessageHandler(MessageHandler next) {
        this.next = next;
    }

    public void handle(Message message, MessageChannel channel) {
        if (match(message)) {
            doHandling(message, channel);
        } else if (next != null) {
            next.handle(message, channel);
        }
    }

    protected abstract boolean match(Message message);
    protected abstract void doHandling(Message message, MessageChannel channel);
}
