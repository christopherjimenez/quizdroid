package jimenchr.washington.edu.quizdroid;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class QuizDroid extends ActionBarActivity {
    public String mathDesc = "Try your hand at some basic math questions.";
    public String[] math ={"4 + 4?","8","3","5","7","8","2 * 7?","14","9","13","turtle","14","6 / 2?","3","2","1","6","3","6 % 4?","2","1","3","4","2","4"};
    public String physicsDesc = "Are you einstein?";
    public String[] physics ={"how much empty space in an atom?","99.9%","10%","5%","80%","99.9%",
            "will a feather and a bowling ball fall at the same speed in a vacuum, assuming there is gravity?","yes","no","maybe","so","yes",
            "a person who studies physics is called?","smart ass","physicist","nerd","einstein","physicist","3"};
    public String marvelDesc = "Let's be honest, Captain America is the coolest";
    public String[] marvel ={"how did captain america gain his abilities?","super soldier program","nuclear accident","nazi soldier experiment","spider bite","super soldier program",
            "what is captain america's real name? ","Steve Rogers","Seth Rogan","Will Smith","Peter Parker","Steve Rogers",
            "who was not an original member of the avengers?","The Hulk","Thor","Ant-Man","Iron Man","The Hulk","3"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_droid);
        final TextView mathView = (TextView) findViewById(R.id.textView2);
        final TextView physicsView = (TextView) findViewById(R.id.textView3);
        final TextView marvelView = (TextView) findViewById(R.id.textView4);
        final Intent questionCategory = new Intent(QuizDroid.this, TopicOverview.class);

        View.OnClickListener click = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.equals(mathView)) {
                    questionCategory.putExtra("desc",mathDesc);
                    questionCategory.putExtra("questions",math);

                } else if (v.equals(physicsView)) {
                    questionCategory.putExtra("desc",physicsDesc);
                    questionCategory.putExtra("questions",physics);

                } else if(v.equals(marvelView)) {
                    questionCategory.putExtra("desc",marvelDesc);
                    questionCategory.putExtra("questions",marvel);
                }
                startActivity(questionCategory);
            }
        };

        mathView.setOnClickListener(click);
        physicsView.setOnClickListener(click);
        marvelView.setOnClickListener(click);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quiz_droid, menu);
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
