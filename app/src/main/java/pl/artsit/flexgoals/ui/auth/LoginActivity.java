package pl.artsit.flexgoals.ui.auth;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.graphics.drawable.DrawableCompat;

import pl.artsit.flexgoals.MainActivity;
import pl.artsit.flexgoals.R;
import pl.artsit.flexgoals.http.services.HttpClient;
import pl.artsit.flexgoals.http.services.UserService;
import pl.artsit.flexgoals.http.user.UserLoginCallback;
import pl.artsit.flexgoals.model.ModalWidgets;
import pl.artsit.flexgoals.model.user.AuthData;
import pl.artsit.flexgoals.model.user.User;

public class LoginActivity extends AppCompatActivity implements UserLoginCallback {

    private EditText editTextPassword;
    private EditText editTextLogin;
    private Button buttonLogin;
    private Button buttonRegister;
    private TextView textViewLogin;
    private TextView textViewPassword;
    private ModalWidgets modalWidgets;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        modalWidgets = new ModalWidgets(getApplicationContext());

        if (!loadUserWhenCredentialsAreSaved()) {
            setContentView(R.layout.activity_login);

            editTextLogin = findViewById(R.id.editTextLogin);
            editTextPassword = findViewById(R.id.editTextPassword);
            textViewLogin = findViewById(R.id.textViewLogin);
            textViewPassword = findViewById(R.id.textViewPassword);
            buttonRegister = findViewById(R.id.buttonRegister);
            buttonLogin = findViewById(R.id.buttonLogin);

            addActions();
        }
    }

    public void redirectToMain(User loggedUser) {
        MainActivity.isUser = true;
        MainActivity.currentUser = loggedUser;
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    public void informAboutFailedLogin() {
        System.out.println("Nieprawidłowe dane logowania");
        modalWidgets.showToast(getString(R.string.incorrect_data));
    }

    private boolean checkPassword(){
        if (editTextPassword.getText().toString().equals("")){
            textViewPassword.setVisibility(TextView.VISIBLE);
            editTextPassword.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_user_solid, 0, R.drawable.ic_times_circle_solid, 0);

            return false;
        }
        textViewPassword.setVisibility(TextView.INVISIBLE);
        editTextPassword.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_user_solid,0, R.drawable.ic_check_circle_solid, 0);

        return true;
    }

    private boolean checkLogin(){
        if (editTextLogin.getText().toString().equals("")){
            textViewLogin.setVisibility(TextView.VISIBLE);
            editTextLogin.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_user_solid, 0, R.drawable.ic_times_circle_solid, 0);

            return false;
        }
        editTextLogin.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_user_solid,0, R.drawable.ic_check_circle_solid, 0);
        textViewLogin.setVisibility(TextView.INVISIBLE);

        return true;
    }

    public void saveUserCredentials(String login, String password) {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("USER_CREDENTIALS_LOGIN", login);
        editor.apply();
        editor.putString("USER_CREDENTIALS_PASSWORD", password);
        editor.apply();
    }

    private boolean loadUserWhenCredentialsAreSaved() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        String login = sharedPreferences.getString("USER_CREDENTIALS_LOGIN", "");
        String password = sharedPreferences.getString("USER_CREDENTIALS_PASSWORD", "");

        if (!login.equals("") && !password.equals("")) {
            new UserService().getUser(this, new AuthData(login, password));
            return true;
        }

        return false;
    }

    private void addActions() {
        editTextPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkPassword();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        editTextLogin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkLogin();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        buttonLogin.setOnClickListener(view -> {
            boolean isCorrectLogin = checkLogin();
            boolean isCorrectPassword = checkPassword();
            if (!isCorrectLogin) {
                modalWidgets.showToast(getString(R.string.login_is_empty));
            }
            if (!isCorrectPassword) {
                modalWidgets.showToast(getString(R.string.password_is_empty));
            }
            if (isCorrectLogin && isCorrectPassword) {
                new UserService().getUser(
                        this,
                        new AuthData(editTextLogin.getText().toString(), editTextPassword.getText().toString())
                );
            }
        });
        buttonRegister.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
            startActivity(intent);
        });
        editTextPassword.setOnTouchListener((v, event) -> {
            final int DRAWABLE_RIGHT = 2;
            if (event.getAction() == MotionEvent.ACTION_UP) {
                if (event.getRawX() >= (editTextPassword.getRight() - editTextPassword.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {

                    Drawable unwrappedDrawable = AppCompatResources.getDrawable(getBaseContext(), R.drawable.ic_eye_solid);
                    Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);

                    if (editTextPassword.getTransformationMethod().equals(HideReturnsTransformationMethod.getInstance())) {
                        DrawableCompat.setTint(wrappedDrawable, getResources().getColor(R.color.colorAccent));
                        editTextPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    } else {
                        DrawableCompat.setTint(wrappedDrawable, getResources().getColor(R.color.colorPrimary));
                        editTextPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    }
                    editTextPassword.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_lock_solid, 0, R.drawable.ic_eye_solid, 0);

                    return true;
                }
            }
            return false;
        });
    }

}