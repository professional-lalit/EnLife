package com.enlife.app.screens.home.fragments.goals;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.enlife.app.R;
import com.enlife.app.screens.widgets.CustomActionBar;


public class GoalManagementFragment extends Fragment implements CustomActionBar.CustomActionBarCallback {

    private CustomActionBar customActionBar;

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
    }

    private void initViews() {
        customActionBar = requireView().findViewById(R.id.action_bar);
    }

    private void initToolbar() {
        customActionBar.title("Add Goal")
                .titleColor(R.color.white)
                .callback(this)
                .backGroundColor(R.color.transparent)
                .homeIcon(R.drawable.ic_back)
                .optionIcon(R.drawable.ic_add_photo);
    }

    @Override
    public void onHomeButtonClicked() {
        requireActivity().onBackPressed();
    }

    @Override
    public void onOptionButtonClicked() {

    }
}