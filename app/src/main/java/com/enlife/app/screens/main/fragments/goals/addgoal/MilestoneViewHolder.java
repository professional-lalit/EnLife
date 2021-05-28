package com.enlife.app.screens.main.fragments.goals.addgoal;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.enlife.app.R;
import com.enlife.app.database.models.Milestone;
import com.enlife.app.utils.DateFormatter;

public class MilestoneViewHolder extends RecyclerView.ViewHolder {

    private final TextView txtMilestoneTitle;
    private final TextView txtMilestoneDescription;
    private final TextView txtMilestoneDuration;

    public MilestoneViewHolder(@NonNull View itemView) {
        super(itemView);
        txtMilestoneTitle = itemView.findViewById(R.id.txt_milestone_title);
        txtMilestoneDescription = itemView.findViewById(R.id.txt_milestone_description);
        txtMilestoneDuration = itemView.findViewById(R.id.txt_milestone_duration);
    }

    void onBind(Milestone milestone) {
        txtMilestoneTitle.setText(milestone.getTitle());
        txtMilestoneDescription.setText(milestone.getDescription());

        String strFromDate = new DateFormatter().changeFormat(
                DateFormatter.DateFormat.INDIAN_DATE_FORMAT,
                DateFormatter.DateFormat.MMM_dd_yyyy,
                milestone.getFromDate()
        );
        String strToDate = new DateFormatter().changeFormat(
                DateFormatter.DateFormat.INDIAN_DATE_FORMAT,
                DateFormatter.DateFormat.MMM_dd_yyyy,
                milestone.getToDate()
        );
        String durationText = strFromDate + " - " + strToDate;
        txtMilestoneDuration.setText(durationText);
    }
}
