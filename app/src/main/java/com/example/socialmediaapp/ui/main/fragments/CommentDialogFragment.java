package com.example.socialmediaapp.ui.main.fragments;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.socialmediaapp.R;
import com.example.socialmediaapp.databinding.FragmentCommentDialogBinding;

public class CommentDialogFragment extends DialogFragment {

    private EditText editTextComment;
    FragmentCommentDialogBinding binding;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCommentDialogBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        editTextComment = binding.etPostComment;



        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().setGravity(Gravity.BOTTOM);
        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);



        editTextComment.requestFocus();
        InputMethodManager imm = (InputMethodManager) requireContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editTextComment, InputMethodManager.SHOW_IMPLICIT);
    }

    @Override
    public void onResume() {
        super.onResume();
        editTextComment.postDelayed(() -> {
            InputMethodManager imm = (InputMethodManager) requireContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(editTextComment, InputMethodManager.SHOW_IMPLICIT);
        }, 200); // Delay to ensure dialog is fully shown before showing keyboard
    }

}