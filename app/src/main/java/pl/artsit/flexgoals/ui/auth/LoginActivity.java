package pl.artsit.flexgoals.ui.auth;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import pl.artsit.flexgoals.MainActivity;
import pl.artsit.flexgoals.R;
import pl.artsit.flexgoals.http.HttpClient;
import pl.artsit.flexgoals.model.user.AuthData;

public class LoginActivity extends AppCompatActivity {

    private EditText passwordEditText;
    private EditText nameEditText;
    private Button loginBtn;
    private Button registrationBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

         nameEditText = findViewById(R.id.editTextLogin);
         passwordEditText = findViewById(R.id.editTextPasswordRepeat);

        loginBtn = findViewById(R.id.loginBtn);
        LoginActivity ref = this;
        loginBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                new HttpClient(ref).getUser(
                        new AuthData(nameEditText.getText().toString(), passwordEditText.getText().toString())
                );
            }
        });
        registrationBtn = findViewById(R.id.registrationBtn);
        registrationBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    public void redirectToMain() {
        MainActivity.isUser = true;
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    public void informAboutFailedLogin() {
        // TODO: zrobić do tego Modal/Popup
        System.out.println("Nieprawidłowe dane logowania");
    }
}