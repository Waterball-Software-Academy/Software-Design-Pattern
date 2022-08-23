package v2.lib;

import static java.lang.String.format;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class TotalColumn implements StatsOperation {
    private final String fieldName;

    public TotalColumn(String fieldName) {
        this.fieldName = fieldName;
    }

    @Override
    public String getName() {
        return format("Total %s", fieldName);
    }

    @Override
    public Object perform(Table table) {
        return table.getColumn(fieldName)
                .stream().mapToDouble(Double::parseDouble)
                .sum();
    }
}
