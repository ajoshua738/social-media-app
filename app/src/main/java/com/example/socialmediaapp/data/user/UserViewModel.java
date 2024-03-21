package com.example.socialmediaapp.data.user;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class UserViewModel extends AndroidViewModel {


    public final LiveData<List<User>> readAllData;
    private final UserRepository userRepository;



    public void addUser(User user){
        userRepository.addUser(user);
    }




    public UserViewModel(@NonNull Application application) {
        super(application);
        UserDao userDao = UserDatabase.getDatabase(application).userDao();
        userRepository = new UserRepository(userDao);
        readAllData = userRepository.readAllData;
    }


//    public LiveData<User> getUserByEmail(String email) {
//        return userRepository.getUserByEmail(email);
//    }

    public User getUserByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }



    public void updateUser(User user) { userRepository.updateUser(user);}

}
