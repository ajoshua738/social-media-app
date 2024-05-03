package com.example.socialmediaapp.data.post;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.util.Log;
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
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.socialmediaapp.R;
import com.example.socialmediaapp.data.card.Card;
import com.example.socialmediaapp.data.merchant.Merchant;
import com.example.socialmediaapp.ui.main.fragments.HomePageFragmentDirections;
import com.google.android.material.imageview.ShapeableImageView;
import com.squareup.picasso.Picasso;
import com.stfalcon.imageviewer.StfalconImageViewer;
import com.stfalcon.imageviewer.loader.ImageLoader;


import java.util.ArrayList;
import java.util.List;

import io.github.glailton.expandabletextview.ExpandableTextView;
import me.relex.circleindicator.CircleIndicator3;

public class HomepageNewPostAdapter extends RecyclerView.Adapter<HomepageNewPostAdapter.NewPostViewHolder>{
    private List<Post> postList = new ArrayList<>();

    private Context context;



    static ArrayList<Card> cardList = new ArrayList<Card>();
    Activity activity;

    String url = "";
    @NonNull
    @Override
    public NewPostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new NewPostViewHolder(LayoutInflater.from(context).inflate(R.layout.list_post_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NewPostViewHolder holder, int position) {
        //Post currentItem = postList.get(position);


//        holder.avLikeButton.setProgress(currentItem.isLiked() ? 1f : 0f); // Set initial animation state
//
//
//        holder.avLikeButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                currentItem.setLiked(!currentItem.isLiked()); // Toggle like state
//                holder.avLikeButton.setProgress(currentItem.isLiked() ? 1f : 0f); // Update animation state
//
//                if (currentItem.isLiked()) {
//                    v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
//                    holder.avLikeButton.setSpeed(4f);
//                    holder.avLikeButton.playAnimation();
//                }
//            }
//        });



        holder.btnComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavDirections action = HomePageFragmentDirections.actionHomePageFragmentToCommentSectionFragment();
                Navigation.findNavController(holder.itemView).navigate(action);

            }
        });






//        holder.tvPostDesc.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                holder.tvPostDesc.toggle(); // Expand or collapse the text
//            }
//        });


        List<PostContent> postContentList = new ArrayList<>(); // Create a new list for each item
        List<String> imageUrls = new ArrayList<>();
        Card currentCard = cardList.get(position);
//        url = currentCard.getImageUrl();

//        Glide.with(holder.itemView.getContext())
//                .load(url)
//                .apply(new RequestOptions()
//                        .dontAnimate()//to prevent image follows placeholder size
//                        .diskCacheStrategy(DiskCacheStrategy.RESOURCE))
//                .into(holder.ivMerchantCardImage);



        holder.tvName.setText(currentCard.getCardName());
        holder.tvPostDesc.setText(currentCard.getBenefitsDesc());
        PostContent cardImg = new PostContent(ContentType.IMAGE_URL,currentCard.getImageUrl());
        postContentList.add(cardImg);
        imageUrls.add(currentCard.getImageUrl());
        if (!currentCard.getBenefitsImg().isEmpty()) {
            PostContent benefitImg = new PostContent(ContentType.IMAGE_URL, currentCard.getBenefitsImg());
            postContentList.add(benefitImg);
            imageUrls.add(currentCard.getBenefitsImg());
        }

        if(currentCard.getBenefitsImg().isEmpty()){
            Log.d("BENEFITS IS EMPTY","");
        }


        Glide.with(holder.itemView.getContext())
                .load(currentCard.getImageUrl())
                .apply(new RequestOptions()
                        .dontAnimate()//to prevent image follows placeholder size
                        .diskCacheStrategy(DiskCacheStrategy.RESOURCE))
                .into(holder.ivProfilePic);


        Log.d("LOGO URL",""+currentCard.getLogoUrl());
        PostContentViewPagerAdapter adapter = new PostContentViewPagerAdapter(postContentList, imageUrls);
        holder.vp2PostContent.setAdapter(adapter);

        holder.vpIndicator.setViewPager(holder.vp2PostContent);




//        holder.vp2PostContent.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
//            @Override
//            public void onPageSelected(int position) {
//                super.onPageSelected(position);
//
//                new StfalconImageViewer.Builder<>(activity, imageUrls, (imageView, image) -> {
//                    Glide.with(context)
//                            .load(image) // Load the image URL into Glide
//                            .into(imageView);
//                }).withStartPosition(position) // Start at the selected position
//                        .show();
//            }
//        });



    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }


    public static class NewPostViewHolder extends RecyclerView.ViewHolder{
        LottieAnimationView avLikeButton;
        TextView tvName;
        ImageView btnComment;

        ViewPager2 vp2PostContent;
        ExpandableTextView tvPostDesc;

        ShapeableImageView ivProfilePic;

        CircleIndicator3 vpIndicator;
        public NewPostViewHolder(@NonNull View itemView){
            super(itemView);

            avLikeButton = itemView.findViewById(R.id.avLikeButton);
            tvName = itemView.findViewById(R.id.tvPostName);
            btnComment = itemView.findViewById(R.id.ivListPostComment);
            tvPostDesc = itemView.findViewById(R.id.tvPostDesc);

            vp2PostContent = itemView.findViewById(R.id.vp2PostContent);

            ivProfilePic = itemView.findViewById(R.id.ivPostProfilePic);

            vpIndicator = itemView.findViewById(R.id.vp2PostContentIndicator);


        }
    }


    public HomepageNewPostAdapter(List<Post> postList) {
        this.postList = postList;
        notifyDataSetChanged();
    }

    public HomepageNewPostAdapter(ArrayList<Card> cardList, Activity activity) {
        this.cardList = cardList;
        this.activity = activity;
        notifyDataSetChanged();
    }



}
