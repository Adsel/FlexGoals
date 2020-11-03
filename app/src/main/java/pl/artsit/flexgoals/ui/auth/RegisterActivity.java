package pl.artsit.flexgoals.ui.auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.graphics.drawable.DrawableCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pl.artsit.flexgoals.R;
import pl.artsit.flexgoals.model.user.User;
import pl.artsit.flexgoals.http.HttpClient;

public class RegisterActivity extends AppCompatActivity {
    TextView textViewLogin;
    TextView textViewMail;
    TextView textViewPassword;
    EditText editTextLogin;
    EditText editTextMail;
    EditText editTextPassword;
    EditText editTextPasswordRepeat;
    Button buttonRegister;

    String login = "";
    String mail = "";
    String password = "";
    String passwordRepeat = "";
    public static final Pattern emailRegex =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    private boolean checkPasswords(){
        if (!password.equals(passwordRepeat) && !password.equals("") && !passwordRepeat.equals("")){
            textViewPassword.setVisibility(TextView.VISIBLE);
            textViewPassword.setText(R.string.passwordNotSame);
            editTextPasswordRepeat.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_lock_solid, 0, R.drawable.ic_times_circle_solid, 0);
            return false;
        } else {
            textViewPassword.setVisibility(TextView.INVISIBLE);
            editTextPasswordRepeat.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_lock_solid, 0, 0, 0);

            return true;
        }
    }

    private boolean checkMail(){
        Matcher matcher = emailRegex.matcher(mail);
        if(mail.equals("")){
            textViewMail.setVisibility(TextView.VISIBLE);
            textViewMail.setText(R.string.mailIsEmpty);
        } else if (!matcher.find()){
            textViewMail.setVisibility(TextView.VISIBLE);
            textViewMail.setText(R.string.mailIncorrect);
        } else {
            textViewMail.setVisibility(TextView.INVISIBLE);
            editTextMail.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_email_24, 0, R.drawable.ic_check_circle_solid, 0);

            return true;
        }
        editTextMail.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_email_24, 0, R.drawable.ic_times_circle_solid, 0);

        return false;
    }

    private boolean checkLogin(){
        if(login.equals("")){
            textViewLogin.setVisibility(TextView.VISIBLE);
            editTextLogin.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_user_solid, 0, R.drawable.ic_times_circle_solid, 0);

        } else {
            editTextLogin.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_user_solid,0, R.drawable.ic_check_circle_solid, 0);
            textViewLogin.setVisibility(TextView.INVISIBLE);
            return true;
        }
        return false;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            this.getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        setContentView(R.layout.activity_register);
        textViewLogin= (TextView) findViewById(R.id.textViewLogin);
        textViewMail= (TextView) findViewById(R.id.textViewMail);
        textViewPassword= (TextView) findViewById(R.id.textViewPassword);
        editTextLogin= (EditText) findViewById(R.id.editTextLogin);
        editTextMail = (EditText) findViewById(R.id.editTextMail);
        editTextPassword= (EditText) findViewById(R.id.editTextPassword);
        editTextPasswordRepeat= (EditText) findViewById(R.id.editTextPasswordRepeat);
        buttonRegister= (Button) findViewById(R.id.buttonRegister);


        editTextLogin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                login = editTextLogin.getText().toString();
                checkLogin();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        editTextPasswordRepeat.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                passwordRepeat = editTextPasswordRepeat.getText().toString();
                checkPasswords();

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
                checkPasswords();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        editTextPassword.setOnTouchListener((v, event) -> {
            final int DRAWABLE_RIGHT = 2;
            if(event.getAction() == MotionEvent.ACTION_UP) {
                if(event.getRawX() >= (editTextPassword.getRight() - editTextPassword.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {

                    Drawable unwrappedDrawable = AppCompatResources.getDrawable(getBaseContext(), R.drawable.ic_eye_solid);
                    Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);

                    if(editTextPassword.getTransformationMethod().equals(HideReturnsTransformationMethod.getInstance())){

                        DrawableCompat.setTint(wrappedDrawable, getResources().getColor(R.color.colorAccent));
                        editTextPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        editTextPasswordRepeat.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    }
                    else{

                        DrawableCompat.setTint(wrappedDrawable, getResources().getColor(R.color.colorPrimary));
                        editTextPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        editTextPasswordRepeat.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                    }
                    editTextPassword.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_lock_solid, 0, R.drawable.ic_eye_solid, 0);

                    return true;
                }
                else {
                }
            }
            return false;
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



            }
        });
        editTextMail.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {}
            @Override
            public void beforeTextChanged(CharSequence s, int start,int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                mail = editTextMail.getText().toString();
                checkMail();



            }
        });

        buttonRegister.setOnClickListener(view -> {
            System.out.println(password+login+mail);
            if (checkPasswords() && checkLogin() && checkMail()) {
                User user = new User(0,password,login,0,mail);
                new HttpClient().registerUser(user);
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

    }
}