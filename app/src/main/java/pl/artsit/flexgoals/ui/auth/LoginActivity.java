package pl.artsit.flexgoals.ui.auth;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import pl.artsit.flexgoals.MainActivity;
import pl.artsit.flexgoals.R;
import pl.artsit.flexgoals.http.HttpClient;

public class LoginActivity extends AppCompatActivity {
    private HttpClient httpClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // HTTP
        httpClient = new HttpClient();

        // EXAMPLE OF USAGE:
        //        httpClient.registerUser( new User(
        //                0, "qwe", "MARCINEK", 0, "marcinek@gmail.com"
        //        ));


        //        httpClient.getUser(
        //                new AuthData("MARCINEK", "qwe")
        //        );


        Button buttonRegister = findViewById(R.id.registerBtn);
        buttonRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });

        Button buttonHome = findViewById(R.id.homeBtn);
        buttonHome.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                MainActivity.isUser = true;
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}