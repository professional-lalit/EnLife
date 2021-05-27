package com.enlife.app.screens.main.fragments.goals.addevent;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
        CustomAppBar.CustomActionBarCallback, TimeDurationChooserView.TimeSelectionListener {

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
    private TimeDurationChooserView timeDurationChooserView;

    private Date eventDate;

    private Date eventUpperBoundDateTime;
    private Date eventLowerBoundDateTime;

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
    }

    private void initToolbar() {
        customToolbar.backGroundColor(R.color.transparent)
                .titleColor(R.color.black)
                .title(getString(R.string.add_event))
                .callback(this)
                .optionIcon(R.drawable.ic_close);
    }

    private void setViews() {
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
}
