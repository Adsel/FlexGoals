package pl.artsit.flexgoals.ui.addGoals;

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
import pl.artsit.flexgoals.http.services.HttpClient;
import pl.artsit.flexgoals.http.goals.GoalGetCallback;
import pl.artsit.flexgoals.model.goal.FinalGoalFlag;
import pl.artsit.flexgoals.model.goal.QuantitativeGoalFlag;
import pl.artsit.flexgoals.ui.main.MainViewModel;

public class GoalQuantitativeFragment extends Fragment implements GoalGetCallback {

    private MainViewModel mainViewModel;
    private RecyclerView quantitativeGoalRecyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mainViewModel =
                new ViewModelProvider(this).get(MainViewModel.class);
        View root = inflater.inflate(R.layout.fragment_goal_quantitative, container, false);

        quantitativeGoalRecyclerView = root.findViewById(R.id.quantitative_goals_recycleview);
        quantitativeGoalRecyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        quantitativeGoalRecyclerView.setHasFixedSize(true);
        new HttpClient().getQuantitativeGoals(this, MainActivity.currentUser);

        return root;
    }

    @Override
    public void informAboutFailedGetFinalGoals() {

    }

    @Override
    public void drawFinalGoals(FinalGoalFlag[] finalGoals) {
    }

    @Override
    public void informAboutEmptyFinalGoals() {

    }

    @Override
    public void informAboutFailedGetQuantitativeGoals() {

    }

    @Override
    public void drawQuantitativeGoals(QuantitativeGoalFlag[] quantitativeGoals) {
        QuantitativeGoalsAdapter quantitativeGoalsAdapter = new QuantitativeGoalsAdapter(quantitativeGoals);

        quantitativeGoalRecyclerView.setAdapter(quantitativeGoalsAdapter);
        quantitativeGoalRecyclerView.setVisibility(View.VISIBLE);
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