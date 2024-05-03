
package com.example.socialmediaapp.ui.main.profile;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.socialmediaapp.R;
import com.example.socialmediaapp.data.user.User;
import com.example.socialmediaapp.data.user.UserDataHolder;
import com.example.socialmediaapp.data.user.UserViewModel;
import com.example.socialmediaapp.databinding.FragmentEditProfileBinding;
import com.example.socialmediaapp.helper.ApplicationClass;
import com.example.socialmediaapp.helper.CommonUtil;
import com.example.socialmediaapp.helper.Constant;
import com.example.socialmediaapp.model.MemberDetails;
import com.example.socialmediaapp.model.UserProfile;
import com.example.socialmediaapp.services.CallWebServices;
import com.example.socialmediaapp.services.DataResult;
import com.example.socialmediaapp.services.QueryService;
import com.example.socialmediaapp.services.ServiceConstant;
import com.example.socialmediaapp.utils.ValidationUtils;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateValidatorPointBackward;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.textfield.TextInputEditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class EditProfileFragment extends Fragment {

    FragmentEditProfileBinding binding;
    private Long selectedDate;
    private boolean isValidBirthday = false;


    private TextInputEditText mEmail;
    private TextInputEditText mName;
    private EditText mMobileNo;
    private TextInputEditText mDob;

    Button mSave;

    private String countryCode = "";
    private String mobileNo;
    private String email;
    private String name;
    private String dob;
    private String newDOB;


    private MemberDetails memberDetails = new MemberDetails();

    private Activity activity;
    private Activity currentActivity;
    private String guid = "";
    private int source;

    private Context context;

    private ApplicationClass application = new ApplicationClass();






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Your existing code
        binding = FragmentEditProfileBinding.inflate(inflater,container,false);
        View view = binding.getRoot();







        mMobileNo = binding.etEditProfilePhone;
        mEmail = binding.etEditProfileEmail;
        mDob = binding.etBirthdayEditProfile;
        mSave = binding.btnEditProfileSave;
        mName = binding.etEditProfileName;



        SharedPreferences sharedPreferences = getContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("currentFragment", "editProfile");
        editor.apply(); // or editor.commit();



        // Your existing code
        return view;
    }




    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity = getActivity();
        currentActivity = getActivity();
        Log.d("ACTIVITY",""+activity);
        guid = ((ApplicationClass) activity.getApplication()).getUserProfile().getGUID();
        //application = ((ApplicationClass) activity.getApplication());
        reqProfileGetDetails();
    }

    private void setupViews() {
//        toolbar = findViewById(R.id.toolbar);
//        mScrollView = findViewById(R.id.mScrollView);
//        mTitle = findViewById(R.id.mTitle);
//        mProfileTitle = findViewById(R.id.mProfileTitle);
//        mNormalLayout = findViewById(R.id.mNormalLayout);
//        mFacebookLayout = findViewById(R.id.mFacebookLayout);
//        mGoogleLayout = findViewById(R.id.mGoogleLayout);
//        mAppleLayout = findViewById(R.id.mAppleLayout);
//        mParentLayout = findViewById(R.id.mParentLayout);
//        countryCodePicker = findViewById(R.id.countryCodeHolder);
//        mChangePasswordLayout = findViewById(R.id.mChangePasswordLayout);
//        mChangePIN = findViewById(R.id.mChangePIN);
//        txtLinkedAccount = findViewById(R.id.txtLinkedAccount);
//        manageScroll();

//        mParentLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                closeKeyboard(activity);
//            }
//        });
        UserProfile userProfile = ((ApplicationClass) activity.getApplication()).getUserProfile();

//        int source = ((ApplicationClass) context).getLoginRegisterSource();
        //if login/register source is empty
        if (source == 0) {
            if (!CommonUtil.validateEmail(userProfile.getEmailMobileSocialId())) {//if emailMobileSocialID is social account id
                //mChangePasswordLayout.setVisibility(View.GONE);
            }
        } else {//if login/register source is third party
            if (source != Constant.NORMAL_LOGIN) {
                //mChangePasswordLayout.setVisibility(View.GONE);
            }
        }

        //show logged in with
//        if (source == Constant.NORMAL_LOGIN) {
////            mNormalLayout.setVisibility(View.VISIBLE);
//            txtLinkedAccount.setVisibility(View.GONE);
//        } else if (source == Constant.FACEBOOK_LOGIN) {
//            mFacebookLayout.setVisibility(View.VISIBLE);
//        } else if (source == Constant.GOOGLE_LOGIN) {
//            mGoogleLayout.setVisibility(View.VISIBLE);
//        } else if (source == Constant.APPLE_LOGIN) {
//            mAppleLayout.setVisibility(View.VISIBLE);
//        }

//        gPin = ((ApplicationClass) getApplicationContext()).getGPin();
//        if (gPin.equals("false")) {
//            mChangePIN.setText(getString(R.string.setup_PIN));
//        } else {
//            mChangePIN.setText(getString(R.string.Change_Redemption_PIN));
//        }

//        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
        mobileNo = "+" + memberDetails.getMobile();
//        try {
//            Phonenumber.PhoneNumber numberProto = phoneUtil.parse(mobileNo, "");
//            countryCodePicker.setCountryForPhoneCode(numberProto.getCountryCode());
//            mMobileNo.setText(String.valueOf(numberProto.getNationalNumber()));
//        } catch (NumberParseException e) {
//            e.printStackTrace();
//            mMobileNo.setText(memberDetails.getMobile());
//        }
//        countryCode = "";

        mDob.setText(memberDetails.getBirthDate());

        String dob_update = ((ApplicationClass) activity.getApplication()).getBirthDateUpdate();
//        if (dob_update.equalsIgnoreCase("true")) {
//            mDob.setEnabled(true);
//        } else {
//            mDob.setEnabled(false);
//            mDob.setBackground(getDrawable(R.drawable.rounded_corner_shape));
//        }

        //mNRIC.setText(memberDetails.getNRIC());
        mName.setText(memberDetails.getFullname());
//        mName.setFilters(new InputFilter[]{filter});

//        mDob.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                newDOB = mDob.getText().toString();
//                DialogFragment newFragment = new SelectDateFragment().newInstance(mDob, newDOB);
//                newFragment.show(getSupportFragmentManager(), "DatePicker");
//            }
//        });

        mEmail.setText(memberDetails.getEmail());
//        mEmail.setFilters(new InputFilter[]{filter});

        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkInput()) {
                    String dob_update = ((ApplicationClass) activity.getApplication()).getBirthDateUpdate();
//                    if (!newDOB.equalsIgnoreCase(memberDetails.getBirthDate()) && dob_update.equalsIgnoreCase("true")) {
//                        UpdateBirthDateConfirmationDialog(getString(R.string.update_dob_warning), getString(R.string.Gentle_Reminder));
//                    }
//                    else {
//                        reqSendUpdateOTP();
//                    }
                    reqSendUpdateOTP();
                }
            }
        });

