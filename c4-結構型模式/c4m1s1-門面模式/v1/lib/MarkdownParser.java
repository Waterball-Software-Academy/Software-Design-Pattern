package v1.lib;


import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.Files.readString;
import static java.util.Arrays.asList;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class MarkdownParser {
    public Table parseTableFromFile(String fileName) throws IOException {
        String markdown = readString(Paths.get(fileName));
        String[] lines = markdown.split("\n");
        List<List<String>> fields = new ArrayList<>();
        fields.add(parseRow(0, lines));
        for (int i = 2; i < lines.length; i++) {
            fields.add(parseRow(i, lines));
        }
        return new Table(fields);
    }

    private List<String> parseRow(int i, String[] lines) {
        return asList(lines[i].replaceFirst("[|\\s]+", "")
                .split("[|\\s]+"));
    }
}
