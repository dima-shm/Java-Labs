package shm.dim.lab_8_9;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.FirebaseDatabase;

public class FragmentChangeUserInformation extends Fragment {

    private EditText mName, mSurname, mGroup, mAddInfo;
    private ImageView mProfileImage;
    private User user;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =
                inflater.inflate(R.layout.fragment_change_user_information, container, false);

        mName = view.findViewById(R.id.editText_name);
        mSurname = view.findViewById(R.id.editText_surname);
        mGroup = view.findViewById(R.id.editText_group);
        mAddInfo = view.findViewById(R.id.editText_add_info);
        mProfileImage = view.findViewById(R.id.imageView);

        view.findViewById(R.id.button_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUserInformation();
            }
        });

        return view;
    }


    public void setUser(User user) {
        this.user = user;
        mName.setText(user.getName());
        mSurname.setText(user.getSurname());
        mGroup.setText(user.getGroup());
        mAddInfo.setText(user.getAddInfo());
        Glide.with(this)
                .load(user.getPhotoUri())
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

        writeChangedUser(user.getKey(), name, surname,
                group, addInfo, user.getPhotoUri());

        updateUsersList();
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

    private void updateUsersList() {
        FragmentManager fragmentManager = getFragmentManager();

        FragmentUsersList fragmentUsersList = (FragmentUsersList) fragmentManager
                .findFragmentById(R.id.fragment_users_list);

        fragmentUsersList.onResume();
    }
}