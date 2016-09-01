package np.com.shakya.sugan.mysurvey;

/**
 * Created by sugan on 23/08/16.
 */
public class Question {
    public static final int TEXT_QUESTION = 0;
    public static final int SPINNER_QUESTION = 1;
    public static final int RADIO_BUTTON_QUESTION = 2;
//    public static final int TOGGLE_QUESTION = 3 ;

    private int questionType;
    private String questionText;
    private String answer;
    private String[] options;

    public Question(int questionType, String questionText, String answer, String[] options) {
        this.questionType = questionType;
        this.options = options;
        this.questionText = questionText;
        this.answer = answer;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getQuestionType() {
        return questionType;
    }

    public void setQuestionType(int questionType) {
        this.questionType = questionType;
    }

    public String[] getOptions() {
        return options;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }
}
