package com.example.socialmediaapp.rest;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.socialmediaapp.R;
import com.example.socialmediaapp.data.post.HomepageNewPostAdapter;

import java.util.List;

import io.github.glailton.expandabletextview.ExpandableTextView;

public class RestUserAdapter extends RecyclerView.Adapter<RestUserAdapter.RestUserViewHolder> {
    private List<RestUser> users;

    public RestUserAdapter(List<RestUser> users){
        this.users = users;
        notifyDataSetChanged();
    }

    public void setUsers(List<RestUser> users) {
        this.users = users;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public RestUserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RestUserViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_post_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RestUserViewHolder holder, int position) {
        RestUser user = users.get(position);
        holder.tvName.setText(user.getName());


        holder.tvPostDesc.setText(user.toString());
        Log.d("user haha lol",""+user.toString());

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public static class RestUserViewHolder extends RecyclerView.ViewHolder{

        TextView tvName;
        ExpandableTextView tvPostDesc;
        public RestUserViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvPostName);
            tvPostDesc = itemView.findViewById(R.id.tvPostDesc);
        }
    }
}
