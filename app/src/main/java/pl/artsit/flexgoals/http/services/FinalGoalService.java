package pl.artsit.flexgoals.http.services;

import pl.artsit.flexgoals.http.goals.AddGoalCallback;
import pl.artsit.flexgoals.http.goals.DeleteGoalCallback;
import pl.artsit.flexgoals.http.goals.GoalAchieveCallback;
import pl.artsit.flexgoals.http.goals.GoalGetCallback;
import pl.artsit.flexgoals.http.goals.GoalUpdateCallback;
import pl.artsit.flexgoals.http.user.UserCallback;
import pl.artsit.flexgoals.model.goal.finals.FinalGoal;
import pl.artsit.flexgoals.model.goal.finals.FinalGoalData;
import pl.artsit.flexgoals.model.goal.finals.FinalGoalFlag;
import pl.artsit.flexgoals.model.goal.finals.PredefinedFinalGoal;
import pl.artsit.flexgoals.model.user.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FinalGoalService extends HttpClient{
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

    public void deleteFinalGoal(DeleteGoalCallback deleteFinalCallback, FinalGoalFlag finalGoal){
        Call<Void> call = jsonPlaceholderAPI.deleteFinalGoal(finalGoal.getId());

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()){
                    System.out.println("Unsuccessfull response code" + response.message());
                    return;
                }

                deleteFinalCallback.deleteFinalCallback(finalGoal);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                deleteFinalCallback.informAboutFailedDeleteFinalGoal();
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
