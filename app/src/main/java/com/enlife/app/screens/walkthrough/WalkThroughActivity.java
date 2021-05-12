package com.enlife.app.screens.walkthrough;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.enlife.app.R;
import com.enlife.app.common.Constants;
import com.enlife.app.screens.home.HomeActivity;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator3;

public class WalkThroughActivity extends AppCompatActivity implements View.OnClickListener {

    public static void openScreen(AppCompatActivity activity, @Nullable Bundle bundle) {
        Intent intent = new Intent(activity, WalkThroughActivity.class);
        intent.putExtra(Constants.ARG_BUNDLE, bundle);
        activity.startActivity(intent);
    }

    private final List<PageData> pageDataList = new ArrayList<>();

    private ViewPager2 viewPager;
    private CircleIndicator3 circleIndicator3;
    private Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walk_through);
        initViews();
        setViews();
        loadWalkThroughData();
        setWalkThroughAdapter();
    }

    private void initViews() {
        viewPager = findViewById(R.id.view_pager);
        circleIndicator3 = findViewById(R.id.circle_indicator);
        btnNext = findViewById(R.id.btn_next);
    }

    private void setViews() {
        btnNext.setOnClickListener(this);
    }

    private void loadWalkThroughData() {
        pageDataList.add(new PageData(
                ContextCompat.getDrawable(this, R.drawable.ic_calendar),
                getString(R.string.wt_note_1)
        ));
        pageDataList.add(new PageData(
                ContextCompat.getDrawable(this, R.drawable.ic_chained),
                getString(R.string.wt_note_2)
        ));
        pageDataList.add(new PageData(
                ContextCompat.getDrawable(this, R.drawable.ic_target),
                getString(R.string.wt_note_3)
        ));
    }

    private void setWalkThroughAdapter() {
        WTPagerAdapter wtPagerAdapter = new WTPagerAdapter(pageDataList);
        viewPager.setAdapter(wtPagerAdapter);
        viewPager.setUserInputEnabled(false);
        circleIndicator3.setViewPager(viewPager);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_next) {
            if (viewPager.getCurrentItem() == pageDataList.size() - 1) {
                HomeActivity.openScreen(this, null);
                finish();
                return;
            }
            int nextItem = viewPager.getCurrentItem() + 1;
            if (nextItem == pageDataList.size() - 1) {
                btnNext.setText(getString(R.string.ok_im_in));
            }
            viewPager.setCurrentItem(nextItem);
        }
    }
}