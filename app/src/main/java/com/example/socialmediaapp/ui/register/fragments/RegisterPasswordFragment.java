package com.example.socialmediaapp.ui.register.fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.socialmediaapp.R;
import com.example.socialmediaapp.databinding.FragmentRegisterPasswordBinding;
import com.example.socialmediaapp.ui.register.RegisterViewModel;


public class RegisterPasswordFragment extends Fragment {

    private FragmentRegisterPasswordBinding binding;
    private RegisterViewModel mViewModel;

    CardView cv1,cv2,cv3,cv4;

    private boolean hasCharacters = false, hasUpperCase = false, hasNumber = false, hasSymbol = false, isValid = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRegisterPasswordBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        mViewModel = new ViewModelProvider(requireActivity()).get(RegisterViewModel.class);
        cv1 = binding.cv1;
        cv2 = binding.cv2;
        cv3 = binding.cv3;
        cv4 = binding.cv4;
        binding.tvRegPasswordError.setVisibility(View.GONE);
        binding.etRegPassword.setText("Abcd1234.");




        binding.btnRegPassNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isValid){
                    mViewModel.setPassword(binding.etRegPassword.getText().toString());
                    NavController navController = Navigation.findNavController(requireView());
                    //navController.navigate(R.id.action_registerPasswordFragment_to_registerBirthdayFragment);
                }else{


                }

            }
        });

        inputChange();
        checkPassword();
        return view;
    }


    private void checkPassword(){
        String password = binding.etRegPassword.getText().toString();

        if(password.length() >= 8){
            hasCharacters = true;
            cv1.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.md_theme_light_primary));
        }
        else{
            hasCharacters = false;
            cv1.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.md_theme_light_onPrimary));
        }

        if(password.matches("(.*[A-Z].*)") && password.matches("(.*[a-z].*)")){
            hasUpperCase = true;
            cv2.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.md_theme_light_primary));
        }
        else{
            hasUpperCase = false;
            cv2.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.md_theme_light_onPrimary));
        }



        if(password.matches("(.*[0-9].*)")){
            hasNumber = true;
            cv3.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.md_theme_light_primary));
        }
        else{
            hasNumber = false;
            cv3.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.md_theme_light_onPrimary));
        }

        if (password.matches("^(?=.*[_.()]).*$")){
            hasSymbol = true;
            cv4.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.md_theme_light_primary));
        }
        else{
            hasSymbol = false;
            cv4.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.md_theme_light_onPrimary));
        }

        checkAllConditions();
    }


    private void checkAllConditions(){
        if(hasCharacters && hasUpperCase && hasNumber && hasSymbol){
            isValid = true;
            binding.tvRegPasswordError.setVisibility(View.GONE);

        }
        else{
            isValid = false;
            binding.tvRegPasswordError.setVisibility(View.VISIBLE);
        }
    }




    private void inputChange(){

        binding.etRegPassword.addTextChangedListener(new TextWatcher() {
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



























}