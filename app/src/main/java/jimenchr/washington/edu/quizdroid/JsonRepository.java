package jimenchr.washington.edu.quizdroid;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chris on 5/14/2015.
 */
public class JsonRepository implements TopicRepository {
    private Map<String, Topic> topics;

    public JsonRepository(String json) {
        topics = new HashMap<String, Topic>();

        try{
            JSONArray topics = new JSONArray(json);

            for (int i = 0; i < topics.length(); i++) {
                JSONObject obj = topics.getJSONObject(i);
                String title = obj.getString("title");
                String description = obj.getString("desc");

                List<Quiz> topicQuestions = new ArrayList<Quiz>();
                JSONArray questions = obj.getJSONArray("questions");

                for (int j = 0; j < questions.length(); j++) {
                    JSONObject jsonQuestion = questions.getJSONObject(j);
                    String question = jsonQuestion.getString("text");
                    int correct = jsonQuestion.getInt("answer");
                    JSONArray answers = (JSONArray) jsonQuestion.get("answers");

                    String answer1 = answers.get(0).toString();
                    String answer2 = answers.get(1).toString();
                    String answer3 = answers.get(2).toString();
                    String answer4 = answers.get(3).toString();

                    String[] quizQuestions = {answer1, answer2, answer3, answer4};

                    Quiz quiz = new Quiz(question, quizQuestions, correct);

                    topicQuestions.add(quiz);
                }

                this.topics.put(title, new Topic(description, title, topicQuestions));
            }
        } catch(JSONException error) {
            error.printStackTrace();
        }
    }


    public Map<String, Topic> getAllTopics() {
        return topics;
    }

    public Topic getTopic(String key) {
        return topics.get(key);
    }

}
