package com.example.socialmediaapp.data.chat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.socialmediaapp.R;
import com.example.socialmediaapp.data.post.ContentType;

import java.util.List;

public class ChatMessageAdapter extends RecyclerView.Adapter<ChatMessageAdapter.ChatMessageViewHolder>{

    private List<ChatMessage> chatMessageList;

    public ChatMessageAdapter(List<ChatMessage> chatMessageList) {
        this.chatMessageList = chatMessageList;
    }

    @NonNull
    @Override
    public ChatMessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ChatMessageViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_message_row_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ChatMessageViewHolder holder, int position) {
        ChatMessage currentItem = chatMessageList.get(position);


        if(currentItem.getSender() == ChatMessage.Sender.FRIEND){
            holder.tvName.setText("John Doe");
            holder.ivProfilePic.setImageResource(R.drawable.profilepic1);
        }
        else{
            holder.tvName.setText("Adrian Joshua");
            holder.ivProfilePic.setImageResource(R.drawable.profilepic);

        }

        holder.tvMessage.setText(currentItem.getMessage());
        holder.tvTime.setText(currentItem.getFormattedDateTime());


    }

    @Override
    public int getItemCount() {
        return chatMessageList.size();
    }


    public static class ChatMessageViewHolder extends RecyclerView.ViewHolder{

        TextView tvName;
        TextView tvMessage;
        TextView tvTime;
        ImageView ivProfilePic;
        public ChatMessageViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvChatMessageName);
            tvMessage = itemView.findViewById(R.id.tvChatMessageContent);
            tvTime = itemView.findViewById(R.id.tvChatMessageTime);
            ivProfilePic = itemView.findViewById(R.id.ivChatMessagePic);
        }
    }
}
