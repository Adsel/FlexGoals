package pl.artsit.flexgoals.http;

import pl.artsit.flexgoals.model.goal.PredefinedFinalGoal;
import pl.artsit.flexgoals.model.goal.PredefinedQuantitativeGoal;
import pl.artsit.flexgoals.model.user.AuthData;
import pl.artsit.flexgoals.model.user.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Path;

public interface JsonPlaceholderAPI {
    //@GET("api/users/login-user")
    @HTTP(method = "POST", path = "api/users/login-user", hasBody = true)
    Call<User> getUser(@Body AuthData authdata);

    @HTTP(method = "POST", path = "api/users/register-user", hasBody = true)
    Call<User> registerUser(@Body User user);

    @GET("/api/users/points/{userId}")
    Call<Integer> getUserPoints(@Path("userId") Integer userId);

    @GET("/api/goals/predefined-final")
    Call<PredefinedFinalGoal[]> getPredefinedFinalGoals();

    @GET("/api/goals/predefined-quantitative")
    Call<PredefinedQuantitativeGoal[]> getQuantitativeGoals();

    @DELETE("/api/goals/final-delete/{id_goal}")
    Call<Void> deleteFinalGoal(@Path("id_goal") Integer goalId);

    @DELETE("/api/goals/quantitative-delete/{id_goal}")
    Call<Void> deleteQuantitativeGoal(@Path("id_goal") Integer goalId);

}
