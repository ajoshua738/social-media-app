package com.example.socialmediaapp.ui.register.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.socialmediaapp.data.user.User;
import com.example.socialmediaapp.data.user.UserViewModel;
import com.example.socialmediaapp.databinding.FragmentRegisterNameBinding;
import com.example.socialmediaapp.ui.register.RegisterViewModel;


public class RegisterNameFragment extends Fragment {

    private FragmentRegisterNameBinding binding;
    private boolean validFirstName = false, validLastName = false;
    private RegisterViewModel mViewModel;
    private UserViewModel userViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRegisterNameBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        mViewModel = new ViewModelProvider(requireActivity()).get(RegisterViewModel.class);
        userViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);
        binding.tvFirstNameError.setVisibility(View.GONE);
        binding.tvLastNameError.setVisibility(View.GONE);
        binding.etRegFirstName.setText("Adrian");
        isValidName(binding.etRegFirstName.getText().toString());
        binding.etRegLastName.setText("Joshua");
        isValidName(binding.etRegLastName.getText().toString());


        binding.btnRegNameNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(validFirstName && validLastName){
                    mViewModel.setFirstName(binding.etRegFirstName.getText().toString());
                    mViewModel.setLastName(binding.etRegLastName.getText().toString());
                    TextView textView = binding.textView9;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Email: ").append(mViewModel.getEmail()).append("\n");
                    stringBuilder.append("Password: ").append(mViewModel.getPassword()).append("\n");
                    stringBuilder.append("Age: ").append(mViewModel.getAge()).append("\n");
                    stringBuilder.append("DOB Day: ").append(mViewModel.getDobDay()).append("\n");
                    stringBuilder.append("DOB Month: ").append(mViewModel.getDobMonth()).append("\n");
                    stringBuilder.append("DOB Year: ").append(mViewModel.getDobYear()).append("\n");
                    stringBuilder.append("First Name: ").append(mViewModel.getFirstName()).append("\n");
                    stringBuilder.append("Last Name: ").append(mViewModel.getLastName()).append("\n");

                    textView.setText(stringBuilder.toString());

                    User user = new User
                            (0,mViewModel.getEmail(),mViewModel.getPassword(),mViewModel.getDobDay(),
                                    mViewModel.getDobMonth(),mViewModel.getDobYear(),mViewModel.getAge(),
                                    mViewModel.getFirstName(),mViewModel.getLastName());

                    userViewModel.addUser(user);

                }



            }
        });

        inputChange();
        return view;
    }


    private boolean isValidName(String name) {
        return name.matches("[a-zA-Z]{2,16}");
    }
    private void inputChange(){
        binding.etRegFirstName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String firstName = binding.etRegFirstName.getText().toString().trim();

                if (isValidName(firstName)) {
                    binding.tvFirstNameError.setVisibility(View.GONE);
                    validFirstName = true;
                } else {
                    binding.tvFirstNameError.setVisibility(View.VISIBLE);
                    validFirstName = false;
                }

            }
        });


        binding.etRegLastName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String lastName = binding.etRegLastName.getText().toString().trim();

                if (isValidName(lastName)) {
                    binding.tvLastNameError.setVisibility(View.GONE);
                    validLastName = true;
                } else {
                    binding.tvLastNameError.setVisibility(View.VISIBLE);
                    validLastName = false;
                }

            }
        });
    }


}