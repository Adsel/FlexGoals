package pl.artsit.flexgoals.http.goals;

import pl.artsit.flexgoals.model.user.User;

public interface UserLoginCallback {
    void redirectToMain(User user);
    void informAboutFailedLogin( );
}
