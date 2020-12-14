package pl.artsit.flexgoals.http;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import pl.artsit.flexgoals.http.goals.AddGoalCallback;
import pl.artsit.flexgoals.http.goals.UserCallback;
import pl.artsit.flexgoals.http.goals.UserLoginCallback;
import pl.artsit.flexgoals.http.goals.UserRegistryCallback;
import pl.artsit.flexgoals.model.goal.FinalGoal;
import pl.artsit.flexgoals.model.goal.FinalGoalData;
import pl.artsit.flexgoals.model.goal.PredefinedFinalGoal;
import pl.artsit.flexgoals.model.goal.PredefinedQuantitativeGoal;
import pl.artsit.flexgoals.model.goal.QuantitativeGoal;
import pl.artsit.flexgoals.model.goal.QuantitativeGoalData;
import pl.artsit.flexgoals.model.user.AuthData;
import pl.artsit.flexgoals.model.user.User;
import pl.artsit.flexgoals.ui.auth.LoginActivity;
import pl.artsit.flexgoals.ui.main.MainFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class HttpClient {
    private Retrofit retrofit;
    private String path = "http://147.135.208.69:8080/"; //8080
    private JsonPlaceholderAPI jsonPlaceholderAPI;
    private Gson gson;

    public HttpClient(){
        gson = new GsonBuilder()
                .setLenient()
                .create();
        retrofit = new Retrofit.Builder()
                .baseUrl(this.path)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        jsonPlaceholderAPI = retrofit.create(JsonPlaceholderAPI.class);
        System.out.println("DONT WOKRS");
    }

    public void getUser(UserLoginCallback userLoginCallback, AuthData authData){
        Call<User> call = jsonPlaceholderAPI.getUser(authData);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (!response.isSuccessful()){
                    System.out.println("Unsuccessfull response code" + response.message());
                    return;
                }
                User user = response.body();
                if(user != null) {
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

        System.out.println("STRUCTURE OF USER" + user.toString());

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (!response.isSuccessful()){
                    System.out.println("Unsuccessfull response code");
                    userRegistryCallback.informAboutFailedRegistered();
                    return;
                }

                // User user = response.body();
                userRegistryCallback.informAboutSuccessfulRegistered();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                System.out.println("Failed request");
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
                    System.out.println("Unsuccessfull response code" + response.message());
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

    public void saveQuantitativeGoal(QuantitativeGoal quantitativeGoal) {
        Call<Integer> call = jsonPlaceholderAPI.updateQuantitativeGoal(quantitativeGoal);

        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if (!response.isSuccessful()){
                    System.out.println("Unsuccessfull response code" + response.message());
                    return;
                }

                // Retrive success behaviour (ex. Toast)
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {

            }
        });
    }

    public void saveFinalGoal(FinalGoal finalGoal) {
        Call<Integer> call = jsonPlaceholderAPI.updateFinalGoal(finalGoal);

        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if (!response.isSuccessful()){
                    System.out.println("Unsuccessfull response code" + response.message());
                    return;
                }

                // Retrive success behaviour (ex. Toast)
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {

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

    public void getFinalGoals(User user){
        Call<FinalGoal[]> call = jsonPlaceholderAPI.getUserFinalGoals(user.getId());
        MainFragment fragment = this.mainFragment;

        call.enqueue(new Callback<FinalGoal[]>() {
            @Override
            public void onResponse(Call<FinalGoal[]> call, Response<FinalGoal[]> response) {
                if (!response.isSuccessful()){
                    System.out.println("Unsuccessfull response code" + response.message());
                    return;
                }
                FinalGoal[] goals = response.body();
                if(goals != null) {
                    for(FinalGoal finalG: goals) {
                        System.out.println(finalG);
                    }
                    fragment.showGoals(goals);
                }
            }

            @Override
            public void onFailure(Call<FinalGoal[]> call, Throwable t) {

            }
        });
    }


    public void getQuantitativeGoals(User user){
        Call<QuantitativeGoal[]> call = jsonPlaceholderAPI.getUserQuantitativeGoals(user.getId());

        call.enqueue(new Callback<QuantitativeGoal[]>() {
            @Override
            public void onResponse(Call<QuantitativeGoal[]> call, Response<QuantitativeGoal[]> response) {
                if (!response.isSuccessful()){
                    System.out.println("Unsuccessfull response code" + response.message());
                    return;
                }
                QuantitativeGoal[] goals = response.body();
                if(goals != null) {
                    for(QuantitativeGoal quantitativeGoal: goals) {
                        System.out.println(quantitativeGoal);
                    }
                    mainFragment.showQuantitativeGoals(goals);
                }
            }

            @Override
            public void onFailure(Call<QuantitativeGoal[]> call, Throwable t) {

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
}
