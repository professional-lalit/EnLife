package com.enlife.app.screens.main.fragments.schedule;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.enlife.app.R;
import com.enlife.app.database.models.Event;
import com.enlife.app.database.models.GoalEvent;
import com.enlife.app.screens.main.fragments.home.events.EventViewHolder;

import java.util.List;

public class GoalEventAdapter extends RecyclerView.Adapter<GoalEventViewHolder> {

    private final List<GoalEvent> events;

    public GoalEventAdapter(List<GoalEvent> events) {
        this.events = events;
    }

    @NonNull
    @Override
    public GoalEventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_event_list_layout, null, false);
        return new GoalEventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GoalEventViewHolder holder, int position) {
        holder.onBind(events.get(position));
    }

    @Override
    public int getItemCount() {
        return events.size();
    }
}