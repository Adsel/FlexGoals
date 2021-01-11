package pl.artsit.flexgoals.http.services;

import pl.artsit.flexgoals.http.api.JsonPlaceholderAPI;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public abstract class HttpClient {
    protected Retrofit retrofit;
    protected final String PATH = "http://147.135.208.69:8080/";
    protected JsonPlaceholderAPI jsonPlaceholderAPI;
    protected Gson gson;

    public HttpClient(){
        gson = new GsonBuilder()
                .setLenient()
                .create();
        retrofit = new Retrofit.Builder()
                .baseUrl(this.PATH)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        jsonPlaceholderAPI = retrofit.create(JsonPlaceholderAPI.class);
    }
}
