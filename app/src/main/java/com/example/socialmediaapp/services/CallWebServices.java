package com.example.socialmediaapp.services;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;

import com.example.socialmediaapp.R;
import com.example.socialmediaapp.helper.BaseActivity;
import com.example.socialmediaapp.helper.CustomTypefaceSpan;
import com.example.socialmediaapp.model.UserProfile;
import com.example.socialmediaapp.ui.login.LoginActivity;
import com.example.socialmediaapp.ui.main.MainUIActivity;
import com.example.socialmediaapp.ui.main.fragments.HomePageFragment;
import com.example.socialmediaapp.ui.main.fragments.MerchantCardFragment;
import com.example.socialmediaapp.ui.main.profile.EditProfileFragment;
import com.example.socialmediaapp.ui.register.RegisterActivity;

import java.lang.ref.WeakReference;

public class CallWebServices extends AsyncTask<Bundle, Void, Bundle> {

    public static int module;
    private Context context;
    private boolean showProgress;
    private WeakReference<Context> contextReference;

    private ProgressDialog progress;

    public CallWebServices(int module, Context context, boolean showProgress) {

        this.context = context;
        this.module = module;
        this.showProgress = showProgress;
        this.contextReference = new WeakReference<>(context);
//        this.staticContext = context;
    }


