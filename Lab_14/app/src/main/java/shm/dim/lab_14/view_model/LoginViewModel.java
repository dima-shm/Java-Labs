package shm.dim.lab_14.view_model;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import shm.dim.lab_14.MainActivity;
import shm.dim.lab_14.model.User;

public class LoginViewModel {

    private Context context;
    private String login = "";
    private String password = "";


    public LoginViewModel(Context context) {
        this.context = context;
    }


    public void loginAuthentication(View view) {
        if (loginIsCorrect() && passwordIsCorrect()) {
            if (login.equals("qwerty") && password.equals("qwerty")) {
                User user = new User(login, password);
                context.startActivity(MainActivity.newIntent(context, user));
            }
        } else if (login.equals("") || password.equals("")) {
            Toast.makeText(context, "Login or Password can't be empty", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Login = qwerty \nPassword = qwerty", Toast.LENGTH_SHORT).show();
        }
    }


    private boolean loginIsCorrect() {
        return (login.length() >= 6);
    }

    private boolean passwordIsCorrect() {
        return (password.length() >= 6);
    }


    public TextWatcher getLoginUpdate() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                login = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        };
    }

    public TextWatcher getPasswordUpdate() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                password = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        };
    }
}