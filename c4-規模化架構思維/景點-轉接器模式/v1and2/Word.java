package v1and2;

import java.util.List;
import java.util.Map;

import static java.lang.String.format;
import static java.util.stream.Collectors.joining;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class Word {
    private final String spelling;
    private final Map<PartOfSpeech, List<String>> definitions;

    public enum PartOfSpeech {
        NOUN("N"),
        PRONOUN("PRON"),
        VERB("V"),
        ADV("ADV"),
        ADJ("ADJ"),
        ARTICLE("ARTICLE"),
        PREPOSITION("PREP"),
        CONJUNCTION("CONJ"),
        INTERJECTION("INTERJECTION");
        String representation;

        PartOfSpeech(String representation) {
            this.representation = representation;
        }

        @Override
        public String toString() {
            return representation;
        }
    }

    public Word(String spelling, Map<PartOfSpeech, List<String>> definitions) {
        this.spelling = spelling;
        this.definitions = definitions;
    }

    @Override
    public String toString() {
        return format("%s: \n%s", spelling,
                definitions.entrySet().stream()
                        .flatMap(entry -> entry.getValue().stream()
                                .map(description -> format("%s: %s", entry.getKey(), description)))
                        .collect(joining("\n")));
    }
}
