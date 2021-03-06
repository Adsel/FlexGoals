package pl.artsit.flexgoals.http.services;

import pl.artsit.flexgoals.http.api.JsonPlaceholderAPI;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import pl.artsit.flexgoals.model.goal.finals.FinalGoalFlag;
import pl.artsit.flexgoals.model.goal.quantitative.QuantitativeGoalFlag;
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
    public ArrayList<FinalGoalFlag> sortedFinalGoals(FinalGoalFlag[]  finalGoalFlags){
        ArrayList<FinalGoalFlag> readyDayGoals = null;
        ArrayList<FinalGoalFlag> unreadyDayGoals = null;
        ArrayList <FinalGoalFlag> allListSorted = null;

        for(int i =0 ; i< finalGoalFlags.length;i++){
            if(finalGoalFlags[i].getFlag()== -1){
                unreadyDayGoals.add(finalGoalFlags[i]);
            }else readyDayGoals.add(finalGoalFlags[i]);
        }
        allListSorted.addAll(unreadyDayGoals);
        allListSorted.addAll(readyDayGoals);
        return  allListSorted;
    }


    public ArrayList<QuantitativeGoalFlag> sortedQuatitativeGoals(QuantitativeGoalFlag[] quantitativeGoalFlag){
        ArrayList<QuantitativeGoalFlag> readyDayGoals = null;
        ArrayList<QuantitativeGoalFlag> unreadyDayGoals = null;
        ArrayList <QuantitativeGoalFlag> allListSorted = null;

        for(int i =0 ; i< quantitativeGoalFlag.length;i++){
            if(quantitativeGoalFlag[i].getFlag()== -1){
                unreadyDayGoals.add(quantitativeGoalFlag[i]);
            }else readyDayGoals.add(quantitativeGoalFlag[i]);
        }
        allListSorted.addAll(unreadyDayGoals);
        allListSorted.addAll(readyDayGoals);
        return  allListSorted;
    }
    //Sprawdzone
    public ArrayList<QuantitativeGoalFlag> sortedQuantitativeGoals( QuantitativeGoalFlag[] QuantitativeGoalFlags){
        ArrayList<QuantitativeGoalFlag> readyDayGoals = new ArrayList<QuantitativeGoalFlag>();
        ArrayList<QuantitativeGoalFlag> unreadyDayGoals = new ArrayList<QuantitativeGoalFlag>();
        ArrayList <QuantitativeGoalFlag> allListSorted = new ArrayList<QuantitativeGoalFlag>();

        for(QuantitativeGoalFlag quantitativeGoalFlag: QuantitativeGoalFlags){
            if(quantitativeGoalFlag.getFlag()== -1){
                unreadyDayGoals.add(quantitativeGoalFlag);
            }else readyDayGoals.add(quantitativeGoalFlag);
        }
        allListSorted.addAll(unreadyDayGoals);
        allListSorted.addAll(readyDayGoals);
        return  allListSorted;
    }

}