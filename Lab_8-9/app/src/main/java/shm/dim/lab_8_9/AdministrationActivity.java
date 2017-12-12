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
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class AdministrationActivity extends AppCompatActivity implements View.OnClickListener{

    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private TextView mTextViewActionMsg;
    private EditText mEditTextFind;
    private UserDataAdapter mUserDataAdapter;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;

    private List<User> mUserList = new ArrayList<>();

    private int selectedMenuItem = 0;


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
                selectedMenuItem = 1;
                showActionMessage("Выберите пользователя, которого необходимо изменить");
                return true;
            case R.id.delete_user:
                selectedMenuItem = 2;
                showActionMessage("Выберите пользователя, которого необдходимо удалить");
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
        mRecyclerView = findViewById(R.id.list);
        mTextViewActionMsg = findViewById(R.id.textView_action_message);
        mEditTextFind = findViewById(R.id.editText_find);

        findViewById(R.id.button_find).setOnClickListener(this);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference();

        initListUsers();
        showListUsers();
    }



    private void showListUsers() {
        mUserDataAdapter = new UserDataAdapter(getApplicationContext(), mUserList, new UserDataAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(User user) {
                removeActionMessage();
                if(selectedMenuItem == 1) {
                    changeUser(user);
                    selectedMenuItem = 0;
                } else if(selectedMenuItem == 2) {
                    showDialogMessage("Удалить пользователя " +
                            user.getName() + " " + user.getSurname() + "?", user);
                } else {
                    Toast.makeText(AdministrationActivity.this, "Выберите действие из пункта меню", Toast.LENGTH_SHORT).show();
                }
            }
        });
        mRecyclerView.setAdapter(mUserDataAdapter);
    }

    private void showActionMessage(String msg) {
        mTextViewActionMsg.setVisibility(View.VISIBLE);
        mTextViewActionMsg.setText(msg);
    }

    private void removeActionMessage() {
        mTextViewActionMsg.setVisibility(View.GONE);
    }

    private void showDialogMessage(String msg, final User user) {
        new AlertDialog.Builder(this)
                .setMessage(msg)
                .setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        selectedMenuItem = 0;
                    }
                })
                .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        removeUser(user);
                        selectedMenuItem = 0;
                    }
                }).create().show();
    }

    private void initListUsers() {
        mDatabaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    String key = data.getValue(User.class).getKey();
                    String name = data.getValue(User.class).getName();
                    String surname = data.getValue(User.class).getSurname();
                    String group = data.getValue(User.class).getGroup();
                    String addInfo = data.getValue(User.class).getAddInfo();
                    String photoId = data.getValue(User.class).getPhotoUri();

                    User user = new User();
                    user.setKey(key);
                    user.setName(name);
                    user.setSurname(surname);
                    user.setGroup(group);
                    user.setAddInfo(addInfo);
                    user.setPhotoUri(photoId);

                    mUserList.add(user);
                    mUserDataAdapter.notifyDataSetChanged();
                }
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

    private void findUser() {
        List<User> temp = new ArrayList();
        for (User user : mUserList) {
            if ((user.getName() + " " + user.getSurname()).contains(mEditTextFind.getText().toString())) {
                temp.add(user);
            }
        }
        mUserDataAdapter.updateList(temp);
    }

    private void changeUser(User user) {
        Intent intent = new Intent(AdministrationActivity.this, ChangeUserInformationActivity.class);
        intent.putExtra("userName", user.getName());
        intent.putExtra("userSurname", user.getSurname());
        intent.putExtra("userGroup", user.getGroup());
        intent.putExtra("userAddInfo", user.getAddInfo());
        intent.putExtra("userPhotoUri", user.getPhotoUri());
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private void removeUser(User user) {
        Toast.makeText(AdministrationActivity.this, "Пользователь " +
                user.getName() + " " + user.getSurname() + " удален", Toast.LENGTH_SHORT).show();
        mDatabaseReference.child("Users")
                .child(user.getKey())
                .removeValue();

        mUserList.remove(user);
        showListUsers();
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_find:
                findUser();
                break;
        }
    }
}