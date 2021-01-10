package pl.artsit.flexgoals.http.api;

import pl.artsit.flexgoals.model.goal.finals.FinalGoalData;
import pl.artsit.flexgoals.model.goal.finals.FinalGoalFlag;
import pl.artsit.flexgoals.model.goal.finals.PredefinedFinalGoal;
import pl.artsit.flexgoals.model.goal.quantitative.PredefinedQuantitativeGoal;
import pl.artsit.flexgoals.model.goal.finals.FinalGoal;
import pl.artsit.flexgoals.model.goal.quantitative.QuantitativeGoal;
import pl.artsit.flexgoals.model.goal.quantitative.QuantitativeGoalData;
import pl.artsit.flexgoals.model.goal.quantitative.QuantitativeGoalFlag;
import pl.artsit.flexgoals.model.goal.quantitative.QuantitativeGoalProgress;
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

    @PUT("/api/goals/update-prog-fgoal")
    Call<Integer> scoreFinalGoal(@Body Integer integer);

    @PUT("/api/goals/update-qgoal")
    Call<Integer> scoreQuantitativeGoal(@Body QuantitativeGoalProgress quantitativeGoalProgress);

    @GET("/api/goals/final/{userId}")
    Call<FinalGoalFlag[]> getUserFinalGoals(@Path("userId") Integer userId);

    @GET("api/goals/quantitative-id/{id_goal}")
    Call<QuantitativeGoal> getQuantitativeGoal(@Path("id_goal") Integer goalId);

    @GET("api/goals/final-id/{id_goal}")
    Call<FinalGoal> getFinalGoal(@Path("id_goal") Integer goalId);

    @GET("/api/goals/quantitative/{userId}")
    Call<QuantitativeGoalFlag[]> getUserQuantitativeGoals(@Path("userId") Integer userId);

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

    @POST("/api/goals/quantitative-add")
    Call<QuantitativeGoal> addQuantitativeGoal(@Body QuantitativeGoalData quantitativeGoalData);


}
