package pl.artsit.flexgoals.http;

import pl.artsit.flexgoals.model.goal.FinalGoal;
import pl.artsit.flexgoals.model.goal.QuantitativeGoal;
import pl.artsit.flexgoals.model.user.AuthData;
import pl.artsit.flexgoals.model.user.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Path;

public interface JsonPlaceholderAPI {
    @HTTP(method = "POST", path = "api/users/login-user", hasBody = true)
    Call<User> getUser(@Body AuthData authdata);

    @HTTP(method = "POST", path = "api/users/register-user", hasBody = true)
    Call<User> registerUser(@Body User user);

    @GET("/api/users/points/{userId}")
    Call<Integer> getUserPoints(@Path("userId") Integer userId);

    @GET("/api/goals/final/{userId}")
    Call<FinalGoal[]> getUserFinalGoals(@Path("userId") Integer userId);

    @GET("/api/goals/quantitative/{userId}")
    Call<QuantitativeGoal[]> getUserQuantitativeGoals(@Path("userId") Integer userId);
}
