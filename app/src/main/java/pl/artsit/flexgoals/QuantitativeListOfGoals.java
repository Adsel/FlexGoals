package pl.artsit.flexgoals;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import pl.artsit.flexgoals.http.HttpClient;
import pl.artsit.flexgoals.model.goal.QuantitativeGoal;
import pl.artsit.flexgoals.model.user.User;
import pl.artsit.flexgoals.ui.QuantitativeGoalsAdapter;
import pl.artsit.flexgoals.ui.auth.LoginActivity;


public class QuantitativeListOfGoals extends AppCompatActivity {

    private ArrayList<String> goalNames = new ArrayList<>();
    Intent intent;
    QuantitativeGoalsAdapter adapter;
    List<QuantitativeGoal> models;
    RecyclerView recyclerViewProfile;
    private RecyclerView.LayoutManager layoutManager;
    Context context;
    private Integer id;
    LoginActivity loginActivity;
    User user;
    HttpClient httpClient;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_goal);
        intent = getIntent();
        id = intent.getIntExtra(String.valueOf(user.getId()),1);
        try {
            httpClient.getQuantitativeGoals(user);
            System.out.println(httpClient.getQuantitativeGoals(user));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}

