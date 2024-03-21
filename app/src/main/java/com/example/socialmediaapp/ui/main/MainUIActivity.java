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
import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.socialmediaapp.R;
import com.example.socialmediaapp.databinding.ActivityMainUiBinding;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;



public class MainUIActivity extends AppCompatActivity {

    private ActivityMainUiBinding binding;
    private AppBarConfiguration appBarConfiguration;

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

}