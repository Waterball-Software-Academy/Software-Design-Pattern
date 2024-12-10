package tw.waterballsa.degisnpattern.c2m3s1.v2;

import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class CurrencyHandler extends MessageHandler {
    public CurrencyHandler(MessageHandler next) {
        super(next);
    }

    @Override
    public void handle(Message message, MessageChannel channel) {
        if ("currency".equalsIgnoreCase(message.getContent())) {
            String currencyBody = crawlCurrencyBody();
            channel.createMessage("▋ CURRENCY ▋").block();
            channel.createMessage(currencyBody).block();
        } else if (next != null) {
            next.handle(message, channel);
        }
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
