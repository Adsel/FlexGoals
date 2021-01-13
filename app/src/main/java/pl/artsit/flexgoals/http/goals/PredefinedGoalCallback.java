package pl.artsit.flexgoals.http.goals;

import pl.artsit.flexgoals.model.goal.finals.PredefinedFinalGoal;
import pl.artsit.flexgoals.model.goal.quantitative.PredefinedQuantitativeGoal;

public interface PredefinedGoalCallback {
    void informAboutFailed();
    void drawPreFinal(PredefinedFinalGoal[] predefinedFinalGoals);
    void drawPreQuantitative(PredefinedQuantitativeGoal[] predefinedQuantitativeGoals);
}

