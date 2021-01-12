package pl.artsit.flexgoals.http.goals;

import pl.artsit.flexgoals.model.goal.finals.FinalGoalFlag;
import pl.artsit.flexgoals.model.goal.quantitative.QuantitativeGoalFlag;

public interface DeleteGoalCallback {
    void deleteFinalCallback(FinalGoalFlag deletedFinalGoal);
    void deleteQuantitativeCallback(QuantitativeGoalFlag deletedQuantitativeGoal);
    void informAboutFailedDeleteFinalGoal();
    void informAboutFailedDeleteQuantitativeGoal();
}
