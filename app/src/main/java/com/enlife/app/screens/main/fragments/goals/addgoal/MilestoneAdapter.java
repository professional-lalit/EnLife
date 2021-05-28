package com.enlife.app.screens.main.fragments.goals.addgoal;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.enlife.app.R;
import com.enlife.app.database.models.Milestone;

import java.util.List;

public class MilestoneAdapter extends RecyclerView.Adapter<MilestoneViewHolder> {

    private final List<Milestone> milestones;

    public MilestoneAdapter(List<Milestone> milestones) {
        this.milestones = milestones;
    }

    @NonNull
    @Override
    public MilestoneViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_milestone_list_layout, parent, false);
        return new MilestoneViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MilestoneViewHolder holder, int position) {
        holder.onBind(milestones.get(position));
    }

    @Override
    public int getItemCount() {
        return milestones.size();
    }
}
