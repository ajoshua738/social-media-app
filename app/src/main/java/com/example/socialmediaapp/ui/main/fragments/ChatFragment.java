package com.example.socialmediaapp.ui.main.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.socialmediaapp.R;
import com.example.socialmediaapp.data.chat.ChatItem;
import com.example.socialmediaapp.data.chat.ChatItemAdapter;
import com.example.socialmediaapp.databinding.FragmentChatBinding;

import java.util.ArrayList;
import java.util.List;


public class ChatFragment extends Fragment {

    FragmentChatBinding binding;
    private List<ChatItem> chatItemList = new ArrayList<>();
    private RecyclerView rvChatItem;
    private ChatItemAdapter chatItemAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentChatBinding.inflate(inflater,container,false);
        View view = binding.getRoot();


        // Create some dummy data with different time intervals
        long currentTime = System.currentTimeMillis();
        String dummyString = getString(R.string.placeholder_lorem);
        chatItemList.add(new ChatItem("Kevin Adams", "Hello", currentTime - (1 * 60000))); // 1 minute ago
        Log.d("Chat Time",""+getFormattedTime(currentTime - (4 * 60000)));
        chatItemList.add(new ChatItem("Ivy Thompson", "Bye", currentTime - (1 * 3600000))); // 1 hour ago
        chatItemList.add(new ChatItem("Henry Wilson", "Hello my name is Henry Wilson", currentTime - (1 * 86400000))); // 1 day ago
        chatItemList.add(new ChatItem("Grace Miller", dummyString, currentTime - (1 * 2592000000L))); // 1 month ago
        chatItemList.add(new ChatItem("Charlie Davis", dummyString, currentTime - (1 * 31536000000L))); // 1 year ago


        rvChatItem = binding.rvChatItems;
        rvChatItem.setLayoutManager(new LinearLayoutManager(getActivity()));
        chatItemAdapter = new ChatItemAdapter(chatItemList);
        rvChatItem.setAdapter(chatItemAdapter);
        return view;
    }
    private String getFormattedTime(long timestamp) {
        long currentTime = System.currentTimeMillis();
        long timeDifference = currentTime - timestamp;

        if (timeDifference < 60000) {
            return "0 min";
        } else if (timeDifference < 3600000) {
            return (timeDifference / 60000) + " min";
        } else if (timeDifference < 86400000) {
            return (timeDifference / 3600000) + " h";
        } else if (timeDifference < 604800000) {
            return (timeDifference / 86400000) + " d";
        } else if (timeDifference < 2592000000L) {
            return (timeDifference / 604800000) + " w";
        } else if (timeDifference < 31536000000L) {
            return (timeDifference / 2592000000L) + " m";
        } else {
            return (timeDifference / 31536000000L) + " y";
        }
    }
}