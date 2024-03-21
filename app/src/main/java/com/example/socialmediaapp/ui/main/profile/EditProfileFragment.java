
package com.example.socialmediaapp.ui.main.profile;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.socialmediaapp.R;
import com.example.socialmediaapp.data.user.User;
import com.example.socialmediaapp.data.user.UserDataHolder;
import com.example.socialmediaapp.data.user.UserViewModel;
import com.example.socialmediaapp.databinding.FragmentEditProfileBinding;
import com.example.socialmediaapp.utils.ValidationUtils;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateValidatorPointBackward;
import com.google.android.material.datepicker.MaterialDatePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class EditProfileFragment extends Fragment {

    FragmentEditProfileBinding binding;
    private Long selectedDate;
    private boolean isValidBirthday = false;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentEditProfileBinding.inflate(inflater,container,false);
        View view = binding.getRoot();


        binding.etBirthdayEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openDatePicker();
            }
        });

        return view;
    }


    public void openDatePicker(){

        MaterialDatePicker.Builder<Long> builder = MaterialDatePicker.Builder.datePicker();
        builder.setSelection(selectedDate != null ? selectedDate : MaterialDatePicker.todayInUtcMilliseconds());
        builder.setTitleText("Select Birthday");

        //builder.setTheme(R.style.ThemeOverlay_MaterialComponents_MaterialCalendar);

        // Set the max date to today
        CalendarConstraints.Builder constraintsBuilder = new CalendarConstraints.Builder();
        constraintsBuilder.setValidator(DateValidatorPointBackward.now()); // Use today's date
        constraintsBuilder.setEnd(System.currentTimeMillis());

        builder.setCalendarConstraints(constraintsBuilder.build());

        MaterialDatePicker<Long> picker = builder.build();

        picker.addOnPositiveButtonClickListener(selection -> {
            // Update the TextInputEditText with the selected date
            selectedDate = selection;
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            binding.etBirthdayEditProfile.setText(dateFormat.format(new Date(selection)));
            Log.d("Birthday"," "+selection  );
            if(ValidationUtils.checkAge(ValidationUtils.calculateAge(selectedDate))){
                isValidBirthday = true;
                binding.inLayoutEditProfileBirthday.setError(null);
            }
            else {
                isValidBirthday = false;
                binding.inLayoutEditProfileBirthday.setError(getString(R.string.register_birthday_error));
            }

        });

        picker.show(requireActivity().getSupportFragmentManager(), picker.toString());
    }
}