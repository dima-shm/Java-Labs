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
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mEmail, mPassword;
    private ProgressBar mProgressBar;
    private FirebaseAuth mAuth;

    private final String ADMINISTRATION_ID = "AQwmDhExtKeexCaRQI0RGoNRXGh2";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEmail = findViewById(R.id.editText_email);
        mPassword = findViewById(R.id.editText_password);
        mProgressBar = findViewById(R.id.progressBar);
        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.button_login).setOnClickListener(this);
        findViewById(R.id.textView_sign_up).setOnClickListener(this);
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

    private boolean userIsAdmin() {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user.getUid().equals(ADMINISTRATION_ID)) {
            return true;
        } else {
            return false;
        }
    }

    private void userLogin() {
        String email = mEmail.getText().toString().trim();
        String password = mPassword.getText().toString().trim();
        if(!emailIsCorrect(email) || !passwordIsCorrect(password)){
            return;
        }

        mProgressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                mProgressBar.setVisibility(View.GONE);
                if(task.isSuccessful()) {
                    if(userIsAdmin()) {
                        startActivity(new Intent(MainActivity.this, AdministrationActivity.class));
                    } else {
                        startActivity(new Intent(MainActivity.this, UserInformationActivity.class));
                    }
                } else {
                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_login:
                userLogin();
                break;
            case R.id.textView_sign_up:
                startActivity(new Intent(this, RegisterUserActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                break;
        }
    }
}