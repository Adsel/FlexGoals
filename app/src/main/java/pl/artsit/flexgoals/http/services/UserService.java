package pl.artsit.flexgoals.http.services;

import pl.artsit.flexgoals.http.user.UserCallback;
import pl.artsit.flexgoals.http.user.UserLoginCallback;
import pl.artsit.flexgoals.http.user.UserRegistryCallback;
import pl.artsit.flexgoals.model.user.AuthData;
import pl.artsit.flexgoals.model.user.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserService extends HttpClient{
    public UserService() {
        super();
    }

    public void getUser(UserLoginCallback userLoginCallback, AuthData authData){
        Call<User> call = jsonPlaceholderAPI.getUser(authData);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (!response.isSuccessful()){
                    return;
                }
                User user = response.body();
                if(user != null) {
                    userLoginCallback.saveUserCredentials(authData.getLogin(), authData.getPassword());
                    userLoginCallback.redirectToMain(user);
                } else {
                    userLoginCallback.informAboutFailedLogin();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                userLoginCallback.informAboutFailedLogin();
            }
        });
    }

    public void registerUser(UserRegistryCallback userRegistryCallback, User user){
        Call<User> call = jsonPlaceholderAPI.registerUser(user);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (!response.isSuccessful()){
                    userRegistryCallback.informAboutFailedRegistered();
                    return;
                }

                userRegistryCallback.informAboutSuccessfulRegistered();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                userRegistryCallback.informAboutFailedRegistered();
            }
        });
    }

    public void getUserPoints(UserCallback userCallback, User user){
        Call<Integer> call = jsonPlaceholderAPI.getUserPoints(user.getId());

        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if (!response.isSuccessful()){
                    return;
                }
                Integer points = response.body();
                if(points != null) {
                    userCallback.setPoints(points);
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {

            }
        });
    }
}
