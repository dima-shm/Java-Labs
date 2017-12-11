package shm.dim.lab_8_9;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class AdministrationActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private UserDataAdapter mUserDataAdapter;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;

    private List<User> mUserList = new ArrayList<>();


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.change_info_about_user:
                changeUser();
                return true;
            case R.id.delete_user:
                deleteUser();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administration);

        mToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(mToolbar);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference();

        onPrepareData();

        mRecyclerView = findViewById(R.id.list);
        mUserDataAdapter = new UserDataAdapter(getApplicationContext(), mUserList, new UserDataAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(User item) {
                Toast.makeText(AdministrationActivity.this, mUserDataAdapter
                        .getItemCount(), Toast.LENGTH_LONG ).show();
                //startActivity(new Intent(AdministrationActivity.this, UserInformationActivity.class)
                //        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });
        mRecyclerView.setAdapter(mUserDataAdapter);
    }



    private void onPrepareData() {
        mDatabaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                ShowData(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void ShowData(DataSnapshot dataSnapshot) {
        for (DataSnapshot data : dataSnapshot.getChildren()) {
            String key = data.getValue(User.class).getKey();
            String name = data.getValue(User.class).getName();
            String surname = data.getValue(User.class).getSurname();
            String group = data.getValue(User.class).getGroup();
            String photoId = data.getValue(User.class).getPhotoUri();

            User student = new User();
            student.setKey(key);
            student.setName(name);
            student.setSurname(surname);
            student.setGroup(group);
            student.setPhotoUri(photoId);

            mUserList.add(student);
            mUserDataAdapter.notifyDataSetChanged();
        }
    }

    private void changeUser() {
        Toast.makeText(getApplicationContext(), "changeUser", Toast.LENGTH_SHORT).show();
    }

    private void deleteUser() {
        Toast.makeText(getApplicationContext(), "deleteUser", Toast.LENGTH_SHORT).show();
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
                        Intent intent = new Intent(AdministrationActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }).create().show();
    }
}