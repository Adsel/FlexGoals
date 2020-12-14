package pl.artsit.flexgoals.http.user;

public interface UserRegistryCallback {
    void informAboutFailedRegistered();
    void informAboutSuccessfulRegistered();
}
