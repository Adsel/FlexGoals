package pl.artsit.flexgoals.ui.auth;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import pl.artsit.flexgoals.R;
import pl.artsit.flexgoals.model.user.User;
import pl.artsit.flexgoals.http.HttpClient;

public class RegisterActivity extends AppCompatActivity {
    TextView textViewLogin;
    TextView textViewPassword;
    EditText editTextLogin;
    EditText editTextPassword;
    EditText editTextPasswordRepeat;
    Button buttonRegister;

    String login = "";
    String password = "";
    String passwordRepeat = "";

    private boolean checkPasswords(String a,String b){
        if (!a.equals(b) && !a.equals("") && !b.equals("")){
            textViewPassword.setVisibility(TextView.VISIBLE);
            textViewPassword.setText(R.string.passwordNotSame);
            return false;
        } else {
            textViewPassword.setVisibility(TextView.INVISIBLE);
            return true;
        }
    }

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

        editTextPasswordRepeat.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                passwordRepeat = editTextPasswordRepeat.getText().toString();
                checkPasswords(password,passwordRepeat);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        editTextPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                password = editTextPassword.getText().toString();
                checkPasswords(password,passwordRepeat);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        editTextLogin.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {}
            @Override
            public void beforeTextChanged(CharSequence s, int start,int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                login = editTextLogin.getText().toString();
                if(login.equals("")){
                    textViewLogin.setVisibility(TextView.VISIBLE);
                    textViewLogin.setText(R.string.loginIsEmpty);
                } else {
                    textViewLogin.setVisibility(TextView.INVISIBLE);
                }


            }
        });

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkPasswords(password,passwordRepeat) && !login.equals("")){
                    User user = new User(null,password,login,null,null);
                    new HttpClient().registerUser(user);
                }
            }
        });

    }
}