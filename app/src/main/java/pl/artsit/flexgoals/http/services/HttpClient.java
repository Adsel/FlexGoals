package pl.artsit.flexgoals.http.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import pl.artsit.flexgoals.http.JsonPlaceholderAPI;
import pl.artsit.flexgoals.http.goals.AddGoalCallback;
import pl.artsit.flexgoals.http.goals.GoalAchieveCallback;
import pl.artsit.flexgoals.http.goals.GoalUpdateCallback;
import pl.artsit.flexgoals.http.goals.GoalGetCallback;
import pl.artsit.flexgoals.http.user.UserCallback;
import pl.artsit.flexgoals.http.user.UserLoginCallback;
import pl.artsit.flexgoals.http.user.UserRegistryCallback;
import pl.artsit.flexgoals.model.goal.FinalGoal;
import pl.artsit.flexgoals.model.goal.FinalGoalData;
import pl.artsit.flexgoals.model.goal.FinalGoalFlag;
import pl.artsit.flexgoals.model.goal.PredefinedFinalGoal;
import pl.artsit.flexgoals.model.goal.PredefinedQuantitativeGoal;
import pl.artsit.flexgoals.model.goal.QuantitativeGoal;
import pl.artsit.flexgoals.model.goal.QuantitativeGoalData;
import pl.artsit.flexgoals.model.goal.QuantitativeGoalFlag;
import pl.artsit.flexgoals.model.user.AuthData;
import pl.artsit.flexgoals.model.user.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class HttpClient {
    protected Retrofit retrofit;
    protected String path = "http://147.135.208.69:8080/"; //8080
    protected JsonPlaceholderAPI jsonPlaceholderAPI;
    protected Gson gson;

    public HttpClient(){
        gson = new GsonBuilder()
                .setLenient()
                .create();
        retrofit = new Retrofit.Builder()
                .baseUrl(this.path)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        jsonPlaceholderAPI = retrofit.create(JsonPlaceholderAPI.class);
    }

    public void saveQuantitativeGoal(GoalUpdateCallback goalUpdateCallback, QuantitativeGoal quantitativeGoal) {
        Call<Integer> call = jsonPlaceholderAPI.updateQuantitativeGoal(quantitativeGoal);

        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if (!response.isSuccessful()){
                    System.out.println("Unsuccessfull response code" + response.message());
                    goalUpdateCallback.informAboutFailed();

                    return;
                }
                goalUpdateCallback.goToMain();
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                goalUpdateCallback.informAboutFailed();
            }
        });
    }

    public void saveFinalGoal(GoalUpdateCallback updateCallback, FinalGoal finalGoal) {
        Call<Integer> call = jsonPlaceholderAPI.updateFinalGoal(finalGoal);

        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if (!response.isSuccessful()){
                    System.out.println("Unsuccessfull response code" + response.message());
                    updateCallback.informAboutFailed();

                    return;
                }

                updateCallback.goToMain();
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                updateCallback.informAboutFailed();
            }
        });
    }

    public void addFinalGoal(AddGoalCallback addGoalCallback, FinalGoalData finalGoal) {
        Call<FinalGoal> call = jsonPlaceholderAPI.addFinalGoal(finalGoal);

        call.enqueue(new Callback<FinalGoal>() {
            @Override
            public void onResponse(Call<FinalGoal> call, Response<FinalGoal> response) {
                if (!response.isSuccessful()){
                    System.out.println("Unsuccessfull response code" + response.message());
                    return;
                }

                addGoalCallback.onAddedFinalGoalCallback(response.body());
            }

            @Override
            public void onFailure(Call<FinalGoal> call, Throwable t) {

            }
        });
    }

    public void addQuantitativeGoal(AddGoalCallback addGoalCallback, QuantitativeGoalData quantitativeGoalData) {
        Call<QuantitativeGoal> call = jsonPlaceholderAPI.addQuantitativeGoal(quantitativeGoalData);

        call.enqueue(new Callback<QuantitativeGoal>() {
            @Override
            public void onResponse(Call<QuantitativeGoal> call, Response<QuantitativeGoal> response) {
                if (!response.isSuccessful()){
                    System.out.println("Unsuccessfull response code" + response.message());
                    return;
                }

                addGoalCallback.onAddedQuantitativeGoalCallback(response.body());
            }

            @Override
            public void onFailure(Call<QuantitativeGoal> call, Throwable t) {

            }
        });
    }

    public void getFinalGoals(GoalGetCallback goalGetCallback, User user){
        Call<FinalGoalFlag[]> call = jsonPlaceholderAPI.getUserFinalGoals(user.getId());

        call.enqueue(new Callback<FinalGoalFlag[]>() {
            @Override
            public void onResponse(Call<FinalGoalFlag[]> call, Response<FinalGoalFlag[]> response) {
                if (!response.isSuccessful()){
                    System.out.println("Unsuccessfull response code" + response.message());
                    goalGetCallback.informAboutFailedGetFinalGoals();
                    return;
                }
                FinalGoalFlag[] goals = response.body();
                if(goals != null) {
                    for(FinalGoalFlag finalG: goals) {
                        System.out.println(finalG);
                    }

                    goalGetCallback.drawFinalGoals(goals);
                } else {
                    goalGetCallback.informAboutEmptyFinalGoals();
                }
            }

            @Override
            public void onFailure(Call<FinalGoalFlag[]> call, Throwable t) {
                goalGetCallback.informAboutFailedGetFinalGoals();
            }
        });
    }


    public void getQuantitativeGoals(GoalGetCallback goalGetCallback, User user){
        Call<QuantitativeGoalFlag[]> call = jsonPlaceholderAPI.getUserQuantitativeGoals(user.getId());

        call.enqueue(new Callback<QuantitativeGoalFlag[]>() {
            @Override
            public void onResponse(Call<QuantitativeGoalFlag[]> call, Response<QuantitativeGoalFlag[]> response) {
                if (!response.isSuccessful()){
                    System.out.println("Unsuccessfull response code" + response.message());
                    goalGetCallback.informAboutFailedGetQuantitativeGoals();
                    return;
                }
                QuantitativeGoalFlag[] goals = response.body();
                if(goals != null) {
                    for(QuantitativeGoalFlag quantitativeGoal: goals) {
                        System.out.println(quantitativeGoal);
                    }
                    goalGetCallback.drawQuantitativeGoals(goals);
                } else {
                    goalGetCallback.informAboutEmptyQuantitativeGoals();
                }
            }

            @Override
            public void onFailure(Call<QuantitativeGoalFlag[]> call, Throwable t) {
                goalGetCallback.informAboutFailedGetQuantitativeGoals();
            }
        });
    }

    public void getQuantitativeGoal(GoalGetCallback goalGetCallback, Integer goalId){
        Call<QuantitativeGoal> call = jsonPlaceholderAPI.getQuantitativeGoal(goalId);

        call.enqueue(new Callback<QuantitativeGoal>() {
            @Override
            public void onResponse(Call<QuantitativeGoal> call, Response<QuantitativeGoal> response) {
                if (!response.isSuccessful()){
                    System.out.println("Unsuccessfull response code" + response.message());
                    goalGetCallback.informAboutFailedPreview("quantitative");
                    return;
                }
                QuantitativeGoal goal = response.body();
                if(goal != null) {
                    goalGetCallback.drawQuantitativeProgress();
                } else {
                    goalGetCallback.informAboutFailedPreview("quantitative");
                }

            }

            @Override
            public void onFailure(Call<QuantitativeGoal> call, Throwable t) {
                goalGetCallback.informAboutFailedPreview("quantitative");
            }
        });
    }

    public void getFinalGoal(GoalGetCallback goalGetCallback, Integer goalId){
        Call<FinalGoal> call = jsonPlaceholderAPI.getFinalGoal(goalId);

        call.enqueue(new Callback<FinalGoal>() {
            @Override
            public void onResponse(Call<FinalGoal> call, Response<FinalGoal> response) {
                if (!response.isSuccessful()){
                    System.out.println("Unsuccessfull response code" + response.message());
                    goalGetCallback.informAboutFailedPreview("final");
                    return;
                }
                FinalGoal goal = response.body();
                if(goal != null) {
                    goalGetCallback.drawFinalProgress();
                } else {
                    goalGetCallback.informAboutFailedPreview("final");
                }

            }

            @Override
            public void onFailure(Call<FinalGoal> call, Throwable t) {
                goalGetCallback.informAboutFailedPreview("final");
            }
        });
    }

    public void getPredefinedFinalGoals() {
        Call<PredefinedFinalGoal[]> call = jsonPlaceholderAPI.getPredefinedFinalGoals();

        call.enqueue(new Callback<PredefinedFinalGoal[]>() {
            @Override
            public void onResponse(Call<PredefinedFinalGoal[]> call, Response<PredefinedFinalGoal[]> response) {
                if (!response.isSuccessful()){
                    System.out.println("Unsuccessfull response code" + response.message());
                    return;
                }
                PredefinedFinalGoal[] points = response.body();

                // TODO: set goals
            }

            @Override
            public void onFailure(Call<PredefinedFinalGoal[]> call, Throwable t) {

            }
        });
    }

    public void getQuantitativeGoals() {
        Call<PredefinedQuantitativeGoal[]> call = jsonPlaceholderAPI.getQuantitativeGoals();

        call.enqueue(new Callback<PredefinedQuantitativeGoal[]>() {
            @Override
            public void onResponse(Call<PredefinedQuantitativeGoal[]> call, Response<PredefinedQuantitativeGoal[]> response) {
                if (!response.isSuccessful()){
                    System.out.println("Unsuccessfull response code" + response.message());
                    return;
                }
                PredefinedQuantitativeGoal[] goals = response.body();

                // TODO: set goals
            }

            @Override
            public void onFailure(Call<PredefinedQuantitativeGoal[]> call, Throwable t) {

            }
        });
    }

    public void deleteFinalGoal(UserCallback userCallback, FinalGoal finalGoal){
        Call<Void> call = jsonPlaceholderAPI.deleteFinalGoal(finalGoal.getId());

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()){
                    System.out.println("Unsuccessfull response code" + response.message());
                    return;
                }

                userCallback.goToMain();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    public void deleteQuantitativeGoal(UserCallback userCallback, QuantitativeGoal quantitativeGoal){
        Call<Void> call = jsonPlaceholderAPI.deleteQuantitativeGoal(quantitativeGoal.getId());

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()){
                    System.out.println("Unsuccessfull response code" + response.message());
                    return;
                }

                userCallback.goToMain();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    public void scoreFinalGoal(GoalAchieveCallback achieveCallback, Integer goalId) {
        Call<Integer> call = jsonPlaceholderAPI.scoreFinalGoal(goalId);

        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if (!response.isSuccessful()){
                    System.out.println("Unsuccessfull response code" + response.message());
                    achieveCallback.informAboutFailedUpdated();

                    return;
                }

                achieveCallback.informAboutGoalUpdated();
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                achieveCallback.informAboutFailedUpdated();
            }
        });
    }
}
