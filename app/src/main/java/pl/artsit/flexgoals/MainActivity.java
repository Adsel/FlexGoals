package pl.artsit.flexgoals;

import android.animation.ArgbEvaluator;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

import pl.artsit.flexgoals.http.HttpClient;
import pl.artsit.flexgoals.http.user.UserCallback;
import pl.artsit.flexgoals.mainslider.ImageAdapter;
import pl.artsit.flexgoals.mainslider.Model;
import pl.artsit.flexgoals.model.user.User;
import pl.artsit.flexgoals.ui.auth.LoginActivity;

public class MainActivity extends AppCompatActivity implements UserCallback {
    private AppBarConfiguration mAppBarConfiguration;
    public static User currentUser;
    public static boolean isUser = false;
    public static MainActivity activity;
    private DrawerLayout drawer;
    ViewPager viewPager;
    ImageAdapter adapter;
    List<Model> models;
    Integer[] colors = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(MainActivity.isUser) {
            activity = this;
            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            getSupportActionBar().hide();
//            FloatingActionButton fab = findViewById(R.id.fab);
//            fab.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                            .setAction("Action", null).show();
//                }
//            });

            NavigationView navigationViewOld = findViewById(R.id.nav_view);
            NavController navControllerOld = Navigation.findNavController(this, R.id.nav_host_fragment);
//            NavigationUI.setupActionBarWithNavController(this, navControllerOld, mAppBarConfiguration);
            NavigationUI.setupWithNavController(navigationViewOld, navControllerOld);


            drawer = findViewById(R.id.drawer_layout);
            NavigationView navigationView = findViewById(R.id.nav_view);
            NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);

            mAppBarConfiguration = new AppBarConfiguration.Builder(
                    R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                    .setDrawerLayout(drawer)
                    .build();

            new HttpClient().getUserPoints(this, currentUser);
            new HttpClient().getFinalGoals(currentUser);
            new HttpClient().getQuantitativeGoals(currentUser);


            navController.navigate(R.id.nav_add_goal);

        } else {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        }

        //View Pager-------------------------------------
        models = new ArrayList<>();
        models.add(new Model(R.drawable.mike,"Inspiracja","Z NAMI ODKRYJESZ SIEBIE! \n\nZnajdź z nami to co cię napedza. "));
        models.add(new Model(R.drawable.image_five,"Sport","Poszerzaj swoje granice"));
        models.add(new Model(R.drawable.lora,"Organizer","Twój osobisty menadzer, bedzie pomocnikiem w skutecznym planowaniu dnia"));
        models.add(new Model(R.drawable.image_four,"Motywacja","Zmień swojeń życie na lepsze z aplikacją Flexgo. \n \n POMOŻEMY CI SPEŁNIĆ MARZENIA!!"));
        models.add(new Model(R.drawable.chester_wade,"Cele"," Codzienne przypomnienie o twoich celach pomoże ci nie zapomniec do kąd zmierzasz "));

        //adapter = new Adapter(models,MainActivity.this);
        adapter = new ImageAdapter(models, MainActivity.this);
        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
        viewPager.setPadding(130,0,130,0);

        Integer[] colors_temp = {
                getResources().getColor(R.color.color_one),
                getResources().getColor(R.color.color_two),
                getResources().getColor(R.color.color_three),
                getResources().getColor(R.color.color_four),
                getResources().getColor(R.color.color_five)
        };
        colors = colors_temp;
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener(){
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if(position < (adapter.getCount() - 1) && position < (colors.length - 1 )){
                    viewPager.setBackgroundColor((Integer) argbEvaluator.evaluate(
                            positionOffset,
                            colors[position],
                            colors[position + 1]
                            )
                    );
                }else {
                    viewPager.setBackgroundColor(colors[colors.length - 1]);
                }
            }
            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
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

    public void MainSlider(){


    }

}