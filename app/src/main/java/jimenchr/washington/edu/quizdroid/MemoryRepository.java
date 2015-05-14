package jimenchr.washington.edu.quizdroid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chris on 5/14/2015.
 */
public class MemoryRepository implements TopicRepository {
    private Map<String, Topic> topics;

    public MemoryRepository() {
        topics = new HashMap<String, Topic>();

        List<Quiz> mathQuiz = new ArrayList<Quiz>();
        List<Quiz> physicsQuiz = new ArrayList<Quiz>();
        List<Quiz> marvelQuiz = new ArrayList<Quiz>();

        String[] mathAnswers1 = {"8","3","5","7"};
        String[] mathAnswers2 = {"14","9","13","turtle"};
        String[] mathAnswers3 = {"3","2","1","6"};
        String[] mathAnswers4 = {"2","1","3","4"};
        mathQuiz.add(new Quiz("4 + 4?", mathAnswers1, 1));
        mathQuiz.add(new Quiz("2 * 7?", mathAnswers2, 1));
        mathQuiz.add(new Quiz("6 / 2", mathAnswers3, 1));
        mathQuiz.add(new Quiz("6 % 4", mathAnswers4, 1));

        String[] physicsAnswers1 = {"99.9%","10%","5%","80%"};
        String[] physicsAnswers2 = {"yes","no","maybe","so"};
        String[] physicsAnswers3 = {"smart ass","physicist","nerd","einstein"};
        physicsQuiz.add(new Quiz("how much empty space in an atom?", physicsAnswers1, 1));
        physicsQuiz.add(new Quiz("will a feather and a bowling ball fall at the same speed in a vacuum, assuming there is gravity?", physicsAnswers2, 1));
        physicsQuiz.add(new Quiz("a person who studies physics is called?", physicsAnswers3, 2));

        String[] marvelAnswers1 = {"super soldier program","nuclear accident","nazi soldier experiment","spider bite"};
        String[] marvelAnswers2 = {"Steve Rogers","Seth Rogan","Will Smith","Peter Parker"};
        String[] marvelAnswers3 = {"The Hulk","Thor","Ant-Man","Iron Man"};
        marvelQuiz.add(new Quiz("how did captain america gain his abilities?", marvelAnswers1, 1));
        marvelQuiz.add(new Quiz("what is captain america's real name?", marvelAnswers2, 1));
        marvelQuiz.add(new Quiz("who was not an original member of the avengers?", marvelAnswers3, 1));

        Topic math = new Topic("Try your hand at some basic math questions", "Math", mathQuiz);
        Topic physics = new Topic("Are you einstein?", "Physics", physicsQuiz);
        Topic marvel = new Topic("Let's be honest, Captain America is the coolest", "Marvel", marvelQuiz);

        topics.put("Math", math);
        topics.put("Physics", physics);
        topics.put("Marvel", marvel);

    }

    public Map<String, Topic> getAllTopics() {
        return topics;
    }

    public Topic getTopic(String key){
        return topics.get(key);
    }
}
