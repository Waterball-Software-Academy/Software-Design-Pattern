package v1and2;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class Main {
    public static void main(String[] args) {
        VocabLookupCLI cli = new VocabLookupCLI(new VocabCrawlerAdapter());
        cli.start();
    }
}
