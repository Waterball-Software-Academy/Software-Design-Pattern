package tw.waterballsa.degisnpattern.c2m3s1.v1;

import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.event.domain.message.MessageEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Properties;

import static java.lang.Thread.currentThread;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class WaterballBot {
    // Connect and login on Discord
    public void connect() throws IOException {
        String token = getDiscordToken();
        DiscordClient client = DiscordClient.create(token);
        GatewayDiscordClient gateway = client.login().block();

        gateway.on(MessageEvent.class).subscribe(e -> {
            if (e instanceof MessageCreateEvent) {
                MessageCreateEvent mce = (MessageCreateEvent) e;
                Message message = mce.getMessage();
                MessageChannel channel = message.getChannel().block();
                handle(message, channel);
            }
        });
        gateway.onDisconnect().block();
    }

    private static String getDiscordToken() throws IOException {
        try (InputStream in = currentThread().getContextClassLoader().getResourceAsStream("token.properties")) {
            Properties properties = new Properties();
            properties.load(in);
            return String.valueOf(properties.get("TOKEN"));
        }
    }

    public void handle(Message message, MessageChannel channel) {
        if ("help".equalsIgnoreCase(message.getContent())) {
            channel.createMessage("▋ HELP ▋").block();
            channel.createMessage("Commands: dcard, currency").block();
        } else if ("dcard".equalsIgnoreCase(message.getContent())) {
            String dcardBody = crawlDcardBody();
            channel.createMessage("▋ DCARD ▋").block();
            channel.createMessage(dcardBody).block();
        } else if ("currency".equalsIgnoreCase(message.getContent())) {
            String currencyBody = crawlCurrencyBody();
            channel.createMessage("▋ CURRENCY ▋").block();
            channel.createMessage(currencyBody).block();
        }
    }

    private String crawlDcardBody() {
        final String LINK = "https://www.dcard.tw";
        final String TOPIC = "/f/talk";
        String url = LINK + TOPIC;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            Document doc = Jsoup.connect(url).get();
            Elements definitions = doc.select("h2");
            if (definitions.isEmpty()) {
                throw new Exception();
            }
            for (Element element : definitions) {
                String title = element.childNode(0).childNode(0).toString();
                String link = LINK + element.childNode(0).attr("href");
                String line = title.replace("<span>", "").replace("</span>", "") + " - " + link;
                stringBuilder.append(line).append("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    private String crawlCurrencyBody() {
        Element definitions;
        StringBuilder result = new StringBuilder();
        try {
            Document doc = Jsoup.connect("https://rate.bot.com.tw/xrt?Lang=zh-TW").get();
            definitions = doc.select("#ie11andabove > div > table > tbody").get(0);
            Elements currencies = definitions.select("tr");
            int i = 0;
            for (Element e : currencies) {
                String name = e.select("td:eq(0) > div > div:eq(2) ").text();
                String value = e.select("td:eq(2)").text();
                result.append("匯率幣別 : ").append(name).append(" 匯率:").append(value).append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }

}
