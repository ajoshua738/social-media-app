package com.example.socialmediaapp.data.user;

import android.os.Looper;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import android.os.Handler;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class UserRepository {

    private final UserDao userDao;
    LiveData<List<User>> readAllData;


    public UserRepository(UserDao userDao) {
        this.userDao = userDao;

    }


    void addUser(User user){
        userDao.addUser(user);
    }


//    public LiveData<User> getUserByEmail(String email) {
//        return userDao.getUserByEmail(email);
//    }

    public User getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }



    void updateUser(User user) { userDao.updateUser(user);}


}
