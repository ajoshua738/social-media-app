package com.example.socialmediaapp.utils;

import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;
import android.content.Context;

public class CustomScrollView extends ScrollView {
    private boolean scrollEnabled = true;

    public CustomScrollView(Context context) {
        super(context);
    }

    public CustomScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        return scrollEnabled && super.onTouchEvent(e);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        return scrollEnabled && super.onInterceptTouchEvent(e);
    }

    public void setScrollEnabled(boolean scrollEnabled) {
        this.scrollEnabled = scrollEnabled;
    }
}