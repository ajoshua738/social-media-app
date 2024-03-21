package com.example.socialmediaapp.data.post;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;


import com.example.socialmediaapp.R;

import java.util.ArrayList;
import java.util.List;

//Posts in the user profile
public class UserProfileNewPostAdapter extends RecyclerView.Adapter<UserProfileNewPostAdapter.NewPostViewHolder> {


    private List<Post> postList = new ArrayList<>();

    private int itemLayout;
    private int gridLayout = R.layout.grid_post_layout;
    private int listLayout = R.layout.list_post_layout;



    public UserProfileNewPostAdapter(List<Post> postList, int itemLayout) {
        this.postList = postList;
        this.itemLayout = itemLayout;

    }



    @NonNull
    @Override
    public NewPostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NewPostViewHolder(LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NewPostViewHolder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public class NewPostViewHolder extends RecyclerView.ViewHolder{
        ImageView ivCommentSection;
        public NewPostViewHolder(@NonNull View itemView){
            super(itemView);

            if (itemLayout == gridLayout) {
                LinearLayout itemGridLayout = itemView.findViewById(R.id.gridPostLayout);
                itemGridLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        navigateToPostFragment(itemView);
                    }
                });
            } else if (itemLayout == listLayout) {
                ConstraintLayout itemListLayout = itemView.findViewById(R.id.listPostLayout);
                ivCommentSection = itemView.findViewById(R.id.ivListPostComment);
                itemListLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        navigateToPostFragment(itemView);
                    }
                });

                ivCommentSection.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        navigateToCommentSectionFragment(itemView);
                    }
                });
            }

        }

    }

    private void navigateToPostFragment(View view) {
        NavController navController = Navigation.findNavController(view);
        navController.navigate(R.id.action_profileFragment_to_viewPostFragment);
    }

    private void navigateToCommentSectionFragment(View view){
        NavController navController = Navigation.findNavController(view);
        navController.navigate(R.id.commentSectionFragment);

    }


}
