package shm.dim.lab_14;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import shm.dim.lab_14.databinding.ActivityLoginBinding;
import shm.dim.lab_14.view_model.LoginViewModel;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityLoginBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_login);

        LoginViewModel viewModel = new LoginViewModel(this);

        binding.setViewModel(viewModel);
    }
}