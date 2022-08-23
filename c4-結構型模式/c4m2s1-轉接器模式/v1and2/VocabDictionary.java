package v1and2;

import java.util.List;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public interface VocabDictionary {

    /**
     * @param wordSpelling the exact whole spelling of the word
     * @return the word that exactly matches that spelling
     */
    Word lookup(String wordSpelling) throws WordNotExistsException;

}
