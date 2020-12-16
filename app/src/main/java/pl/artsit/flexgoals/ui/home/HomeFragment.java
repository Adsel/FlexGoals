package pl.artsit.flexgoals.ui.home;

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
import pl.artsit.flexgoals.http.goals.GoalPredefinedGetCallback;
import pl.artsit.flexgoals.model.goal.PredefinedFinalGoal;
import pl.artsit.flexgoals.model.goal.PredefinedQuantitativeGoal;

public class HomeFragment extends Fragment implements GoalPredefinedGetCallback {

    private HomeViewModel homeViewModel;
    private RecyclerView predefinedFinalGoalRecyclerView;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
//        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

        predefinedFinalGoalRecyclerView = root.findViewById(R.id.recyclerViewPredefinedFinal);
        predefinedFinalGoalRecyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        predefinedFinalGoalRecyclerView.setHasFixedSize(true);
        new HttpClient().get(this, MainActivity.currentUser);

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void informAboutFailedGetPredefinedFinalGoals() {

    }

    @Override
    public void drawPredefinedFinalGoals(PredefinedFinalGoal[] predefinedFinalGoals) {
        PredefinedFinalGoalsAdapter predefinedGoalsAdapter = new PredefinedFinalGoalsAdapter(predefinedFinalGoals);

        predefinedFinalGoalRecyclerView.setAdapter(predefinedGoalsAdapter);
        predefinedFinalGoalRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void informAboutEmptyPredefinedFinalGoals() {

    }

    @Override
    public void informAboutFailedGetPredefinedQuantitativeGoals() {

    }

    @Override
    public void drawPredefinedQuantitativeGoals(PredefinedQuantitativeGoal[] predefinedQuantitativeGoals){

    }

    @Override
    public void informAboutEmptyPredefinedQuantitativeGoals() {

    }
}