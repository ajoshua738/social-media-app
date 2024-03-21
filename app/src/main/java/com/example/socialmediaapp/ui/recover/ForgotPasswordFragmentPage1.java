package com.example.socialmediaapp.ui.recover;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.example.socialmediaapp.R;
import com.example.socialmediaapp.databinding.FragmentForgotPasswordPage1Binding;
import com.google.android.material.appbar.MaterialToolbar;


public class ForgotPasswordFragmentPage1 extends Fragment {


    FragmentForgotPasswordPage1Binding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentForgotPasswordPage1Binding.inflate(inflater,container,false);
        View view = binding.getRoot();
        binding.etForgotPasswordContact.setHint("Email");

        binding.btnForgotPasswordOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(requireView());
                navController.navigate(R.id.action_forgotPasswordFragmentPage1_to_forgotPasswordFragmentPage2);
            }
        });


        binding.rgForgotPasswordContactOption.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.rbForgotPasswordEmail){
                    binding.etForgotPasswordContact.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                    binding.etForgotPasswordContact.setHint("Email");
                }
                else if(checkedId == R.id.rbForgotPasswordPhone){
                    binding.etForgotPasswordContact.setInputType(InputType.TYPE_CLASS_PHONE);
                    binding.etForgotPasswordContact.setHint("Phone");
                }
            }
        });


//        MaterialToolbar mtb = requireActivity().findViewById(R.id.mtbForgotPassword);
//        AppCompatActivity activity = (AppCompatActivity) requireActivity();
//        activity.setSupportActionBar(mtb);
//        ActionBar actionBar = activity.getSupportActionBar();
//        if (actionBar != null) {
//            actionBar.setDisplayHomeAsUpEnabled(true);
//        }


        inputChange();

        return view;
    }




    private void inputChange(){
        binding.etForgotPasswordContact.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!binding.etForgotPasswordContact.getText().toString().isEmpty()){
                    binding.btnForgotPasswordOTP.setEnabled(true);

                }
                else{
                    binding.btnForgotPasswordOTP.setEnabled(false);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }



}