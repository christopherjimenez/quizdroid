package jimenchr.washington.edu.quizdroid;

import java.io.Serializable;

/**
 * Created by chris on 5/14/2015.
 */
public class Quiz implements Serializable {
    private String question;
    private String[] answers;
    private int correctAnswer;

    public Quiz(String question, String[] answers, int correctAnswer) {
        this.question = question;
        this.answers = answers;
        this.correctAnswer = correctAnswer - 1; //correcting off by one bug
    }

    public String getQuestion() {
        return question;
    }

    public String[] getAnswers() {
        return answers;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public String getCorrectAnswerString() { return answers[correctAnswer]; }

}
