package pl.artsit.flexgoals;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import pl.artsit.flexgoals.http.HttpClient;
import pl.artsit.flexgoals.model.user.User;
import pl.artsit.flexgoals.ui.auth.LoginActivity;

public class MainActivity extends AppCompatActivity implements DrawerLayout.DrawerListener {

    private AppBarConfiguration mAppBarConfiguration;
    public static User currentUser;
    public static boolean isUser = false;
    private ActionBarDrawerToggle actionBarDrawerToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_HIDE_NAVIGATION );
        System.out.println("HEELLO");



        if(MainActivity.isUser) {
            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            FloatingActionButton fab = findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });
            DrawerLayout drawer = findViewById(R.id.drawer_layout);
            drawer.setDrawerListener(this);
            System.out.println("HEELLO");
            System.out.println("HEELLO");

            NavigationView navigationView = findViewById(R.id.nav_view);
            // Passing each menu ID as a set of Ids because each
            // menu should be considered as top level destinations.
            mAppBarConfiguration = new AppBarConfiguration.Builder(
                     R.id.add_goal, R.id.performance_goals,R.id.quantitative_goals,R.id.statistics,R.id.calendar)
                    .setOpenableLayout(drawer)
                    .build();
            NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
            NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
            NavigationUI.setupWithNavController(navigationView, navController);
            new HttpClient(this).getUserPoints(MainActivity.currentUser);

        } else {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            //startActivity(intent);
        }
    }

    @Override
    public void onDrawerOpened(View arg0) {
        System.out.println("open");
    }

    @Override
    public void onDrawerClosed(View arg0) {
        System.out.println("closed");
    }

    @Override
    public void onDrawerSlide(View arg0, float arg1) {
        System.out.println("slide");
    }

    @Override
    public void onDrawerStateChanged(int arg0) {
        System.out.println("chance");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void setPoints(Integer points) {
        // TODO:
        // set points
        // refresh text view with points
        System.out.println("Points: " + points);
    }

}