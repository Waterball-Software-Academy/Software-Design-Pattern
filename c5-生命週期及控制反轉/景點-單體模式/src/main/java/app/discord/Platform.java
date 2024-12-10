package app.discord;

import app.discord.Events.MessageEvent;
import app.fb.Cart;
import app.fb.Client;
import app.fb.Events.AddToCartEvent;
import app.sdk.v1.ChatBot;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.lang.String.format;

/**
 * @author johnny@waterballsa.tw
 */
public class Platform {
    private final static String thingsBotNeedToKnow = readContext();

    private static String readContext() {
        try {
            return Files.readString(Path.of("Discord-Things-Bot-Need-To-Know"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void reactToEvent(MessageEvent event, Channel channel) {
        String username = event.getUsername();
        String message = event.getContent();

        ChatBot chatBot = new ChatBot();
        chatBot.chat(thingsBotNeedToKnow);
        String response = chatBot.chat(format("%s: %s", username, message));
        channel.sendResponse(new ChatMessageResponse(response));
    }


    public void reactToEvent(AddToCartEvent event, Channel channel) {
        Cart cart = event.getCart();
        if (cart.getSize() % 3 == 0) {
            ChatBot chatBot = new ChatBot();
            chatBot.chat(thingsBotNeedToKnow);
            String suggestion = chatBot.chat(format("%s 剛剛將 %s 加入購物車，現在已經買了 %d 項商品，你覺得他可能還想買哪一項商品？推一項吧。",
                    event.getUsername(), event.getProductName(), cart.getSize()));

            Client client = event.getUserClient();
            client.getBot().addMessage(suggestion);
        }
    }
}
