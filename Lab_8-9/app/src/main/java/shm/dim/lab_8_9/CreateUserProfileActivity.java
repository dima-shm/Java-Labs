package shm.dim.lab_8_9;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class CreateUserProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int CHOOSE_IMAGE = 101;
    private EditText mEditTextName, mEditTextSurname,
                     mEditTextGroup, mEditTextAddInfo;
    private ImageView mImageView;
    private ProgressBar mProgressBar;
    private FirebaseAuth mAuth;

    private Uri uriProfileImage;
    private String profileImageUrl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user_profile);

        mEditTextName = findViewById(R.id.editText_name);
        mEditTextSurname = findViewById(R.id.editText_surname);
        mEditTextGroup = findViewById(R.id.editText_group);
        mEditTextAddInfo = findViewById(R.id.editText_add_info);
        mImageView = findViewById(R.id.imageView_profile);
        mProgressBar = findViewById(R.id.progressBar_profile_image);
        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.imageView_profile).setOnClickListener(this);
        findViewById(R.id.button_save).setOnClickListener(this);
    }


    private void showImageChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Выберите фото профиля"), CHOOSE_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == CHOOSE_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            uriProfileImage = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uriProfileImage);
                mImageView.setImageBitmap(bitmap);
                
                uploadImageToFirebaseStorage();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void uploadImageToFirebaseStorage() {
        StorageReference storageRef =
                FirebaseStorage.getInstance().getReference("profileImages/" + System.currentTimeMillis() + ".jpg");

        if(uriProfileImage != null) {
            mProgressBar.setVisibility(View.VISIBLE);
            storageRef.putFile(uriProfileImage)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            mProgressBar.setVisibility(View.GONE);
                            profileImageUrl = taskSnapshot.getDownloadUrl().toString();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            mProgressBar.setVisibility(View.GONE);
                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    private boolean nameIsEmpty(String name) {
        if(name.isEmpty()) {
            mEditTextName.setError("Введите имя");
            mEditTextName.requestFocus();
            return true;
        } else {
            return false;
        }
    }

    private boolean surnameIsEmpty(String surname) {
        if(surname.isEmpty()) {
            mEditTextSurname.setError("Введите фамилию");
            mEditTextSurname.requestFocus();
            return true;
        } else {
            return false;
        }
    }

    private boolean groupIsEmpty(String group) {
        if(group.isEmpty()) {
            mEditTextGroup.setError("Введите группу");
            mEditTextGroup.requestFocus();
            return true;
        } else {
            return false;
        }
    }

    private void saveUserInformation() {
        String name = mEditTextName.getText().toString();
        String surname = mEditTextSurname.getText().toString();
        String group = mEditTextGroup.getText().toString();
        String addInfo = mEditTextAddInfo.getText().toString();
        if(nameIsEmpty(name) || surnameIsEmpty(surname) || groupIsEmpty(group)){
            return;
        }

        FirebaseUser user = mAuth.getCurrentUser();
        if(user != null && profileImageUrl != null) {
            UserProfileChangeRequest profile = new UserProfileChangeRequest.Builder()
                    .setDisplayName(name + " " + surname)
                    .setPhotoUri(Uri.parse(profileImageUrl))
                    .build();
            writeNewUser(user.getUid(), name, surname, group, addInfo, profileImageUrl.toString());
            user.updateProfile(profile)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "Профиль создан", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(CreateUserProfileActivity.this, MainActivity.class));
                                finish();
                            }
                        }
                    });
        }
    }

    private void writeNewUser(String userId, String name, String surname,
                              String group, String addInfo, String photoUri) {
        User user = new User(name, surname, group, addInfo, photoUri, userId);
        FirebaseDatabase.getInstance().getReference()
                .child("Users").child(userId).setValue(user);
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageView_profile:
                showImageChooser();
                break;
            case R.id.button_save:
                saveUserInformation();
                break;
        }
    }

    @Override
    public void onBackPressed(){
        new AlertDialog.Builder(this)
                .setTitle("Ошибка")
                .setMessage("Необходимо закончить создание профиля")
                .setPositiveButton("OK", null).create().show();
    }
}