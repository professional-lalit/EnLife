package com.enlife.app.screens.main.dialog;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.enlife.app.R;
import com.enlife.app.common.CustomApplication;
import com.enlife.app.database.models.Event;
import com.enlife.app.database.operators.EventDataOperator;
import com.enlife.app.screens.main.fragments.goals.addevent.AddEventBottomDialog;
import com.enlife.app.screens.main.fragments.home.events.EventAdapter;
import com.enlife.app.utils.DateFormatter;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

public class EventsBottomDialog extends BottomSheetDialogFragment implements EventsBottomContract.ViewContract, View.OnClickListener {

    private EventsBottomContract.PresenterContract presenter;

    private RecyclerView recyclerEvents;
    private TextView txtDialogTitle;
    private TextView txtNoData;
    private ImageView imgAddEvent;

    private Date date;

    @Inject
    DateFormatter dateFormatter;

    @Inject
    EventDataOperator databaseOperator;

    private static final String ARG_EVENTS_DATE = "arg_events_date";

    public static EventsBottomDialog createDialog(Date date) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(ARG_EVENTS_DATE, date);
        EventsBottomDialog dialog = new EventsBottomDialog();
        dialog.setArguments(bundle);
        dialog.setCancelable(true);
        return dialog;
    }

    @Override
    public void setArguments(@Nullable Bundle args) {
        super.setArguments(args);
        if (args != null) {
            date = (Date) args.getSerializable(ARG_EVENTS_DATE);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CustomApplication.getInstance()
                .applicationComponent
                .inject(this);
        presenter = new EventsBottomDialogPresenter(this, dateFormatter, databaseOperator);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return View.inflate(requireContext(), R.layout.dialog_bottom_events, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
        setViews();
        presenter.loadEvents(date);
    }

    private void setViews() {
        // Set dialog title
        String formattedDate = dateFormatter.getFormattedDate(DateFormatter.DateFormat.MMM_dd_yyyy, date);
        txtDialogTitle.setText(String.format(getString(R.string.schedule_for), formattedDate));
        imgAddEvent.setOnClickListener(this);
    }

    private void initViews() {
        assert getView() != null;
        recyclerEvents = getView().findViewById(R.id.recycler_events);
        txtDialogTitle = getView().findViewById(R.id.txt_dialog_title);
        txtNoData = getView().findViewById(R.id.txt_no_data);
        imgAddEvent = getView().findViewById(R.id.img_add_event);
    }

    @Override
    public void onEventsLoaded(List<Event> events) {
        recyclerEvents.setAdapter(new EventAdapter(events));
        recyclerEvents.setLayoutManager(new LinearLayoutManager(requireContext()));
        if (events.isEmpty()) {
            recyclerEvents.setVisibility(View.GONE);
            txtNoData.setVisibility(View.VISIBLE);
        } else {
            recyclerEvents.setVisibility(View.VISIBLE);
            txtNoData.setVisibility(View.GONE);
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_add_event:
                AddEventBottomDialog.createDialog(date).show(getChildFragmentManager(), AddEventBottomDialog.TAG);
                break;
        }
    }
}
