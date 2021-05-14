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
import android.widget.TextView;

import com.enlife.app.R;
import com.enlife.app.models.CalendarDay;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class HomeFragment extends Fragment implements HomeFragmentPresenter.HomeFragmentContract {

    private RecyclerView recyclerCalendar;
    private ImageView imgPreviousWeek;
    private ImageView imgNextWeek;
    private TextView txtMonthName;

    private final HomeFragmentPresenter presenter = new HomeFragmentPresenter(this);

    public static HomeFragment newInstance(@Nullable Bundle bundle) {
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
        setViews();
        presenter.initializeCalendar();
    }

    private void initViews() {
        recyclerCalendar = requireView().findViewById(R.id.recycler_calendar);
        imgNextWeek = requireView().findViewById(R.id.img_next_week);
        imgPreviousWeek = requireView().findViewById(R.id.img_previous_week);
        txtMonthName = requireView().findViewById(R.id.txt_month_name);
    }

    private void setViews() {
        imgNextWeek.setOnClickListener(v -> presenter.onNext());
        imgPreviousWeek.setOnClickListener(v -> presenter.onPrevious());
    }

    @Override
    public void setCalenderView(List<CalendarDay> calendarDays) {
        CalendarDaysAdapter calendarDaysAdapter = new CalendarDaysAdapter(calendarDays);
        recyclerCalendar.setAdapter(calendarDaysAdapter);
        recyclerCalendar.setLayoutManager(new GridLayoutManager(requireContext(), 7));
    }

    @Override
    public void setMonthTitle(Calendar calendar) {
        SimpleDateFormat monthFormat = new SimpleDateFormat("MMMM");
        txtMonthName.setText(monthFormat.format(calendar.getTime()));
    }
}