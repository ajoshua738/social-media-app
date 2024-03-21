package com.example.socialmediaapp.utils;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.TypedValue;

import androidx.appcompat.app.ActionBar;

public class DarkenBackground {

    public static void darkenActionBar(boolean darken, Context context, ActionBar actionBar){
        TypedValue typedValueBackground = new TypedValue();
        context.getTheme().resolveAttribute(com.google.android.material.R.attr.backgroundColor, typedValueBackground, true);
        int defaultColor = typedValueBackground.data;

        TypedValue typedValueDarkenColor = new TypedValue();

        if(darken){
            actionBar.setBackgroundDrawable(new ColorDrawable(defaultColor));

        }else{

        }

    }
}
