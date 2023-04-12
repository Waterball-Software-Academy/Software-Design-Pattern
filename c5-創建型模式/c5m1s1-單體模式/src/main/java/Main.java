import app.sdk.v1.ChatBot;

import java.io.IOException;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class Main {
    public static void main(String[] args) throws IOException {
        ChatBot chatBot = new ChatBot();
        String chat = chatBot.chat("水球軟體學院想要名正言順地串接你這個猛 AI 到學院" +
                "Discord 中應用，說說你第一個最想被應用的地方吧！(我們有2200人和各種論壇喔）！）");
        System.out.println(chat);
    }
}
