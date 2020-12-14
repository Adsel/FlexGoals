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

import pl.artsit.flexgoals.R;
import pl.artsit.flexgoals.http.HttpClient;
import pl.artsit.flexgoals.model.goal.FinalGoal;
import pl.artsit.flexgoals.model.goal.QuantitativeGoal;

public class MainFragment extends Fragment {

    private MainViewModel mainViewModel;
    private RecyclerView finalGoalRecyclerView;
    private RecyclerView quantitativeGoalRecyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mainViewModel =
                new ViewModelProvider(this).get(MainViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);

        HttpClient httpClient = new HttpClient(this);
        finalGoalRecyclerView = root.findViewById(R.id.final_goals_recycleview);
        finalGoalRecyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        finalGoalRecyclerView.setHasFixedSize(true);
        httpClient.getFinalGoals(MainActivity.currentUser);

        quantitativeGoalRecyclerView = root.findViewById(R.id.final_goals_recycleview);
        quantitativeGoalRecyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        quantitativeGoalRecyclerView.setHasFixedSize(true);
        httpClient.getQuantitativeGoals(MainActivity.currentUser);


        return root;
    }

    public void showGoals(FinalGoal[] goals) {
        FinalGoalsAdapter finalGoalsAdapter = new FinalGoalsAdapter(goals);

        finalGoalRecyclerView.setAdapter(finalGoalsAdapter);
        finalGoalRecyclerView.setVisibility(View.VISIBLE);
    }

    public void showQuantitativeGoals(QuantitativeGoal[] quantitativeGoal) {
        QuantitativeGoalsAdapter quantitativeGoalsAdapter = new QuantitativeGoalsAdapter(quantitativeGoal);

        quantitativeGoalRecyclerView.setAdapter(quantitativeGoalsAdapter);
        quantitativeGoalRecyclerView.setVisibility(View.VISIBLE);
    }
}