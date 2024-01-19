package v1;
/**
 * @author johnny@waterballsa.tw
 */
public class Question {
    private final String description;
    private final String answer;

    public Question(String description, String answer) {
        this.description = description;
        this.answer = answer;
    }

    public boolean isCorrectAnswer(String answer) {
        return this.answer.equalsIgnoreCase(answer);
    }

    public String getDescription() {
        return description;
    }

    public String getAnswer() {
        return answer;
    }

    public void ask(int questionNumber) {
        System.out.printf("(%d) %s%nPlease answer: ", questionNumber, description);
    }
}
