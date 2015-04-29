package jimenchr.washington.edu.quizdroid;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class AnswerPage extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_page);
        Bundle extras = getIntent().getExtras();
        final String[] questions = extras.getStringArray("questions");
        final int count = extras.getInt("count");
        final int initialCount = extras.getInt("initialCount");
        int total = extras.getInt("total");
        boolean correct = extras.getBoolean("correct");
        int j = (initialCount - (count + 1));
        final String correctAnswer = questions[(j * 6) + 5];
        TextView answerView = (TextView) findViewById(R.id.correctanswer);
        answerView.setText(correctAnswer);
        if (correct) {
            total++;
        }
        final int newTotal = total;
        TextView questionView = (TextView) findViewById(R.id.correct);
        questionView.setText("you have " + total + " out of " + questions[questions.length -1] + " correct.");

        Button next = (Button) findViewById(R.id.nextFinish);
        if(count == 0) {
            next.setText("Finish");
            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent questionContents = new Intent(AnswerPage.this, QuizDroid.class);
                    questionContents.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(questionContents);
                }
            });
        } else {
            next.setText("Next");
            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent questionContents = new Intent(AnswerPage.this, QuestionPage.class);
                    questionContents.putExtra("questions", questions);
                    questionContents.putExtra("count", count);
                    questionContents.putExtra("total", newTotal);
                    questionContents.putExtra("initialCount", initialCount);
                    startActivity(questionContents);
                    finish();
                }
            });
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_answer_page, menu);
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
