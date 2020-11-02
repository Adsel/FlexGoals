package pl.artsit.flexgoals.ui.auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.graphics.drawable.DrawableCompat;

import android.annotation.SuppressLint;
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
            editTextPasswordRepeat.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_lock_solid, 0, R.drawable.ic_times_circle_solid, 0);
            return false;
        } else {
            textViewPassword.setVisibility(TextView.INVISIBLE);
            editTextPasswordRepeat.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_lock_solid, 0, 0, 0);

            return true;
        }
    }

    private boolean checkLogin(String a){
        System.out.println("XDDD: " + a);
        if(login.equals("")){
            textViewLogin.setVisibility(TextView.VISIBLE);
            editTextLogin.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_user_solid, 0, R.drawable.ic_times_circle_solid, 0);
            return false;
        } else {
            textViewLogin.setVisibility(TextView.INVISIBLE);
            editTextLogin.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_user_solid,0, R.drawable.ic_check_circle_solid, 0);
            return true;
        }

    }

    @SuppressLint("ClickableViewAccessibility")
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

        editTextLogin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                login = editTextLogin.getText().toString();
                checkLogin(login);
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
                if(login.equals("")){
                    textViewLogin.setVisibility(TextView.VISIBLE);
                    textViewLogin.setText(R.string.loginIsEmpty);
                } else {
                    textViewLogin.setVisibility(TextView.INVISIBLE);
                }


            }
        });

        buttonRegister.setOnClickListener(view -> {
            if (checkPasswords(password,passwordRepeat) && !login.equals("")){
                User user = new User(null,password,login,null,null);
                new HttpClient().registerUser(user);
            }
        });



    }
}