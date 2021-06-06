package com.enlife.app.screens.main.fragments.schedule;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.enlife.app.R;
import com.enlife.app.common.CustomApplication;
import com.enlife.app.database.models.GoalEvent;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;


public class DailyScheduleFragment extends Fragment implements DailyScheduleContract.ViewContract {

    public static final String TAG = DailyScheduleFragment.class.getSimpleName();

    public static DailyScheduleFragment openScreen(Date date) {
        DailyScheduleFragment dailyScheduleFragment = new DailyScheduleFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("date", date);
        dailyScheduleFragment.setArguments(bundle);
        return dailyScheduleFragment;
    }

    @Override
    public void setArguments(@Nullable Bundle args) {
        super.setArguments(args);
        date = (Date) Objects.requireNonNull(args).getSerializable("date");
    }

    private Date date;

    @Inject
    DailySchedulePresenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CustomApplication.getInstance()
                .applicationComponent
                .dailyScheduleBuilder()
                .build()
                .inject(this);
        presenter.setViewContract(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_daily_schedule, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.loadEvents(date);
    }

    @Override
    public void onEventsLoaded(List<GoalEvent> goalEvents) {
        Log.d(this.getClass().getSimpleName(), String.valueOf(goalEvents.size()));
    }
}