package pl.artsit.flexgoals.http.goals;

import pl.artsit.flexgoals.model.goal.FinalGoal;
import pl.artsit.flexgoals.model.goal.QuantitativeGoal;

public interface AddGoalCallback {
    void onAddedFinalGoalCallback(FinalGoal finalGoalData);
    void onAddedQuantitativeGoalCallback(QuantitativeGoal quantitativeGoalData);
}
