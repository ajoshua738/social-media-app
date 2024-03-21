package com.example.socialmediaapp.ui.main.profile;

import android.content.DialogInterface;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.HapticFeedbackConstants;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import com.example.socialmediaapp.R;
import com.example.socialmediaapp.databinding.FragmentViewPostBinding;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;


public class ViewPostFragment extends Fragment {


    FragmentViewPostBinding binding;
    boolean isLiked = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentViewPostBinding.inflate(inflater,container,false);
        View view = binding.getRoot();


        // Create a popup menu
        PopupMenu popupMenu = new PopupMenu(requireContext(), binding.ivMoreOptions);
        popupMenu.inflate(R.menu.post_options_menu);




        binding.avViewPostLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                isLiked = !isLiked; // Toggle liked state
                if (isLiked) {
                    // Play animation normally
                    binding.avViewPostLike.setSpeed(4f); // Set speed to positive for normal playback
                    binding.avViewPostLike.playAnimation();
                } else {
                    // Play animation in reverse
                    binding.avViewPostLike.setSpeed(-4f); // Set speed to negative for reverse playback
                    binding.avViewPostLike.playAnimation();
                }
            }
        });




        // Set a listener for menu item clicks
        popupMenu.setOnMenuItemClickListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.post_share) {// Handle share option
                return true;
            } else if (itemId == R.id.post_download) {// Handle download option
                return true;
            } else if (itemId == R.id.post_delete) {// Handle delete option
                showDeleteDialog();
                return true;
            }
            return false;

        });

        // Show the popup menu
        binding.ivMoreOptions.setOnClickListener(v -> popupMenu.show());


        binding.ivViewPostComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(requireView());
                navController.navigate(R.id.action_viewPostFragment_to_commentSectionFragment);
            }
        });


        return view;
    }


    public void showDeleteDialog(){

        new MaterialAlertDialogBuilder(requireContext(),R.style.ThemeOverlay_App_MaterialAlertDialog)
                .setTitle(getString(R.string.dialog_delete_post_title))
                .setMessage(getString(R.string.dialog_delete_post_message))
                .setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Respond to negative button press
                    }
                })
                .setPositiveButton(getString(R.string.delete), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Respond to positive button press
                    }
                })
                .show();
    }

}