//        mChangePasswordLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                preventMultiClick(mChangePasswordLayout);
//                showPopUpChangePwd();
//            }
//        });
//
//        mChangePIN.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                preventMultiClick(mChangePIN);
//                showPopUpChangePIN();
//            }
//        });

    }


    private void reqProfileGetDetails() {
        UserProfile userProfile = ((ApplicationClass) activity.getApplication()).getUserProfile();
        String strEnData = userProfile.getEmailMobileSocialId() + "|" + userProfile.getPassword() + "|" + guid;

        Bundle b = new Bundle();
        b.putString(ServiceConstant.EN_DATA, strEnData);
        b.putInt(QueryService.COMMAND, QueryService.QUERY_PROFILE_GET_DETAILS);
        new CallWebServices(QueryService.QUERY_PROFILE_GET_DETAILS, activity, true).execute(b);
    }


    private void reqSendUpdateOTP() {
        UserProfile userProfile = ((ApplicationClass) activity.getApplication()).getUserProfile();
        String strEnData = userProfile.getEmailMobileSocialId() + "|" + userProfile.getPassword() + "|" + "" + "|" + email + "|" + mobileNo +
                "|" + guid;

        Bundle b = new Bundle();
        b.putString(ServiceConstant.EN_DATA, strEnData);
        b.putInt(QueryService.COMMAND, QueryService.QUERY_SEND_UPDATE_OTP);
        new CallWebServices(QueryService.QUERY_SEND_UPDATE_OTP, activity, true).execute(b);

    }
    public void processWSData(Bundle resultBundle) {

        int command = resultBundle.getInt(QueryService.COMMAND);
        DataResult dataResult = resultBundle.getParcelable("dataResult");

        if (command == QueryService.QUERY_PROFILE_GET_DETAILS) {

            if (dataResult.values != null) {
                ArrayList<String> strInfoList = CommonUtil.tokenize(
                        dataResult.values.get(0), QueryService.DELIMITER_FIELD);

                /*1. Email
                  2. mobile
                  3. Name
                  4. Birthdate
                  5. Birthdate update indicator
                  6. NRIC
                  7. regSource
                   */
                if (strInfoList.size() >= 5) {
                    memberDetails = new MemberDetails(strInfoList.get(2)
                            .trim(), "", "", "",
                            "", "", "",
                            "", "", strInfoList.get(0).trim(),
                            strInfoList.get(1).trim(), "",
                            strInfoList.get(3).trim(),
                            "", strInfoList.get(5).trim());
                }
                Log.d("MEMBER DETAIL","EMAIL : "+strInfoList.get(0));
                Log.d("MEMBER DETAIL","BIRTHDATE UPDATE INDICATOR : "+strInfoList.get(4));

                Log.d("ACTIVITY",""+activity);
                ((ApplicationClass) activity.getApplicationContext()).setBirthDateUpdate(strInfoList.get(4).trim());


                //((ApplicationClass) requireActivity().getApplication()).setBirthDateUpdate(strInfoList.get(4).trim());
                source = Integer.parseInt(strInfoList.get(6));
            }
            else {

            }
            setupViews();
        }
//        else if (command == QueryService.QUERY_SEND_UPDATE_OTP) {
//            ArrayList<String> reqOtp = null;
//            reqOtp = CommonUtil.tokenize(dataResult.values.get(0), QueryService.DELIMITER_FIELD);
//            /*if (reqOtp.get(0).equals("true") && otpDialog == false) {
//                mobileNo = reqOtp.get(1);
//                verifyByOtp();*/
//            if (reqOtp.get(0).equals("true")) {
//                startResendTimer(60);
//                if(otpDialog == false){
//                    mobileNo = reqOtp.get(1);
//                    verifyByOtp();
//                }
//            } else if (reqOtp.get(0).equals("false")) {
//                otpCode = "";
//                reqUpdateOTPConfirmation("");
//            }
//
//        }
//        else if (command == QueryService.QUERY_UPDATE_OTP_CONFIRMATION) {
//            ArrayList<String> updateStr = null;
//            updateStr = CommonUtil.tokenize(dataResult.values.get(0), QueryService.DELIMITER_FIELD);
//            UserProfile userProfile = ((ApplicationClass) getApplicationContext()).getUserProfile();
//
//            //get indicator whether user need to confirm email or not
//            if (updateStr.get(0).equals("true")) {
//                displayTitleAlertDialog(getString(R.string.verify_new_email), getString(R.string.verify_email));
//            } else if (updateStr.get(0).equals("false")) {
//                displayTitleAlertCloseDialog(getString(R.string.Member_details_updated), getString(R.string.Success));
//            }
//            userProfile.setProfileName(name);
//
//            userProfile.setEmailMobileSocialId(updateStr.get(1)); //mobile number
//            ((ApplicationClass) getApplicationContext()).setUserProfile(userProfile);
//            if (dialog != null) { //close the otp dialog (if mobile is changed)
//                dialog.dismiss();
////                otpDialog = false;
//            }
//
//            reqProfileGetDetails();
//
//        }
//        else if (command == QueryService.QUERY_CHANGE_PASSWORD) {
//            passwordDialog.dismiss();
//
//            //update user password
//            UserProfile userProfile = ((ApplicationClass) getApplicationContext()).getUserProfile();
//            userProfile.setPassword(newPwd);
//            ((ApplicationClass) getApplicationContext()).setUserProfile(userProfile);
//
//            displayTitleAlertDialog(getString(R.string.change_password_success), getString(R.string.Success));
//        }
//        else if (command == QueryService.QUERY_CHANGE_PIN) {
//            dialogChangePin.dismiss();
//            if (gPin.equals("false")) {
//                displayTitleAlertDialog(getString(R.string.Redemption_PIN_setup), getString(R.string.Success));
//                ((ApplicationClass) getApplicationContext()).setGPin("true");
//                mChangePIN.setText(getString(R.string.PIN_change));
//            } else if (gPin.equals("true")) {
//                displayTitleAlertDialog(getString(R.string.Redemption_PIN_changed), getString(R.string.Success));
//            }
//        }
//        else if (command == QueryService.QUERY_PROFILE_FORGET_PIN) {
//            displayTitleAlertDialog(getString(R.string.Please_check_your_email_sms_to_reset_pin), getString(R.string.Success));
//
//        }
    }

    private boolean checkInput() {
        boolean isValid = true;

//        countryCode = countryCodePicker.getSelectedCountryCode();
//        mobileNo = countryCode + mMobileNo.getText().toString();
        email = mEmail.getText().toString();
        name = mName.getText().toString();
        newDOB = mDob.getText().toString();

        if (name.length() == 0) {
            mName.setError(getString(R.string.EnterYourName));
            focusOnView(mName);
            isValid = false;
        } else if (name.length() > 0) {
            mName.setError(null);
        }
        if(newDOB.length() == 0){
            //displayErrorAlertDialog(getString(R.string.enter_your_dob));
            isValid = false;
        } else {
            try { // use to check date is valid only
                SimpleDateFormat dateFormat = new SimpleDateFormat(CommonUtil.YYYYMMDD_, Locale.ENGLISH);
                Date dateObj = dateFormat.parse(newDOB);
            } catch (ParseException e) {
                //displayErrorAlertDialog(getString(R.string.invalid_dob));
                isValid = false;
            }
        }

        if (mMobileNo.getText().toString().length() == 0) {
            mMobileNo.setError(getString(R.string.enter_mobile_number));
            focusOnView(mMobileNo);
            isValid = false;
        } else if (mobileNo.length() > 0) {
            mMobileNo.setError(null);
        }

       /* if(email.length() == 0){
            mEmail.setError(getString(R.string.enter_your_email));
            isValid = false;
        }*/

        if (isValid) {
            if (email.length() > 0) {
                if (!CommonUtil.validateEmail(email)) {
                    mEmail.setError(getString(R.string.err_invalid_email));
                    isValid = false;
                } else {
                    mEmail.setError(null);
                }
            }

        }

        if (isValid) {
//            PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
//            Phonenumber.PhoneNumber numberProto = new Phonenumber.PhoneNumber();
//            try {
//                numberProto = phoneUtil.parse("+" + mobileNo, "");
//            } catch (NumberParseException e) {
//                e.printStackTrace();
//            }
//            if (!phoneUtil.isValidNumber(numberProto)) {
//                mMobileNo.setError(getString(R.string.invalid_mobile_number));
//                focusOnView(mMobileNo);
//                isValid = false;
//            } else {
//                mMobileNo.setError(null);
//            }
        }

    /*    if(isValid){
            if(maritalStatus.equalsIgnoreCase("-")){
                displayTitleAlertDialog(getString(R.string.err_blank_marital), getString(R.string.incomplete_data));
                isValid = false;
            }
        }*/

        return isValid;
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


    private void focusOnView(final View view) {
        view.getParent().requestChildFocus(view, view);
    }
}