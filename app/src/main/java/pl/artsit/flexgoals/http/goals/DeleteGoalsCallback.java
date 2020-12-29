package pl.artsit.flexgoals.http.goals;

import pl.artsit.flexgoals.model.goal.FinalGoal;

public interface DeleteGoalsCallback {

    void deleteFinalCallback(Void finalGoalData);
    void informAboutFailedDeleteFinalGoal();

}
