package pl.artsit.flexgoals.http.user;

import pl.artsit.flexgoals.model.user.User;

public interface UserLoginCallback {
    void redirectToMain(User user);
    void informAboutFailedLogin();
    void saveUserCredentials(String login, String password);
}
