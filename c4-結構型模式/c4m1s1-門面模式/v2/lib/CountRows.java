package v2.lib;

import static java.lang.String.format;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class CountRows implements StatsOperation {
    public CountRows(String rowName) {
        this.rowName = rowName;
    }

    private final String rowName;
    @Override
    public String getName() {
        return format("Count %s", rowName);
    }

    @Override
    public Object perform(Table table) {
        return table.getData().size();
    }
}
