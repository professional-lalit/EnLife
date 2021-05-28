package com.enlife.app.screens.main.fragments.goals.addgoal;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.enlife.app.R;
import com.enlife.app.common.CustomApplication;
import com.enlife.app.database.models.Goal;
import com.enlife.app.database.models.Milestone;
import com.enlife.app.database.operators.EventDataOperator;
import com.enlife.app.database.operators.GoalDataOperator;
import com.enlife.app.database.operators.MilestoneDataOperator;
import com.enlife.app.screens.main.fragments.goals.addmilestone.AddMilestoneBottomDialog;
import com.enlife.app.screens.widgets.CustomAppBar;
import com.enlife.app.screens.widgets.DateDurationChooserView;
import com.enlife.app.utils.DateFormatter;
import com.enlife.app.utils.Utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;


public class AddGoalFragment extends Fragment implements
        CustomAppBar.CustomActionBarCallback,
        View.OnClickListener,
        GoalManagementContract.ViewContract,
        DateDurationChooserView.DateSelectionListener, AddMilestoneBottomDialog.MilestoneAddedCallback {

    private CustomAppBar customAppBar;
    private ImageView imgAddMilestone;
    private Button btnSaveGoal;
    private DateDurationChooserView dateDurationChooserView;
    private EditText edtGoalTitle;
    private EditText edtGoalDescriptionText;
    private RadioGroup rgGoalType;
    private RecyclerView recyclerMilestones;
    private MilestoneAdapter milestoneAdapter;

    private GoalManagementPresenter presenter;

    private Date fromDate;
    private Date toDate;

    private List<Milestone> milestonesAdded = new ArrayList<>();

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
        presenter = new GoalManagementPresenter(this,
                dateFormatter,
                goalDatabaseOperator,
                milestoneDatabaseOperator,
                eventDatabaseOperator
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
        setMilesToneAdapter();
    }

    private void initViews() {
        edtGoalTitle = requireView().findViewById(R.id.edt_goal_title);
        edtGoalDescriptionText = requireView().findViewById(R.id.edt_goal_description);
        rgGoalType = requireView().findViewById(R.id.rg_goal_type);
        customAppBar = requireView().findViewById(R.id.action_bar);
        imgAddMilestone = requireView().findViewById(R.id.img_add_milestone);
        dateDurationChooserView = requireView().findViewById(R.id.time_duration_chooser);
        btnSaveGoal = requireView().findViewById(R.id.btn_save_goal);
        recyclerMilestones = requireView().findViewById(R.id.recycler_milestones);
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
        btnSaveGoal.setOnClickListener(this);
    }

    private void setGoalDurationBounds() {
        Calendar calendar = Calendar.getInstance();
        dateDurationChooserView.setLowerBoundDate(calendar.getTime());
        calendar.add(Calendar.YEAR, 1);
        dateDurationChooserView.setUpperBoundDate(calendar.getTime());
    }

    private void setMilesToneAdapter() {
        milestoneAdapter = new MilestoneAdapter(milestonesAdded);
        recyclerMilestones.setAdapter(milestoneAdapter);
        recyclerMilestones.setLayoutManager(new LinearLayoutManager(requireContext()));
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
                AddMilestoneBottomDialog dialog = AddMilestoneBottomDialog.createDialog(fromDate, toDate);
                dialog.setMilestoneAddedCallback(this);
                if (fromDate != null && toDate != null) {
                    dialog.show(getChildFragmentManager(), AddMilestoneBottomDialog.TAG);
                } else {
                    utils.showToast(getString(R.string.plz_goal_duration));
                }
                break;

            case R.id.btn_save_goal:
                Goal goal = new Goal(
                        0L,
                        edtGoalTitle.getText().toString(),
                        edtGoalDescriptionText.getText().toString(),
                        getGoalType(),
                        dateFormatter.getFormattedDate(DateFormatter.DateFormat.INDIAN_DATE_FORMAT, fromDate),
                        dateFormatter.getFormattedDate(DateFormatter.DateFormat.INDIAN_DATE_FORMAT, toDate),
                        milestonesAdded
                );
                presenter.saveGoal(goal);
                break;
        }
    }

    @SuppressLint("NonConstantResourceId")
    private Goal.GoalType getGoalType() {
        switch (rgGoalType.getCheckedRadioButtonId()) {
            case R.id.rb_weekly:
                return Goal.GoalType.WEEKLY;
            case R.id.rb_monthly:
                return Goal.GoalType.MONTHLY;
            case R.id.rb_annual:
                return Goal.GoalType.ANNUAL;
        }
        return Goal.GoalType.MONTHLY;
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

    @Override
    public void onMilestoneAdded(Milestone milestone) {
        milestonesAdded.add(milestone);
        milestoneAdapter.notifyDataSetChanged();
    }
}