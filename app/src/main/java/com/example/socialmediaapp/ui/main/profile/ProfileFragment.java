package com.example.socialmediaapp.ui.main.profile;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.socialmediaapp.R;
import com.example.socialmediaapp.data.post.Post;
import com.example.socialmediaapp.data.post.UserProfileNewPostAdapter;
import com.example.socialmediaapp.databinding.FragmentProfileBinding;
import com.example.socialmediaapp.ui.main.MainUIViewModel;

import java.util.ArrayList;
import java.util.List;


public class ProfileFragment extends Fragment {

    private List<Post> postList = new ArrayList<>();

    FragmentProfileBinding binding;
    MainUIViewModel mainUIViewModel;


    GridLayoutManager gridLayoutManager;
    LinearLayoutManager linearLayoutManager;

    RecyclerView rvUserPosts;

    UserProfileNewPostAdapter userProfileNewPostAdapter;


    private boolean isGridLayout;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        mainUIViewModel = new ViewModelProvider(requireActivity()).get(MainUIViewModel.class);


        rvUserPosts = binding.rvPostList;

        gridLayoutManager = new GridLayoutManager(requireContext(), 3);
        linearLayoutManager = new LinearLayoutManager(requireContext());


        binding.btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(requireView());
                navController.navigate(R.id.action_profileFragment_to_editProfileFragment);
            }
        });



        binding.ivSettingsPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(requireView());
                navController.navigate(R.id.action_profileFragment_to_settingsFragment);
            }
        });



        binding.btnProfileChangePostLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isGridLayout = rvUserPosts.getLayoutManager() == gridLayoutManager;
                if(isGridLayout){
                    userProfileNewPostAdapter = new UserProfileNewPostAdapter(postList, R.layout.list_post_layout);
                    rvUserPosts.setAdapter(userProfileNewPostAdapter);
                    rvUserPosts.setLayoutManager(linearLayoutManager);
                    binding.btnProfileChangePostLayout.setImageResource(R.drawable.grid_view_24px);
                }
                else{
                    userProfileNewPostAdapter = new UserProfileNewPostAdapter(postList, R.layout.grid_post_layout);
                    rvUserPosts.setAdapter(userProfileNewPostAdapter);
                    rvUserPosts.setLayoutManager(gridLayoutManager);
                    binding.btnProfileChangePostLayout.setImageResource(R.drawable.list_24px);
                }


            }
        });




        binding.btnProfileCreatePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(requireView());
                navController.navigate(R.id.createPostFragment);

            }
        });

        postList.clear();
        addData();


        userProfileNewPostAdapter = new UserProfileNewPostAdapter(postList, R.layout.grid_post_layout);

        rvUserPosts.setAdapter(userProfileNewPostAdapter);
        rvUserPosts.setLayoutManager(gridLayoutManager);






        return view;
    }


    private void addData(){
        postList.add(new Post());
        postList.add(new Post());
        postList.add(new Post());
        postList.add(new Post());
        postList.add(new Post());
        postList.add(new Post());
        postList.add(new Post());
        postList.add(new Post());
        postList.add(new Post());
        postList.add(new Post());
    }


}