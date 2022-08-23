package v1and2;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class WordNotExistsException extends RuntimeException {
    private final String wordSpelling;
    public WordNotExistsException(String wordSpelling, Exception e) {
        super(e);
        this.wordSpelling = wordSpelling;
    }

    public String getWordSpelling() {
        return wordSpelling;
    }
}
