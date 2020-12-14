package pl.artsit.flexgoals.ui.main;

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

public class MainFragment extends Fragment implements GoalGetCallback {

    private MainViewModel mainViewModel;
    private RecyclerView finalGoalRecyclerView;
    private RecyclerView quantitativeGoalRecyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mainViewModel =
                new ViewModelProvider(this).get(MainViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);

        HttpClient httpClient = new HttpClient();
//        finalGoalRecyclerView = root.findViewById(R.id.final_goals_recycleview);
//        finalGoalRecyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
//        finalGoalRecyclerView.setHasFixedSize(true);
//        httpClient.getFinalGoals(this, MainActivity.currentUser);

        quantitativeGoalRecyclerView = root.findViewById(R.id.final_goals_recycleview);
        quantitativeGoalRecyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        quantitativeGoalRecyclerView.setHasFixedSize(true);
        httpClient.getQuantitativeGoals(this, MainActivity.currentUser);

        return root;
    }

    @Override
    public void informAboutFailedGetFinalGoals() {

    }

    @Override
    public void drawFinalGoals(FinalGoal[] finalGoals) {
//        FinalGoalsAdapter finalGoalsAdapter = new FinalGoalsAdapter(finalGoals);
//
//        finalGoalRecyclerView.setAdapter(finalGoalsAdapter);
//        finalGoalRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void informAboutEmptyFinalGoals() {

    }

    @Override
    public void informAboutFailedGetQuantitativeGoals() {

    }

    @Override
    public void drawQuantitativeGoals(QuantitativeGoal[] quantitativeGoals) {
        QuantitativeGoalsAdapter quantitativeGoalsAdapter = new QuantitativeGoalsAdapter(quantitativeGoals);

        quantitativeGoalRecyclerView.setAdapter(quantitativeGoalsAdapter);
        quantitativeGoalRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void informAboutEmptyQuantitativeGoals() {

    }
}