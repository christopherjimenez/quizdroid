package jimenchr.washington.edu.quizdroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class QuizDroidActivity extends ActionBarActivity {
    List<String> titles;
    private static final int SELECTED_SETTINGS = 1;
    private String url;
    private int minutes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_droid);

        QuizApp app = (QuizApp) getApplication();
        final Map<String, Topic> topics = app.getAllTopics();

        titles = new ArrayList<String>();

        for(String key: topics.keySet()) {
            titles.add(topics.get(key).getTitle());
        }

        ListView topicList = (ListView) findViewById(R.id.categoryList);

        populateListView(topicList,titles);

        topicList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String key = titles.get(position);
                Topic topic = topics.get(key);

                Intent startQuiz = new Intent(QuizDroidActivity.this, GameActivity.class);
                startQuiz.putExtra("topic", topic);
                startActivity(startQuiz);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == SELECTED_SETTINGS) {
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
            url = sharedPreferences.getString("location", "http://tednewardsandbox.site44.com/questions.json");
            minutes = Integer.parseInt(sharedPreferences.getString("minutes", "15"));

            QuizApp.getInstance().startBroadcasting(minutes, url);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quiz_droid, menu);
        return true;
    }

    private void openPreferences() {
        Intent prefs = new Intent(getApplicationContext(), QuizDroidPreferences.class);
        startActivityForResult(prefs, SELECTED_SETTINGS);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch(id) {
            case R.id.actionPreferences:
                openPreferences();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void populateListView(ListView v, List<String> titles) {
        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, titles);
        v.setAdapter(categoryAdapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        QuizApp.getInstance().killAlarm();
    }
}
