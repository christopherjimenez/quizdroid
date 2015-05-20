package jimenchr.washington.edu.quizdroid;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;


public class GameActivity extends ActionBarActivity {
    private Topic topic;
    private static final int SELECTED_SETTINGS = 1;
    String url;
    int minutes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Bundle extras = getIntent().getExtras();
        topic = (Topic) extras.getSerializable("topic");

        String desc = topic.getDescription();
        int questionCount = topic.questionCount();

        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        Bundle questionBundle = new Bundle();
        questionBundle.putString("desc", desc);
        questionBundle.putInt("questionCount", questionCount);

        OverviewFragment of = new OverviewFragment();
        of.setArguments(questionBundle);

        ft.add(R.id.fragment_container, of);
        ft.commit();
    }

    public void loadQuestionFragment(int correct, int initialCount, int count) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        List<Quiz> questions = topic.getQuizQuestions();

        Bundle questionBundle = new Bundle();

        questionBundle.putSerializable("question", questions.get(initialCount - count));
        questionBundle.putInt("correct", correct);
        questionBundle.putInt("initialCount", initialCount);
        questionBundle.putInt("count", count);

        QuestionFragment qf = new QuestionFragment();
        qf.setArguments(questionBundle);

        ft.replace(R.id.fragment_container, qf);
        ft.commit();
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

    public void loadAnswerFragment(String correctAnswer, int correct, int initialCount, int count) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        Bundle questionBundle = new Bundle();
        questionBundle.putString("correctAnswer", correctAnswer);
        questionBundle.putInt("correct", correct);
        questionBundle.putInt("initialCount", initialCount);
        questionBundle.putInt("count", count);

        AnswerFragment af = new AnswerFragment();
        af.setArguments(questionBundle);

        ft.replace(R.id.fragment_container, af);
        ft.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game, menu);
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        QuizApp.getInstance().killAlarm();
    }
}
