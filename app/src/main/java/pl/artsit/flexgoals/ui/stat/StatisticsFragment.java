package pl.artsit.flexgoals.ui.stat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import pl.artsit.flexgoals.MainActivity;
import pl.artsit.flexgoals.R;
import pl.artsit.flexgoals.http.services.FinalGoalService;
import pl.artsit.flexgoals.http.goals.GoalGetCallback;
import pl.artsit.flexgoals.http.services.QuantitativeGoalService;
import pl.artsit.flexgoals.model.goal.finals.FinalGoalFlag;
import pl.artsit.flexgoals.model.goal.quantitative.QuantitativeGoalFlag;
import pl.artsit.flexgoals.shared.Helper;

public class StatisticsFragment extends Fragment implements GoalGetCallback {
    private TextView statisticQuantityCount;
    private TextView statisticFinalCount;

    public View onCreateView(@NonNull LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_statistic, container, false);

        ((TextView) root.findViewById(R.id.textViewStatisticUsername)).setText(MainActivity.currentUser.getLogin());
        ((TextView) root.findViewById(R.id.textViewStatisticEmail)).setText(MainActivity.currentUser.getEmail());
        ((TextView) root.findViewById(R.id.textViewStatisticPointsU)).setText(MainActivity.currentUser.getPoints().toString());

        statisticQuantityCount = root.findViewById(R.id.StatisticQuantityCount);
        statisticFinalCount = root.findViewById(R.id.StatisticFinalCount);

        (root.findViewById(R.id.buttonF)).setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.nav_final_goals);
        });

        (root.findViewById(R.id.buttonQ)).setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.nav_quantitative_goals);
        });

        new FinalGoalService().getFinalGoals(this, MainActivity.currentUser);
        new QuantitativeGoalService().getQuantitativeGoals(this, MainActivity.currentUser);

        return root;
    }

    @Override
    public void informAboutFailedGetFinalGoals() {

    }

    @Override
    public void drawFinalGoals(FinalGoalFlag[] finalGoals) {
        Integer left = 0;

        for (FinalGoalFlag goal: finalGoals) {
            if (goal.getFlag() != Helper.GOAL_FINISHED) {
                left++;
            }
        }

        statisticFinalCount.setText(left.toString());
    }

    @Override
    public void informAboutEmptyFinalGoals() {

    }

    @Override
    public void informAboutFailedGetQuantitativeGoals() {

    }

    @Override
    public void drawQuantitativeGoals(QuantitativeGoalFlag[] quantitativeGoals) {
        Integer left = 0;

        for (QuantitativeGoalFlag goal: quantitativeGoals) {
            if (goal.getFlag() != Helper.GOAL_FINISHED) {
                left++;
            }
        }

        statisticQuantityCount.setText(left.toString());
    }


    @Override
    public void drawQuantitativeProgress() {
    }

    @Override
    public void informAboutEmptyQuantitativeGoals() {

    }

    @Override
    public void informAboutFailedPreview(String flag) {

    }

    @Override
    public void drawFinalProgress() {

    }
}