package pl.artsit.flexgoals.http;

import android.os.Build;
import android.os.Bundle;
import android.text.Layout;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import pl.artsit.flexgoals.MainActivity;
import pl.artsit.flexgoals.R;
import pl.artsit.flexgoals.model.goal.FinalGoal;
import pl.artsit.flexgoals.model.goal.QuantitativeGoal;
import pl.artsit.flexgoals.model.user.AuthData;
import pl.artsit.flexgoals.model.user.User;
import pl.artsit.flexgoals.ui.QuantitativeGoalsAdapter;
import pl.artsit.flexgoals.ui.auth.LoginActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class HttpClient extends AppCompatActivity {
    private Retrofit retrofit;
    private String path = "http://147.135.208.69:8080/"; //8080
    private JsonPlaceholderAPI jsonPlaceholderAPI;
    private Gson gson;
    private LoginActivity loginActivity;
    private MainActivity mainActivity;
    List<QuantitativeGoal> models;
    QuantitativeGoalsAdapter adapter;
    RecyclerView recyclerViewProfile;


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

    public HttpClient(LoginActivity loginActivity) {
        this();
        this.loginActivity = loginActivity;
    }

    public HttpClient(MainActivity mainActivity) {
        this();
        this.mainActivity = mainActivity;
    }

    public void getUser(AuthData authData){
        Call<User> call = jsonPlaceholderAPI.getUser(authData);

        System.out.println("STRUCTURE OF USER" + authData.toString());
        LoginActivity ref = this.loginActivity;
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (!response.isSuccessful()){
                    System.out.println("Unsuccessfull response code" + response.message());
                    return;
                }
                User user = response.body();
                if(user != null) {
                    ref.redirectToMain(user);
                } else {
                    ref.informAboutFailedLogin();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                ref.informAboutFailedLogin();
            }
        });
    }

    public void registerUser(User user){
        Call<User> call = jsonPlaceholderAPI.registerUser(user);

        System.out.println("STRUCTURE OF USER" + user.toString());

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (!response.isSuccessful()){
                    System.out.println("Unsuccessfull response code");
                    return;
                }

                User user = response.body();
                System.out.printf("RESPONSE FROM SERVER" + user.getLogin());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                System.out.println("Failed request");
            }
        });
    }

    public void getUserPoints(User user){
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
                    mainActivity.setPoints(points);
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {

            }
        });
    }

    public void getFinalGoals(User user){
        Call<FinalGoal[]> call = jsonPlaceholderAPI.getUserFinalGoals(user.getId());

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
                }
            }

            @Override
            public void onFailure(Call<FinalGoal[]> call, Throwable t) {

            }
        });
    }

    public QuantitativeGoal[] getQuantitativeGoals(User user) throws IOException {
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
                }
            }

            @Override
            public void onFailure(Call<QuantitativeGoal[]> call, Throwable t) {

            }
        });

        return call.execute().body();
    }



    public void deleteFinalGoal(FinalGoal finalGoal){
        Call<Void> call = jsonPlaceholderAPI.deleteFinalGoal(finalGoal.getId());

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()){
                    System.out.println("Unsuccessfull response code" + response.message());
                    return;
                }

                mainActivity.goToMain();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    public void deleteQuantitativeGoal(QuantitativeGoal quantitativeGoal){
        Call<Void> call = jsonPlaceholderAPI.deleteQuantitativeGoal(quantitativeGoal.getId());

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()){
                    System.out.println("Unsuccessfull response code" + response.message());
                    return;
                }

                mainActivity.goToMain();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }
}
