package pl.artsit.flexgoals.http;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import pl.artsit.flexgoals.model.user.AuthData;
import pl.artsit.flexgoals.model.user.User;
import pl.artsit.flexgoals.ui.auth.LoginActivity;
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
    private LoginActivity loginActivity;

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
                    ref.redirectToMain();
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
}
