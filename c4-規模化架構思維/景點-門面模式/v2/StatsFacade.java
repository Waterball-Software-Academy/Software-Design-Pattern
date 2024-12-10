package v2;

import v2.lib.*;

import java.io.IOException;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class StatsFacade {
    public static final int TOTAL_COST = 0x01;
    public static final int COUNT_MEMBERS = 0x01 << 1;
    private final MarkdownParser parser = new MarkdownParser();

    public void printMarkdownTableStats(String fileName, int statOpsFlag) throws IOException {
        Table table = parser.parseTableFromFile(fileName);

        TableStatsPerformer statsPerformer = new TableStatsPerformer();

        if ((statOpsFlag & TOTAL_COST) != 0) {
            statsPerformer.addStatsOperation(new TotalColumn("Cost"));
        }
        if ((statOpsFlag & COUNT_MEMBERS) != 0) {
            statsPerformer.addStatsOperation(new CountRows("Members"));
        }

        statsPerformer.print(table);
    }

}
