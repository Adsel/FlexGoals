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
import pl.artsit.flexgoals.http.services.FinalGoalService;
import pl.artsit.flexgoals.http.goals.GoalGetCallback;
import pl.artsit.flexgoals.model.goal.finals.FinalGoalFlag;
import pl.artsit.flexgoals.model.goal.quantitative.QuantitativeGoalFlag;
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

        new FinalGoalService().getFinalGoals(this, MainActivity.currentUser);

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
    public void drawQuantitativeGoals(QuantitativeGoalFlag[] quantitativeGoals) {

    }

    @Override
    public void drawFinalGoals(FinalGoalFlag[] finalGoals) {
        FinalGoalsAdapter finalGoalsAdapter = new FinalGoalsAdapter(finalGoals);
        finalGoalRecyclerView.setAdapter(finalGoalsAdapter);
        finalGoalRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void informAboutEmptyQuantitativeGoals() {

    }

    @Override
    public void informAboutFailedPreview(String flag) {

    }

    @Override
    public void drawQuantitativeProgress() {

    }

    @Override
    public void drawFinalProgress() {

    }
}