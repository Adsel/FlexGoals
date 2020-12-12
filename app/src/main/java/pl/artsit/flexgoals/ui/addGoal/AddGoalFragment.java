package pl.artsit.flexgoals.ui.addGoal;

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

import pl.artsit.flexgoals.R;

public class AddGoalFragment extends Fragment {

    private AddGoalViewModel addGoalViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        addGoalViewModel =
                new ViewModelProvider(this).get(AddGoalViewModel.class);
        View root = inflater.inflate(R.layout.fragment_add_goal, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);
        addGoalViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}