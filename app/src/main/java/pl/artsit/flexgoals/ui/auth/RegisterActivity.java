package pl.artsit.flexgoals.ui.auth;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import pl.artsit.flexgoals.R;

public class RegisterActivity extends AppCompatActivity {
    TextView textViewLogin;
    TextView textViewPassword;
    EditText editTextLogin;
    EditText editTextPassword;
    EditText editTextPasswordRepeat;
    Button buttonRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        textViewLogin= (TextView) findViewById(R.id.textViewLogin);
        textViewPassword= (TextView) findViewById(R.id.textViewPassword);
        editTextLogin= (EditText) findViewById(R.id.editTextLogin);
        editTextPassword= (EditText) findViewById(R.id.editTextPassword);
        editTextPasswordRepeat= (EditText) findViewById(R.id.editTextPasswordRepeat);
        buttonRegister= (Button) findViewById(R.id.buttonRegister);
    }
}