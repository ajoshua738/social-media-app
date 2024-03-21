package com.example.socialmediaapp.ui.register.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.socialmediaapp.R;
import com.example.socialmediaapp.data.user.UserViewModel;
import com.example.socialmediaapp.databinding.FragmentRegisterEmailBinding;
import com.example.socialmediaapp.ui.register.RegisterViewModel;
import com.example.socialmediaapp.utils.ValidationUtils;


public class RegisterEmailFragment extends Fragment {


    private FragmentRegisterEmailBinding binding;
    //private RegisterEmailViewModel mViewModel;
    private RegisterViewModel mViewModel;
    private UserViewModel userViewModel;
    private boolean isValidEmail = false;
    private boolean isValidPassword = false;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRegisterEmailBinding.inflate(inflater, container, false);

        View view = binding.getRoot();


        mViewModel = new ViewModelProvider(requireActivity()).get(RegisterViewModel.class);
        userViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);





        binding.btnRegEmailNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isValidEmail && isValidPassword)
                {
                    String currentEmail = binding.etEmailRegister.getText().toString();
                    if(ValidationUtils.isEmailInDatabase(userViewModel,currentEmail)){
                        binding.inLayoutRegEmail.setError(getString(R.string.email_exists));

                    }else{
                        mViewModel.setEmail(binding.etEmailRegister.getText().toString());
                        mViewModel.setPassword(binding.etPasswordRegister1.getText().toString());
                        NavController navController = Navigation.findNavController(requireView());
                        navController.navigate(R.id.action_registerEmailFragment_to_registerBirthdayFragment);
                    }


                }
            }
        });



        inputChange();
        binding.etEmailRegister.setText("test789@gmail.com");
        binding.etPasswordRegister1.setText("Abcd1234.");
        return  view;
    }






    private void inputChange(){
        binding.etEmailRegister.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String currentEmail = binding.etEmailRegister.getText().toString();
                if(ValidationUtils.isValidEmail(currentEmail)){
                    isValidEmail = true;
                    binding.inLayoutRegEmail.setError(null);
                }
                else{
                    isValidEmail = false;
                    binding.inLayoutRegEmail.setError(getString(R.string.register_email_error));
                }



            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        binding.etPasswordRegister1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String currentPassword = binding.etPasswordRegister1.getText().toString();

                if(ValidationUtils.isPasswordValid(currentPassword)){
                    isValidPassword =true;
                    binding.inLayoutRegPassword.setError(null);


                }else{
                    isValidPassword = false;
                    binding.inLayoutRegPassword.setError(getString(R.string.register_password_error));
                }

                binding.checkBox.setChecked(ValidationUtils.hasRequiredCharacters(currentPassword));
                binding.checkBox2.setChecked(ValidationUtils.hasUpperCase(currentPassword));
                binding.checkBox3.setChecked(ValidationUtils.hasNumber(currentPassword));
                binding.checkBox4.setChecked(ValidationUtils.hasSymbol(currentPassword));


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }






    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}