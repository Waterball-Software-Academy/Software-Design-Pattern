package v1;

import v1.lib.CountRows;
import v1.lib.MarkdownParser;
import v1.lib.Table;
import v1.lib.TableStatsPerformer;
import v1.lib.TotalColumn;

import java.io.IOException;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class Main {
    public static void main(String[] args) throws IOException {
        MarkdownParser parser = new MarkdownParser();
        Table table = parser.parseTableFromFile("data.table");
        TableStatsPerformer statsPerformer = new TableStatsPerformer();
        statsPerformer.addStatsOperation(new TotalColumn("Cost"));
        statsPerformer.addStatsOperation(new CountRows("Members"));
        statsPerformer.print(table);
    }
}
