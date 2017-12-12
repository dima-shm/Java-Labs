package shm.dim.lab_8_9;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class ChangeUserInformationActivity extends AppCompatActivity {

    private EditText mName, mGroup, mAddInfo;
    private ImageView mProfileImage;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_user_information);

        mName = findViewById(R.id.editText_name_and_surname);
        mGroup = findViewById(R.id.editText_group);
        mAddInfo = findViewById(R.id.editText_add_info);
        mProfileImage = findViewById(R.id.imageView);

        Intent intent = getIntent();

        mName.setText(intent.getStringExtra("userName") + " " +intent.getStringExtra("userSurname"));
        mGroup.setText(intent.getStringExtra("userGroup"));
        mAddInfo.setText(intent.getStringExtra("userAddInfo"));

        Glide.with(getApplicationContext())
                .load(intent.getStringExtra("userPhotoUri"))
                .into(mProfileImage);
    }
}