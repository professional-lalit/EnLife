package com.enlife.app.screens.main.fragments.schedule;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.enlife.app.R;
import com.enlife.app.database.models.Event;
import com.enlife.app.database.models.GoalEvent;

public class GoalEventViewHolder extends RecyclerView.ViewHolder {

    private final TextView txtEventIndex;
    private final TextView txtEventTitle;
    private final TextView txtEventTime;
    private final TextView txtEventGoal;

    public GoalEventViewHolder(@NonNull View itemView) {
        super(itemView);
        txtEventIndex = itemView.findViewById(R.id.txt_event_index);
        txtEventTitle = itemView.findViewById(R.id.txt_event_title);
        txtEventTime = itemView.findViewById(R.id.txt_event_time);
        txtEventGoal = itemView.findViewById(R.id.txt_event_goal);
    }

    void onBind(GoalEvent event) {
        txtEventIndex.setText(String.valueOf(getAdapterPosition() + 1));
        txtEventTitle.setText(event.getEvent().getTitle());
        txtEventTime.setText(getTimeText(event.getEvent()).toLowerCase());
        if (event.getGoal() != null) {
            txtEventGoal.setText(getGoalText(event));
            txtEventGoal.setVisibility(View.VISIBLE);
        } else {
            txtEventGoal.setVisibility(View.GONE);
        }
    }

    private String getGoalText(GoalEvent event) {
        return "Associated with "
                + event.getGoal().getTitle()
                + ", "
                + event.getMilestone().getTitle();
    }

    private String getTimeText(Event event) {
        return event.getFromTime() +
                " : " +
                event.getToTime();
    }
}
