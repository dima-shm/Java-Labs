package shm.dim.lab_8_9;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class UserProfileActivity extends AppCompatActivity {

    private TextView mName, mGroup, mAddInfo;
    private ImageView mProfileImage;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        mName = findViewById(R.id.textView_name_and_surname);
        mGroup = findViewById(R.id.textView_group);
        mAddInfo = findViewById(R.id.textView_add_info);
        mProfileImage = findViewById(R.id.imageView);
        mAuth = FirebaseAuth.getInstance();

        mName.setClickable(false);
        mGroup.setClickable(false);
        mAddInfo.setClickable(false);

        loadUserInformation();
    }


    private void loadUserInformation() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseRef = database.getReference().child("Users")
                .child(mAuth.getCurrentUser().getUid().toString());
        ValueEventListener eventListener = new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String, String> map = dataSnapshot.getValue(User.class).toMap();

                mName.setText(map.get("name") + " " + map.get("surname"));
                mGroup.setText(map.get("group"));
                mAddInfo.setText(map.get("addInfo"));

                Glide.with(getApplicationContext())
                        .load(map.get("photoId"))
                        .into(mProfileImage);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        databaseRef.addListenerForSingleValueEvent(eventListener);
    }


    @Override
    public void onBackPressed(){
        new AlertDialog.Builder(this)
                .setTitle("Выход")
                .setMessage("Вы уверены, что хотите выйти из аккаунта?")
                .setNegativeButton("Отмена", null)
                .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseAuth.getInstance().signOut();
                        Intent intent = new Intent(UserProfileActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }).create().show();
    }
}