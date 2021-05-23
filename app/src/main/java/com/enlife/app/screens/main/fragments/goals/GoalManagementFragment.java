package com.enlife.app.screens.main.fragments.goals;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.enlife.app.R;
import com.enlife.app.screens.widgets.CustomAppBar;


public class GoalManagementFragment extends Fragment implements CustomAppBar.CustomActionBarCallback, View.OnClickListener {

    private CustomAppBar customAppBar;
    private ImageView imgAddMilestone;

    public static GoalManagementFragment newInstance() {
        return new GoalManagementFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_goal_management, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
        initToolbar();
        setViews();
    }

    private void initViews() {
        customAppBar = requireView().findViewById(R.id.action_bar);
        imgAddMilestone = requireView().findViewById(R.id.img_add_milestone);
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
                AddMilestoneBottomDialog.createDialog(null).show(getChildFragmentManager(), AddMilestoneBottomDialog.TAG);
                break;
        }
    }
}