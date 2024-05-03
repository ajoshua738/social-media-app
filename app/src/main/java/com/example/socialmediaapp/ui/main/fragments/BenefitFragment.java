package com.example.socialmediaapp.ui.main.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.socialmediaapp.R;
import com.example.socialmediaapp.databinding.FragmentBenefitBinding;


public class BenefitFragment extends Fragment {

    FragmentBenefitBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentBenefitBinding.inflate(inflater,container,false);
        View view = binding.getRoot();

        return view;
    }
}