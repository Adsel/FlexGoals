package pl.artsit.flexgoals.http.services;

import pl.artsit.flexgoals.http.goals.AddGoalCallback;
import pl.artsit.flexgoals.http.goals.DeleteGoalCallback;
import pl.artsit.flexgoals.http.goals.GoalAchieveCallback;
import pl.artsit.flexgoals.http.goals.GoalGetCallback;
import pl.artsit.flexgoals.http.goals.GoalUpdateCallback;
import pl.artsit.flexgoals.http.user.UserCallback;
import pl.artsit.flexgoals.model.goal.quantitative.PredefinedQuantitativeGoal;
import pl.artsit.flexgoals.model.goal.quantitative.QuantitativeGoal;
import pl.artsit.flexgoals.model.goal.quantitative.QuantitativeGoalData;
import pl.artsit.flexgoals.model.goal.quantitative.QuantitativeGoalFlag;
import pl.artsit.flexgoals.model.goal.quantitative.QuantitativeGoalProgress;
import pl.artsit.flexgoals.model.user.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuantitativeGoalService extends HttpClient {
    public QuantitativeGoalService() {
        super();
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

    public void deleteQuantitativeGoal(DeleteGoalCallback deleteGoalCallback, QuantitativeGoalFlag quantitativeGoal){
        Call<Void> call = jsonPlaceholderAPI.deleteQuantitativeGoal(quantitativeGoal.getId());

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()){
                    System.out.println("Unsuccessfull response code" + response.message());
                    return;
                }

                deleteGoalCallback.deleteQuantitativeCallback(quantitativeGoal);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                deleteGoalCallback.informAboutFailedDeleteQuantitativeGoal();
            }
        });
    }

    public void scoreQuantitativeGoal(GoalAchieveCallback achieveCallback, QuantitativeGoalProgress quantitativeGoalProgress) {
        Call<Integer> call = jsonPlaceholderAPI.scoreQuantitativeGoal(quantitativeGoalProgress);

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
