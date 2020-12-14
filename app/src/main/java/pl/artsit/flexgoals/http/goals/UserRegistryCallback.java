package pl.artsit.flexgoals.http.goals;

public interface UserRegistryCallback {
    void informAboutFailedRegistered();
    void informAboutSuccessfulRegistered();
}
