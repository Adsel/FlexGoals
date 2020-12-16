package pl.artsit.flexgoals.http.goals;

import pl.artsit.flexgoals.model.goal.PredefinedFinalGoal;
import pl.artsit.flexgoals.model.goal.PredefinedQuantitativeGoal;

public interface GoalPredefinedGetCallback {
    void informAboutFailedGetPredefinedFinalGoals();
    void drawPredefinedFinalGoals(PredefinedFinalGoal[] predefinedFinalGoals);
    void informAboutEmptyPredefinedFinalGoals();

    void informAboutFailedGetPredefinedQuantitativeGoals();
    void drawPredefinedQuantitativeGoals(PredefinedQuantitativeGoal[] predefinedQuantitativeGoals);
    void informAboutEmptyPredefinedQuantitativeGoals();
}
