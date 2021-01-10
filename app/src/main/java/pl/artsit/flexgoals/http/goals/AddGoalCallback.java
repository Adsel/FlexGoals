package pl.artsit.flexgoals.http.goals;

import pl.artsit.flexgoals.model.goal.finals.FinalGoal;
import pl.artsit.flexgoals.model.goal.quantitative.QuantitativeGoal;

public interface AddGoalCallback {
    void onAddedFinalGoalCallback(FinalGoal finalGoalData);
    void onAddedQuantitativeGoalCallback(QuantitativeGoal quantitativeGoalData);
}
