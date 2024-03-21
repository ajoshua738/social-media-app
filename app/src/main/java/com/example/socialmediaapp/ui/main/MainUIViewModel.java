package com.example.socialmediaapp.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.socialmediaapp.data.user.User;

public class MainUIViewModel extends ViewModel {

    private User user = new User();

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


//    private MutableLiveData<User> userLiveData = new MutableLiveData<>();
//
//    public void setUser(User user) {
//        userLiveData.setValue(user);
//    }
//
//    public LiveData<User> getUser() {
//        return userLiveData;
//    }
}
