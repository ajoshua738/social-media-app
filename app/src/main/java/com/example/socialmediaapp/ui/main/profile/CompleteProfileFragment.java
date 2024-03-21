package com.example.socialmediaapp.ui.main.profile;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.socialmediaapp.R;
import com.example.socialmediaapp.databinding.FragmentCompleteProfileBinding;


public class CompleteProfileFragment extends Fragment {



    FragmentCompleteProfileBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCompleteProfileBinding.inflate(inflater,container,false);
        View view = binding.getRoot();

        binding.btnProfileComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(requireView());
                navController.navigate(R.id.action_completeProfileFragment_to_completeProfileFragment_1);
            }
        });
        return view;
    }
}