package com.enlife.app.screens.home;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.enlife.app.R;
import com.enlife.app.common.Constants;
import com.enlife.app.screens.home.fragments.goals.GoalManagementFragment;
import com.enlife.app.screens.home.fragments.home.HomeFragment;
import com.enlife.app.screens.widgets.CustomActionBar;
import com.enlife.app.utils.Utils;
import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    public static void openScreen(AppCompatActivity activity, @Nullable Bundle bundle) {
        Intent intent = new Intent(activity, HomeActivity.class);
        intent.putExtra(Constants.ARG_BUNDLE, bundle);
        activity.startActivity(intent);
    }

    private ImageView imgDrawerClose;
    private DrawerLayout homeDrawerLayout;
    private NavigationView homeNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initViews();
        setViews();
        loadHomeFragment();
    }

    private void initViews() {
        homeDrawerLayout = findViewById(R.id.home_drawer_layout);
        homeNavigation = findViewById(R.id.home_navigation);
        imgDrawerClose = homeNavigation.getHeaderView(0).findViewById(R.id.img_drawer_close);
    }

    private void setViews() {
        imgDrawerClose.setOnClickListener(this);
        homeNavigation.setNavigationItemSelectedListener(this);
    }

    private void loadHomeFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, HomeFragment.newInstance(null))
                .addToBackStack(HomeFragment.class.getSimpleName())
                .commit();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_drawer_close:
                homeDrawerLayout.closeDrawer(GravityCompat.START);
                break;
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_home:
                homeDrawerLayout.closeDrawer(GravityCompat.START);
                return true;
            case R.id.action_goals:
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.fragment_container, GoalManagementFragment.newInstance())
                        .addToBackStack(GoalManagementFragment.class.getSimpleName())
                        .commit();
                homeDrawerLayout.closeDrawer(GravityCompat.START);
                return true;
            case R.id.action_habit_tracking:
                homeDrawerLayout.closeDrawer(GravityCompat.START);
                return true;
            case R.id.action_notes:
                homeDrawerLayout.closeDrawer(GravityCompat.START);
                return true;
            case R.id.action_daily_stocism:
                homeDrawerLayout.closeDrawer(GravityCompat.START);
                return true;
            case R.id.action_settings:
                homeDrawerLayout.closeDrawer(GravityCompat.START);
                return true;
            case R.id.action_logout:
                homeDrawerLayout.closeDrawer(GravityCompat.START);
                return true;
        }
        return false;
    }

}