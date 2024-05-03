package com.example.socialmediaapp.ui.main;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.socialmediaapp.R;
import com.example.socialmediaapp.databinding.ActivityMainUiBinding;

import com.example.socialmediaapp.helper.CommonUtil;
import com.example.socialmediaapp.helper.Constant;
import com.example.socialmediaapp.services.DataResult;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.example.socialmediaapp.model.UserProfile;
import com.example.socialmediaapp.services.ServiceConstant;
import com.example.socialmediaapp.services.QueryService;
import com.example.socialmediaapp.helper.ApplicationClass;
import com.example.socialmediaapp.services.CallWebServices;

import java.util.ArrayList;

public class MainUIActivity extends AppCompatActivity {

    private ActivityMainUiBinding binding;
    private AppBarConfiguration appBarConfiguration;

    public static boolean refreshApp = false;

    //MainUIViewModel mainUIViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainUiBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        BottomNavigationView bottomNavigationView = binding.bottomNavigationView;


        // Set up the toolbar
        MaterialToolbar toolbar = findViewById(R.id.tbMainUI);
        setSupportActionBar(toolbar);


        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nvMainUIFragment);
        NavController navController = navHostFragment.getNavController();

        NavigationUI.setupWithNavController(bottomNavigationView, navController);


        appBarConfiguration =
                new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        reqProfileIsNew();

        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController navController, @NonNull NavDestination navDestination, @Nullable Bundle bundle) {
                // Show the toolbar for specific destinations
                boolean showToolbar = navDestination.getId() == R.id.editProfileFragment
                        || navDestination.getId() == R.id.viewPostFragment
                        || navDestination.getId() == R.id.homePageFragment
                        || navDestination.getId() == R.id.createPostFragment
                        || navDestination.getId() == R.id.settingsFragment
                        || navDestination.getId() == R.id.commentSectionFragment
                        || navDestination.getId() == R.id.commentReplyFragment;
                toolbar.setVisibility(showToolbar ? View.VISIBLE : View.GONE);

                // Show the search icon for homepage
                boolean hideSearchIcon = navDestination.getId() == R.id.homePageFragment;
                hideIcons(hideSearchIcon);


                SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                boolean isProfileComplete = sharedPreferences.getBoolean("profileComplete", true);
                if (!isProfileComplete) {
                    if (navDestination.getId() == R.id.profileFragment ||
                            navDestination.getId() == R.id.createPostFragment ||
                            navDestination.getId() == R.id.friendListFragment) {
                        navController.navigate(R.id.completeProfileFragment);
                    }
                }

                boolean hideBottomNav = navDestination.getId() == R.id.commentSectionFragment
                        || navDestination.getId() == R.id.editProfileFragment
                        || navDestination.getId() == R.id.commentReplyFragment
                        || navDestination.getId() == R.id.createPostFragment
                        || navDestination.getId() == R.id.viewPostFragment
                        || navDestination.getId() == R.id.chatMessageFragment;

                bottomNavigationView.setVisibility(hideBottomNav ? View.GONE : View.VISIBLE);

            }
        });


        setBadgeCount(bottomNavigationView, R.id.homePageFragment, 9999); //
        showBadge(bottomNavigationView, R.id.profileFragment, true);



        binding.ivCreatePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.createPostFragment);
            }
        });


    }

    private void setBadgeCount(BottomNavigationView bottomNavigationView, int itemId, int count) {
        BadgeDrawable badgeDrawable = bottomNavigationView.getOrCreateBadge(itemId);
        badgeDrawable.setNumber(count);
    }

    private void showBadge(BottomNavigationView bottomNavigationView, int itemId, boolean showBadge) {
        BadgeDrawable badgeDrawable = bottomNavigationView.getOrCreateBadge(itemId);
        badgeDrawable.setVisible(showBadge);
    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nvMainUIFragment);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.bottom_menu, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        if (item.getItemId() == R.id.action_search) {
//            // Handle search icon click here
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }


