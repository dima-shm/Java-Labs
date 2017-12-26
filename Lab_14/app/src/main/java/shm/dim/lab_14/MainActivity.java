package shm.dim.lab_14;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import shm.dim.lab_14.databinding.ActivityMainBinding;
import shm.dim.lab_14.model.User;
import shm.dim.lab_14.view_model.MainViewModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        User user = getIntent().getParcelableExtra("extra_user");

        ActivityMainBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_main);

        MainViewModel viewModel = new MainViewModel(this, user);

        binding.setViewModel(viewModel);
    }

    public static Intent newIntent(Context context, User user){
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("extra_user", user);
        return intent;
    }
}