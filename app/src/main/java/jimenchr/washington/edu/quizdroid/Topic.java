package jimenchr.washington.edu.quizdroid;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chris on 5/14/2015.
 */
public class Topic implements Serializable {
    private String description;
    private String title;
    private List<Quiz> quizQuestions;

    public Topic(String description, String title, List<Quiz> quizQuestions) {
        this.description = description;
        this.title = title;
        this.quizQuestions = quizQuestions;
    }


    public Topic(String description, String title) {
        this(description, title, new ArrayList<Quiz>());
    }

    public void addQuestion(Quiz question) {
        quizQuestions.add(question);
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public List<Quiz> getQuizQuestions() {
        return quizQuestions;
    }

    public int questionCount() {
        return quizQuestions.size();
    }
}
