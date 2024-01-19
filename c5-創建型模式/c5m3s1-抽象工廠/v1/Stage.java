package v1;
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
public class Stage {
    private final Floor floor;
    private final List<Question> questions;
    private int fail = 0;

    public Stage(Floor floor) {
        this.floor = floor;
        try {
            this.questions = loadQuestions();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Question> loadQuestions() throws IOException {
        String floorCanonicalName = floor.getName().toLowerCase().replace(" ", "");
        String raw = Files.readString(Paths.get(format("%s.questions", floorCanonicalName)));
        String[] questionRaws = raw.split("===");
        return stream(questionRaws)
                .map(this::parseQuestion)
                .limit(3)
                .sorted((a, b) -> (int) (Math.random() * 3 - 1))
                .collect(toList());
    }

    private Question parseQuestion(String questionRaw) {
        String[] parts = questionRaw.split("\\s*---\\s*");
        return new Question(parts[0].trim(), parts[1].trim());
    }

    public void play(Player player) {
        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            question.ask(i + 1);
            String answer = player.answer();
            if (question.isCorrectAnswer(answer)) {
                System.out.println("You are correct!");
            } else {
                System.out.println("You are wrong!");
                if (++fail >= 2) {
                    System.out.println("You lose the game!");
                    player.loseGame();
                    return;
                }
            }
        }
        System.out.println("You pass this stage!");
    }

    public boolean pass() {
        return fail >= 2;
    }
}
