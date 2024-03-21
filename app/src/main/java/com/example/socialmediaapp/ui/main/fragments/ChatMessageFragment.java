package com.example.socialmediaapp.ui.main.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.socialmediaapp.R;
import com.example.socialmediaapp.data.chat.ChatMessage;
import com.example.socialmediaapp.data.chat.ChatMessageAdapter;
import com.example.socialmediaapp.databinding.FragmentChatMessageBinding;

import java.util.ArrayList;
import java.util.List;

public class ChatMessageFragment extends Fragment {

    FragmentChatMessageBinding binding;
    List<ChatMessage> dummyMessages = new ArrayList<>();
    RecyclerView rvChatMessage;
    ChatMessageAdapter chatMessageAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentChatMessageBinding.inflate(inflater, container, false);
        View view = binding.getRoot();



        long currentTime = System.currentTimeMillis();
        dummyMessages.add(new ChatMessage("Hello", ChatMessage.Sender.FRIEND, currentTime - (1 * 31536000000L)));
        dummyMessages.add(new ChatMessage("How are you?", ChatMessage.Sender.USER, currentTime - (1 * 2592000000L)));
        dummyMessages.add(new ChatMessage("I'm good, thanks!", ChatMessage.Sender.FRIEND, currentTime - (1 * 86400000)));
        dummyMessages.add(new ChatMessage("What's up?", ChatMessage.Sender.USER, currentTime - (1 * 3600000)));
        dummyMessages.add(new ChatMessage("Long time no see!", ChatMessage.Sender.FRIEND, currentTime - (1 * 60000)));
        dummyMessages.add(new ChatMessage("Long time no see!", ChatMessage.Sender.FRIEND, currentTime - (1 * 60000)));
        dummyMessages.add(new ChatMessage("Long time no see!", ChatMessage.Sender.FRIEND, currentTime - (1 * 60000)));
        dummyMessages.add(new ChatMessage("Long time no see!", ChatMessage.Sender.FRIEND, currentTime - (1 * 60000)));
        dummyMessages.add(new ChatMessage("Long time no see!", ChatMessage.Sender.FRIEND, currentTime - (1 * 60000)));
        dummyMessages.add(new ChatMessage("Long time no see!", ChatMessage.Sender.FRIEND, currentTime - (1 * 60000)));
        dummyMessages.add(new ChatMessage("Last message!", ChatMessage.Sender.FRIEND, currentTime - (1 * 60000)));

        rvChatMessage = binding.rvChatMessage;
        rvChatMessage.setLayoutManager(new LinearLayoutManager(getActivity()));
        chatMessageAdapter = new ChatMessageAdapter(dummyMessages);
        rvChatMessage.setAdapter(chatMessageAdapter);

        rvChatMessage.scrollToPosition(dummyMessages.size()-1);








        return view;
    }
}