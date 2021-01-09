package pl.artsit.flexgoals;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import java.util.Locale;

import pl.artsit.flexgoals.http.services.HttpClient;
import pl.artsit.flexgoals.http.services.UserService;
import pl.artsit.flexgoals.http.user.UserCallback;
import pl.artsit.flexgoals.model.goal.finals.FinalGoalFlag;
import pl.artsit.flexgoals.model.goal.quantitative.QuantitativeGoalFlag;
import pl.artsit.flexgoals.model.user.User;
import pl.artsit.flexgoals.ui.auth.LoginActivity;

public class MainActivity extends AppCompatActivity implements UserCallback {
    private AppBarConfiguration mAppBarConfiguration;
    public static User currentUser;
    public static boolean isUser = false;


    public enum GOAL_TYPE {
        FINAL,
        QUANTITATIVE
    }

    public static GOAL_TYPE previewGoalType;
    public static QuantitativeGoalFlag previewQuantitativeGoal;
    public static FinalGoalFlag previewFinalGoal;
    private DrawerLayout drawer;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO
        setLocale(this,"EN");

        if(MainActivity.isUser) {
            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            getSupportActionBar().hide();

            NavigationView navigationView = findViewById(R.id.nav_view);
            NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
            NavigationUI.setupWithNavController(navigationView, navController);

            drawer = findViewById(R.id.drawer_layout);

            mAppBarConfiguration = new AppBarConfiguration.Builder(
                    R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                    .setDrawerLayout(drawer)
                    .build();

            new UserService().getUserPoints(this, currentUser);
        } else {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        ((TextView) findViewById(R.id.userName)).setText(currentUser.getLogin());
        ((TextView) findViewById(R.id.userEmail)).setText(currentUser.getEmail());
        ((TextView) findViewById(R.id.userPoints)).setText(currentUser.getPoints().toString());

        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void setPoints(Integer points) {
        currentUser.setPoints(points);
    }

    public void goToMain() {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

    public void toggleDrawer(View view) {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            drawer.openDrawer(GravityCompat.START);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static void setLocale(Activity activity, String languageCode) {
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);
        Resources resources = activity.getResources();
        Configuration config = resources.getConfiguration();
        config.setLocale(locale);
        resources.updateConfiguration(config, resources.getDisplayMetrics());
    }
}


