package com.enlife.app.screens.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.core.content.ContextCompat;

import com.enlife.app.R;

public class CustomToolbar extends RelativeLayout {

    public CustomToolbar(Context context) {
        super(context);
        init(context);
    }

    public CustomToolbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private ImageView imgHome;
    private ImageView imgOption;
    private TextView txtToolbarTitle;
    private CustomAppBar.CustomActionBarCallback customActionBarCallback;

    private void init(Context context) {
        inflate(context, R.layout.toolbar_common, this);
        txtToolbarTitle = findViewById(R.id.txt_toolbar_title);
        imgHome = findViewById(R.id.img_home);
        imgOption = findViewById(R.id.img_option);
    }

    public CustomToolbar callback(CustomAppBar.CustomActionBarCallback customActionBarCallback) {
        this.customActionBarCallback = customActionBarCallback;
        return this;
    }

    public CustomToolbar title(String title) {
        txtToolbarTitle.setText(title);
        return this;
    }

    public CustomToolbar homeIcon(@DrawableRes int homeIcon) {
        imgHome.setVisibility(VISIBLE);
        imgHome.setImageResource(homeIcon);
        imgHome.setOnClickListener(v -> customActionBarCallback.onHomeButtonClicked());
        return this;
    }

    public CustomToolbar optionIcon(@DrawableRes int optionIcon) {
        imgOption.setVisibility(VISIBLE);
        imgOption.setImageResource(optionIcon);
        imgOption.setOnClickListener(v -> customActionBarCallback.onOptionButtonClicked());
        return this;
    }

    public CustomToolbar backGroundColor(@ColorRes int backGroundColor) {
        setBackgroundColor(ContextCompat.getColor(getContext(), backGroundColor));
        return this;
    }

    public CustomToolbar titleColor(@ColorRes int titleColor) {
        txtToolbarTitle.setTextColor(ContextCompat.getColor(getContext(), titleColor));
        return this;
    }

    public void removeHomeIcon() {
        imgHome.setVisibility(GONE);
    }

    public CustomToolbar removeOptionIcon() {
        imgOption.setVisibility(GONE);
        return this;
    }

    public interface CustomActionBarCallback {
        void onHomeButtonClicked();

        void onOptionButtonClicked();
    }
}
