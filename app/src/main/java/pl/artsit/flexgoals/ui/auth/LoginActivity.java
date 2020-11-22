package pl.artsit.flexgoals.ui.auth;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import pl.artsit.flexgoals.MainActivity;
import pl.artsit.flexgoals.R;
import pl.artsit.flexgoals.http.HttpClient;
import pl.artsit.flexgoals.http.JsonPlaceholderAPI;
import pl.artsit.flexgoals.model.user.AuthData;
import pl.artsit.flexgoals.model.user.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.nfc.NfcAdapter.EXTRA_ID;

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

    public void redirectToMain(User loggedUser) {
        MainActivity.isUser = true;
        MainActivity.currentUser = loggedUser;
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    public void informAboutFailedLogin() {
        System.out.println("Nieprawidłowe dane logowania");
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context,"Niepoprawne Dane!!", duration);
        toast.show();
    }

}