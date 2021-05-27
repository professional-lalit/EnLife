package com.enlife.app.screens.widgets;

import android.app.DatePickerDialog;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.enlife.app.R;
import com.enlife.app.utils.DateFormatter;

import java.util.Calendar;
import java.util.Date;

public class DateDurationChooserView extends ConstraintLayout {

    private TextView txtFromDate;
    private TextView txtToDate;

    private Date upperBoundDate;
    private Date lowerBoundDate;

    private DateSelectionListener selectionListener;

    public DateDurationChooserView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public DateDurationChooserView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        initViews(context);
        setViews();
    }

    private void initViews(Context context) {
        inflate(context, R.layout.layout_date_duration_view, this);
        txtFromDate = findViewById(R.id.txt_from_date);
        txtToDate = findViewById(R.id.txt_to_date);
    }

    private void setViews() {
        txtFromDate.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            if (lowerBoundDate != null) {
                calendar.setTime(lowerBoundDate);
            }
            DatePickerDialog dialog = new DatePickerDialog(getContext(), fromDateSetListener,
                    calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
            );
            if (upperBoundDate != null && lowerBoundDate != null) {
                dialog.getDatePicker().setMaxDate(upperBoundDate.getTime());
                dialog.getDatePicker().setMinDate(lowerBoundDate.getTime());
            }
            dialog.show();
        });

        txtToDate.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            if (lowerBoundDate != null) {
                calendar.setTime(lowerBoundDate);
                calendar.add(Calendar.DAY_OF_MONTH, 1);
            }
            DatePickerDialog dialog = new DatePickerDialog(
                    getContext(),
                    toDateSetListener,
                    calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
            );
            if (upperBoundDate != null && lowerBoundDate != null) {
                dialog.getDatePicker().setMaxDate(upperBoundDate.getTime());
                dialog.getDatePicker().setMinDate(lowerBoundDate.getTime());
            }
            dialog.show();
        });
    }

    private final DatePickerDialog.OnDateSetListener fromDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.YEAR, year);
            lowerBoundDate = calendar.getTime();
            txtFromDate.setText(new DateFormatter().getFormattedDate(DateFormatter.DateFormat.INDIAN_DATE_FORMAT, calendar.getTime()));
            if (selectionListener != null) {
                selectionListener.onFromDateSet(calendar.getTime());
            }
        }
    };

    private final DatePickerDialog.OnDateSetListener toDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.YEAR, year);
            txtToDate.setText(new DateFormatter().getFormattedDate(DateFormatter.DateFormat.INDIAN_DATE_FORMAT, calendar.getTime()));
            if (selectionListener != null) {
                selectionListener.onToDateSet(calendar.getTime());
            }
        }
    };

    public void setSelectionListener(DateSelectionListener selectionListener) {
        this.selectionListener = selectionListener;
    }

    public interface DateSelectionListener {
        void onFromDateSet(Date date);

        void onToDateSet(Date date);
    }

    public void setUpperBoundDate(Date upperBoundDate) {
        this.upperBoundDate = upperBoundDate;
    }

    public void setLowerBoundDate(Date lowerBoundDate) {
        this.lowerBoundDate = lowerBoundDate;
    }
}
