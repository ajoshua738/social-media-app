package com.example.socialmediaapp.data.post;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.List;

public class Post implements Parcelable {

    private String name;

    private String description;

    private boolean isLiked;

    private boolean isExpanded;

    private List<PostContent> postContents;


    protected Post(Parcel in) {
        name = in.readString();
        description = in.readString();
        isLiked = in.readByte() != 0;
        isExpanded = in.readByte() != 0;
    }

    public static final Creator<Post> CREATOR = new Creator<Post>() {
        @Override
        public Post createFromParcel(Parcel in) {
            return new Post(in);
        }

        @Override
        public Post[] newArray(int size) {
            return new Post[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }


    public List<PostContent> getPostContents() {
        return postContents;
    }

    public void setPostContents(List<PostContent> postContents) {
        this.postContents = postContents;
    }

    public Post() {
    }

    public Post(String name, String description, boolean isLiked, boolean isExpanded, List<PostContent> postContents) {
        this.name = name;
        this.description = description;
        this.isLiked = isLiked;
        this.isExpanded = isExpanded;
        this.postContents = postContents;
    }

    public Post(String name, String description, boolean isLiked, boolean isExpanded) {
        this.name = name;
        this.description = description;
        this.isLiked = isLiked;
        this.isExpanded = isExpanded;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(description);
        dest.writeByte((byte) (isLiked ? 1 : 0));
        dest.writeByte((byte) (isExpanded ? 1 : 0));
    }
}
