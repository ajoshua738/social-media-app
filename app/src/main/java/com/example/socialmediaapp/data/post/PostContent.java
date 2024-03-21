package com.example.socialmediaapp.data.post;

import java.util.List;

public class PostContent {
    private final ContentType type;
    private final String resourceId;

    public PostContent(ContentType type, String resourceId) {
        this.type = type;
        this.resourceId = resourceId;
    }

    public ContentType getType() {
        return type;
    }

    public String getResourceId() {
        return resourceId;
    }
}

