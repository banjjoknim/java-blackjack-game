package domain.user;

public class Answer {
    private static final String YES = "y";

    private String answer;

    public Answer(String answer) {
        this.answer = answer;
    }

    public boolean isYes() {
        return answer.equals(YES);
    }

}
