package com.example.socialmediaapp.ui.main.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.socialmediaapp.R;
import com.example.socialmediaapp.data.comment.Comment;
import com.example.socialmediaapp.data.comment.CommentAdapter;
import com.example.socialmediaapp.databinding.FragmentCommentReplyBinding;

import java.util.ArrayList;
import java.util.List;


public class CommentReplyFragment extends Fragment {

    FragmentCommentReplyBinding binding;

    RecyclerView replyRecyclerView;

    CommentAdapter commentAdapter;

    private List<Comment> replyList = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCommentReplyBinding.inflate(inflater,container,false);
        View view = binding.getRoot();

        addData();

        commentAdapter = new CommentAdapter(replyList);
        replyRecyclerView = binding.rvCommentReply;

        replyRecyclerView.setAdapter(commentAdapter);
        replyRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));



        return view;
    }

    private void addData(){
        replyList.add(new Comment());
        replyList.add(new Comment());
        replyList.add(new Comment());
        replyList.add(new Comment());
        replyList.add(new Comment());
        replyList.add(new Comment());

        replyList.add(new Comment());
        replyList.add(new Comment());
        replyList.add(new Comment());
        replyList.add(new Comment());
        replyList.add(new Comment());
        replyList.add(new Comment());

        replyList.add(new Comment());
        replyList.add(new Comment());
        replyList.add(new Comment());
        replyList.add(new Comment());
        replyList.add(new Comment());
        replyList.add(new Comment());
    }
}