package pl.artsit.flexgoals.ui.goals;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import pl.artsit.flexgoals.MainActivity;
import pl.artsit.flexgoals.R;
import pl.artsit.flexgoals.http.HttpClient;
import pl.artsit.flexgoals.http.goals.GoalGetCallback;
import pl.artsit.flexgoals.model.goal.FinalGoal;
import pl.artsit.flexgoals.model.goal.QuantitativeGoal;
import pl.artsit.flexgoals.ui.main.MainViewModel;

public class GoalFinalFragment extends Fragment implements GoalGetCallback {

    private MainViewModel mainViewModel;
    private RecyclerView finalGoalRecyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mainViewModel =
                new ViewModelProvider(this).get(MainViewModel.class);
        View root = inflater.inflate(R.layout.fragment_goal_final, container, false);

        finalGoalRecyclerView = root.findViewById(R.id.final_goals_recycleview);
        finalGoalRecyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        finalGoalRecyclerView.setHasFixedSize(true);
        new HttpClient().getFinalGoals(this, MainActivity.currentUser);

        return root;
    }

    @Override
    public void informAboutFailedGetFinalGoals() {

    }

    @Override
    public void informAboutEmptyFinalGoals() {

    }

    @Override
    public void informAboutFailedGetQuantitativeGoals() {

    }

    @Override
    public void drawQuantitativeGoals(QuantitativeGoal[] quantitativeGoals) {

    }

    @Override
    public void drawFinalGoals(FinalGoal[] finalGoals) {
        FinalGoalsAdapter finalGoalsAdapter = new FinalGoalsAdapter(finalGoals);

        finalGoalRecyclerView.setAdapter(finalGoalsAdapter);
        finalGoalRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void informAboutEmptyQuantitativeGoals() {

    }
}