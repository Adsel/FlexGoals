package pl.artsit.flexgoals.http.goals;

import pl.artsit.flexgoals.model.goal.finals.FinalGoalFlag;
import pl.artsit.flexgoals.model.goal.quantitative.QuantitativeGoalFlag;

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
