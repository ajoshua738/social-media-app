package com.example.socialmediaapp.ui.login;



import androidx.appcompat.app.AppCompatActivity;

import androidx.lifecycle.ViewModelProvider;


import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.RadioGroup;


import com.example.socialmediaapp.R;
import com.example.socialmediaapp.data.user.User;
import com.example.socialmediaapp.data.user.UserDataHolder;
import com.example.socialmediaapp.data.user.UserViewModel;
import com.example.socialmediaapp.databinding.ActivityLoginBinding;
import com.example.socialmediaapp.helper.ApplicationClass;
import com.example.socialmediaapp.helper.CommonUtil;
import com.example.socialmediaapp.helper.Constant;
import com.example.socialmediaapp.model.UserProfile;
import com.example.socialmediaapp.services.CallWebServices;
import com.example.socialmediaapp.services.DataResult;
import com.example.socialmediaapp.services.QueryService;
import com.example.socialmediaapp.services.ServiceConstant;
import com.example.socialmediaapp.ui.main.MainUIActivity;
import com.example.socialmediaapp.ui.main.MainUIViewModel;

import com.example.socialmediaapp.ui.recover.ForgotPasswordActivity;
import com.example.socialmediaapp.ui.register.RegisterActivity;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    UserViewModel userViewModel;
    MainUIViewModel mainUIViewModel;
    private boolean isValidAccount = false;

    private String password;
    private String mobileEmail;
    private TextInputEditText mMobileEmail;


    private String loginType = Constant.LOGIN_TYPE_MOBILE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        setContentView(view);


        mMobileEmail = binding.etLoginEmail;
