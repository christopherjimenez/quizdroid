package jimenchr.washington.edu.quizdroid;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class GameActivity extends ActionBarActivity {
    private String desc;
    private String[] questions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Bundle extras = getIntent().getExtras();
        desc = extras.getString("desc");
        questions = extras.getStringArray("questions");

        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        Bundle questionBundle = new Bundle();
        questionBundle.putString("desc", desc);
        questionBundle.putStringArray("questions", questions);

        OverviewFragment of = new OverviewFragment();
        of.setArguments(questionBundle);

        ft.add(R.id.fragment_container, of);
        ft.commit();


    }

    public void loadQuestionFragment(int correct, int initialCount, int count) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        Bundle questionBundle = new Bundle();

        questionBundle.putStringArray("questions", questions);
        questionBundle.putInt("correct", correct);
        questionBundle.putInt("initialCount", initialCount);
        questionBundle.putInt("count", count);

        QuestionFragment qf = new QuestionFragment();
        qf.setArguments(questionBundle);

        ft.replace(R.id.fragment_container, qf);
        ft.commit();
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
