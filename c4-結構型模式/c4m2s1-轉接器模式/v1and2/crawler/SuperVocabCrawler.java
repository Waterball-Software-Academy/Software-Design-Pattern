package v1and2.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class SuperVocabCrawler {
    /**
     * @param spelling the exact word's spelling
     * @return word contains the exact spelling
     * @throws YouSpellItWrongException can't find any word with the exact spelling
     */
    public SuperWORD crawl(String spelling) throws YouSpellItWrongException {
        final String LINK = "https://www.vocabulary.com/dictionary/" + spelling;
        try {
            Document doc = Jsoup.connect(LINK).get();
            Elements definitionElements = doc.select("div.word-definitions > ol > li.sense");

            if (definitionElements.isEmpty()) {
                throw new YouSpellItWrongException();
            }
            List<String> definitions = new ArrayList<>();
            for (Element definitionElement : definitionElements) {
                definitions.add(definitionElement.text());
            }
            return new SuperWORD(spelling, definitions);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

