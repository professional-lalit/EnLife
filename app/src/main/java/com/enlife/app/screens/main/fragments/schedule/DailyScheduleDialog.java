package com.enlife.app.screens.main.fragments.schedule;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.enlife.app.R;
import com.enlife.app.common.CustomApplication;
import com.enlife.app.database.models.Event;
import com.enlife.app.database.models.GoalEvent;
import com.enlife.app.screens.main.fragments.goals.addevent.AddEventBottomDialog;
import com.enlife.app.screens.widgets.CustomAppBar;
import com.enlife.app.screens.widgets.CustomToolbar;
import com.enlife.app.utils.DateFormatter;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;


public class DailyScheduleDialog extends BottomSheetDialogFragment implements
        DailyScheduleContract.ViewContract,
        CustomAppBar.CustomActionBarCallback,
        AddEventBottomDialog.EventAddedCallback {

    public static final String TAG = DailyScheduleDialog.class.getSimpleName();

    public static DailyScheduleDialog createDialog(Date date) {
        DailyScheduleDialog dailyScheduleDialog = new DailyScheduleDialog();
        Bundle bundle = new Bundle();
        bundle.putSerializable("date", date);
        dailyScheduleDialog.setArguments(bundle);
        dailyScheduleDialog.setCancelable(true);
        return dailyScheduleDialog;
    }

    @Override
    public void setArguments(@Nullable Bundle args) {
        super.setArguments(args);
        date = (Date) Objects.requireNonNull(args).getSerializable("date");
    }

    private Date date;
    private AddEventBottomDialog.EventAddedCallback eventAddedCallback;

    @Inject
    DailySchedulePresenter presenter;

    @Inject
    DateFormatter dateFormatter;

    private CustomToolbar toolbar;
    private RecyclerView recyclerEvents;
    private final List<GoalEvent> goalEvents = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CustomApplication.getInstance()
                .applicationComponent
                .dailyScheduleBuilder()
                .create(this)
                .inject(this);
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
        initViews();
        initToolbar();
        setUpEventList();
        presenter.loadEvents(date);
    }

    private void setUpEventList() {
        recyclerEvents.setAdapter(new GoalEventAdapter(goalEvents));
        recyclerEvents.setLayoutManager(new LinearLayoutManager(requireContext()));
    }

    private void initToolbar() {
        toolbar.callback(this)
                .optionIcon(R.drawable.add)
                .titleColor(R.color.black)
                .title(getString(R.string.daily_schedule) + " - "
                        + dateFormatter.getFormattedDate(DateFormatter.DateFormat.MMM_dd_yyyy, date)
                );
    }

    private void initViews() {
        toolbar = requireView().findViewById(R.id.toolbar);
        recyclerEvents = requireView().findViewById(R.id.recycler_events);
    }

    @Override
    public void onEventsLoaded(List<GoalEvent> goalEvents) {
        Log.d(this.getClass().getSimpleName(), String.valueOf(goalEvents.size()));
        if (goalEvents != null) {
            this.goalEvents.addAll(goalEvents);
            recyclerEvents.getAdapter().notifyDataSetChanged();
        }
    }

    @Override
    public void onHomeButtonClicked() {
    }

    @Override
    public void onOptionButtonClicked() {
        AddEventBottomDialog dialog = AddEventBottomDialog.createDialog(date);
        dialog.setEventAddedCallback(this);
        dialog.show(getChildFragmentManager(), AddEventBottomDialog.TAG);
    }

    @Override
    public void onEventAdded(Event event) {
        presenter.saveEvent(event);
    }

    @Override
    public void onEventSaved(Event event) {
        goalEvents.clear();
        presenter.loadEvents(date);
    }
}