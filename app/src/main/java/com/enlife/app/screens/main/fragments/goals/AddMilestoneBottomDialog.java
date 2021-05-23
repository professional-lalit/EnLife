package com.enlife.app.screens.main.fragments.goals;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.enlife.app.R;
import com.enlife.app.screens.widgets.CustomAppBar;
import com.enlife.app.screens.widgets.CustomToolbar;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class AddMilestoneBottomDialog extends BottomSheetDialogFragment implements CustomAppBar.CustomActionBarCallback, View.OnClickListener {

    public static final String TAG = AddMilestoneBottomDialog.class.getSimpleName();

    public static AddMilestoneBottomDialog createDialog(@Nullable Bundle bundle) {
        AddMilestoneBottomDialog dialog = new AddMilestoneBottomDialog();
        dialog.setArguments(bundle);
        dialog.setCancelable(true);
        return dialog;
    }

    private CustomToolbar customToolbar;
    private LinearLayout linAddEvent;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_add_milestone, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        customToolbar = view.findViewById(R.id.action_bar);
        linAddEvent = view.findViewById(R.id.lin_add_event);

        customToolbar.title(getString(R.string.add_milestone))
                .backGroundColor(R.color.transparent)
                .callback(this)
                .titleColor(R.color.black)
                .optionIcon(R.drawable.ic_close);

        linAddEvent.setOnClickListener(this);
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
                AddEventBottomDialog.createDialog(null).show(getChildFragmentManager(), AddEventBottomDialog.TAG);
                break;
        }
    }
}
