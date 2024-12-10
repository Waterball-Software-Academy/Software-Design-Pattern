package tw.waterballsa.degisnpattern.c2m3s1.v3;

import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.event.domain.message.MessageEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static java.lang.Thread.currentThread;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class WaterballBot {
    private final MessageHandler handler;

    public WaterballBot(MessageHandler handler) {
        this.handler = handler;
    }

    // Connect and login on Discord
    public void connect() throws IOException {
        String token = getDiscordToken();
        DiscordClient client = DiscordClient.create(token);
        GatewayDiscordClient gateway = client.login().block();

        gateway.on(MessageEvent.class).subscribe(this::processMessage);
        gateway.onDisconnect().block();
    }

    private static String getDiscordToken() throws IOException {
        try (InputStream in = currentThread().getContextClassLoader().getResourceAsStream("token.properties")) {
            Properties properties = new Properties();
            properties.load(in);
            return String.valueOf(properties.get("TOKEN"));
        }
    }

    public void processMessage(MessageEvent e) {
        if (e instanceof MessageCreateEvent) {
            MessageCreateEvent messageCreateEvent = (MessageCreateEvent) e;
            Message message = messageCreateEvent.getMessage();
            MessageChannel channel = message.getChannel().block();
            handler.handle(message, channel);
        }
    }
}
