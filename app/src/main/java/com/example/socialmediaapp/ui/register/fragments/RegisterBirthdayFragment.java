package com.example.socialmediaapp.ui.register.fragments;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.socialmediaapp.R;
import com.example.socialmediaapp.data.user.User;
import com.example.socialmediaapp.data.user.UserDataHolder;
import com.example.socialmediaapp.data.user.UserViewModel;
import com.example.socialmediaapp.databinding.FragmentRegisterBirthdayBinding;
import com.example.socialmediaapp.ui.login.LoginActivity;
import com.example.socialmediaapp.ui.main.MainUIActivity;
import com.example.socialmediaapp.ui.register.RegisterViewModel;
import com.example.socialmediaapp.utils.ValidationUtils;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateValidatorPointBackward;
import com.google.android.material.datepicker.MaterialDatePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class RegisterBirthdayFragment extends Fragment {

    private FragmentRegisterBirthdayBinding binding;
    private DatePickerDialog datePickerDialog;

    private boolean isValidBirthday = false, isValidFirstName = false, isValidLastName = false;
    private RegisterViewModel mViewModel;
    private UserViewModel userViewModel;

    int validDay, validMonth, validYear;
    int userAge = 0;

    private Long selectedDate;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRegisterBirthdayBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        mViewModel = new ViewModelProvider(requireActivity()).get(RegisterViewModel.class);
        userViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);
        initializeBirthdayField();
       // initDatePicker();
        //binding.tvRegBirthday.setText(getTodaysDate());

        binding.tvRegBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openDatePicker();
            }
        });


        binding.btnRegBirthdayNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isValidBirthday){
                    binding.inLayoutRegBirthday.setError(getString(R.string.register_birthday_error));
                }
                else{
                    binding.inLayoutRegBirthday.setError(null);
                }
                if(isValidBirthday && isValidFirstName && isValidLastName){
                    mViewModel.setDobMillies(selectedDate);
                    mViewModel.setFirstName(binding.etRegFirstName1.getText().toString());
                    mViewModel.setLastName(binding.etRegLastName1.getText().toString());
//                    NavController navController = Navigation.findNavController(requireView());
//                    navController.navigate(R.id.action_registerBirthdayFragment_to_registerNameFragment);
                    User user = new User
                            (0,mViewModel.getEmail(),mViewModel.getPassword(),
                                    mViewModel.getFirstName(),mViewModel.getLastName(),
                                    mViewModel.getDobMillies());

                    userViewModel.addUser(user);
                    UserDataHolder.setUser(user);

                    Intent intent = new Intent(requireActivity(), MainUIActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
            }
        });

        inputChange();
        binding.etRegFirstName1.setText("Adrian");
        binding.etRegLastName1.setText("Joshua");
        return view;
    }


    private void initializeBirthdayField() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
       // binding.tvRegBirthday.setText(dateFormat.format(new Date()));
        binding.tvRegBirthday.setText(dateFormat.format(new Date(0)));
    }

    public void openDatePicker(){

        MaterialDatePicker.Builder<Long> builder = MaterialDatePicker.Builder.datePicker();
        //builder.setSelection(selectedDate != null ? selectedDate : MaterialDatePicker.todayInUtcMilliseconds());
        builder.setSelection(selectedDate != null ? selectedDate : 0);
        builder.setTitleText("Select Birthday");

        //builder.setTheme(R.style.ThemeOverlay_MaterialComponents_MaterialCalendar);

        Calendar todayMinus13Years = Calendar.getInstance();
        todayMinus13Years.add(Calendar.YEAR, -13);

        // Set the max date to today
        CalendarConstraints.Builder constraintsBuilder = new CalendarConstraints.Builder();
        constraintsBuilder.setValidator(DateValidatorPointBackward.before(todayMinus13Years.getTimeInMillis())); // Use today's date
        //constraintsBuilder.setEnd(System.currentTimeMillis());
        constraintsBuilder.setEnd(todayMinus13Years.getTimeInMillis());
        builder.setCalendarConstraints(constraintsBuilder.build());

        MaterialDatePicker<Long> picker = builder.build();

        picker.addOnPositiveButtonClickListener(selection -> {
            // Update the TextInputEditText with the selected date
            selectedDate = selection;
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            binding.tvRegBirthday.setText(dateFormat.format(new Date(selection)));
            Log.d("Birthday"," "+selection  );
            if(ValidationUtils.checkAge(ValidationUtils.calculateAge(selectedDate))){
                isValidBirthday = true;
                binding.inLayoutRegBirthday.setError(null);
            }
            else {
                isValidBirthday = false;
                binding.inLayoutRegBirthday.setError(getString(R.string.register_birthday_error));
            }

        });

        picker.show(requireActivity().getSupportFragmentManager(), picker.toString());
    }




    private void inputChange(){
        binding.etRegFirstName1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                isValidFirstName = ValidationUtils.isValidName(binding.etRegFirstName1.getText().toString());

                if(!isValidFirstName){
                    binding.inLayoutRegFirstName.setError(getString(R.string.register_firstName_error));
                }
                else{
                    binding.inLayoutRegFirstName.setError(null);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        binding.etRegLastName1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                isValidLastName = ValidationUtils.isValidName(binding.etRegLastName1.getText().toString());
                if(!isValidLastName){
                    binding.inLayoutRegLastName.setError(getString(R.string.register_lastName_error));
                }
                else{
                    binding.inLayoutRegLastName.setError(null);
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

}

