package pl.artsit.flexgoals.ui.stat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import pl.artsit.flexgoals.MainActivity;
import pl.artsit.flexgoals.R;
import pl.artsit.flexgoals.http.services.HttpClient;
import pl.artsit.flexgoals.http.goals.GoalGetCallback;
import pl.artsit.flexgoals.model.goal.FinalGoalFlag;
import pl.artsit.flexgoals.model.goal.QuantitativeGoalFlag;

public class StatisticsFragment extends Fragment implements GoalGetCallback {
    private TextView statisticQuantityCount;
    private TextView statisticFinalCount;

    public View onCreateView(@NonNull LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_statistic, container, false);

        ((TextView) root.findViewById(R.id.textViewStatisticUsername)).setText(MainActivity.currentUser.getLogin());
        ((TextView) root.findViewById(R.id.textViewStatisticEmail)).setText(MainActivity.currentUser.getEmail());
        ((TextView) root.findViewById(R.id.textViewStatisticPoints)).setText(MainActivity.currentUser.getPoints().toString());

        statisticQuantityCount = root.findViewById(R.id.textViewStatisticPoints);
        statisticFinalCount = root.findViewById(R.id.StatisticFinalCount);

        new HttpClient().getFinalGoals(this, MainActivity.currentUser);
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
    // TODO: after reformating FinalGoalFlag structure
    public void drawQuantitativeGoals(QuantitativeGoalFlag[] quantitativeGoals) {
        Integer left = 0;

//        for (QuantitativeGoal quantitativeGoal: quantitativeGoals) {
//            if (quantitativeGoal.getFlag() != Helper.GOAL_FINISHED) {
//                left++;
//            }
//        }

        statisticQuantityCount.setText(left.toString());
    }


    // TODO: after reformating FinalGoalFlag structure
    @Override
    public void drawQuantitativeProgress() {
        Integer left = 0;

//        for (QuantitativeGoal quantitativeGoal: quantitativeGoals) {
//            if (quantitativeGoal.getFlag() != Helper.GOAL_FINISHED) {
//                left++;
//            }
//        }

        statisticFinalCount.setText(left.toString());
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