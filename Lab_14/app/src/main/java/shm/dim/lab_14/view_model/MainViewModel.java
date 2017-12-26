package shm.dim.lab_14.view_model;

import android.content.Context;

import shm.dim.lab_14.model.User;

public class MainViewModel {

    private Context context;
    private User user;

    public MainViewModel(Context context, User user) {
        this.context = context;
        this.user = user;
    }

    public String getLogin(){
        return user.getLogin();
    }

    public String getPassword(){
        return user.getPassword();
    }
}
