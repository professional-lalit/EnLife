package com.enlife.app.screens.home.fragments.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.enlife.app.R;
import com.enlife.app.models.CalendarDay;
import com.enlife.app.utils.DateFormatter;

import java.util.List;

public class CalendarDaysAdapter extends RecyclerView.Adapter<CalendarDaysAdapter.CalenderDayViewHolder> {

    private List<CalendarDay> calendarDays;

    public CalendarDaysAdapter(List<CalendarDay> calendarDays) {
        this.calendarDays = calendarDays;
    }

    @NonNull
    @Override
    public CalenderDayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_calendar_day_layout, parent, false);
        return new CalenderDayViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CalenderDayViewHolder holder, int position) {
        holder.onBind(calendarDays.get(position));
    }

    @Override
    public int getItemCount() {
        return calendarDays.size();
    }

    class CalenderDayViewHolder extends RecyclerView.ViewHolder {

        private CalendarDay calendarDay;

        private ImageView imgSelectedBg;
        private TextView txtDayValue;
        private ImageView imgContentIndicator;

        public CalenderDayViewHolder(@NonNull View itemView) {
            super(itemView);
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

            if(this.calendarDay.isCurrentMonthDay()){
                txtDayValue.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.black));
            }else{
                txtDayValue.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.greyText));
            }

            txtDayValue.setText(this.calendarDay.getDate() + "");

        }
    }
}
