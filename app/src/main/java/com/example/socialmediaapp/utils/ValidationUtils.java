package com.example.socialmediaapp.utils;

import android.util.Patterns;

import com.example.socialmediaapp.R;
import com.example.socialmediaapp.data.user.UserViewModel;

import java.util.Calendar;


public class ValidationUtils {

    public static boolean isValidEmail(String currentEmail){
        return !currentEmail.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(currentEmail).matches();
    }

    public static boolean isEmailInDatabase(UserViewModel userViewModel, String currentEmail){
        return userViewModel.getUserByEmail(currentEmail) != null;
    }



    public static boolean isPasswordValid(String password){
        return hasRequiredCharacters(password) && hasUpperCase(password) && hasNumber(password) && hasSymbol(password);
    }

    public static boolean hasRequiredCharacters(String password){
        return password.length() >= 8;
    }

    public static boolean hasUpperCase(String password){
        return password.matches("(.*[A-Z].*)") && password.matches("(.*[a-z].*)");
    }

    public static boolean hasNumber(String password){
        return password.matches("(.*[0-9].*)");
    }

    public static boolean hasSymbol(String password){
        return password.matches("^(?=.*[_.()]).*$");
    }


    public static int calculateAge(long selectedDateMillis) {
        Calendar dob = Calendar.getInstance();
        dob.setTimeInMillis(selectedDateMillis);

        Calendar today = Calendar.getInstance();

        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) {
            age--;
        }

        return age;
    }

    public static boolean checkAge(int age){
        return age >= 13;
    }

    public static boolean isValidName(String name){
        return name.matches("[a-zA-Z]{2,16}");
    }
}
