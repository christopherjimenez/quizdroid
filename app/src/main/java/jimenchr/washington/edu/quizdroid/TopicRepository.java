package jimenchr.washington.edu.quizdroid;

import java.util.Map;

/**
 * Created by chris on 5/14/2015.
 */
public interface TopicRepository {
    public Map<String, Topic> getAllTopics();
    public Topic getTopic(String key);
}
