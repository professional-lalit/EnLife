package com.enlife.app.screens.main.fragments.goals.addevent;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.enlife.app.R;
import com.enlife.app.common.CustomApplication;
import com.enlife.app.database.models.Event;
import com.enlife.app.database.operators.EventDataOperator;
import com.enlife.app.screens.widgets.CustomAppBar;
import com.enlife.app.screens.widgets.CustomToolbar;
import com.enlife.app.screens.widgets.TimeDurationChooserView;
import com.enlife.app.utils.DateFormatter;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.Date;

import javax.inject.Inject;

public class AddEventBottomDialog extends BottomSheetDialogFragment
        implements AddEventContract.ViewContract,
        CustomAppBar.CustomActionBarCallback, TimeDurationChooserView.TimeSelectionListener, View.OnClickListener {

    public static final String TAG = AddEventBottomDialog.class.getSimpleName();
    private AddEventDialogPresenter presenter;
    private static final String EVENT_DATE = "event-date";

    public static AddEventBottomDialog createDialog(Date eventDate) {
        AddEventBottomDialog dialog = new AddEventBottomDialog();
        Bundle bundle = new Bundle();
        bundle.putSerializable(EVENT_DATE, eventDate);
        dialog.setArguments(bundle);
        return dialog;
    }

    private CustomToolbar customToolbar;
    private Button btnAddEvent;
    private TimeDurationChooserView timeDurationChooserView;
    private EditText edtEventTitle;
    private EditText edtEventDescription;

    private Date eventDate;
    private Date eventUpperBoundDateTime;
    private Date eventLowerBoundDateTime;
    private EventAddedCallback eventAddedCallback;

    @Inject
    EventDataOperator databaseOperator;

    @Inject
    DateFormatter dateFormatter;

    @Override
    public void setArguments(@Nullable Bundle args) {
        super.setArguments(args);
        this.eventDate = (Date) args.getSerializable(EVENT_DATE);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CustomApplication.getInstance()
                .applicationComponent
                .inject(this);

        presenter = new AddEventDialogPresenter(dateFormatter, databaseOperator);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_add_event, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
        initToolbar();
        setViews();
    }

    private void initViews() {
        customToolbar = requireView().findViewById(R.id.action_bar);
        timeDurationChooserView = requireView().findViewById(R.id.time_duration_chooser);
        btnAddEvent = requireView().findViewById(R.id.btn_add_event);
        edtEventTitle = requireView().findViewById(R.id.edt_event_title);
        edtEventDescription = requireView().findViewById(R.id.edt_event_description);
    }

    private void initToolbar() {
        customToolbar.backGroundColor(R.color.transparent)
                .titleColor(R.color.black)
                .title(getString(R.string.add_event))
                .callback(this)
                .optionIcon(R.drawable.ic_close);
    }

    private void setViews() {
        btnAddEvent.setOnClickListener(this);
        timeDurationChooserView.setTimeSelectionListener(this);
    }

    @Override
    public void onHomeButtonClicked() {

    }

    @Override
    public void onOptionButtonClicked() {
        dismiss();
    }

    @Override
    public void onEventAdded(Event event) {

    }

    @Override
    public void onFromTimeSet(Date date) {
        eventLowerBoundDateTime = date;
    }

    @Override
    public void onToTimeSet(Date date) {
        eventUpperBoundDateTime = date;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add_event:
                Event event = new Event(0L,
                        edtEventTitle.getText().toString(),
                        edtEventDescription.getText().toString(),
                        dateFormatter.getFormattedDate(DateFormatter.DateFormat.INDIAN_DATE_FORMAT, eventDate),
                        false,
                        "",
                        Event.RepeatMode.NONE,
                        dateFormatter.getFormattedDate(DateFormatter.DateFormat.HH_mm_a, eventLowerBoundDateTime),
                        dateFormatter.getFormattedDate(DateFormatter.DateFormat.HH_mm_a, eventUpperBoundDateTime),
                        "",
                        0L,
                        0L
                );
                if (eventAddedCallback != null) {
                    eventAddedCallback.onEventAdded(event);
                }
                dismiss();
                break;
        }
    }

    public void setEventAddedCallback(EventAddedCallback eventAddedCallback) {
        this.eventAddedCallback = eventAddedCallback;
    }

    public interface EventAddedCallback {
        void onEventAdded(Event event);
    }
}
