package pl.artsit.flexgoals.http.goals;

import pl.artsit.flexgoals.model.goal.FinalGoal;
import pl.artsit.flexgoals.model.goal.QuantitativeGoal;

public interface GoalGetCallback {
    void informAboutFailedGetFinalGoals();
    void drawFinalGoals(FinalGoal[] finalGoals);
    void informAboutEmptyFinalGoals();

    void informAboutFailedGetQuantitativeGoals();
    void drawQuantitativeGoals(QuantitativeGoal[] quantitativeGoals);
    void informAboutEmptyQuantitativeGoals();
}
