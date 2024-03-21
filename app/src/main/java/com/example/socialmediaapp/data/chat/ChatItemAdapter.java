package com.example.socialmediaapp.data.chat;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.socialmediaapp.R;

import java.util.ArrayList;
import java.util.List;

public class ChatItemAdapter extends RecyclerView.Adapter<ChatItemAdapter.ChatItemViewHolder> {

    private List<ChatItem> chatItemList = new ArrayList<>();

    @NonNull
    @Override
    public ChatItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ChatItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item_row_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ChatItemViewHolder holder, int position) {
        ChatItem currentItem = chatItemList.get(position);

        holder.tvName.setText(currentItem.getFriendName());
        holder.tvMessage.setText(currentItem.getLastMessage());
        holder.tvTime.setText(getFormattedTime(currentItem.getTimeSinceLastMessage()));

        holder.clChatItemRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.action_chatFragment_to_chatMessageFragment);
            }
        });

    }

    @Override
    public int getItemCount() {
        return chatItemList.size();
    }


    public static class ChatItemViewHolder extends RecyclerView.ViewHolder{
        TextView tvName;
        TextView tvMessage;
        TextView tvTime;
        ImageView ivPic;

        ConstraintLayout clChatItemRow;
        public ChatItemViewHolder(@NonNull View itemView){
            super(itemView);

            tvName = itemView.findViewById(R.id.tvChatItemName);
            tvMessage = itemView.findViewById(R.id.tvChatItemMessage);
            tvTime = itemView.findViewById(R.id.tvChatItemTime);
            ivPic = itemView.findViewById(R.id.ivChatItemPic);
            clChatItemRow = itemView.findViewById(R.id.clChatItemRow);
        }
    }

    public ChatItemAdapter(List<ChatItem> chatItemList){
        this.chatItemList = chatItemList;
        notifyDataSetChanged();
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
