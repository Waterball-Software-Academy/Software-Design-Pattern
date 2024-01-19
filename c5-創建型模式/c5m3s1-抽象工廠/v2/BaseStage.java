package v2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static java.lang.String.format;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

/**
 * @author johnny@waterballsa.tw
 */
public class BaseStage extends Stage {
    public static final int DEFAULT_QUESTIONS_NUMBER = 3;
    private final Floor floor;

    public BaseStage(Region region) {
        this.floor = region.getFloor();
    }

    @Override
    protected List<Question> loadQuestions() {
        try {
            String floorCanonicalName = floor.getName().toLowerCase().replace(" ", "");
            String raw = Files.readString(Paths.get(format("%s.questions", floorCanonicalName)));
            String[] questionRaws = raw.split("===");
            return stream(questionRaws)
                    .map(this::parseQuestion)
                    .limit(DEFAULT_QUESTIONS_NUMBER)
                    .sorted((a, b) -> (int) (Math.random() * DEFAULT_QUESTIONS_NUMBER - 1))
                    .collect(toList());
        } catch (IOException err) {
            throw new RuntimeException(err);
        }
    }

    private Question parseQuestion(String questionRaw) {
        String[] parts = questionRaw.split("\\s*---\\s*");
        String description = parts[0].trim();
        String answer = parts[1].trim();
        return new Question(description, answer);
    }

    @Override
    public boolean failed() {
        return fails >= 2;
    }
}
