package com.example.socialmediaapp.ui.main.fragments;

import android.content.ContentResolver;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.os.ParcelFileDescriptor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.socialmediaapp.R;
import com.example.socialmediaapp.data.post.ContentType;
import com.example.socialmediaapp.data.post.HomepageNewPostAdapter;
import com.example.socialmediaapp.data.post.Post;

import com.example.socialmediaapp.data.post.PostContent;
import com.example.socialmediaapp.databinding.FragmentHomePageBinding;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class HomePageFragment extends Fragment {
    FragmentHomePageBinding binding;
    private List<Post> postList = new ArrayList<>();


    HomepageNewPostAdapter adapter;

    SwipeRefreshLayout swipeRefreshLayout;


    List<PostContent> postContentList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomePageBinding.inflate(inflater,container,false);
        View view = binding.getRoot();





        addData();

        swipeRefreshLayout = binding.refreshLayout;


        adapter = new HomepageNewPostAdapter(postList);
        RecyclerView recyclerView = view.findViewById(R.id.rvHomepagePost);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                postList.clear();
                refreshData();
            }
        });


        if (getArguments() != null) {
            Post post = getArguments().getParcelable("post");
            // Use 'post' to update your UI or add it to your postList
            if (post != null) {
                postList.add(0,post);
                adapter.notifyDataSetChanged();
            }
        }


        return view;
    }


    public void addData(){

        String dummy = getString(R.string.placeholder_lorem);
        String onlineVideo = "https://videocdn.cdnpk.net/joy/content/video/free/2019-11/large_preview/190301_1_25_11.mp4";
        String onlineImage = "https://cdn.pixabay.com/photo/2015/11/16/14/43/cat-1045782_1280.jpg";


//        postContentList.add(new PostContent(ContentType.IMAGE_D,"android.resource://" + requireContext().getPackageName() + "/" + R.drawable.cat7));
//        postContentList.add(new PostContent(ContentType.IMAGE_D,"android.resource://" + requireContext().getPackageName() + "/" + R.drawable.cat8));
//        postContentList.add(new PostContent(ContentType.IMAGE_D,"android.resource://" + requireContext().getPackageName() + "/" + R.drawable.cat2));
//        postContentList.add(new PostContent(ContentType.VIDEO_D,"android.resource://" + requireContext().getPackageName() + "/" + R.raw.video1));
//        postContentList.add(new PostContent(ContentType.VIDEO_D,"android.resource://" + requireContext().getPackageName() + "/" + R.raw.video4));
          postContentList.add(new PostContent(ContentType.VIDEO,onlineVideo));
          postContentList.add(new PostContent(ContentType.IMAGE, onlineImage));


        // Add new posts with the same post content
        for (int i = 0; i < 8; i++) {
            postList.add(new Post("John Doe", dummy, false, false, postContentList));
        }




    }


    private void refreshData() {
        // Simulate a network request to fetch new data
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Clear the existing data and add new data
                postList.clear();
                addData();
                // Add more data as needed

                // Notify the adapter that the data has changed
                adapter.notifyDataSetChanged();

                // Stop the swipe refresh animation
                swipeRefreshLayout.setRefreshing(false);
            }
        }, 2000); // Delay for 2 seconds to simulate a network request
    }


}