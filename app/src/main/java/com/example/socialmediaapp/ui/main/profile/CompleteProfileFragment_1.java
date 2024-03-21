package com.example.socialmediaapp.ui.main.profile;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.example.socialmediaapp.R;
import com.example.socialmediaapp.data.user.User;
import com.example.socialmediaapp.data.user.UserDataHolder;
import com.example.socialmediaapp.databinding.FragmentCompleteProfile1Binding;
import com.example.socialmediaapp.ui.main.MainUIActivity;


public class CompleteProfileFragment_1 extends Fragment {

    FragmentCompleteProfile1Binding binding;
    String selectedGender;
    String bio = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCompleteProfile1Binding.inflate(inflater,container,false);
        View view = binding.getRoot();





        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            activity.setSupportActionBar(binding.toolbar);
            ActionBar actionBar = activity.getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true); // Show the back button
                actionBar.setDisplayShowTitleEnabled(false);
            }
        }

        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the back button click (e.g., pop the fragment back stack)
                requireActivity().onBackPressed();

            }
        });

        binding.etProfileGender.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedGender = parent.getItemAtPosition(position).toString();
            }
        });

        binding.btnCompleteProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedGender != null){
                    User user = UserDataHolder.getUser();
                    user.setGender(selectedGender);
                    user.setBio(bio);
                    binding.textInputLayout4.setError(null);

                    MainUIActivity mainUIActivity = (MainUIActivity) getActivity();


                    SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("profileComplete", true);
                    editor.apply();
                    NavController navController = Navigation.findNavController(requireView());
                    navController.navigate(R.id.homePageFragment);


                }
                else{
                    binding.textInputLayout4.setError(getString(R.string.gender_error));
                }

            }
        });
        return view;
    }
}






















