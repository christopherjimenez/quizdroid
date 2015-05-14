package jimenchr.washington.edu.quizdroid;


import android.app.Application;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * Created by chris on 5/14/2015.
 */
public class QuizApp extends Application {
    private static QuizApp instance = null;

    private TopicRepository repository;

    public QuizApp() {
        if(instance == null) {
            instance = this;
        } else {
            throw new RuntimeException("You already have a quizapp instance running");
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("QuizApp", "Quiz app is running");
        try {
            InputStream is = getAssets().open("questions.json");
            String json = readJSONFile(is);
            repository = new JsonRepository(json);
        } catch(IOException e) {
            e.printStackTrace();
            //fallback
            repository = new MemoryRepository();
        }
    }

    public Map<String, Topic> getAllTopics() {
        return repository.getAllTopics();
    }

    private String readJSONFile(InputStream is) throws IOException {
        int size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();

        return new String(buffer, "UTF-8");
    }
}
