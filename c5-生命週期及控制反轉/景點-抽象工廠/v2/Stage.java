package v2;

import java.util.List;

/**
 * @author johnny@waterballsa.tw
 */
public abstract class Stage {
    protected int fails;

    public void play(Player player) {
        List<Question> questions = loadQuestions();
        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            question.ask(i + 1);
            String answer = player.answer();
            if (question.isCorrectAnswer(answer)) {
                System.out.println("You are correct!");
            } else {
                System.out.println("You are wrong!");
                ++fails;
                if (failed()) {
                    System.out.println("You lose the game!");
                    player.loseGame();
                    return;
                }
            }
        }
        System.out.println("You pass this stage!");
    }

    protected abstract List<Question> loadQuestions();


    public boolean failed() {
        return fails >= 2;
    }
}
