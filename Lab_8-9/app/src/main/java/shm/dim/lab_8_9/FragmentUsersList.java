package shm.dim.lab_8_9;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class FragmentUsersList extends Fragment {

    private RecyclerView mRecyclerView;
    private UserDataAdapter mUserDataAdapter;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;

    private List<User> mUserList = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =
                inflater.inflate(R.layout.fragment_users_list, container, false);

        mRecyclerView = view.findViewById(R.id.list);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference();

        return view;
    }

    @Override
    public void onResume() {
        mUserList.clear();
        initListUsers();
        showListUsers();
        super.onResume();
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int position = UserDataAdapter.getPosition();
        User user = mUserList.get(position);
        showDialogMessage("Удалить пользователя " +
                user.getName() + " " + user.getSurname() + "?", user);
        return super.onContextItemSelected(item);
    }


    public void initListUsers() {
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

    private void showListUsers() {
        mUserDataAdapter = new UserDataAdapter(this.getContext(), mUserList, new UserDataAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(User user) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentChangeUserInformation fragmentChangeUserInfo = (FragmentChangeUserInformation) fragmentManager
                        .findFragmentById(R.id.fragment_change_user_info);
                if (fragmentChangeUserInfo == null || !fragmentChangeUserInfo.isVisible()) {
                    Intent intent = new Intent(getActivity(), ChangeUserInformationActivity.class);
                    intent.putExtra("userName", user.getName());
                    intent.putExtra("userSurname", user.getSurname());
                    intent.putExtra("userGroup", user.getGroup());
                    intent.putExtra("userAddInfo", user.getAddInfo());
                    intent.putExtra("userPhotoUri", user.getPhotoUri());
                    intent.putExtra("userKey", user.getKey());
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                } else {
                    fragmentChangeUserInfo.setUser(user);
                }
            }
        });
        mRecyclerView.setAdapter(mUserDataAdapter);
    }

    private void showDialogMessage(String msg, final User user) {
        new AlertDialog.Builder(this.getContext())
                .setMessage(msg)
                .setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        return;
                    }
                })
                .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        removeUser(user);
                    }
                }).create().show();
    }

    private void removeUser(User user) {
        Toast.makeText(this.getContext(), "Пользователь " +
                user.getName() + " " + user.getSurname() + " удален", Toast.LENGTH_SHORT).show();
        mDatabaseReference.child("Users")
                .child(user.getKey())
                .removeValue();

        mUserList.remove(user);
        showListUsers();
    }

    public void findUser(String userName) {
        List<User> temp = new ArrayList();
        for (User user : mUserList) {
            if ((user.getName() + " " + user.getSurname()).contains(userName)) {
                temp.add(user);
            }
        }
        mUserDataAdapter.updateList(temp);
    }
}