package com.example.socialmediaapp.ui.recover;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.View;

import com.example.socialmediaapp.R;
import com.example.socialmediaapp.databinding.ActivityForgotPasswordBinding;
import com.google.android.material.appbar.MaterialToolbar;

public class ForgotPasswordActivity extends AppCompatActivity {


    ActivityForgotPasswordBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityForgotPasswordBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();


        setSupportActionBar(binding.mtbForgotPassword);
        getSupportActionBar().setTitle("Forgot Password");

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nvForgotPassword);
        NavController navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(binding.mtbForgotPassword, navController);


        setContentView(view);
    }


    @Override
    public boolean onSupportNavigateUp() {

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nvForgotPassword);
        NavController navController = navHostFragment.getNavController();

        return navController.navigateUp() || super.onSupportNavigateUp();
    }
}