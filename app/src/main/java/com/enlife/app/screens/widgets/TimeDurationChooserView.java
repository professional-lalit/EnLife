package com.enlife.app.screens.widgets;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.enlife.app.R;
import com.enlife.app.utils.DateFormatter;

import java.util.Calendar;
import java.util.Date;

public class TimeDurationChooserView extends ConstraintLayout {

    private TextView txtFromDate;
    private TextView txtToDate;

    private DateFormatter dateFormatter = new DateFormatter();

    private Date upperBoundDate;
    private Date lowerBoundDate;

    private TimeSelectionListener timeSelectionListener;


    public TimeDurationChooserView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public TimeDurationChooserView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        inflate(context, R.layout.layout_time_duration_view, this);
        txtFromDate = findViewById(R.id.txt_from_date);
        txtToDate = findViewById(R.id.txt_to_date);

        txtFromDate.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            new TimePickerDialog(getContext(), fromTimeSetListener,
                    calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE),
                    false).show();
        });

        txtToDate.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            new TimePickerDialog(getContext(), toTimeSetListener,
                    calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE),
                    false).show();
        });
    }

    private final TimePickerDialog.OnTimeSetListener fromTimeSetListener = new TimePickerDialog.OnTimeSetListener() {

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
            calendar.set(Calendar.MINUTE, minute);
            String time = dateFormatter.getFormattedDate(DateFormatter.DateFormat.HH_mm_a, calendar.getTime());
            txtFromDate.setText(time);
            timeSelectionListener.onFromTimeSet(calendar.getTime());
        }
    };

    private final TimePickerDialog.OnTimeSetListener toTimeSetListener = new TimePickerDialog.OnTimeSetListener() {

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
            calendar.set(Calendar.MINUTE, minute);
            String time = dateFormatter.getFormattedDate(DateFormatter.DateFormat.HH_mm_a, calendar.getTime());
            txtToDate.setText(time);
            timeSelectionListener.onToTimeSet(calendar.getTime());
        }
    };

    public void setTimeSelectionListener(TimeSelectionListener timeSelectionListener) {
        this.timeSelectionListener = timeSelectionListener;
    }

    public void setUpperBoundDate(Date upperBoundDate) {
        this.upperBoundDate = upperBoundDate;
    }

    public void setLowerBoundDate(Date lowerBoundDate) {
        this.lowerBoundDate = lowerBoundDate;
    }

    public interface TimeSelectionListener {

        void onFromTimeSet(Date date);

        void onToTimeSet(Date date);
    }
}