//        binding.etLoginEmail.setText("test@gmail.com");
//        binding.etLoginPassword.setText("Abcd1234.");
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        mainUIViewModel = new ViewModelProvider(this).get(MainUIViewModel.class);
//        Intent intent = new Intent(this, MainUIActivity.class);
//        startActivity(intent);

        mMobileEmail.setHint(getString(R.string.mobile_no_example));
        binding.etLoginPassword.setHint("Password");
        RadioGroup radioGroup = findViewById(R.id.rgLoginType);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Handle the checked change event here
                if (checkedId == R.id.rbLoginMobile) {
                    // Handle radio button 1 selection
                    loginType = Constant.LOGIN_TYPE_MOBILE;
                    mMobileEmail.setInputType(InputType.TYPE_CLASS_NUMBER);
                    mMobileEmail.setHint(getString(R.string.mobile_no_example));
                    mMobileEmail.setFilters(new InputFilter[] { new InputFilter.LengthFilter(15)}); //longest phone number in the world
                } else if (checkedId == R.id.rbLoginEmail) {
                    // Handle radio button 2 selection
                    loginType = Constant.LOGIN_TYPE_EMAIL;
                    mMobileEmail.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                    mMobileEmail.setHint(getString(R.string.email));
                    mMobileEmail.setFilters(new InputFilter[] { new InputFilter.LengthFilter(100)}); //max length of db
                }
            }
        });


        binding.btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(checkEmail() && checkPassword()){
//                    checkAccount();
//                }
                password = binding.etLoginPassword.getText().toString().trim();
                mobileEmail = binding.etLoginEmail.getText().toString().trim();
                ((ApplicationClass) getApplicationContext()).setLoginRegisterSource(Constant.NORMAL_LOGIN);
                reqLogin("", password, mobileEmail); //normal login


            }
        });


        binding.tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });





        //inputChange();
        binding.etLoginEmail.setText("60163070048");
        binding.etLoginPassword.setText("123456.");

    }

    private void checkAccount(){
        String currentEmail = binding.etLoginEmail.getText().toString();
        String currentPassword = binding.etLoginPassword.getText().toString();

        User user = userViewModel.getUserByEmail(currentEmail);
        if(user != null){
            isValidAccount = user.getPassword().equals(currentPassword);
            if(!isValidAccount){
                binding.inLayoutLoginPassword.setError(getString(R.string.password_incorrect));
            }else{
                binding.inLayoutLoginPassword.setError(null);
            }
        }
        else{
            isValidAccount = false;
            binding.inLayoutLoginEmail.setError("No account found with this email");
        }

        if(isValidAccount){
            Intent intent = new Intent(LoginActivity.this, MainUIActivity.class);
            UserDataHolder.setUser(user);
            //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

        }

    }


    private void inputChange(){
        binding.etLoginEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkEmail();


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        binding.etLoginPassword.addTextChangedListener(new TextWatcher() {
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

        binding.etLoginEmail.setText("60163070048");
        binding.etLoginPassword.setText("123456.");
    }

    private boolean checkEmail(){
        String currentEmail = binding.etLoginEmail.getText().toString();
        if(!currentEmail.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(currentEmail).matches()){
            binding.inLayoutLoginEmail.setError(null);
            return true;
        }
        else{
            binding.inLayoutLoginEmail.setError(getString(R.string.register_email_error));
            return false;
        }
    }

    private boolean checkPassword(){
        String currentPassword = binding.etLoginPassword.getText().toString();
        if(!currentPassword.isEmpty()){
            binding.inLayoutLoginPassword.setError(null);
            return true;
        }else{
            binding.inLayoutLoginPassword.setError(getString(R.string.register_password_error));
            return false;

        }
    }

    public void reqLogin(String userName, String password, String emailMobile) {
        String imei = ((ApplicationClass) getApplicationContext()).getImei();
        String token = ((ApplicationClass) getApplicationContext()).getFinalToken();
        int source = ((ApplicationClass) getApplicationContext()).getLoginRegisterSource();

        String platform = "";
        if (((ApplicationClass) getApplicationContext()).getFirebaseToken().length() > 0) {
            platform = Constant.ANDROID;
        } else if (((ApplicationClass) getApplicationContext()).getHuaweiToken().length() > 0) {
            platform = Constant.HUAWEI;
        } else {
            platform = "";
        }

        if (token != null) {
            //Log.e("MOBILE ID", token);
        } else {
            token = "";
        }

        String strEnData = emailMobile + "|" + password + "|" + userName + "|" + String.valueOf(source) + "|" + token + "|" + platform + "|" + imei;

        Bundle b = new Bundle();
        b.putString(ServiceConstant.EN_DATA, strEnData);
        b.putInt(QueryService.COMMAND, QueryService.QUERY_PROFILE_LOGIN);
        new CallWebServices(QueryService.QUERY_PROFILE_LOGIN, LoginActivity.this, true).execute(b);

    }

    public void processWSData(Bundle resultBundle) {
        int command = resultBundle.getInt(QueryService.COMMAND);

        ArrayList<String> loginProfile = null;
        UserProfile userProfile;

        DataResult dataResult = resultBundle.getParcelable("dataResult");
        if (command == QueryService.QUERY_PROFILE_LOGIN) {

            if (dataResult.values != null && dataResult.values.size() >= 1) {
                Log.d("Data Result in login activity",""+dataResult.values.get(0));
                loginProfile = CommonUtil.tokenize(dataResult.values.get(0), QueryService.DELIMITER_FIELD);
            }
            else {
                //displayErrorAlertDialog(getString(R.string.err_result_data));
                Log.d("Login Error","error in processWSData");
            }

            /*1.emailMobileSocialID
              2.password
              3.profileName*/
            int loginSource = ((ApplicationClass) getApplicationContext()).getLoginRegisterSource();

            if(loginSource == Constant.NORMAL_LOGIN){
                userProfile = new UserProfile(loginProfile.get(0), password, loginProfile.get(2), "");//get password from user input
            }else{
                userProfile = new UserProfile(loginProfile.get(0), loginProfile.get(1), loginProfile.get(2), "");//get password from backend
            }

            ((ApplicationClass) getApplicationContext()).setUserProfile(userProfile);

            ((ApplicationClass) getApplicationContext()).setLoginStatus(true);

            goNext();
            //reqMobileIdRegister();


        }
        else if (command == QueryService.QUERY_REGISTER_MOBILEID) {
            goNext();
        }
//        else if (command == QueryService.QUERY_PROFILE_LOGIN_GET_OTP){
//            goVerifyOTP();
//        }

    }

    private void reqMobileIdRegister() {
        String token = ((ApplicationClass) getApplicationContext()).getFinalToken();
        String platform = "";

        if (((ApplicationClass) getApplicationContext()).getFirebaseToken().length() > 0) {
            platform = Constant.ANDROID;
        } else if (((ApplicationClass) getApplicationContext()).getHuaweiToken().length() > 0) {
            platform = Constant.HUAWEI;
        } else {
            platform = "";
        }

        if (token != null) {
            //Log.e("MOBILE ID", token);
        } else {
            token = "";
        }

        //Log.e("LOGIN NOTIFICATION", "token: " + token);
        //Log.e("LOGIN NOTIFICATION", "platform: " + platform);

        UserProfile userProfile = ((ApplicationClass) getApplicationContext()).getUserProfile();
        String imei = ((ApplicationClass) getApplicationContext()).getImei();
        String strEnData = userProfile.getEmailMobileSocialId() + "|" + userProfile.getPassword() + "|" + token + "|" + platform + "|" + imei;

        Bundle b = new Bundle();
        b.putString(ServiceConstant.EN_DATA, strEnData);
        b.putInt(QueryService.COMMAND, QueryService.QUERY_REGISTER_MOBILEID);

        new CallWebServices(QueryService.QUERY_REGISTER_MOBILEID, LoginActivity.this, false).execute(b);
    }

    public void goNext() {
        Intent intent = new Intent(LoginActivity.this, MainUIActivity.class);
        startActivityForResult(intent, RESULT_CANCELED);
    }

}