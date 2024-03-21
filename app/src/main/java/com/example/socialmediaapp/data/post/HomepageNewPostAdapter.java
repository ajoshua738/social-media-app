package com.example.socialmediaapp.data.post;

import android.content.Context;
import android.view.HapticFeedbackConstants;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.airbnb.lottie.LottieAnimationView;
import com.example.socialmediaapp.R;
import com.example.socialmediaapp.ui.main.fragments.HomePageFragmentDirections;

import java.util.ArrayList;
import java.util.List;

import io.github.glailton.expandabletextview.ExpandableTextView;

public class HomepageNewPostAdapter extends RecyclerView.Adapter<HomepageNewPostAdapter.NewPostViewHolder>{
    private List<Post> postList = new ArrayList<>();
    private List<PostContent> postContentList = new ArrayList<>();
    private Context context;

    @NonNull
    @Override
    public NewPostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new NewPostViewHolder(LayoutInflater.from(context).inflate(R.layout.list_post_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NewPostViewHolder holder, int position) {
        Post currentItem = postList.get(position);

        holder.avLikeButton.setProgress(currentItem.isLiked() ? 1f : 0f); // Set initial animation state


        holder.avLikeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentItem.setLiked(!currentItem.isLiked()); // Toggle like state
                holder.avLikeButton.setProgress(currentItem.isLiked() ? 1f : 0f); // Update animation state

                if (currentItem.isLiked()) {
                    v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                    holder.avLikeButton.setSpeed(4f);
                    holder.avLikeButton.playAnimation();
                }
            }
        });



        holder.btnComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavDirections action = HomePageFragmentDirections.actionHomePageFragmentToCommentSectionFragment();
                Navigation.findNavController(holder.itemView).navigate(action);

            }
        });






        holder.tvPostDesc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.tvPostDesc.toggle(); // Expand or collapse the text
            }
        });




        PostContentViewPagerAdapter adapter = new PostContentViewPagerAdapter(currentItem.getPostContents());
        holder.vp2PostContent.setAdapter(adapter);

    }

    @Override
    public int getItemCount() {
        return postList.size();
    }


    public static class NewPostViewHolder extends RecyclerView.ViewHolder{
        LottieAnimationView avLikeButton;
        TextView tvName;
        ImageView btnComment;

        ViewPager2 vp2PostContent;


        ExpandableTextView tvPostDesc;
        public NewPostViewHolder(@NonNull View itemView){
            super(itemView);

            avLikeButton = itemView.findViewById(R.id.avLikeButton);
            tvName = itemView.findViewById(R.id.tvPostName);
            btnComment = itemView.findViewById(R.id.ivListPostComment);
            tvPostDesc = itemView.findViewById(R.id.tvPostDesc);

            vp2PostContent = itemView.findViewById(R.id.vp2PostContent);



        }
    }


    public HomepageNewPostAdapter(List<Post> postList) {
        this.postList = postList;
        notifyDataSetChanged();
    }
}
