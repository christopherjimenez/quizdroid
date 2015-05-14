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

    Quiz quiz;
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
            quiz = (Quiz) getArguments().getSerializable("question");
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
        question.setText(quiz.getQuestion());
        String[] answers = quiz.getAnswers();

        radio1.setText(answers[0]);
        radio2.setText(answers[1]);
        radio3.setText(answers[2]);
        radio4.setText(answers[3]);

        final String correctAnswer = quiz.getCorrectAnswerString();
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
