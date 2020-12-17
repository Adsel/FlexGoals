package pl.artsit.flexgoals.http.goals;

import pl.artsit.flexgoals.model.goal.FinalGoalFlag;
import pl.artsit.flexgoals.model.goal.QuantitativeGoalFlag;

public interface GoalGetCallback {
    void informAboutFailedGetFinalGoals();
    void drawFinalGoals(FinalGoalFlag[] finalGoals);
    void informAboutEmptyFinalGoals();

    void informAboutFailedGetQuantitativeGoals();
    void drawQuantitativeGoals(QuantitativeGoalFlag[] quantitativeGoals);
    void informAboutEmptyQuantitativeGoals();

    void informAboutFailedPreview(String flag);
    void drawQuantitativeProgress();
    void drawFinalProgress();
}
