package pl.artsit.flexgoals.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import pl.artsit.flexgoals.MainActivity;
import pl.artsit.flexgoals.R;
import pl.artsit.flexgoals.http.HttpClient;
import pl.artsit.flexgoals.model.goal.FinalGoal;
import pl.artsit.flexgoals.model.goal.QuantitativeGoal;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    private RecyclerView finalGoalRecyclerView;
    private RecyclerView quantitativeGoalRecyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);
        galleryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

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