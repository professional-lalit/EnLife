package com.enlife.app.screens.main.fragments.goals;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.enlife.app.R;
import com.enlife.app.screens.widgets.CustomAppBar;
import com.enlife.app.screens.widgets.CustomToolbar;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class AddEventBottomDialog extends BottomSheetDialogFragment implements CustomAppBar.CustomActionBarCallback {

    public static final String TAG = AddEventBottomDialog.class.getSimpleName();

    public static AddEventBottomDialog createDialog(@Nullable Bundle bundle) {
        AddEventBottomDialog dialog = new AddEventBottomDialog();
        dialog.setArguments(bundle);
        return dialog;
    }

    private CustomToolbar customToolbar;

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
}
