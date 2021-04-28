package domain.user;

public class Answer {
    private static final String YES = "y";
    private static final String NO = "n";

    private String answer;

    public Answer(String answer) {
        validateAnswer(answer);
        this.answer = answer;
    }

    private void validateAnswer(String answer) {
        boolean isYes = YES.equals(answer);
        boolean isNo = NO.equals(answer);
        if (!isYes && !isNo) {
            throw new IllegalArgumentException("대답으로는 'y' 또는 'n' 만 입력할 수 있습니다.");
        }
    }

    public boolean isYes() {
        return answer.equals(YES);
    }

    public String getAnswer() {
        return answer;
    }

}
