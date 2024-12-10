package v2;

import java.io.IOException;

import static v2.StatsFacade.COUNT_MEMBERS;
import static v2.StatsFacade.TOTAL_COST;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class Main {
    public static void main(String[] args) throws IOException {
        StatsFacade statsFacade = new StatsFacade();
        statsFacade.printMarkdownTableStats("data.table",
                TOTAL_COST | COUNT_MEMBERS);
    }
}
