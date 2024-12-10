package v1.lib;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class Table {
    private final List<String> header;
    private final List<List<String>> data;

    public Table(List<List<String>> data) {
        this.header = data.get(0);
        this.data = data;
    }

    public List<String> getColumn(String fieldName) {
        int column = header.indexOf(fieldName);
        return data.stream()
                .skip(1) // skip the header
                .map(row -> row.get(column)).collect(toList());
    }

    public List<String> getRow(int row) {
        return data.get(row);
    }

    public List<List<String>> getRows() {
        return data.subList(1 /*skip the header*/, data.size());
    }

    public List<List<String>> getData() {
        return data;
    }
}
