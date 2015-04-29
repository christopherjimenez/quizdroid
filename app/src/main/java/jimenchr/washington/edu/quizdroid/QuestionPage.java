package jimenchr.washington.edu.quizdroid;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;


public class QuestionPage extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_page);
        Bundle extras = getIntent().getExtras();
        String[] questions = extras.getStringArray("questions");
        int count = extras.getInt("count");
        int initialCount = extras.getInt("initialCount");
        int total = extras.getInt("total");
        RadioButton radio1 = (RadioButton) findViewById(R.id.radio1);
        RadioButton radio2 = (RadioButton) findViewById(R.id.radio2);
        RadioButton radio3 = (RadioButton) findViewById(R.id.radio3);
        RadioButton radio4 = (RadioButton) findViewById(R.id.radio4);

        int j = initialCount - count;
        String radio1Question = questions[(j * 4) + 1];
        radio1.setText(radio1Question);
        String radio2Question = questions[(j * 4) + 2];
        radio2.setText(radio2Question);
        String radio3Question = questions[(j * 4) + 3];
        radio3.setText(radio3Question);
        String radio4Question = questions[(j * 4) + 4];
        radio4.setText(radio4Question);
        final String correctAnswer = questions[(j * 4) + 5];
        count--;

        final RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
               findViewById(R.id.submit).setVisibility(View.VISIBLE);
            }
        });
        Button submit = (Button) findViewById(R.id.submit);


        final Intent questionContents = new Intent(QuestionPage.this, AnswerPage.class);
        questionContents.putExtra("questions", questions);
        questionContents.putExtra("count",count);
        questionContents.putExtra("initialCount",initialCount);
        questionContents.putExtra("total",total);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean rightAnswer = false;
                int radioButtonID = rg.getCheckedRadioButtonId();
                View radioButton = rg.findViewById(radioButtonID);
                int radioId = rg.indexOfChild(radioButton);
                RadioButton btn = (RadioButton) rg.getChildAt(radioId);
                String answer = (String) btn.getText();
                if(answer.equals(correctAnswer)) {
                    rightAnswer = true;
                }
                questionContents.putExtra("answer", answer);
                questionContents.putExtra("correct", rightAnswer);
                startActivity(questionContents);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_question_page, menu);
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