    @Override
    protected void onPreExecute() {

        if (showProgress && context != null) {
            Typeface typeface = ResourcesCompat.getFont(context, R.font.roboto);
            //SpannableString spannableTitle = new SpannableString("Loading");
            SpannableString spannableTitle = new SpannableString(context.getString(R.string.loading));
            StyleSpan styleSpan = new StyleSpan(Typeface.BOLD);
            spannableTitle.setSpan(styleSpan, 0, spannableTitle.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
            spannableTitle.setSpan(new CustomTypefaceSpan("", typeface), 0, spannableTitle.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);

            progress = new ProgressDialog(context);
            progress.setTitle(spannableTitle);
            //progress.setMessage("Wait while loading...");
            progress.setMessage(context.getString(R.string.wait_loading));
            progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
            progress.show();
        }
    }

    @Override
    protected Bundle doInBackground(Bundle... b) {
        //create a new soap request object
        return QueryService.processRequest(b[0], context);
    }

    @Override
    protected void onPostExecute(Bundle resultBundle) {
        super.onPostExecute(resultBundle);

        //handling progress dialog
        if (showProgress) {
            if (progress != null && progress.isShowing()) {
                try {
                    progress.dismiss();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }




        Log.d("INSIDE CALLWEBSERVICE","ONPOST");
        SharedPreferences sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        String currentFragment = sharedPreferences.getString("currentFragment", "");





        String result = resultBundle.getString(QueryService.RESULT);

        DataResult dataResult;

        dataResult = DataResult.create(result); //with decryption

        resultBundle.putParcelable("dataResult", dataResult);
//        Log.e("TAG", "Context Before class: " + context.getClass().toString() + " Called api: " + module);
//        Log.e("TAG", "Static Context getclass: " + staticContext.getClass().toString() + " Called api: " + module);
//        Log.e("TAG", "Static Context: " + staticContext + " Called api: " + module);
        context = contextReference.get();
//        context = staticContext;
//        Log.e("TAG", "Context class: " + context.getClass().toString() + " called api: " + module);
        if (dataResult.resultCode == 0) {
            switch (module) {
                case QueryService.QUERY_PROFILE_LOGIN:
                    if (context instanceof LoginActivity) {
                        ((LoginActivity) context).processWSData(resultBundle);
                    }
//                    else if (context instanceof SignUpActivity) {
//                        ((SignUpActivity) context).processWSData(resultBundle);
//                    }
                    break;
                /*case QueryService.QUERY_PROFILE_REGISTER:
                    ((SignUpActivity) context).processWSData(resultBundle);
                    break;*/
//                case QueryService.QUERY_PROFILE_FORGOT_PWD:
//                    ((ForgotPasswordActivity) context).processWSData(resultBundle);
//                    break;
//                case QueryService.QUERY_USERCARD_GETLIST:
//                    HomeFragment.processWSData(resultBundle);
//                    break;
                case QueryService.QUERY_PROFILE_IS_NEW:
                    Log.d("INSIDE CALLWEBSERVICE","PROFILE IS NEW");
                    //Log.e(TAG, "Context class: profileIsNew " + context.getClass());
                    ((MainUIActivity) context).processWSData(resultBundle);
                    break;
                case QueryService.QUERY_CARD_GETLIST:
                    HomePageFragment.processWSDataCard(resultBundle);
                    //MerchantCardFragment.processWSDataCard(resultBundle);
                    break;
//                case QueryService.QUERY_USERCARD_ADD_V:
//                    AddCardAdapter.processWSData(resultBundle);
//                    break;
//                case QueryService.QUERY_USERCARD_ADD_P:
                case QueryService.QUERY_GET_CATEGORIZE_MERCHANT_HQ:
                    //MerchantCardFragment.processWSData(resultBundle);
                    HomePageFragment.processWSData(resultBundle);
//                    if(currentFragment.equals("HOMEPAGE")){
//                        HomePageFragment.processWSData(resultBundle);
//                    }
//                    else if(currentFragment.equals("MERCHANTCARD")){
//                        MerchantCardFragment.processWSData(resultBundle);
//                    }

                    break;
//
//                case QueryService.QUERY_GETCARDTYPE_BY_CARDNO:
//                    if (context instanceof MainActivity){
//                        MerchantCardFragment.processWSData(resultBundle);
//                    }
//                    break;
//
//                case QueryService.QUERY_GET_CARD_REQUEST_LIST:
//                    ((AddCardRequestActivity) context).processWSData(resultBundle);
//                    break;
//                case QueryService.QUERY_OUTLET_LIST:
//                    if (context instanceof MainActivity) {
//                        HomeFragment.processWSData(resultBundle);
//                        //CardAdapterNew.processWSData(resultBundle);
//                        //CardAdapter.processWSData(resultBundle);
//                    }/*else if(context instanceof OutletListActivity){
//                        ((OutletListActivity) context).processWSData(resultBundle);
//                    }*/
//                    break;
//                case QueryService.QUERY_OUTLET_DETAILS:
//                    ((OutletDetailsActivity) context).processWSData(resultBundle);
//                    break;
//                case QueryService.QUERY_USERCARD_POINTINQUIRY:
//
//                    if (context instanceof RedeemActivity) {
//                        ((RedeemActivity) context).processWSData(resultBundle);
//                    } else if (context instanceof HistoryActivity) {
//                        ((HistoryActivity) context).processWSData(resultBundle);
//                    }
//
//                    break;
//                case QueryService.QUERY_REDEMPTION:
//                    RedeemPointFragment.processWSData(resultBundle);
//                    break;
                //case QueryService.QUERY_PROMOTION_BY_CATEGORY_NEW:
                /*case QueryService.QUERY_USERCARD_DELETE:
                    HomeFragment.processWSData(resultBundle);
                    //CardAdapterNew.processWSData(resultBundle);
//                    CardAdapter.processWSData(resultBundle);
                    break;*/
                /*case QueryService.QUERY_PROMOTION_BY_LOCATION:
                    ((OldPromotionListActivity) context).processWSData(resultBundle);
                    break;*/
//                case QueryService.QUERY_GET_SINGLEPROMO:
//                    ((PromotionDetailsActivity) context).processWSData(resultBundle);
//                    break;
//                case QueryService.QUERY_CARD_TRANSACTION_GET:
//                    ((HistoryActivity) context).processWSData(resultBundle);
//                    break;
//                case QueryService.QUERY_CARD_REDEMPTION_GET:
//                    ((HistoryActivity) context).processWSData(resultBundle);
//                    break;
//                case QueryService.QUERY_CHANGE_PIN:
//                    //((MoreActivity) context).processWSData(resultBundle);
//                    if (context instanceof EditProfileActivity) {
//                        ((EditProfileActivity) context).processWSData(resultBundle);
//                    } else if (context instanceof MainActivity) {
//                        SetupPinFragment.successChangePIN();
//                    }
//                    break;
//                case QueryService.QUERY_PROFILE_FORGET_PIN:
//                    if (context instanceof EditProfileActivity){
//                        ((EditProfileActivity) context).processWSData(resultBundle);
//                    } else if (context instanceof VoucherDetailsActivity){
//                        ((VoucherDetailsActivity) context).processWSData(resultBundle);
//                    } else if (context instanceof RedeemActivity){
//                        RedeemPointFragment.processWSData(resultBundle);
//                    } else if (context instanceof RedeemVoucherDetailActivity){
//                        ((RedeemVoucherDetailActivity) context).processWSData(resultBundle);
//                    }
//                    break;
                case QueryService.QUERY_PROFILE_GET_DETAILS:
                    if(context instanceof MainUIActivity){
                        if(currentFragment.equals("editProfile")){
                            //EditProfileFragment editProfileFragment = new EditProfileFragment();
                            //EditProfileFragment.processWSData(resultBundle);
                            //EditProfileFragment.processWSData(resultBundle);
                        }
                    }
                    break;
//                case QueryService.QUERY_SEND_UPDATE_OTP:
//                    if (context instanceof EditProfileActivity) {
//                        ((EditProfileActivity) context).processWSData(resultBundle);
//                    } else if (context instanceof MainActivity) {
//                        if (UpdateProfileFragment.updateProfile) {
//                            UpdateProfileFragment.processWSData(resultBundle);
//                        } else {
//                            UpdateCardFragment.processWSData(resultBundle);
//                        }
//                    }
//
//                    break;
//                case QueryService.QUERY_UPDATE_OTP_CONFIRMATION:
//                    if (context instanceof EditProfileActivity) {
//                        ((EditProfileActivity) context).processWSData(resultBundle);
//                    } else if (context instanceof MainActivity) {
//                        UpdateCardFragment.processWSData(resultBundle);
//                    }
//                    break;
//                /*case QueryService.QUERY_USERCARD_MEMBER_DETAILSGET:
//                    ((EditProfileActivity) context).processWSData(resultBundle);
//                    break;
//                case QueryService.QUERY_USERCARD_MEMBER_DETAILSUPDATE:
//                    ((EditProfileActivity) context).processWSData(resultBundle);
//                    break;*/
//                case QueryService.QUERY_PROFILE_FEEDBACK:
//                    ((FeedbackActivity) context).processWSData(resultBundle);
//                    break;
//                case QueryService.QUERY_GET_NOTIFICATION_SENT:
//                    ((NotificationActivity) context).processWSData(resultBundle);
//                    break;
//                case QueryService.QUERY_CHANGE_PASSWORD:
//                    ((EditProfileActivity) context).processWSData(resultBundle);
//                    break;
                case QueryService.QUERY_REGISTER_MOBILEID:
                    if (context instanceof LoginActivity) {
                        ((LoginActivity) context).processWSData(resultBundle);
                    }
//                    else if (context instanceof SignUpActivity) {
//                        ((SignUpActivity) context).processWSData(resultBundle);
//                    }
                    else if (context instanceof MainUIActivity) {
                        ((MainUIActivity) context).processWSData(resultBundle);
                    }
//                    else if(context instanceof LoginOTPActivity){
//                        ((LoginOTPActivity) context).processWSData(resultBundle);
//                    }
                    break;
//                case QueryService.QUERY_PLATFORM_UPDATE:
//                    ((MainActivity) context).processWSData(resultBundle);
//                    break;
//                case QueryService.QUERY_PROMO_LIST_GET:
//                    ((PromotionPagerActivity) context).processWSData(resultBundle);
//                    break;
//                case QueryService.QUERY_GET_VOUCHER_LIST_BY_CARD:
//                    if (context instanceof VoucherListActivity) {
//                        ((VoucherListActivity) context).processWSData(resultBundle);
//                    }
//                    break;
//                case QueryService.QUERY_GET_SINGLE_VOUCHER:
//                    if (context instanceof VoucherDetailsActivity) {
//                        ((VoucherDetailsActivity) context).processWSData(resultBundle);
//                    }
//                    break;
//                case QueryService.QUERY_GENERATE_VOUCHER_OTP:
//                    ((VoucherDetailsActivity) context).processWSData(resultBundle);
//                    break;
//                case QueryService.QUERY_GET_VOUCHER_LOG:
//                    ((HistoryActivity) context).processWSData(resultBundle);
//                    break;
//                case QueryService.QUERY_SEND_REG_OTP:
//                    ((SignUpActivity) context).processWSData(resultBundle);
//                    break;
//                case QueryService.QUERY_REG_OTP_CONFIRMATION:
//                    ((SignUpActivity) context).processWSData(resultBundle);
//                    break;
//                case QueryService.QUERY_GET_MERCHANT_HQ_CONTACT:
//                    if (context instanceof AddCardRequestActivity) {
//                        RequestAdapter.processWSData(resultBundle);
//                    } else if (context instanceof AddCardActivity) {
//                        AddCardAdapter.processWSData(resultBundle);
//                    }
//                    break;
//                case QueryService.QUERY_GET_ADDITIONAL_INFO_BY_CARD:
//                case QueryService.QUERY_UPDATE_ADDITIONAL_INFO_BY_CARD:
//                    ((AdditionalInfoActivity) context).processWSData(resultBundle);
//                    break;
//                case QueryService.QUERY_GET_RACE_LIST:
//                case QueryService.QUERY_GET_OCCUPATION_LIST:
//                case QueryService.QUERY_GET_STATE_LIST:
//                case QueryService.QUERY_GET_COUNTRY_LIST:
//                    if (context instanceof AdditionalInfoActivity) {
//                        ((AdditionalInfoActivity) context).processWSData(resultBundle);
//                    } else if (context instanceof MainActivity || context instanceof AddCardActivity) {
//                        AdditionalFieldsFragment.processWSData(resultBundle);
//                    }
//                    break;
//                case QueryService.QUERY_GET_GIFT_LIST_BY_CARD_NO:
//                    RedeemVoucherFragment.processWSData(resultBundle);
//                    break;
//                case QueryService.QUERY_REDEEM_GIFT:
//                    ((RedeemVoucherDetailActivity) context).processWSData(resultBundle);
//                    break;
//                case QueryService.QUERY_SWIPE_REDEEM_VOUCHER:
//                case QueryService.QUERY_END_SWIPE_VOUCHER:
//                    ((VoucherDetailsActivity) context).processWSData(resultBundle);
//                    break;
//                case QueryService.QUERY_GET_REFERRAL_BY_CARD_TYPE:
//                    if (context instanceof ReferralActivity) {
//                        ((ReferralActivity) context).processWSData(resultBundle);
//                    } else if (context instanceof BenefitsActivity) {
//                        BenefitsReferralFragment.processWSData(resultBundle);
//                    }
//                    break;
//                case QueryService.QUERY_DELETE_ACCOUNT:
//                    ((AccountDeletionActivity) context).processWSData(resultBundle);
//                    break;
//                case QueryService.QUERY_REDEMPTION_PIN_CHECK:
//                    if (context instanceof RedeemActivity) {
//                        RedeemPointFragment.processWSData(resultBundle);
//                    } else if (context instanceof RedeemVoucherDetailActivity) {
//                        ((RedeemVoucherDetailActivity) context).processWSData(resultBundle);
//                    } else if (context instanceof VoucherDetailsActivity) {
//                        ((VoucherDetailsActivity) context).processWSData(resultBundle);
//                    }
//                    break;
//                case QueryService.QUERY_GET_DISPLAY_NAME_BY_CLIENT:
//                    MerchantCardFragment.processWSData(resultBundle);
//                    break;
//                case QueryService.QUERY_PROFILE_LOGIN_GET_OTP:
//                    if(context instanceof LoginActivity) {
//                        ((LoginActivity) context).processWSData(resultBundle);
//                    }else if(context instanceof LoginOTPActivity) {
//                        ((LoginOTPActivity) context).processWSData(resultBundle);
//                    }
//                    break;
//                case QueryService.QUERY_PROFILE_LOGIN_BY_OTP:
//                    ((LoginOTPActivity) context).processWSData(resultBundle);
//                    break;
//                case QueryService.QUERY_UPDATE_OTP_CONFIRMATION_2:
//                    SetupPinFragment.successUpdatedBirthDate();
//                    break;
            }
        }
        else {
            processResultError(dataResult.resultCode);
//            if (dataResult.resultCode == 26) {
//                if (module == QueryService.QUERY_CARD_GETLIST) {
//                    ((AddCardActivity) context).processNoCard(resultBundle);
//                }
//            }
        }
    }



    protected void processResultError(int resultCode) {

        switch (resultCode) {
            case 1:
                showAlertDialog(context.getString(R.string.Username_is_taken));
                break;
            case 2:
                showAlertDialog(context.getString(R.string.Invalid_merchant));
                break;
            case 3:
                showAlertDialog(context.getString(R.string.Invalid_pin));
                break;
            case 4:
//                showAlertDialog(context.getString(R.string.Invalid_card));
                //add because RedeemActivity loading // only redeem voucher history got return 04,
                // receipt, news promo, outlets, referral, additional info nope
                //removed card referral code cannot use
//                if(context instanceof MainActivity){
//                    showAlertDialog(context.getString(R.string.Invalid_card));
//                }else {
//                    ((BaseActivity) context).showAlertDialogFinish(context, context.getString(R.string.Invalid_card));
//                }

                break;
            case 5:
                showAlertDialog(context.getString(R.string.Invalid_card_type)); //also handle invalid client
                break;
            case 6:
                if (module == QueryService.QUERY_USERCARD_POINTINQUIRY) {
//                    if (context instanceof RedeemActivity) {
//                        ((RedeemActivity) context).processExpiredCard(false);
//                    }
//                    else if (context instanceof HistoryActivity) {
//                        ((HistoryActivity) context).processExpiredCard(false);
//                    }
//                    else {
//                        showAlertDialog(context.getString(R.string.Blocked_card));
//                    }
                }
                else {
                    showAlertDialog(context.getString(R.string.Blocked_card));
                }
                break;
            case 7:
                if (module == QueryService.QUERY_USERCARD_POINTINQUIRY) {
//                    if (context instanceof RedeemActivity) {
//                        ((RedeemActivity) context).processExpiredCard(true);
//                    }
//                    else if (context instanceof HistoryActivity) {
//                        ((HistoryActivity) context).processExpiredCard(true);
//                    }
//                    else {
//                        showAlertDialog(context.getString(R.string.Card_is_expired));
//                    }
                }
                else {
                    showAlertDialog(context.getString(R.string.Card_is_expired));
                }
                break;
          /*  case 8:
                showAlertDialog(context.getString(R.string.Invalid_email_password));
                break;*/
            case 9:
                showAlertDialog(context.getString(R.string.Invalid_card_holder_name));
                break;
            case 10:
                showAlertDialog(context.getString(R.string.Invalid_NRIC_Passport));
                break;
            case 11:
                showAlertDialog(context.getString(R.string.Invalid_address_1));
                break;
            case 12:
                showAlertDialog(context.getString(R.string.Invalid_address_2));
                break;
            case 13:
                showAlertDialog(context.getString(R.string.Invalid_address_3));
                break;
            case 14:
                showAlertDialog(context.getString(R.string.Invalid_postcode));
                break;
            case 15:
                showAlertDialog(context.getString(R.string.Invalid_state));
                break;
            case 16:
                showAlertDialog(context.getString(R.string.Invalid_city));
                break;
            case 17:
                showAlertDialog(context.getString(R.string.Invalid_country));
                break;
            case 18:
                showAlertDialog(context.getString(R.string.Blocked_mobile_number));
                break;
            case 19:
                showAlertDialog(context.getString(R.string.Invalid_email));
                break;
            case 20:
                showAlertDialog(context.getString(R.string.Invalid_category));
                break;
            case 21:
                showAlertDialog(context.getString(R.string.Invalid_promotion_code));
                break;
            case 22:
                showAlertDialog(context.getString(R.string.Invalid_start_index));
                break;
            case 23:
                showAlertDialog(context.getString(R.string.Invalid_end_index));
                break;
            case 24:
                showAlertDialog(context.getString(R.string.Email_already_registered));
                break;
            case 25:
                //showAlertDialog(context.getString(R.string.add_card_err));
                break;
            case 26:
//                if (module == QueryService.QUERY_CARD_TRANSACTION_GET) {
//                    ((HistoryActivity) context).processEmptyTransactionLog();
//                }
//                else if (module == QueryService.QUERY_CARD_REDEMPTION_GET) {
//                    ((HistoryActivity) context).processEmptyRedemptionLog();
//                }
//                else if (module == QueryService.QUERY_GET_VOUCHER_LOG) {
//                    ((HistoryActivity) context).processEmptyVoucherLog();
//                }
//                else if (module == QueryService.QUERY_CARD_GETLIST) {
//                    //when there is no cardtype
//                    //display message handle above when calling processResultError()
//                }
//                else {
//                    showAlertDialog(context.getString(R.string.End_of_List));
//                    // end of list, used by transaction
//                }
                break;
            case 27:
                showAlertDialog(context.getString(R.string.Mobile_already_registered));
                break;
            case 28:
                showAlertDialog(context.getString(R.string.Email_mobile_already_registered));
                break;
            case 29:
                showAlertDialog(context.getString(R.string.Add_email_to_continue));
                break;
            case 30: //invalid member: user changed password/username(mobileNo) in other device, logout user
                if (context instanceof LoginActivity || context instanceof RegisterActivity) {
                    showAlertDialog(context.getString(R.string.Invalid_email_mobile_password));
                }
//                else if (context instanceof MainActivity) {
//                    showLogoutAlertDialog(context.getString(R.string.Invalid_member)); //logout
//                }
//                else {
//                    showLogoutActivitiesAlertDialog(context.getString(R.string.Invalid_member)); //close all activities and logout
//                }
                break;
            case 31:
//                showAlertDialog(context.getString(R.string.Invalid_old_password));
                break;
            case 32:
//                if (context instanceof ForgotPasswordActivity) {
//                    ((ForgotPasswordActivity) context).handleInvalidMobileEmail();
//                }
//                else if (context instanceof LoginActivity || context instanceof LoginOTPActivity) {
//                    showAlertDialog(context.getString(R.string.unverified_mobile_no));
//                }
                break;
            case 33:
                showAlertDialog(context.getString(R.string.Invalid_otp));
                break;
            case 34:
                showAlertDialog(context.getString(R.string.Expired_otp));
                break;
            case 37:
//                showAlertDialog(context.getString(R.string.no_more_card));
                break;
            case 39:
//                if (module == QueryService.QUERY_GET_SINGLE_VOUCHER) {
//                    ((BaseActivity) context).showAlertDialogFinish(context, context.getString(R.string.err_voucher_redeemed));
//                }
                break;
            case 40:
//                if (module == QueryService.QUERY_GET_SINGLE_VOUCHER) {
//                    ((BaseActivity) context).showAlertDialogFinish(context, context.getString(R.string.err_voucher_expired));
//                }
            case 41:
//                if (context instanceof ForgotPasswordActivity) {
//                    ((ForgotPasswordActivity) context).handleGoogleAccount();
//                } else {
//                    showAlertDialog(context.getString(R.string.err_Google_login));
//                }
                break;
            case 42:
//                if (context instanceof ForgotPasswordActivity) {
//                    ((ForgotPasswordActivity) context).handleAppleAccount();
//                } else {
//                    showAlertDialog(context.getString(R.string.err_Apple_login));
//                }
                break;
            case 43:
//                if (context instanceof ForgotPasswordActivity) {
//                    ((ForgotPasswordActivity) context).handleFacebookAccount();
//                } else {
//                    showAlertDialog(context.getString(R.string.err_Facebook_login));
//                }
                break;
            case 45:
//                showAlertDialog(context.getString(R.string.You_already_send_a_request));
                break;
            case 46:
//                showAlertDialog(context.getString(R.string.merchant_contact_not_found));
                break;
            case 47:
                showAlertDialog(context.getString(R.string.Invalid_current_pin));
                break;
            case 48:
//                if (context instanceof LoginActivity) {
//                    ((BaseActivity) context).displayEmailSupportDialog(context, "", context.getString(R.string.Email_registered));
//                }
                break;
            case 49:
                //showAlertDialog(context.getString(R.string.err_card_owned_by_other_user));
                break;
            case 50:
                //showAlertDialog(context.getString(R.string.err_login_with_social_account));
                break;
            case 51: //Apple id : complete profile
                //showAlertDialog(context.getString(R.string.err_verify_profile));
                break;
            case 52:
                //showAlertDialog(context.getString(R.string.err_invalid_nric));
                break;
            case 54:
                //showAlertDialog(context.getString(R.string.err_insufficient_point));
                break;
            case 55:
                //showAlertDialog(context.getString(R.string.err_voucher_not_available));
                break;
            case 56:
                //showAlertDialog(context.getString(R.string.err_invalid_referral_code));
                break;
            case 57:
                //showAlertDialog(context.getString(R.string.err_referral_limit));
                break;
            case 58:
                //showAlertDialog(context.getString(R.string.err_physical_item_not_available));
                break;
            case 59:
                //showAlertDialog(context.getString(R.string.err_not_receiving_otp));
                break;
            case 93:
               // showAlertDialog(context.getString(R.string.err_invalid_en_data));
                break;
            case 94://invalid id or member not found in db, logout
                if (context instanceof LoginActivity || context instanceof RegisterActivity) {
                    showAlertDialog(context.getString(R.string.err_invalid_id));
                } else if (context instanceof MainUIActivity) {
                    showLogoutAlertDialog(context.getString(R.string.Invalid_member)); //logout
                }
                else {
                    //showLogoutActivitiesAlertDialog(context.getString(R.string.Invalid_member)); //close all activities and logout
//                    if(HomeFragment.popupWindow != null) {
//                        HomeFragment.popupWindow.dismiss();
//                    }
                    /*if (CardAdapter.popupWindow != null) {
                        CardAdapter.popupWindow.dismiss();
                    }*/
                }
                break;
            case 95:
                showAlertDialog(context.getString(R.string.err_verify_email));
                break;
            case 96://new login using Facebook, Google, Apple account
//                if (module == QueryService.QUERY_PROFILE_LOGIN) {
//                    LoginActivity.goRegister();
//                }
                break;
            case 97:
//                if (module == QueryService.QUERY_REGISTER_MOBILEID) {
//                    byPassMethod(module);
//                }
                //showAlertDialog(context.getString(R.string.Invalid_length));
                break;
            case 98:
//                if (module == QueryService.QUERY_PLATFORM_UPDATE || module == QueryService.QUERY_REGISTER_MOBILEID) {
//                    byPassMethod(module);
//                }
                //showAlertDialog(context.getString(R.string.Invalid_authentication_key));
                break;
            case 99:
//                if (module == QueryService.QUERY_CARD_GETLIST) {
//                    ((AddCardActivity) context).processErrorGetCard();
//                    showAlertDialog(context.getString(R.string.something_went_wrong));
//                } else if (module == QueryService.QUERY_PLATFORM_UPDATE || module == QueryService.QUERY_REGISTER_MOBILEID) {
//                    byPassMethod(module);
//                } else if (module == QueryService.QUERY_USERCARD_POINTINQUIRY || module == QueryService.QUERY_GET_GIFT_LIST_BY_CARD_NO) {
//                    RedeemActivity.dismissProgressDialog();
//                    showAlertDialog(context.getString(R.string.something_went_wrong));
//                } else {
//                    showAlertDialog(context.getString(R.string.something_went_wrong));
//                }
                break;
            case 101:
            case 103:
            case 104:
//                if (module == QueryService.QUERY_USERCARD_GETLIST || module == QueryService.QUERY_PLATFORM_UPDATE || module == QueryService.QUERY_REGISTER_MOBILEID) {
//                    if (context instanceof MainActivity) {
//                        HomeFragment.goOffline(); //enable user to view their card and scan qr
//                        ((BaseActivity) context).displayErrorAlertDialog(context.getString(R.string.Connection_to_server_is_unavailable));
//                    }
//                } else if (module == QueryService.QUERY_GET_NOTIFICATION_SENT) {
//                    ((NotificationActivity) context).manageNotification();
//                    ((NotificationActivity) context).manageAdapter();
//                    ((BaseActivity) context).displayErrorAlertDialog(context.getString(R.string.Connection_to_server_is_unavailable));
//                } else if (module == QueryService.QUERY_GET_CATEGORIZE_MERCHANT_HQ) {
//                    MerchantCardFragment.goOffline();
//                    ((BaseActivity) context).displayErrorAlertDialog(context.getString(R.string.Connection_to_server_is_unavailable));
//                } else if (module == QueryService.QUERY_USERCARD_POINTINQUIRY ||
//                        module == QueryService.QUERY_GET_REFERRAL_BY_CARD_TYPE ||
//                        module == QueryService.QUERY_PROFILE_GET_DETAILS ||
//                        module == QueryService.QUERY_GET_SINGLEPROMO ||
//                        module == QueryService.QUERY_GET_SINGLE_VOUCHER ||
//                        context instanceof HistoryActivity) {
//                    if(context instanceof RedeemActivity){
//                        RedeemActivity.manageOfflinePoint();
//                        ((BaseActivity) context).displayErrorAlertDialog(context.getString(R.string.Connection_to_server_is_unavailable));
//                    }else{
//                        ((BaseActivity) context).showAlertDialogFinish(context, context.getString(R.string.Connection_to_server_is_unavailable));
//                    }
//                } else if (module == QueryService.QUERY_GET_GIFT_LIST_BY_CARD_NO) {
//                    RedeemVoucherFragment.handleOffline();
//                    ((BaseActivity) context).displayErrorAlertDialog(context.getString(R.string.Connection_to_server_is_unavailable));
//                } else if (context instanceof AdditionalInfoActivity) {
//                    ((AdditionalInfoActivity) context).cancelProgress();
//                    ((BaseActivity) context).showAlertDialogFinish(context, context.getString(R.string.Connection_to_server_is_unavailable));
//                } else if (module == QueryService.QUERY_CARD_GETLIST) {
//                    ((AddCardActivity) context).processErrorGetCard();
//                    ((BaseActivity) context).displayErrorAlertDialog(context.getString(R.string.Connection_to_server_is_unavailable));
//                } else if(module == QueryService.QUERY_GET_CARD_REQUEST_LIST) {
//                    ((AddCardRequestActivity) context).setupViews();
//                    ((BaseActivity) context).displayErrorAlertDialog(context.getString(R.string.Connection_to_server_is_unavailable));
//                }else {
//                    ((BaseActivity) context).displayErrorAlertDialog(context.getString(R.string.Connection_to_server_is_unavailable));
//                }

                break;
            case 102:
                showAlertDialog(context.getString(R.string.Invalid_response_please_try_again));
               /* if(!((MobileApplication) getApplication()).getRedeemVoucherID("RedeemVoucherID").equals("")){
                    Bundle b1 = new Bundle();
                    b1.putString("WSKey","mobileApp");
                    b1.putString("username", ((MobileApplication) getApplication()).getUsername("Username"));
                    b1.putString("userPsw", ((MobileApplication) getApplication()).getUserPwd("UserPwd"));
                    b1.putString("VoucherID",((MobileApplication) getApplication()).getRedeemVoucherID("RedeemVoucherID"));
                    b1.putString("cardNo",((MobileApplication) getApplication()).getRedeemCardNoD("RedeemCardNo"));
                    b1.putString("Companycode",((MobileApplication) getApplication()).getRedeemCompanyCode("RedeemCompanyCode"));
                    b1.putString("BranchCode",((MobileApplication) getApplication()).getRedeemBranchCode("RedeemBranchCode"));
                    requestPut(QueryService.QUERY_REVERSEL_REDEEM_VOUCHER, b1,
                            R.id.loadingView, R.id.errorView);
                    ((MobileApplication) getApplication()).setUsername("Username", "");
                    ((MobileApplication) getApplication()).setUserPwd("UserPwd", "");
                    ((MobileApplication) getApplication()).setRedeemVoucherID("RedeemVoucherID", "");
                    ((MobileApplication) getApplication()).setRedeemCardNo("RedeemCardNo", "");
                    ((MobileApplication) getApplication()).setRedeemCompanyCode("RedeemCompanyCode", "");
                    ((MobileApplication) getApplication()).setRedeemCompanyCode("RedeemBranchCode", "");
                }*/
                break;

            default:
                showAlertDialog(context.getString(R.string.Unknown_Error)
                        + " (" + resultCode + ")");
                break;
        }

//        if (context instanceof HistoryActivity) {
//            HistoryActivity.dismissProgressDialog();
//        } else if (module == QueryService.QUERY_PLATFORM_UPDATE || module == QueryService.QUERY_PROFILE_IS_NEW || module == QueryService.QUERY_REGISTER_MOBILEID || module == QueryService.QUERY_USERCARD_GETLIST) {
//            if (context instanceof MainActivity) {
//                MainActivity.cancelProgress();
//            }
//        } else if (context instanceof VoucherDetailsActivity) {
//            ((VoucherDetailsActivity) context).cancelProgress();
//        }else if(module == QueryService.QUERY_SEND_REG_OTP){
//            SignUpActivity.dismissProgressDialog();
//        }
    }

    protected void showLogoutAlertDialog(String errMessage) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);

        builder.setMessage(errMessage);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //BaseActivity.logout(context);
            }
        });
        AlertDialog alertDialog = builder.create();
//
//        if (!((BaseActivity) context).isFinishing()) {
//            alertDialog.show();
//        }

    }

    protected void showAlertDialog(String errMessage) {
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_dialog_error);

        TextView mTitle = dialog.findViewById(R.id.tvErrorDialogTitle);
        TextView mMessage = dialog.findViewById(R.id.tvErrorDialogMsg);
        TextView mOK = dialog.findViewById(R.id.btnErrorDialog);

        mTitle.setVisibility(View.GONE);
        mMessage.setText(errMessage);

        mOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
//        try {
//            if (!((BaseActivity) context).isFinishing()) {
//                dialog.show();
//            }
//        } catch (Exception e) {
//            Log.e("displayUseVoucherDialog", e.getMessage());
//        }
        dialog.show();
        //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Window window = dialog.getWindow();
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

    }

}
