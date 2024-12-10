package tw.waterballsa.degisnpattern.c2m3s1.v3;

import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class DcardHandler extends MessageHandler {
    public DcardHandler(MessageHandler next) {
        super(next);
    }

    @Override
    protected boolean match(Message message) {
        return "dcard".equalsIgnoreCase(message.getContent());
    }

    @Override
    protected void doHandling(Message message, MessageChannel channel) {
        String dcardBody = crawlDcardBody();
        channel.createMessage("▋ DCARD ▋").block();
        channel.createMessage(dcardBody).block();
    }

    private String crawlDcardBody() {
        final String LINK = "https://www.dcard.tw";
        final String TOPIC = "/f/talk";
        String url = LINK+ TOPIC;
        StringBuilder stringBuilder =  new StringBuilder();
        try {
            Document doc = Jsoup.connect(url).get();
            Elements definitions = doc.select("h2");
            if (definitions.isEmpty()) {
                throw new Exception();
            }
            for (Element element : definitions) {
                String title = element.childNode(0).childNode(0).toString();
                String link = LINK + element.childNode(0).attr("href");
                String line = title.replace("<span>","").replace("</span>","") + " - " + link;
                stringBuilder.append(line).append("\n\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}
