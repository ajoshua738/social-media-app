package com.example.socialmediaapp.data.notification;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.socialmediaapp.R;

import java.util.ArrayList;
import java.util.List;

public class FriendListAdapter extends RecyclerView.Adapter<FriendListAdapter.FriendListViewHolder>{


    private List<Notification> notificationList = new ArrayList<>();


    public FriendListAdapter(List<Notification> notificationList) {
        this.notificationList = notificationList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FriendListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FriendListAdapter.FriendListViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.friendlist_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FriendListViewHolder holder, int position) {
        Notification currentItem = notificationList.get(position);
        holder.tvName.setText(String.valueOf(currentItem.getName()));

        holder.btnAdd.setOnClickListener(v -> {
            int adapterPosition = holder.getAdapterPosition();
            if (adapterPosition != RecyclerView.NO_POSITION) {
                notificationList.remove(adapterPosition);
                notifyItemRemoved(adapterPosition);
            }
        });

        holder.btnIgnore.setOnClickListener(v -> {
            int adapterPosition = holder.getAdapterPosition();
            if (adapterPosition != RecyclerView.NO_POSITION) {
                notificationList.remove(adapterPosition);
                notifyItemRemoved(adapterPosition);
            }
        });

    }

    @Override
    public int getItemCount() {
        return notificationList.size();
    }

    public class FriendListViewHolder extends RecyclerView.ViewHolder{
        private Button btnAdd;
        private Button btnIgnore;

        private TextView tvName;
        public FriendListViewHolder(@NonNull View itemView){
            super(itemView);
            tvName = itemView.findViewById(R.id.tvFriendListName);
            btnAdd = itemView.findViewById(R.id.btnAddFriend);
            btnIgnore = itemView.findViewById(R.id.btnIgnoreFriend);

        }
    }

}
