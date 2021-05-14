package com.enlife.app.screens.home.fragments.home;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.enlife.app.R;
import com.enlife.app.models.CalendarDay;

public class CalenderDayViewHolder extends RecyclerView.ViewHolder {

    private final ImageView imgSelectedBg;
    private final TextView txtDayValue;
    private final ImageView imgContentIndicator;

    private CalendarDay calendarDay;
    private final DayClickListener clickListener;

    public CalenderDayViewHolder(@NonNull View itemView, DayClickListener clickListener) {
        super(itemView);
        this.clickListener = clickListener;
        imgSelectedBg = itemView.findViewById(R.id.img_selected_bg);
        txtDayValue = itemView.findViewById(R.id.txt_day_value);
        imgContentIndicator = itemView.findViewById(R.id.img_content_indicator);
    }

    void onBind(CalendarDay calendarDay) {
        this.calendarDay = calendarDay;

        if (this.calendarDay.isHasContent()) {
            imgContentIndicator.setVisibility(View.VISIBLE);
        } else {
            imgContentIndicator.setVisibility(View.GONE);
        }

        if (this.calendarDay.isSelected()) {
            imgSelectedBg.setVisibility(View.VISIBLE);
        } else {
            imgSelectedBg.setVisibility(View.GONE);
        }

        if (this.calendarDay.isCurrentMonthDay()) {
            txtDayValue.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.black));
        } else {
            txtDayValue.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.greyText));
        }

        txtDayValue.setText(this.calendarDay.getDate() + "");

        itemView.setOnClickListener(v -> clickListener.onDayClicked(getAdapterPosition(), this.calendarDay));
    }
}
