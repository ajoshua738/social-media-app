package com.example.socialmediaapp.data.user;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Update;
import androidx.room.Upsert;

import java.util.List;

import io.reactivex.Completable;

@Dao
public interface UserDao {

    @Upsert
    void addUser(User user);



    @Query("SELECT * FROM user_table ORDER BY id ASC")
    LiveData<List<User>> readAllData();


    //Check if email exists

//    @Query("SELECT * FROM user_table WHERE email = :email")
//    LiveData<User> getUserByEmail(String email);


    @Query("SELECT * FROM user_table WHERE email = :email")
    User getUserByEmail(String email);



    @Update
    void updateUser(User user);





}
