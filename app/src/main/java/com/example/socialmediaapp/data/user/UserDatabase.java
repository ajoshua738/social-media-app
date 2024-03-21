package com.example.socialmediaapp.data.user;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class}, version = 7, exportSchema = false)
abstract public class UserDatabase extends RoomDatabase {

    public abstract UserDao userDao();


    private static volatile UserDatabase INSTANCE;

    public static synchronized UserDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            synchronized (UserDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            UserDatabase.class, "user_database").
                            allowMainThreadQueries().
                            fallbackToDestructiveMigration().build();
                }
            }
        }
        return INSTANCE;
    }

}
































