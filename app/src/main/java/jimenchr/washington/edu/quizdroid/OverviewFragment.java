package jimenchr.washington.edu.quizdroid;


import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class OverviewFragment extends Fragment {

    private Activity hostActivity;
    private String desc;
    private int questionCount;

    public OverviewFragment() {
        // Required empty public constructor
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments() != null) {
            desc = getArguments().getString("desc");
            questionCount = getArguments().getInt("questionCount");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_overview, container, false);

        TextView description = (TextView) rootView.findViewById(R.id.description);
        description.setText(desc);
        TextView countView = (TextView) rootView.findViewById(R.id.questioncount);
        countView.setText(questionCount + " questions");

        Button begin = (Button) rootView.findViewById(R.id.begin);

        begin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(hostActivity instanceof GameActivity) {
                    ((GameActivity) hostActivity).loadQuestionFragment(0,questionCount,questionCount);
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
