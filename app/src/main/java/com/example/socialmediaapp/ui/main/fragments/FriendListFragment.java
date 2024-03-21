package com.example.socialmediaapp.ui.main.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.socialmediaapp.R;
import com.example.socialmediaapp.data.notification.FriendListAdapter;
import com.example.socialmediaapp.data.notification.Notification;
import com.example.socialmediaapp.data.notification.NotificationAdapter;
import com.example.socialmediaapp.databinding.FragmentFriendListBinding;
import com.example.socialmediaapp.databinding.FragmentNotificationBinding;

import java.util.ArrayList;
import java.util.List;


public class FriendListFragment extends Fragment {


    FragmentFriendListBinding binding;
    private List<Notification> notificationList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentFriendListBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        notificationList = new ArrayList<>();
        notificationList.add(new Notification("John Doe", System.currentTimeMillis(), false));
        notificationList.add(new Notification("Jane Smith", System.currentTimeMillis() - 10000, false));
        notificationList.add(new Notification("Alice Johnson", System.currentTimeMillis() - 20000, false));
        notificationList.add(new Notification("Bob Brown", System.currentTimeMillis() - 30000, false));
        notificationList.add(new Notification("Eve Wilson", System.currentTimeMillis() - 40000, false));
        notificationList.add(new Notification("Charlie Davis", System.currentTimeMillis() - 50000, false));
        notificationList.add(new Notification("Grace Miller", System.currentTimeMillis() - 60000, false));
        notificationList.add(new Notification("Henry Wilson", System.currentTimeMillis() - 70000, false));
        notificationList.add(new Notification("Ivy Thompson", System.currentTimeMillis() - 80000, false));
        notificationList.add(new Notification("Kevin Adams", System.currentTimeMillis() - 90000, false));



        FriendListAdapter adapter = new FriendListAdapter(notificationList);
        RecyclerView recyclerView = view.findViewById(R.id.rvFriendsList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        return view;
    }


}