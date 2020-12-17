package pl.artsit.flexgoals.http.goals;

public interface GoalAchieveCallback {
    void informAboutGoalUpdated();
    void informAboutFailedUpdated();
}
