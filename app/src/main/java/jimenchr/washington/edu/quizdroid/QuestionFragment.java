package jimenchr.washington.edu.quizdroid;


import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionFragment extends Fragment {

    String[] questions;
    int correct;
    int initialCount;
    int count;
    private Activity hostActivity;

    public QuestionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments() != null) {
            questions  = getArguments().getStringArray("questions");
            correct = getArguments().getInt("correct");
            initialCount = getArguments().getInt("initialCount");
            count = getArguments().getInt("count");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_question, container, false);

        RadioButton radio1 = (RadioButton) rootView.findViewById(R.id.radio1);
        RadioButton radio2 = (RadioButton) rootView.findViewById(R.id.radio2);
        RadioButton radio3 = (RadioButton) rootView.findViewById(R.id.radio3);
        RadioButton radio4 = (RadioButton) rootView.findViewById(R.id.radio4);
        TextView question = (TextView) rootView.findViewById(R.id.textView5);
        final RadioGroup rg = (RadioGroup) rootView.findViewById(R.id.radioGroup);
        final Button submit = (Button) rootView.findViewById(R.id.submit);

        int j = (initialCount - count);
        question.setText(questions[(j * 6) + 0]);
        String radio1Question = questions[(j * 6) + 1];
        radio1.setText(radio1Question);
        String radio2Question = questions[(j * 6) + 2];
        radio2.setText(radio2Question);
        String radio3Question = questions[(j * 6) + 3];
        radio3.setText(radio3Question);
        String radio4Question = questions[(j * 6) + 4];
        radio4.setText(radio4Question);
        final String correctAnswer = questions[(j * 6) + 5];
        count--;

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                submit.setVisibility(View.VISIBLE);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioButtonID = rg.getCheckedRadioButtonId();
                View radioButton = rg.findViewById(radioButtonID);
                int radioId = rg.indexOfChild(radioButton);
                RadioButton btn = (RadioButton) rg.getChildAt(radioId);
                String answer = (String) btn.getText();

                boolean rightAnswer = correctAnswer.equals(answer);
                if(rightAnswer) {
                    correct++;
                }
                if(hostActivity instanceof GameActivity) {
                    ((GameActivity) hostActivity).loadAnswerFragment(correctAnswer, correct, initialCount, count);
                }
            }
        });

        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.hostActivity = activity;
    }
}
