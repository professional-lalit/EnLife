package com.enlife.app.screens.main.fragments.goals.addgoal;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.enlife.app.R;
import com.enlife.app.common.CustomApplication;
import com.enlife.app.database.operators.EventDataOperator;
import com.enlife.app.database.operators.GoalDataOperator;
import com.enlife.app.database.operators.MilestoneDataOperator;
import com.enlife.app.screens.main.fragments.goals.addmilestone.AddMilestoneBottomDialog;
import com.enlife.app.screens.widgets.CustomAppBar;
import com.enlife.app.screens.widgets.DateDurationChooserView;
import com.enlife.app.utils.DateFormatter;
import com.enlife.app.utils.Utils;

import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;


public class AddGoalFragment extends Fragment implements
        CustomAppBar.CustomActionBarCallback,
        View.OnClickListener,
        GoalManagementContract.ViewContract,
        DateDurationChooserView.DateSelectionListener {

    private CustomAppBar customAppBar;
    private ImageView imgAddMilestone;
    private DateDurationChooserView dateDurationChooserView;

    private GoalManagementPresenter presenter;

    private Date fromDate;
    private Date toDate;

    @Inject
    GoalDataOperator goalDatabaseOperator;

    @Inject
    MilestoneDataOperator milestoneDatabaseOperator;

    @Inject
    EventDataOperator eventDatabaseOperator;

    @Inject
    DateFormatter dateFormatter;

    @Inject
    Utils utils;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CustomApplication.getInstance()
                .applicationComponent
                .inject(this);
        presenter = new GoalManagementPresenter(
                dateFormatter, goalDatabaseOperator,
                milestoneDatabaseOperator, eventDatabaseOperator
        );
    }

    public static AddGoalFragment newInstance() {
        return new AddGoalFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_goal, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
        initToolbar();
        setViews();
        setGoalDurationBounds();
    }

    private void initViews() {
        customAppBar = requireView().findViewById(R.id.action_bar);
        imgAddMilestone = requireView().findViewById(R.id.img_add_milestone);
        dateDurationChooserView = requireView().findViewById(R.id.time_duration_chooser);
    }

    private void initToolbar() {
        customAppBar.title("Add Goal")
                .titleColor(R.color.white)
                .callback(this)
                .backGroundColor(R.color.transparent)
                .homeIcon(R.drawable.ic_back)
                .optionIcon(R.drawable.ic_add_photo);
    }

    private void setViews() {
        imgAddMilestone.setOnClickListener(this);
        dateDurationChooserView.setSelectionListener(this);
    }

    private void setGoalDurationBounds() {
        Calendar calendar = Calendar.getInstance();
        dateDurationChooserView.setLowerBoundDate(calendar.getTime());
        calendar.add(Calendar.YEAR, 1);
        dateDurationChooserView.setUpperBoundDate(calendar.getTime());
    }

    @Override
    public void onHomeButtonClicked() {
        requireActivity().onBackPressed();
    }

    @Override
    public void onOptionButtonClicked() {

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_add_milestone:
                if (fromDate != null && toDate != null) {
                    AddMilestoneBottomDialog.createDialog(fromDate, toDate)
                            .show(getChildFragmentManager(), AddMilestoneBottomDialog.TAG);
                } else {
                    utils.showToast(getString(R.string.plz_goal_duration));
                }
                break;
        }
    }

    @Override
    public void onFromDateSet(Date date) {
        fromDate = date;
    }

    @Override
    public void onToDateSet(Date date) {
        toDate = date;
    }

    @Override
    public void onDataSaved() {
        utils.showToast(getString(R.string.goal_is_saved));
        getActivity().onBackPressed();
    }
}