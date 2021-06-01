package com.enlife.app.screens.main.fragments.goals.addmilestone;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.enlife.app.R;
import com.enlife.app.common.CustomApplication;
import com.enlife.app.database.models.Event;
import com.enlife.app.database.models.Milestone;
import com.enlife.app.database.operators.MilestoneDataOperator;
import com.enlife.app.screens.main.fragments.goals.addevent.AddEventBottomDialog;
import com.enlife.app.screens.main.fragments.home.events.EventAdapter;
import com.enlife.app.screens.widgets.CustomAppBar;
import com.enlife.app.screens.widgets.CustomToolbar;
import com.enlife.app.screens.widgets.DateDurationChooserView;
import com.enlife.app.utils.DateFormatter;
import com.enlife.app.utils.Utils;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

public class AddMilestoneBottomDialog extends BottomSheetDialogFragment
        implements CustomAppBar.CustomActionBarCallback,
        AddMilestoneContract.ViewContract,
        View.OnClickListener,
        DateDurationChooserView.DateSelectionListener,
        DatePickerDialog.OnDateSetListener,
        AddEventBottomDialog.EventAddedCallback {

    public static final String TAG = AddMilestoneBottomDialog.class.getSimpleName();

    private static final String UPPER_BOUND_DATE = "upper-bound-date";
    private static final String LOWER_BOUND_DATE = "lower-bound-date";

    public static AddMilestoneBottomDialog createDialog(Date lowerBoundDate, Date upperBoundDate) {
        AddMilestoneBottomDialog dialog = new AddMilestoneBottomDialog();
        Bundle bundle = new Bundle();
        bundle.putSerializable(LOWER_BOUND_DATE, lowerBoundDate);
        bundle.putSerializable(UPPER_BOUND_DATE, upperBoundDate);
        dialog.setArguments(bundle);
        dialog.setCancelable(true);
        return dialog;
    }

    private CustomToolbar customToolbar;
    private LinearLayout linAddEvent;
    private DateDurationChooserView dateDurationChooserView;
    private EditText edtMilestoneTitle;
    private EditText edtMilestoneDescription;
    private Button btnAddMilestone;
    private RecyclerView recyclerEvents;
    private EventAdapter eventAdapter;

    private Date goalUpperBoundDate;
    private Date goalLowerBoundDate;

    private Date milestoneLowerBoundDate;
    private Date milestoneUpperBoundDate;

    private MilestoneAddedCallback milestoneAddedCallback;

    private List<Event> eventsAdded = new ArrayList<>();

    @Inject
    MilestoneDataOperator databaseOperator;
    
    @Inject
    Utils utils;

    @Inject
    AddMilestoneDialogPresenter presenter;

    @Override
    public void setArguments(@Nullable Bundle args) {
        super.setArguments(args);
        this.goalLowerBoundDate = (Date) args.getSerializable(LOWER_BOUND_DATE);
        this.goalUpperBoundDate = (Date) args.getSerializable(UPPER_BOUND_DATE);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CustomApplication.getInstance()
                .applicationComponent
                .goalsComponentBuilder()
                .build()
                .inject(this);
        presenter.setViewContract(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_add_milestone, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        setToolbar();
        setViews();
        setEventsAdapter();
    }

    private void initViews(@NonNull View view) {
        customToolbar = view.findViewById(R.id.action_bar);
        linAddEvent = view.findViewById(R.id.lin_add_event);
        dateDurationChooserView = view.findViewById(R.id.time_duration_chooser);
        btnAddMilestone = view.findViewById(R.id.btn_add_milestone);
        edtMilestoneTitle = view.findViewById(R.id.edt_milestone_title);
        edtMilestoneDescription = view.findViewById(R.id.edt_milestone_description);
        recyclerEvents = view.findViewById(R.id.recycler_events);
    }

    private void setToolbar() {
        customToolbar.title(getString(R.string.add_milestone))
                .backGroundColor(R.color.transparent)
                .callback(this)
                .titleColor(R.color.black)
                .optionIcon(R.drawable.ic_close);
    }

    private void setViews() {
        linAddEvent.setOnClickListener(this);
        btnAddMilestone.setOnClickListener(this);
        dateDurationChooserView.setSelectionListener(this);
        dateDurationChooserView.setUpperBoundDate(goalUpperBoundDate);
        dateDurationChooserView.setLowerBoundDate(goalLowerBoundDate);
    }

    private void setEventsAdapter() {
        eventAdapter = new EventAdapter(eventsAdded);
        recyclerEvents.setAdapter(eventAdapter);
        recyclerEvents.setLayoutManager(new LinearLayoutManager(requireContext()));
    }

    @Override
    public void onHomeButtonClicked() {

    }

    @Override
    public void onOptionButtonClicked() {
        dismiss();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.lin_add_event:
                if (milestoneUpperBoundDate != null && milestoneLowerBoundDate != null) {
                    showDatePicker();
                } else {
                    utils.showToast(getString(R.string.plz_milestone_duration));
                }
                break;

            case R.id.btn_add_milestone:
                presenter.addMilestone(
                        edtMilestoneTitle.getText().toString(),
                        edtMilestoneDescription.getText().toString(),
                        milestoneLowerBoundDate,
                        milestoneUpperBoundDate,
                        eventsAdded
                );
                break;
        }
    }

    private void showDatePicker() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(milestoneLowerBoundDate);
        DatePickerDialog dialog = new DatePickerDialog(getContext(), this,
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        dialog.getDatePicker().setMaxDate(milestoneUpperBoundDate.getTime());
        dialog.getDatePicker().setMinDate(milestoneLowerBoundDate.getTime());
        dialog.show();
    }

    @Override
    public void onFromDateSet(Date date) {
        milestoneLowerBoundDate = date;
    }

    @Override
    public void onToDateSet(Date date) {
        milestoneUpperBoundDate = date;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.YEAR, year);

        AddEventBottomDialog dialog = AddEventBottomDialog.createDialog(calendar.getTime());
        dialog.setEventAddedCallback(this);
        new Handler().postDelayed(() ->
                dialog.show(getChildFragmentManager(), AddEventBottomDialog.TAG), 500L
        );
    }

    @Override
    public void onEventAdded(Event event) {
        eventsAdded.add(event);
        eventAdapter.notifyDataSetChanged();
    }

    @Override
    public void onMilestoneAdded(Milestone milestone) {
        milestoneAddedCallback.onMilestoneAdded(milestone);
        dismiss();
    }

    public void setMilestoneAddedCallback(MilestoneAddedCallback milestoneAddedCallback) {
        this.milestoneAddedCallback = milestoneAddedCallback;
    }

    public interface MilestoneAddedCallback {
        void onMilestoneAdded(Milestone milestone);
    }
}
