package com.enlife.app.screens.widgets;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.core.content.ContextCompat;

import com.enlife.app.R;
import com.google.android.material.appbar.AppBarLayout;

public class CustomActionBar extends AppBarLayout {


    public CustomActionBar(Context context) {
        super(context);
        init(context);
    }

    public CustomActionBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private ImageView imgHome;
    private ImageView imgOption;
    private TextView txtToolbarTitle;
    private CustomActionBarCallback customActionBarCallback;

    private void init(Context context) {
        inflate(context, R.layout.toolbar_common, this);
        txtToolbarTitle = findViewById(R.id.txt_toolbar_title);
        imgHome = findViewById(R.id.img_home);
        imgOption = findViewById(R.id.img_option);
    }

    public CustomActionBar callback(CustomActionBarCallback customActionBarCallback) {
        this.customActionBarCallback = customActionBarCallback;
        return this;
    }

    public CustomActionBar title(String title) {
        txtToolbarTitle.setText(title);
        return this;
    }

    public CustomActionBar homeIcon(@DrawableRes int homeIcon) {
        imgHome.setVisibility(VISIBLE);
        imgHome.setImageResource(homeIcon);
        imgHome.setOnClickListener(v -> customActionBarCallback.onHomeButtonClicked());
        return this;
    }

    public CustomActionBar optionIcon(@DrawableRes int optionIcon) {
        imgOption.setVisibility(VISIBLE);
        imgOption.setImageResource(optionIcon);
        imgOption.setOnClickListener(v -> customActionBarCallback.onOptionButtonClicked());
        return this;
    }

    public CustomActionBar backGroundColor(@ColorRes int backGroundColor) {
        setBackgroundColor(ContextCompat.getColor(getContext(), backGroundColor));
        return this;
    }

    public CustomActionBar titleColor(@ColorRes int titleColor) {
        txtToolbarTitle.setTextColor(ContextCompat.getColor(getContext(), titleColor));
        return this;
    }

    public void removeHomeIcon() {
        imgHome.setVisibility(GONE);
    }

    public CustomActionBar removeOptionIcon() {
        imgOption.setVisibility(GONE);
        return this;
    }

    public interface CustomActionBarCallback {
        void onHomeButtonClicked();

        void onOptionButtonClicked();
    }

}
