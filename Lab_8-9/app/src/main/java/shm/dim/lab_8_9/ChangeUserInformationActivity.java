package shm.dim.lab_8_9;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.FirebaseDatabase;

public class ChangeUserInformationActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mName, mSurname, mGroup, mAddInfo;
    private ImageView mProfileImage;
    private Intent intent;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_user_information);

        mName = findViewById(R.id.editText_name);
        mSurname = findViewById(R.id.editText_surname);
        mGroup = findViewById(R.id.editText_group);
        mAddInfo = findViewById(R.id.editText_add_info);
        mProfileImage = findViewById(R.id.imageView);

        findViewById(R.id.button_save).setOnClickListener(this);

        intent = getIntent();

        mName.setText(intent.getStringExtra("userName"));
        mSurname.setText(intent.getStringExtra("userSurname"));
        mGroup.setText(intent.getStringExtra("userGroup"));
        mAddInfo.setText(intent.getStringExtra("userAddInfo"));

        Glide.with(getApplicationContext())
                .load(intent.getStringExtra("userPhotoUri"))
                .into(mProfileImage);
    }


    private boolean nameIsEmpty(String name) {
        if(name.isEmpty()) {
            mName.setError("Введите имя");
            mName.requestFocus();
            return true;
        } else {
            return false;
        }
    }

    private boolean surnameIsEmpty(String surname) {
        if(surname.isEmpty()) {
            mSurname.setError("Введите фамилию");
            mSurname.requestFocus();
            return true;
        } else {
            return false;
        }
    }

    private boolean groupIsEmpty(String group) {
        if(group.isEmpty()) {
            mGroup.setError("Введите группу");
            mGroup.requestFocus();
            return true;
        } else {
            return false;
        }
    }

    private void saveUserInformation() {
        String name = mName.getText().toString();
        String surname = mSurname.getText().toString();
        String group = mGroup.getText().toString();
        String addInfo = mAddInfo.getText().toString();
        if(nameIsEmpty(name) || surnameIsEmpty(surname) || groupIsEmpty(group)){
            return;
        }

        writeChangedUser(intent.getStringExtra("userKey"), name, surname,
                group, addInfo, intent.getStringExtra("userPhotoUri"));

        startActivity(new Intent(ChangeUserInformationActivity.this, AdministrationActivity.class));
        finish();
    }

    private void writeChangedUser(String userId, String name, String surname,
                              String group, String addInfo, String photoUri) {
        User user = new User(name, surname, group, addInfo, photoUri, userId);
        FirebaseDatabase.getInstance().getReference()
                .child("Users")
                .child(user.getKey())
                .removeValue();
        FirebaseDatabase.getInstance().getReference()
                .child("Users")
                .child(user.getKey())
                .setValue(user);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_save:
                saveUserInformation();
                break;
        }
    }
}