package pl.artsit.flexgoals.http.goals;

public interface GoalGetCallback {
    void informAboutFailedPreview(String flag);
    void drawQuantitativeProgress();
    void drawFinalProgress();
}
