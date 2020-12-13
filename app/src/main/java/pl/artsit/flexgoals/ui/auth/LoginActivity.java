package pl.artsit.flexgoals.ui.auth;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.graphics.drawable.DrawableCompat;

import pl.artsit.flexgoals.MainActivity;
import pl.artsit.flexgoals.R;
import pl.artsit.flexgoals.http.HttpClient;
import pl.artsit.flexgoals.model.ModalWidgets;
import pl.artsit.flexgoals.model.user.AuthData;
import pl.artsit.flexgoals.model.user.User;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextPassword;
    private EditText editTextLogin;
    private Button buttonLogin;
    private Button buttonRegister;
    private ModalWidgets modalWidgets;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        modalWidgets = new ModalWidgets(getApplicationContext());

         editTextLogin = findViewById(R.id.editTextLogin);
         editTextPassword = findViewById(R.id.editTextPassword);

        buttonLogin = findViewById(R.id.buttonLogin);
        LoginActivity ref = this;
        buttonLogin.setOnClickListener(view -> new HttpClient(ref).getUser(
                new AuthData(editTextLogin.getText().toString(), editTextPassword.getText().toString())
        ));

        buttonRegister = findViewById(R.id.buttonRegister);
        buttonRegister.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
            startActivity(intent);
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
                    }
                    else{

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

    public void redirectToMain(User loggedUser) {
        MainActivity.isUser = true;
        MainActivity.currentUser = loggedUser;
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    public void informAboutFailedLogin() {
        System.out.println("Nieprawidłowe dane logowania");
        modalWidgets.showToast(getString(R.string.incorrect_data));
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context,"Niepoprawne Dane!!", duration);
        toast.show();
    }

}