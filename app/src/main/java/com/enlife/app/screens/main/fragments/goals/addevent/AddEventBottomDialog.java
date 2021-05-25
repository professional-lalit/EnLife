package com.enlife.app.screens.main.fragments.goals.addevent;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.enlife.app.R;
import com.enlife.app.common.CustomApplication;
import com.enlife.app.database.models.GoalEvent;
import com.enlife.app.database.operators.GoalEventDataOperator;
import com.enlife.app.screens.widgets.CustomAppBar;
import com.enlife.app.screens.widgets.CustomToolbar;
import com.enlife.app.utils.DateFormatter;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import javax.inject.Inject;

public class AddEventBottomDialog extends BottomSheetDialogFragment
        implements AddEventContract.ViewContract,
        CustomAppBar.CustomActionBarCallback {

    public static final String TAG = AddEventBottomDialog.class.getSimpleName();
    private AddEventDialogPresenter presenter;

    public static AddEventBottomDialog createDialog(@Nullable Bundle bundle) {
        AddEventBottomDialog dialog = new AddEventBottomDialog();
        dialog.setArguments(bundle);
        return dialog;
    }

    private CustomToolbar customToolbar;

    @Inject
    GoalEventDataOperator databaseOperator;

    @Inject
    DateFormatter dateFormatter;

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
    }

    private void initViews() {
        customToolbar = requireView().findViewById(R.id.action_bar);
    }


    private void initToolbar() {
        customToolbar.backGroundColor(R.color.transparent)
                .titleColor(R.color.black)
                .title(getString(R.string.add_event))
                .callback(this)
                .optionIcon(R.drawable.ic_close);
    }

    @Override
    public void onHomeButtonClicked() {

    }

    @Override
    public void onOptionButtonClicked() {
        dismiss();
    }

    @Override
    public void onEventAdded(GoalEvent event) {

    }
}
