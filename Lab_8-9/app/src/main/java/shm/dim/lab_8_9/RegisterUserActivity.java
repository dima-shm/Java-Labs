package shm.dim.lab_8_9;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;

public class RegisterUserActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mEmail, mPassword, mRepeatPassword;
    private ProgressBar mProgressBar;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        mEmail = findViewById(R.id.editText_email);
        mPassword = findViewById(R.id.editText_password);
        mRepeatPassword =findViewById(R.id.editText_repeat_password);
        mProgressBar = findViewById(R.id.progressBar);
        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.button_sign_up).setOnClickListener(this);
        findViewById(R.id.textView_login).setOnClickListener(this);
    }


    private boolean emailIsCorrect(String email) {
        if(email.isEmpty()) {
            mEmail.setError("Введите email");
            mEmail.requestFocus();
            return false;
        } else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmail.setError("Введите корректный email");
            mEmail.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    private boolean passwordIsCorrect(String password) {
        if(password.isEmpty()) {
            mPassword.setError("Введите пароль");
            mPassword.requestFocus();
            return false;
        } else if(password.length() < 6) {
            mPassword.setError("Минимальная длина пароля составляет 6 симолов");
            mPassword.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    private void registerUser() {
        String email = mEmail.getText().toString().trim();
        String password = mPassword.getText().toString().trim();
        String repeatPassword = mRepeatPassword.getText().toString().trim();
        if(!emailIsCorrect(email) ||
           !passwordIsCorrect(password) ||
           !passwordIsCorrect(repeatPassword)) {
            return;
        }
        if (!password.equals(repeatPassword)) {
            Toast.makeText(getApplicationContext(), "Пароли не совпадают", Toast.LENGTH_SHORT).show();
            return;
        }

        mProgressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                mProgressBar.setVisibility(View.GONE);
                if(task.isSuccessful()) {
                    startActivity(new Intent(RegisterUserActivity.this, CreateUserProfileActivity.class));
                    finish();
                } else {
                    if(task.getException() instanceof FirebaseAuthUserCollisionException) {
                        Toast.makeText(getApplicationContext(), "Вы уже зарегистрировались", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_sign_up:
                registerUser();
                break;
            case R.id.textView_login:
                startActivity(new Intent(this, MainActivity.class).setFlags(FLAG_ACTIVITY_CLEAR_TOP));
                break;
        }
    }
}