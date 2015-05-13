package jimenchr.washington.edu.quizdroid;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class AnswerFragment extends Fragment {
    String correctAnswer;
    int correct;
    int initialCount;
    int count;
    Activity hostActivity;

    public AnswerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments() != null) {
            correctAnswer = getArguments().getString("correctAnswer");
            correct = getArguments().getInt("correct");
            initialCount = getArguments().getInt("initialCount");
            count = getArguments().getInt("count");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_answer, container, false);

        TextView answerView = (TextView) rootView.findViewById(R.id.correctanswer);
        TextView questionView = (TextView) rootView.findViewById(R.id.correct);
        Button next = (Button) rootView.findViewById(R.id.nextFinish);

        answerView.setText(correctAnswer);
        questionView.setText("You have " + correct + " out of " + initialCount + " correct.");

        if(count == 0) {
            next.setText("Finish");
            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent questionContents = new Intent(getActivity(), QuizDroidActivity.class);
                    questionContents.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(questionContents);
                }
            });
        } else {
            next.setText("Next");
            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((GameActivity) hostActivity).loadQuestionFragment(correct, initialCount, count);
                }
            });
        }
        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        this.hostActivity = activity;
    }
}
