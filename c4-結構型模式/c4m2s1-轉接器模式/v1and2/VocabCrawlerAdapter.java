package v1and2;

import v1and2.Word.PartOfSpeech;
import v1and2.crawler.SuperVocabCrawler;
import v1and2.crawler.SuperWORD;
import v1and2.crawler.YouSpellItWrongException;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class VocabCrawlerAdapter implements VocabDictionary {
    private final SuperVocabCrawler superVocabCrawler = new SuperVocabCrawler();

    @Override
    public Word lookup(String wordSpelling) throws WordNotExistsException {
        SuperWORD superWORD;
        try {
            Map<PartOfSpeech, List<String>> definitions = new LinkedHashMap<>();
            superWORD = superVocabCrawler.crawl(wordSpelling);
            String spelling = superWORD.raw;
            for (String definitionLine : superWORD.definitions) {
                String[] splits = definitionLine.split("\\s+", 2);
                PartOfSpeech partOfSpeech = convertPartOfSpeech(splits[0]);
                String definition = splits[1];
                definitions.computeIfAbsent(partOfSpeech, k -> new ArrayList<>())
                        .add(definition);
            }
            return new Word(spelling, definitions);
        } catch (YouSpellItWrongException e) {
            throw new WordNotExistsException();
        }
    }

    private PartOfSpeech convertPartOfSpeech(String partOfSpeech) {
        switch (partOfSpeech) {
            case "noun":
                return PartOfSpeech.NOUN;
            case "verb":
                return PartOfSpeech.VERB;
            default:
                throw new IllegalArgumentException(String.format("Unsupported partOfSpeech '%s'.", partOfSpeech));
        }
    }

}
