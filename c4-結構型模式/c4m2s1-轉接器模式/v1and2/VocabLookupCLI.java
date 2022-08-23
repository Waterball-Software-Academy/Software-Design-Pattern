package v1and2;

import java.util.Scanner;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class VocabLookupCLI {
    private static final Scanner in = new Scanner(System.in);
    private final VocabDictionary dictionary;

    public VocabLookupCLI(VocabDictionary dictionary) {
        this.dictionary = dictionary;
    }

    public void start() {
        while (true) {
            System.out.println("Lookup a word: ");
            String spelling = in.nextLine();
            if (!spelling.isBlank()) {
                try {
                    Word word = dictionary.lookup(spelling);
                    System.out.println(word);
                } catch (WordNotExistsException e) {
                    System.out.printf("Can't find the word '%s'.\n", spelling);
                }
            }
        }
    }

    public static void main(String[] args) {
        VocabLookupCLI cli = new VocabLookupCLI(new VocabCrawlerAdapter());
        cli.start();
    }
}
