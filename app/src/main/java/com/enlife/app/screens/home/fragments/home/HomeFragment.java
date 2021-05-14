package com.enlife.app.screens.home.fragments.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.Pair;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.enlife.app.R;
import com.enlife.app.models.CalendarDay;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class HomeFragment extends Fragment {

    private RecyclerView recyclerCalendar;
    private ImageView imgPreviousWeek;
    private ImageView imgNextWeek;
    private String monthSelected = "";
    private Date pivotDay;

    public static HomeFragment newInstance(@Nullable Bundle bundle) {
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        pivotDay = new Date();
        initViews();
        initCalendarView(new Date());
        setViews();
    }

    private void initViews() {
        recyclerCalendar = requireView().findViewById(R.id.recycler_calendar);
        imgNextWeek = requireView().findViewById(R.id.img_next_week);
        imgPreviousWeek = requireView().findViewById(R.id.img_previous_week);
    }

    private void initCalendarView(Date date) {

        SimpleDateFormat dayFormat = new SimpleDateFormat("d");
        SimpleDateFormat monthFormat = new SimpleDateFormat("M");

        Calendar calendar = getCalendar(monthFormat, date);
        List<Pair<String, String>> days = getDaysDataSet(dayFormat, monthFormat, calendar);
        List<CalendarDay> calendarDays = getCalendarDays(days);

        CalendarDaysAdapter calendarDaysAdapter = new CalendarDaysAdapter(calendarDays);
        recyclerCalendar.setAdapter(calendarDaysAdapter);
        recyclerCalendar.setLayoutManager(new GridLayoutManager(requireContext(), 7));
    }

    private List<CalendarDay> getCalendarDays(List<Pair<String, String>> days) {
        List<CalendarDay> calendarDays = new ArrayList<>();
        for (Pair<String, String> day : days) {
            if (day != null) {
                assert day.first != null;
                calendarDays.add(
                        new CalendarDay(
                                Integer.parseInt(day.first),
                                false,
                                false,
                                monthSelected.equals(day.second))
                );
            }
        }
        return calendarDays;
    }

    private List<Pair<String, String>> getDaysDataSet(SimpleDateFormat dayFormat, SimpleDateFormat monthFormat, Calendar calendar) {
        List<Pair<String, String>> days = new ArrayList<>();
        for (int i = 0; i < 35; i++) {
            Date date = new Date(calendar.getTimeInMillis());
            String day = (String) dayFormat.format(date);
            String month = monthFormat.format(date);
            days.add(new Pair<>(day, month));
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        return days;
    }

    private Calendar getCalendar(SimpleDateFormat monthFormat, Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(pivotDay);
        calendar.setFirstDayOfWeek(Calendar.SUNDAY);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        monthSelected = monthFormat.format(date);
        return calendar;
    }

    private void setViews() {
        imgNextWeek.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(pivotDay);
            calendar.add(Calendar.MONTH, 1);
            pivotDay = calendar.getTime();
            initCalendarView(pivotDay);
        });
        imgPreviousWeek.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(pivotDay);
            calendar.add(Calendar.MONTH, -1);
            pivotDay = calendar.getTime();
            initCalendarView(pivotDay);
        });
    }
}