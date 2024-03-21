package com.example.socialmediaapp.ui.main.fragments;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.socialmediaapp.R;
import com.example.socialmediaapp.data.comment.Comment;
import com.example.socialmediaapp.data.comment.CommentAdapter;
import com.example.socialmediaapp.databinding.FragmentCommentSectionBinding;

import java.util.ArrayList;
import java.util.List;


public class CommentSectionFragment extends Fragment {

    FragmentCommentSectionBinding binding;

    private List<Comment> commentList = new ArrayList<>();

    CommentAdapter commentAdapter;

    RecyclerView commentRecyclerView;

    NestedScrollView nsvCommentSection;

    ConstraintLayout clTopOptions;

    TypedValue typedValue= new TypedValue();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCommentSectionBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        nsvCommentSection = binding.nsvCommentSection;
        clTopOptions = binding.clTopCommentSection;
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        Window window = requireActivity().getWindow();

        addData();




        binding.spinnerSortComment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(
                requireContext(),
                R.array.comment_sort_options,
                R.layout.custom_spinner
        );


        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerSortComment.setAdapter(spinnerAdapter);

        commentAdapter = new CommentAdapter(commentList);
        commentRecyclerView = binding.rvCommentSection;

        commentRecyclerView.setAdapter(commentAdapter);
        commentRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));




//        binding.etAddComment.setOnFocusChangeListener((view1, hasFocus) -> {
//            if (hasFocus) {
//                binding.darkOverlay.setVisibility(View.VISIBLE);
//                binding.constraintLayout4.setAlpha(1.0f);
//                binding.textView35.setBackgroundColor(0);
//
//
//                actionBar.setBackgroundDrawable(new ColorDrawable(Color.argb(128, 0, 0, 0)));
//
//
//
//                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//                window.setStatusBarColor(getResources().getColor(android.R.color.black));
//
//
//            } else {
//                binding.darkOverlay.setVisibility(View.GONE);
//                binding.constraintLayout4.setAlpha(0.5f);
//
//
//
//                getContext().getTheme().resolveAttribute(com.google.android.material.R.attr.colorSurfaceVariant, typedValue, true);
//                binding.textView35.setBackgroundColor(typedValue.data);
//
//
//                getContext().getTheme().resolveAttribute(com.google.android.material.R.attr.backgroundColor, typedValue, true);
//                int color = typedValue.data;
//
//
//                actionBar.setBackgroundDrawable(new ColorDrawable(color));
//
//
//                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//                window.setStatusBarColor(Color.TRANSPARENT);
//
//
//            }
//        });

//        nsvCommentSection.getViewTreeObserver().addOnScrollChangedListener(() -> {
//            if (!isViewVisible(clTopOptions)) {
//                nsvCommentSection.setNestedScrollingEnabled(false);
//                commentRecyclerView.setNestedScrollingEnabled(true);
//            } else {
//                nsvCommentSection.setNestedScrollingEnabled(true);
//                commentRecyclerView.setNestedScrollingEnabled(false);
//            }
//        });

        return view;
    }

    private boolean isViewVisible(View view) {
        Rect scrollBounds = new Rect();
        nsvCommentSection.getDrawingRect(scrollBounds);
        float top = view.getY();
        float bottom = top + view.getHeight();
        return scrollBounds.top < bottom && scrollBounds.bottom > top;
    }
    private void addData(){
        commentList.add(new Comment());
        commentList.add(new Comment());
        commentList.add(new Comment());
        commentList.add(new Comment());
        commentList.add(new Comment());
        commentList.add(new Comment());

        commentList.add(new Comment());
        commentList.add(new Comment());
        commentList.add(new Comment());
        commentList.add(new Comment());
        commentList.add(new Comment());
        commentList.add(new Comment());

        commentList.add(new Comment());
        commentList.add(new Comment());
        commentList.add(new Comment());
        commentList.add(new Comment());
        commentList.add(new Comment());
        commentList.add(new Comment());
    }





}