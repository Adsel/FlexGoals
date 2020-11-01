package pl.artsit.flexgoals.ui.auth;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import pl.artsit.flexgoals.R;

public class RegisterActivity extends AppCompatActivity {
    TextView textViewLogin;
    TextView textViewPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        textViewLogin= (TextView) findViewById(R.id.textViewLogin);

    }
}