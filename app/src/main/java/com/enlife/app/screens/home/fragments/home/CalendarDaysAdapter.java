package com.enlife.app.screens.home.fragments.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.enlife.app.R;
import com.enlife.app.models.CalendarDay;

import java.util.List;

public class CalendarDaysAdapter extends RecyclerView.Adapter<CalenderDayViewHolder> {

    private List<CalendarDay> calendarDays;
    private DayClickListener clickListener;

    public CalendarDaysAdapter(List<CalendarDay> calendarDays, DayClickListener clickListener) {
        this.calendarDays = calendarDays;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public CalenderDayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_calendar_day_layout, parent, false);
        return new CalenderDayViewHolder(view, clickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CalenderDayViewHolder holder, int position) {
        holder.onBind(calendarDays.get(position));
    }

    @Override
    public int getItemCount() {
        return calendarDays.size();
    }
}
