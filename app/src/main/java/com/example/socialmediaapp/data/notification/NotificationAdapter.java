package com.example.socialmediaapp.data.notification;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.socialmediaapp.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder> {

    private List<Notification> notificationList = new ArrayList<>();



    public static class NotificationViewHolder extends RecyclerView.ViewHolder{
        public NotificationViewHolder(@NonNull View itemView){ super(itemView);}
    }

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NotificationViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {
        Notification currentItem = notificationList.get(position);

        TextView tvName = holder.itemView.findViewById(R.id.tvNotificationName);
        tvName.setText(String.valueOf(currentItem.getName()));


        // Format dateTimeInMillis
        SimpleDateFormat formatter = new SimpleDateFormat("EEE 'at' HH:mm", Locale.getDefault());
        String formattedDateTime = formatter.format(new Date(currentItem.getDateTimeInMillis()));
        TextView tvDateTime = holder.itemView.findViewById(R.id.tvNotificationTime);
        tvDateTime.setText(formattedDateTime);


        ConstraintLayout rowLayout = holder.itemView.findViewById(R.id.notificationRowLayout);



        if (currentItem.getIsViewed()) {
            rowLayout.setBackground(null);
        }
        rowLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Set background to null
                currentItem.setIsViewed(true);
                notifyDataSetChanged();

            }
        });


    }

    @Override
    public int getItemCount() {
        return notificationList.size();
    }

    public NotificationAdapter(List<Notification> notificationList) {
        this.notificationList = notificationList;
        notifyDataSetChanged();
    }






    public Notification getItem(int position) {
        if (position >= 0 && position < notificationList.size()) {
            return notificationList.get(position);
        }
        return null;
    }


    public void deleteItem(int position) {
        if (position >= 0 && position < notificationList.size()) {
            notificationList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void restoreItem(Notification item, int position) {
        if (position >= 0 && position <= notificationList.size()) {
            notificationList.add(position, item);
            notifyItemInserted(position);
        } else {
            notificationList.add(item);
            notifyItemInserted(notificationList.size() - 1);
        }
    }



}
