package com.example.socialmediaapp.ui.login;



import androidx.appcompat.app.AppCompatActivity;

import androidx.lifecycle.ViewModelProvider;


import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;


import com.example.socialmediaapp.R;
import com.example.socialmediaapp.data.user.User;
import com.example.socialmediaapp.data.user.UserDataHolder;
import com.example.socialmediaapp.data.user.UserViewModel;
import com.example.socialmediaapp.databinding.ActivityLoginBinding;
import com.example.socialmediaapp.ui.main.MainUIActivity;
import com.example.socialmediaapp.ui.main.MainUIViewModel;

import com.example.socialmediaapp.ui.main.fragments.CommentDialogFragment;
import com.example.socialmediaapp.ui.recover.ForgotPasswordActivity;
import com.example.socialmediaapp.ui.register.RegisterActivity;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    UserViewModel userViewModel;
    MainUIViewModel mainUIViewModel;
    private boolean isValidAccount = false;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        setContentView(view);



        binding.etLoginEmail.setText("test@gmail.com");
        binding.etLoginPassword.setText("Abcd1234.");
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        mainUIViewModel = new ViewModelProvider(this).get(MainUIViewModel.class);
//        Intent intent = new Intent(this, MainUIActivity.class);
//        startActivity(intent);




        binding.btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkEmail() && checkPassword()){
                    checkAccount();
                }


            }
        });


        binding.tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });





        inputChange();

    }

    private void checkAccount(){
        String currentEmail = binding.etLoginEmail.getText().toString();
        String currentPassword = binding.etLoginPassword.getText().toString();

        User user = userViewModel.getUserByEmail(currentEmail);
        if(user != null){
            isValidAccount = user.getPassword().equals(currentPassword);
            if(!isValidAccount){
                binding.inLayoutLoginPassword.setError(getString(R.string.password_incorrect));
            }else{
                binding.inLayoutLoginPassword.setError(null);
            }
        }
        else{
            isValidAccount = false;
            binding.inLayoutLoginEmail.setError("No account found with this email");
        }

        if(isValidAccount){
            Intent intent = new Intent(LoginActivity.this, MainUIActivity.class);
            UserDataHolder.setUser(user);
            //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

        }

    }


    private void inputChange(){
        binding.etLoginEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkEmail();


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        binding.etLoginPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkPassword();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private boolean checkEmail(){
        String currentEmail = binding.etLoginEmail.getText().toString();
        if(!currentEmail.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(currentEmail).matches()){
            binding.inLayoutLoginEmail.setError(null);
            return true;
        }
        else{
            binding.inLayoutLoginEmail.setError(getString(R.string.register_email_error));
            return false;
        }
    }

    private boolean checkPassword(){
        String currentPassword = binding.etLoginPassword.getText().toString();
        if(!currentPassword.isEmpty()){
            binding.inLayoutLoginPassword.setError(null);
            return true;
        }else{
            binding.inLayoutLoginPassword.setError(getString(R.string.register_password_error));
            return false;

        }
    }




}