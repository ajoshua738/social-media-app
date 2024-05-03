package com.example.socialmediaapp.helper;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.socialmediaapp.R;
import com.example.socialmediaapp.model.UserProfile;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ApplicationClass extends com.orm.SugarApp {

    private SharedPreferences sharedPreferences;
    public int QRcodeWidth;
    private int screenWidth;
    //LocalizationApplicationDelegate localizationDelegate = new LocalizationApplicationDelegate();
    public byte[] imageByte;
    public Bitmap rawBitmap = null;
    public String imageString = "";
    public String photoSource = "";
    public Uri imageUri = null;
    public int currentOrientation = 0;
    //public static Card currentCard = null;

//    @Nullable
//    public static RecaptchaTasksClient recaptchaTasksClient = null;

//    @Override
//    protected void attachBaseContext(Context base) {
//        super.attachBaseContext(localizationDelegate.attachBaseContext(base));
//    }
//
//    @Override
//    public void onConfigurationChanged(Configuration newConfig) {
//        super.onConfigurationChanged(newConfig);
//        localizationDelegate.onConfigurationChanged(this);
//
//    }
//
//    @Override
//    public Context getApplicationContext() {
//        return localizationDelegate.getApplicationContext(super.getApplicationContext());
//    }

    @Override
    public void onCreate() {
        super.onCreate();

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        //initializeRecaptchaClient(); //commented due to many "Inter Error" issue arise when member try to sign up
    }

//    private void initializeRecaptchaClient() {
//        Recaptcha
//                .getTasksClient(this, getString(R.string.recaptcha_key))
//                .addOnSuccessListener(
//                        new OnSuccessListener<RecaptchaTasksClient>() {
//                            @Override
//                            public void onSuccess(RecaptchaTasksClient client) {
//                                recaptchaTasksClient = client;
//                            }
//                        })
//                .addOnFailureListener(
//                        new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                                // Handle communication errors ...
//                                // See "Handle communication errors" section
//                                if (e instanceof ApiException) {
//                                    ApiException apiException = (ApiException) e;
//                                    Log.e(TAG, "Error message: " +
//                                            CommonStatusCodes.getStatusCodeString(apiException.getStatusCode()));
//
//                                } else {
//                                    Log.e(TAG, "Unknown type of error: " + e.getMessage());
//                                }
//                            }
//                        });
//    }

    public void setLoginRegisterSource(int source) {
        sharedPreferences.edit().putInt(getString(R.string.pref_login_register_source), source).commit();
    }

    public int getLoginRegisterSource() {
        int source = sharedPreferences.getInt(getString(R.string.pref_login_register_source), Constant.NORMAL_LOGIN); //default or normal login
        return source;
    }

    public void setSocialID(String socialID) {
        sharedPreferences.edit().putString(getString(R.string.pref_login_register_id), socialID).commit();
    }

    public String getSocialID() {
        String id = sharedPreferences.getString(getString(R.string.pref_login_register_id), "");
        return id;
    }

    public void setAppleAuthCode(String email) {
        sharedPreferences.edit().putString(Constant.APPLE_AUTHORIZATION_CODE, email).commit();
    }

    public String getAppleAuthCode() {
        String authCode = sharedPreferences.getString(Constant.APPLE_AUTHORIZATION_CODE, "");
        return authCode;
    }

    public void setEmail(String email) {
        sharedPreferences.edit().putString(getString(R.string.pref_email), email).commit();
    }

    public String getEmail() {
        String email = sharedPreferences.getString(getString(R.string.pref_email), "");
        return email;
    }

    public void setName(String name) {
        sharedPreferences.edit().putString(getString(R.string.pref_name), name).commit();
    }

    public String getName() {
        String name = sharedPreferences.getString(getString(R.string.pref_name), "");
        return name;
    }

    public void setMobileNo(String mobileNo) {
        sharedPreferences.edit().putString(getString(R.string.pref_mobile_no), mobileNo).commit();
    }

    public String getMobileNo() {
        String mobileNo = sharedPreferences.getString(getString(R.string.pref_mobile_no), "");
        return mobileNo;
    }

    public void setBirthDate(String dob) {
        sharedPreferences.edit().putString(getString(R.string.pref_dob), dob).commit();
    }

    public String getBirthDate() {
        String dob = sharedPreferences.getString(getString(R.string.pref_dob), "");
        return dob;
    }

    public void setBirthDateUpdate(String update) {
        sharedPreferences.edit().putString(getString(R.string.pref_dob_update), update).commit();
    }

    public String getBirthDateUpdate() {
        String dob_update = sharedPreferences.getString(getString(R.string.pref_dob_update), "false");
        return dob_update;
    }

//    public CardBalance getPoint() {
//
//        //retrieve point info
//        Gson gson = new Gson();
//        String json = sharedPreferences.getString(getString(R.string.pref_point), "");
//        CardBalance point = gson.fromJson(json, CardBalance.class);
//
//        return point;
//    }

//    public void setPoint(CardBalance point) {
//        //save point info
//        Gson gson = new Gson();
//        String json = gson.toJson(point);
//        sharedPreferences.edit().putString(getString(R.string.pref_point), json).commit();
//
//    }

    public UserProfile getUserProfile() {

        //retrieve member info
        Gson gson = new Gson();
        String json = sharedPreferences.getString(getString(R.string.pref_user_profile), "");
        UserProfile userProfile = gson.fromJson(json, UserProfile.class);

        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        //save member info
        Gson gson = new Gson();
        String json = gson.toJson(userProfile);
        sharedPreferences.edit().putString(getString(R.string.pref_user_profile), json).commit();

    }

//    public ArrayList<ReferralByCardType> getShownReferral() {
//        Gson gson = new Gson();
//        String json = sharedPreferences.getString(getString(R.string.pref_shown_referral), "");
//        Type type = new TypeToken<ArrayList<ReferralByCardType>>() {
//        }.getType();
//
//        return gson.fromJson(json, type);
//    }

//    public void setShownReferral(ArrayList<ReferralByCardType> shownReferral) {
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        Gson gson = new Gson();
//        String json = gson.toJson(shownReferral);
//        editor.putString(getString(R.string.pref_shown_referral), json);
//        editor.apply();
//
//    }

    public String getCurrentLanguage() {
        String language = sharedPreferences.getString(getString(R.string.pref_language), Constant.LANGUAGE_ENGLISH);
        return language;
    }

    public void setLanguage(String language) {
        sharedPreferences.edit().putString(getString(R.string.pref_language), language).commit();
    }

    public int getLastSelectedCard() {
        int position = sharedPreferences.getInt(getString(R.string.pref_last_selected_card), 0);
        return position;
    }

    public void setIsNew(String isNew) {
        sharedPreferences.edit().putString(getString(R.string.pref_isnew), isNew).commit();
    }

    public String getIsNew() {
        String isNew = sharedPreferences.getString(getString(R.string.pref_isnew), "");
        return isNew;
    }

    public void setGPin(String gPin) {
        sharedPreferences.edit().putString(getString(R.string.pref_gpin), gPin).commit();
    }

    public String getGPin() {
        String gPin = sharedPreferences.getString(getString(R.string.pref_gpin), "");
        return gPin;
    }

    public void setLastSelectedCard(int position) {
        sharedPreferences.edit().putInt(getString(R.string.pref_last_selected_card), position).commit();
    }

    public boolean getEditProfileDialogStatus() {
        boolean status = sharedPreferences.getBoolean(getString(R.string.pref_edit_profile_dialog_status), false);
        return status;
    }

    public void setEditProfileDialogStatus(boolean status) {
        sharedPreferences.edit().putBoolean(getString(R.string.pref_edit_profile_dialog_status), status).commit();
    }

    public boolean getShowReceiptTips() {
        boolean status = sharedPreferences.getBoolean(getString(R.string.pref_show_receipt_tips), true);
        return status;
    }

    public void setShowReceiptTips(boolean status) {
        sharedPreferences.edit().putBoolean(getString(R.string.pref_show_receipt_tips), status).commit();
    }


    public boolean isLoggingOut() {
        boolean status = sharedPreferences.getBoolean(getString(R.string.pref_is_logging_out), false);
        return status;
    }

    public void setIsLoggingOut(boolean status) {
        sharedPreferences.edit().putBoolean(getString(R.string.pref_is_logging_out), status).commit();
    }

    public boolean getAllowRate() {
        boolean flag = sharedPreferences.getBoolean(getString(R.string.pref_allow_rate), true);
        return flag;
    }

    public void setAllowRate(boolean flag) {
        sharedPreferences.edit().putBoolean(getString(R.string.pref_allow_rate), flag).commit();
    }

    public String getFirebaseToken() {
        String token = sharedPreferences.getString(getString(R.string.pref_token), "");
        return token;
    }

    public void setFirebaseToken(String token) {
        sharedPreferences.edit().putString(getString(R.string.pref_token), token).commit();
    }

    public String getHuaweiToken() {
        return sharedPreferences.getString(getString(R.string.pref_huawei_token), "");
    }

    public void setHuaweiToken(String token) {
        sharedPreferences.edit().putString(getString(R.string.pref_huawei_token), token).apply();
    }

    public String getFinalToken() {
        String token = "";
        String huaweiToken = ((ApplicationClass) getApplicationContext()).getHuaweiToken();
        String firebaseToken = ((ApplicationClass) getApplicationContext()).getFirebaseToken();

        if (firebaseToken.length() > 0) {
            token = firebaseToken;
        } else {
            token = huaweiToken;
        }

        return token;
    }

    public void setIsHuawei(boolean flag) {
        sharedPreferences.edit().putBoolean(getString(R.string.pref_is_huawei), flag).apply();
    }

    public boolean isHuawei() {
        return sharedPreferences.getBoolean(getString(R.string.pref_is_huawei), false);
    }

    public String getLastUpdateDate() {
        String date = sharedPreferences.getString(getString(R.string.pref_last_noti_update_date), "");
        return date;
    }

    public void setLastUpdateDate(String date) {
        sharedPreferences.edit().putString(getString(R.string.pref_last_noti_update_date), date).commit();
    }

    public boolean getLoginStatus() {
        boolean loginStatus = sharedPreferences.getBoolean(getString(R.string.pref_login_status), false);
        return loginStatus;
    }

    public void setLoginStatus(boolean loginStatus) {
        sharedPreferences.edit().putBoolean(getString(R.string.pref_login_status), loginStatus).commit();
    }

    public boolean getTriggeredFromFirebase() {
        boolean status = sharedPreferences.getBoolean(getString(R.string.pref_triggered_from_firebase), false);
        return status;
    }

    public void setTriggeredFromFirebase(boolean status) {
        sharedPreferences.edit().putBoolean(getString(R.string.pref_triggered_from_firebase), status).commit();
    }

    public int getNotificationCount() {
        int count = sharedPreferences.getInt(getString(R.string.pref_notification_count), 0);
        return count;
    }

    public void setNotificationCount(int count) {
        sharedPreferences.edit().putInt(getString(R.string.pref_notification_count), count).commit();
    }

    public String getImei() {
        String imei = sharedPreferences.getString(getString(R.string.pref_imei), "");
        return imei;
    }

    public void setImei(String imei) {
        sharedPreferences.edit().putString(getString(R.string.pref_imei), imei).commit();
    }

    private void getWidthAndHeight(Activity activity) {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        screenWidth = displaymetrics.widthPixels;

        QRcodeWidth = screenWidth / 2;
    }

//    public Bitmap TextToImageEncode(String Value, Activity activity) throws WriterException {
//        getWidthAndHeight(activity);
//        BitMatrix bitMatrix;
//        try {
//            bitMatrix = new MultiFormatWriter().encode(
//                    Value,
//                    BarcodeFormat.DATA_MATRIX.QR_CODE,
//                    QRcodeWidth, QRcodeWidth, null
//            );
//
//        } catch (IllegalArgumentException Illegalargumentexception) {
//
//            return null;
//        }
//        int bitMatrixWidth = bitMatrix.getWidth();
//        int bitMatrixHeight = bitMatrix.getHeight();
//
//        int[] pixels = new int[bitMatrixWidth * bitMatrixHeight];
//        int black = activity.getResources().getColor(R.color.cs_black_primary_text);
//        int white = activity.getResources().getColor(R.color.cs_super_light_grey);
//
//        for (int y = 0; y < bitMatrixHeight; y++) {
//            int offset = y * bitMatrixWidth;
//
//            for (int x = 0; x < bitMatrixWidth; x++) {
//
//                pixels[offset + x] = bitMatrix.get(x, y) ? black : white;
//            }
//        }
//        Bitmap bitmap = Bitmap.createBitmap(bitMatrixWidth, bitMatrixHeight, Bitmap.Config.ARGB_8888);
//
//        bitmap.setPixels(pixels, 0, QRcodeWidth, 0, 0, bitMatrixWidth, bitMatrixHeight);
//        return bitmap;
//    }


}
