package tw.waterballsa.degisnpattern.c2m3s1.v2;

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

    public abstract void handle(Message message, MessageChannel channel);
}
