package com.example.socialmediaapp.ui.main.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.socialmediaapp.R;
import com.example.socialmediaapp.data.post.ContentType;
import com.example.socialmediaapp.data.post.Post;
import com.example.socialmediaapp.data.post.PostContent;
import com.example.socialmediaapp.data.post.PostContentViewPagerAdapter;
import com.example.socialmediaapp.databinding.FragmentCreatePostBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;


public class CreatePostFragment extends Fragment {


    FragmentCreatePostBinding binding;

    ActivityResultLauncher<PickVisualMediaRequest> pickMultipleMedia;

    List<PostContent> postContentList = new ArrayList<>();

    PostContentViewPagerAdapter postContentViewPagerAdapter;
    ViewPager2 vp2SelectedContent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCreatePostBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        vp2SelectedContent = binding.vp2CreatePost;
        registerPhotoPicker();

        CreatePostBottomSheetFragment createPostBottomSheetFragment = new CreatePostBottomSheetFragment();


        binding.imageView14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createPostBottomSheetFragment.show(getActivity().getSupportFragmentManager(), "CreatePostBottomSheet");

            }
        });


        binding.btnCreatePostGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectContent();

            }
        });

        binding.btnCreateNewPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewPost();

            }
        });

        //postContentViewPagerAdapter = new PostContentViewPagerAdapter(postContentList);
        vp2SelectedContent.setAdapter(postContentViewPagerAdapter);

        return view;
    }



    private void registerPhotoPicker(){
        pickMultipleMedia = registerForActivityResult(new ActivityResultContracts.PickMultipleVisualMedia(2), uris -> {
                    if (!uris.isEmpty()) {
                        Log.d("PhotoPicker", "Number of items selected: " + uris.size());
                        for (Uri uri : uris) {
                            Log.d("PhotoPicker", "URI: " + uri.toString());
                            if (isImageUri(uri)) {
                                postContentList.add(new PostContent(ContentType.IMAGE, uri.toString()));
                            } else if (isVideoUri(uri)) {
                                postContentList.add(new PostContent(ContentType.VIDEO, uri.toString()));
                            }
                        }
                        postContentViewPagerAdapter.notifyDataSetChanged();

                    } else {
                        Log.d("PhotoPicker", "No media selected");
                    }
                });
    }

    private void selectContent(){
        pickMultipleMedia.launch(new PickVisualMediaRequest.Builder()
                .setMediaType(ActivityResultContracts.PickVisualMedia.ImageAndVideo.INSTANCE)
                .build());

    }


    private boolean isImageUri(Uri uri) {
        String mimeType = requireContext().getContentResolver().getType(uri);
        return mimeType != null && mimeType.startsWith("image");
    }

    private boolean isVideoUri(Uri uri) {
        String mimeType = requireContext().getContentResolver().getType(uri);
        return mimeType != null && mimeType.startsWith("video");
    }


    private void createNewPost(){
        String desc = binding.etCreatePost.getText().toString();
        Post post = new Post("John Doe",desc,false,false,postContentList);
        Bundle bundle = new Bundle();

        bundle.putParcelable("post", post); // Assuming 'post' is your Parcelable Post object
        HomePageFragment homePageFragment = new HomePageFragment();
        homePageFragment.setArguments(bundle);
        NavController navController = Navigation.findNavController(requireView());
        navController.navigate(R.id.homePageFragment, bundle);
    }

}