//    @Override
//    public boolean dispatchTouchEvent(MotionEvent event) {
//        View view = getCurrentFocus();
//        boolean ret = super.dispatchTouchEvent(event);
//
//        if (view instanceof EditText) {
//            View w = getCurrentFocus();
//            int scrcoords[] = new int[2];
//            w.getLocationOnScreen(scrcoords);
//            float x = event.getRawX() + w.getLeft() - scrcoords[0];
//            float y = event.getRawY() + w.getTop() - scrcoords[1];
//
//            if (event.getAction() == MotionEvent.ACTION_UP &&
//                    (x < w.getLeft() || x >= w.getRight() || y < w.getTop() || y > w.getBottom())) {
//                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//                imm.hideSoftInputFromWindow(getWindow().getCurrentFocus().getWindowToken(), 0);
//                view.clearFocus();
//            }
//        }
//        return ret;
//    }



    private void hideIcons(boolean hideSearchIcon){
        binding.ivToolbarSearch.setVisibility(hideSearchIcon ? View.VISIBLE : View.GONE);
        binding.ivCreatePost.setVisibility(hideSearchIcon ? View.VISIBLE : View.GONE);
        binding.ivAppLogo.setVisibility(hideSearchIcon ? View.VISIBLE : View.GONE);
        binding.tvAppName.setVisibility(hideSearchIcon ? View.VISIBLE : View.GONE);

    }


    public void reqProfileIsNew() {
        UserProfile userProfile = ((ApplicationClass) getApplicationContext()).getUserProfile();
        String lastUpdateDate = ((ApplicationClass) getApplicationContext()).getLastUpdateDate();
        String token = ((ApplicationClass) getApplicationContext()).getFinalToken();
        String strEnData = userProfile.getEmailMobileSocialId() + "|" + userProfile.getPassword() + "|" + token + "|" + lastUpdateDate;

        Log.d("EN DATA MainUI",""+strEnData);
        Bundle b = new Bundle();
        b.putString(ServiceConstant.EN_DATA, strEnData);
        b.putInt(QueryService.COMMAND, QueryService.QUERY_PROFILE_IS_NEW);
        //Log.e(TAG, "Context class: reqProfileIsNew");

        new CallWebServices(QueryService.QUERY_PROFILE_IS_NEW, MainUIActivity.this, false).execute(b);
    }


    public void processWSData(Bundle resultBundle) {
        int command = resultBundle.getInt(QueryService.COMMAND);
        DataResult dataResult = resultBundle.getParcelable("dataResult");

        if (command == QueryService.QUERY_PLATFORM_UPDATE) {
            //setupViews();
            reqProfileIsNew();
        }
        else if (command == QueryService.QUERY_PROFILE_IS_NEW) {
            ArrayList<String> profileIsNew = null;
            profileIsNew = CommonUtil.tokenize(dataResult.values.get(0), QueryService.DELIMITER_FIELD);
            UserProfile userProfile = ((ApplicationClass) getApplicationContext()).getUserProfile();

            /*  1.GUID
                2.ProfileName
                3.email
                4. mobile no
                5.IsNew
                6.GotPin
                7.notificationCount
                8. birth date
                9.ProfileCardList*/
            userProfile.setGUID(profileIsNew.get(0));
            Log.d("GUID Main UI",""+profileIsNew.get(0));

            userProfile.setProfileName(profileIsNew.get(1));
            userProfile.setMobileNo(profileIsNew.get(3));
            ((ApplicationClass) getApplicationContext()).setUserProfile(userProfile);
            if (((ApplicationClass) getApplicationContext()).getEmail().equals("")) {
                ((ApplicationClass) getApplicationContext()).setEmail(profileIsNew.get(2));
            }
            ((ApplicationClass) getApplicationContext()).setMobileNo(profileIsNew.get(3));
            ((ApplicationClass) getApplicationContext()).setIsNew(profileIsNew.get(4));
            ((ApplicationClass) getApplicationContext()).setGPin(profileIsNew.get(5));
            ((ApplicationClass) getApplicationContext()).setNotificationCount(Integer.parseInt(profileIsNew.get(6)));
            ((ApplicationClass) getApplicationContext()).setBirthDate(profileIsNew.get(7));
            /*((ApplicationClass) getApplicationContext()).setIsNew(profileIsNew.get(3));
            ((ApplicationClass) getApplicationContext()).setGPin(profileIsNew.get(4));
            ((ApplicationClass) getApplicationContext()).setNotificationCount(Integer.parseInt(profileIsNew.get(5)));
            */

            //get card list for user to confirm the card(s) they have in UpdateCardFragment
            //updateCardList.clear();
            if (dataResult.values.size() > 1) {

                for (int x = 1; x < dataResult.values.size(); x++) {
                    ArrayList<String> cardDataList = CommonUtil.tokenize(dataResult.values.get(x), QueryService.DELIMITER_FIELD);

             	/*	1. card_no
                    2. membername
                    3. owner
                    4. client_code
                    5. company_name
                    6. card_type_id
                    7. card_name
                    8. remark
                    9. background_url
                    10. logo_url
                    11. image_url
                    12. cardBase
                    13. balPoint
                    14. balAmount
                    15. enableReceipt */

//                    if (cardDataList.size() >= 14) {
//
//                        Card card = new Card(cardDataList.get(0),
//                                cardDataList.get(1), cardDataList.get(2),
//                                cardDataList.get(3), cardDataList.get(4), cardDataList.get(5),
//                                cardDataList.get(6), cardDataList.get(7), cardDataList.get(8),
//                                cardDataList.get(9), cardDataList.get(10), cardDataList.get(11), cardDataList.get(12), cardDataList.get(13), cardDataList.get(14));
//
//                        updateCardList.add(card);
//                    }
//                    else {
//
//                    }
                }
            }

            //register mobile id for old user to update to new mobile id table(revamp)
            if(!refreshApp){
                reqMobileIdRegister();
            }else {
                doNext(Constant.REFRESH_CARD);
            }

        }
        else if (command == QueryService.QUERY_REGISTER_MOBILEID) {
            //show update profile fragment if need to
            doNext("");
            refreshApp = true;
        }


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
            // Log.e("MOBILE ID", token);
        } else {
            token = "";
        }

        //Log.e("NOTIFICATION", "token: " + token);
        //Log.e("NOTIFICATION", "platform: " + platform);

        UserProfile userProfile = ((ApplicationClass) getApplicationContext()).getUserProfile();
        String imei = ((ApplicationClass) getApplicationContext()).getImei();
        String strEnData = userProfile.getEmailMobileSocialId() + "|" + userProfile.getPassword() + "|" + token + "|" + platform + "|" + imei;

        Bundle b = new Bundle();
        b.putString(ServiceConstant.EN_DATA, strEnData);
        b.putInt(QueryService.COMMAND, QueryService.QUERY_REGISTER_MOBILEID);
        //Log.e(TAG, "Context class: reqMobileIdRegister");
        new CallWebServices(QueryService.QUERY_REGISTER_MOBILEID, MainUIActivity.this, false).execute(b);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // Stop the background service here
        Intent serviceIntent = new Intent(this, CallWebServices.class);
        stopService(serviceIntent);
    }
    public static void doNext(String mode) {
//        HomeFragment.mainCall = true;
//        HomeFragment.reqProfileCardGetList("", mode);
//        MerchantCardFragment.firstLoad = true; //reset to first load to let it call api
        //mDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED); //disable left navigation drawer to prevent user access profile
    }

}