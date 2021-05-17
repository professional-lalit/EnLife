package com.enlife.app.screens.home.fragments.home.events;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.enlife.app.R;
import com.enlife.app.database.models.Event;
import com.enlife.app.utils.DateFormatter;

import java.util.Date;

public class EventViewHolder extends RecyclerView.ViewHolder {

    private ImageView imgEvent;
    private TextView txtEventTitle;
    private TextView txtEventTime;

    public EventViewHolder(@NonNull View itemView) {
        super(itemView);
        imgEvent = itemView.findViewById(R.id.img_event);
        txtEventTitle = itemView.findViewById(R.id.txt_event_title);
        txtEventTime = itemView.findViewById(R.id.txt_event_time);
    }

    void onBind(Event event) {
        if (!event.getImagePath().isEmpty()) {
            imgEvent.setVisibility(View.GONE);
        } else {
            imgEvent.setVisibility(View.VISIBLE);
        }

        txtEventTitle.setText(event.getTitle());
        txtEventTime.setText(getTimeText(event));
    }

    private String getTimeText(Event event) {
        StringBuilder strTimeString = new StringBuilder();
        strTimeString.append(event.getFromTime());
        strTimeString.append(":");
        strTimeString.append(event.getToTime());
        return strTimeString.toString();
    }
}
