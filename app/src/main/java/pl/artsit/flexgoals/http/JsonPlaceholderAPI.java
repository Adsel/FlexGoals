package pl.artsit.flexgoals.http;

import pl.artsit.flexgoals.model.goal.FinalGoalData;
import pl.artsit.flexgoals.model.goal.PredefinedFinalGoal;
import pl.artsit.flexgoals.model.goal.PredefinedQuantitativeGoal;
import pl.artsit.flexgoals.model.goal.FinalGoal;
import pl.artsit.flexgoals.model.goal.QuantitativeGoal;
import pl.artsit.flexgoals.model.user.AuthData;
import pl.artsit.flexgoals.model.user.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface JsonPlaceholderAPI {
    @HTTP(method = "POST", path = "api/users/login-user", hasBody = true)
    Call<User> getUser(@Body AuthData authdata);

    @HTTP(method = "POST", path = "api/users/register-user", hasBody = true)
    Call<User> registerUser(@Body User user);

    @GET("/api/users/points/{userId}")
    Call<Integer> getUserPoints(@Path("userId") Integer userId);

    @PUT("/api/goals/update-qgoaldata")
    Call<Integer> updateQuantitativeGoal(@Body QuantitativeGoal quantitativeGoal);

    @PUT("/api/goals/update-fgoal")
    Call<Integer> updateFinalGoal(@Body FinalGoal finalGoal);

    @GET("/api/goals/final/{userId}")
    Call<FinalGoal[]> getUserFinalGoals(@Path("userId") Integer userId);

    @GET("/api/goals/quantitative/{userId}")
    Call<QuantitativeGoal[]> getUserQuantitativeGoals(@Path("userId") Integer userId);

    @GET("/api/goals/predefined-final")
    Call<PredefinedFinalGoal[]> getPredefinedFinalGoals();

    @GET("/api/goals/predefined-quantitative")
    Call<PredefinedQuantitativeGoal[]> getQuantitativeGoals();

    @DELETE("/api/goals/final-delete/{id_goal}")
    Call<Void> deleteFinalGoal(@Path("id_goal") Integer goalId);

    @DELETE("/api/goals/quantitative-delete/{id_goal}")
    Call<Void> deleteQuantitativeGoal(@Path("id_goal") Integer goalId);

    @POST("/api/goals/final-add")
    Call<FinalGoal> addFinalGoal(@Body FinalGoalData finalGoalData);


}
