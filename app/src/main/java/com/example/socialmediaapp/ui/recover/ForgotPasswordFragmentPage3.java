package com.example.socialmediaapp.ui.recover;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.socialmediaapp.R;
import com.example.socialmediaapp.databinding.FragmentForgotPasswordPage3Binding;
import com.example.socialmediaapp.ui.login.LoginActivity;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;


public class ForgotPasswordFragmentPage3 extends Fragment {

    FragmentForgotPasswordPage3Binding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentForgotPasswordPage3Binding.inflate(inflater,container,false);
        View view = binding.getRoot();

        binding.btnForgotPasswordComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialog();

            }
        });


        return view;
    }

    private void showCustomDialog() {

        // Inflate custom layout for the dialog
        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.custom_dialog_reset_password, null);

        // Get a reference to the button inside the custom dialog layout
        Button btnComplete = dialogView.findViewById(R.id.btnResetPasswordDialog);

        // Set click listener for the button
        btnComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle button click: Navigate to login activity
                Intent intent = new Intent(requireContext(), LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                requireActivity().finish(); // Optional: Finish current activity
            }
        });

        // Create and show the dialog
        new MaterialAlertDialogBuilder(requireContext())
                .setView(dialogView)
                .show();

    }


}