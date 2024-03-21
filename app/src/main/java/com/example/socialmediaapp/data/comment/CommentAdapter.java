package com.example.socialmediaapp.data.comment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.socialmediaapp.R;

import java.util.ArrayList;
import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {

    private List<Comment> commentList = new ArrayList<>();

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CommentViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }

    public class CommentViewHolder extends RecyclerView.ViewHolder{
        TextView tvViewCommentReply;

        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);

            tvViewCommentReply = itemView.findViewById(R.id.tvViewCommentReply);

            tvViewCommentReply.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    navigateToReplyFragment(itemView);
                }
            });
        }
    }

    public CommentAdapter(List<Comment> commentList) {
        this.commentList = commentList;
        notifyDataSetChanged();
    }


    private void navigateToReplyFragment(View view){
        NavController navController = Navigation.findNavController(view);
        navController.navigate(R.id.action_commentSectionFragment_to_commentReplyFragment);

    }
}
