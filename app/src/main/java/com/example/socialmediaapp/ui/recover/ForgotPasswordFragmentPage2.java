package com.example.socialmediaapp.ui.recover;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.socialmediaapp.R;
import com.example.socialmediaapp.databinding.FragmentForgotPasswordPage2Binding;


public class ForgotPasswordFragmentPage2 extends Fragment {


    FragmentForgotPasswordPage2Binding binding;
    private int secondsLeft = 5;
    private Handler handler;
    private Runnable countdownRunnable = new Runnable() {
        @Override
        public void run() {
            if (secondsLeft > 0) {
                secondsLeft--;
                updateCountdownText();
                handler.postDelayed(this, 1000); // Run this runnable again after 1 second
            }else {
                stopCountdown();
            }
        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentForgotPasswordPage2Binding.inflate(inflater,container,false);
        View view = binding.getRoot();
        handler = new Handler();
        startCountdown();




        binding.btnForgotPasswordResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(requireView());
                navController.navigate(R.id.action_forgotPasswordFragmentPage2_to_forgotPasswordFragmentPage3);
            }
        });

        binding.tvForgotPasswordCountdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click action here, e.g., resend OTP
                // For demonstration, let's restart the countdown
                startCountdown();
            }
        });

        inputChange();

        return view;
    }


    private void startCountdown() {
        secondsLeft = 5;
        binding.tvForgotPasswordCountdown.setEnabled(false);
        handler.postDelayed(countdownRunnable, 1000); // Start the countdown
    }

    private void stopCountdown() {
        binding.tvForgotPasswordCountdown.setEnabled(true); // Enable the TextView after countdown ends
        binding.tvForgotPasswordCountdown.setText("Resend OTP");
        //binding.tvForgotPasswordCountdown.setTextColor(getResources().getColor(R.color.your_color)); // Change text color
    }
    private void updateCountdownText() {
        binding.tvForgotPasswordCountdown.setText("Resend OTP in " + secondsLeft + " seconds");
    }

    private void inputChange(){
        binding.pvForgotPasswordOTP.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d("Input number",""+binding.pvForgotPasswordOTP.getText().toString());

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

}