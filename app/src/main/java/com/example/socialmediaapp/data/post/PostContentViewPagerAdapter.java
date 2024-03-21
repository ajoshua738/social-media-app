package com.example.socialmediaapp.data.post;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.media3.common.MediaItem;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.ui.PlayerView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.socialmediaapp.R;

import java.util.List;

public class PostContentViewPagerAdapter extends RecyclerView.Adapter<PostContentViewPagerAdapter.PostContentViewHolder> {
    private final List<PostContent> postContents;
    private VideoView lastVideoView;

    public PostContentViewPagerAdapter(List<PostContent> postContents) {
        this.postContents = postContents;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PostContentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_content_layout, parent, false);
        return new PostContentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostContentViewHolder holder, int position) {
        PostContent postContent = postContents.get(position);
        switch (postContent.getType()) {
            case IMAGE:
                holder.imageView.setVisibility(View.VISIBLE);
                holder.playerView.setVisibility(View.GONE);
                Glide.with(holder.itemView.getContext()).load(Uri.parse(postContent.getResourceId())).into(holder.imageView);

                //holder.imageView.setImageResource(postContent.getResourceId());
                break;
            case VIDEO:

                holder.imageView.setVisibility(View.GONE);
                holder.playerView.setVisibility(View.VISIBLE);
                ExoPlayer player = new ExoPlayer.Builder(holder.itemView.getContext()).build();
                holder.playerView.setPlayer(player);
                MediaItem mediaItem = MediaItem.fromUri(Uri.parse(postContent.getResourceId()));
                player.setMediaItem(mediaItem);
                player.prepare();

                // Pause the player when it's not visible
                holder.itemView.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
                    @Override
                    public void onViewAttachedToWindow(View v) {
                        ExoPlayer player = new ExoPlayer.Builder(holder.itemView.getContext()).build();
                        holder.playerView.setPlayer(player);
                        MediaItem mediaItem = MediaItem.fromUri(Uri.parse(postContent.getResourceId()));
                        player.setMediaItem(mediaItem);
                        player.prepare();

                    }

                    @Override
                    public void onViewDetachedFromWindow(View v) {
                        player.pause();
                        player.release();
                    }
                });
                break;

            case IMAGE_D:
                holder.imageView.setVisibility(View.VISIBLE);
                holder.playerView.setVisibility(View.GONE);
                Glide.with(holder.itemView.getContext()).load(postContent.getResourceId()).into(holder.imageView);
                break;

            case VIDEO_D:
                holder.imageView.setVisibility(View.GONE);
                holder.playerView.setVisibility(View.VISIBLE);
                ExoPlayer player1 = new ExoPlayer.Builder(holder.itemView.getContext()).build();
                holder.playerView.setPlayer(player1);
                MediaItem mediaItem1 = MediaItem.fromUri("android.resource://" + holder.itemView.getContext().getPackageName() + "/" + postContent.getResourceId());
                player1.setMediaItem(mediaItem1);
                player1.prepare();

                holder.itemView.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
                    @Override
                    public void onViewAttachedToWindow(View v) {
                        ExoPlayer player = new ExoPlayer.Builder(holder.itemView.getContext()).build();
                        holder.playerView.setPlayer(player);
                        MediaItem mediaItem = MediaItem.fromUri(Uri.parse(postContent.getResourceId()));
                        player.setMediaItem(mediaItem);
                        player.prepare();

                    }

                    @Override
                    public void onViewDetachedFromWindow(View v) {
                        player1.pause();
                        player1.release();
                    }
                });
                break;
        }
    }

    @Override
    public int getItemCount() {
        return postContents.size();
    }

    public static class PostContentViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        //VideoView videoView;

        PlayerView playerView;
        public PostContentViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.ivPostContent);
            //videoView = itemView.findViewById(R.id.vvPostContent);
            playerView = itemView.findViewById(R.id.pvPostContent);
        }
    }


}