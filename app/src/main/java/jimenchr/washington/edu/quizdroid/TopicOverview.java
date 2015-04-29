package jimenchr.washington.edu.quizdroid;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class TopicOverview extends ActionBarActivity {
    public String desc;
    public String[] questions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_overview);
        Bundle extras = getIntent().getExtras();
        desc = extras.getString("desc");
        questions = extras.getStringArray("questions");
        TextView description = (TextView) findViewById(R.id.description);
        description.setText(desc);
        TextView countView = (TextView) findViewById(R.id.questioncount);
        countView.setText(questions[questions.length-1] + " questions");
        final Intent questionContents = new Intent(TopicOverview.this, QuestionPage.class);
        questionContents.putExtra("questions", questions);
        questionContents.putExtra("count",Integer.parseInt(questions[questions.length-1]));
        questionContents.putExtra("initialCount",Integer.parseInt(questions[questions.length-1]));
        questionContents.putExtra("total",0);

        Button begin = (Button) findViewById(R.id.begin);
        begin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(questionContents);
                finish();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_topic_overview, menu);
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
