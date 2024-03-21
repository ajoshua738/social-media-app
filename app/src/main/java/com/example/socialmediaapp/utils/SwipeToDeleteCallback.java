package com.example.socialmediaapp.utils;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.socialmediaapp.data.notification.Notification;
import com.example.socialmediaapp.data.notification.NotificationAdapter;
import com.google.android.material.snackbar.Snackbar;

public class SwipeToDeleteCallback extends ItemTouchHelper.Callback {
    private NotificationAdapter notificationAdapter;
    private RecyclerView recyclerView;

    public SwipeToDeleteCallback(NotificationAdapter adapter, RecyclerView recyclerView){
        notificationAdapter = adapter;
        this.recyclerView = recyclerView;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        return makeMovementFlags(0, ItemTouchHelper.RIGHT); // Only allow right swipe
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                          RecyclerView.ViewHolder target) {
        return false; // Do not allow moving items
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        int position = viewHolder.getAdapterPosition();
        // Show a dialog or perform some action to confirm deletion
//        AlertDialog.Builder builder = new AlertDialog.Builder(viewHolder.itemView.getContext());
//        builder.setMessage("Are you sure you want to delete this item?")
//                .setCancelable(false)
//                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        notificationAdapter.deleteItem(position);
//                    }
//                })
//                .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        notificationAdapter.notifyItemChanged(position); // Notify to reset the view
//                    }
//                });
//        AlertDialog alert = builder.create();
//        alert.show();



        Notification deletedItem = notificationAdapter.getItem(position); // Get the deleted item

        // Delete the item from the adapter
        notificationAdapter.deleteItem(position);

        // Show a Snackbar to allow undo
        Snackbar.make(recyclerView, "Notification deleted", Snackbar.LENGTH_LONG)
                .setAction("Undo", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // If the user clicks undo, re-add the item to the adapter
                        notificationAdapter.restoreItem(deletedItem, position);
                        recyclerView.scrollToPosition(position); // Scroll to the restored item
                    }
                })
                .show();
    }



}
