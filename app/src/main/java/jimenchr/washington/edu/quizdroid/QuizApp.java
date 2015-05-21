package jimenchr.washington.edu.quizdroid;


import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * Created by chris on 5/14/2015.
 */
public class QuizApp extends Application {
    private static QuizApp instance = null;
    private String url;
    private int minutes;
    private boolean alarm;
    private AlarmManager am;
    private PendingIntent pi;

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

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        url = sharedPreferences.getString("location", "http://tednewardsandbox.site44.com/questions.json");
        minutes = Integer.parseInt(sharedPreferences.getString("minutes", "15"));
        am = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarm = false;

        BroadcastReceiver alarmReciever = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Toast.makeText(QuizApp.this, url, Toast.LENGTH_SHORT).show();
            }
        };

        registerReceiver(alarmReciever, new IntentFilter("jimenchr.washington.edu.getQuestions"));

        Intent intent = new Intent();
        intent.setAction("jimenchr.washington.edu.getQuestions");
        pi = PendingIntent.getBroadcast(this,0,intent,0);
        startBroadcasting(minutes, url);

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

    public void startBroadcasting(int minutes, String url) {
        int interval = minutes * 60000;
        if (alarm) {
            killAlarm();
        }
        alarm = true;
        am.setRepeating(AlarmManager.RTC, System.currentTimeMillis() - interval, interval, pi);
    }

    private String readJSONFile(InputStream is) throws IOException {
        int size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();

        return new String(buffer, "UTF-8");
    }

    public void killAlarm() {
        am.cancel(pi);
        pi.cancel();
        alarm = false;
    }


    public static QuizApp getInstance() {
        return instance;
    }
}
