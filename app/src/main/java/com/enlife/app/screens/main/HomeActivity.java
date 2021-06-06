package com.enlife.app.screens.main;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.enlife.app.R;
import com.enlife.app.WakeUpReceiver;
import com.enlife.app.common.Constants;
import com.enlife.app.common.CustomApplication;
import com.enlife.app.common.PreferenceManager;
import com.enlife.app.screens.main.fragments.goals.addgoal.AddGoalFragment;
import com.enlife.app.screens.main.fragments.home.HomeFragment;
import com.enlife.app.screens.main.fragments.stoicism.QuotesFragment;
import com.enlife.app.utils.NotificationHelper;
import com.google.android.material.navigation.NavigationView;

import java.util.Calendar;

import javax.inject.Inject;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    public static void openScreen(AppCompatActivity activity, @Nullable Bundle bundle) {
        Intent intent = new Intent(activity, HomeActivity.class);
        intent.putExtra(Constants.ARG_BUNDLE, bundle);
        activity.startActivity(intent);
    }

    public static void openScreen(Context context, @Nullable Bundle bundle) {
        Intent intent = new Intent(context, HomeActivity.class);
        intent.putExtra(Constants.ARG_BUNDLE, bundle);
        context.startActivity(intent);
    }

    private ImageView imgDrawerClose;
    private DrawerLayout homeDrawerLayout;
    private NavigationView homeNavigation;

    private AlarmManager alarmMgr;
    private PendingIntent alarmIntent;

    @Inject
    PreferenceManager preferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        CustomApplication.getInstance()
                .applicationComponent
                .mainComponentBuilder()
                .build()
                .inject(this);

        initViews();
        setViews();
        loadHomeFragment();

//        if (!preferenceManager.isDailyWakeupNotificationSet()) {
//        scheduleDailyWakeupNotification();
//        }
    }

    private void scheduleDailyWakeupNotification() {
        alarmMgr = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, WakeUpReceiver.class);
        alarmIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, 1);
//        calendar.set(Calendar.HOUR_OF_DAY, 7);
//        calendar.set(Calendar.MINUTE, 0);
        alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 1000 * 60 * 20, alarmIntent);
        preferenceManager.setDailyWakeupNotification(true);
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

    public void homeClicked() {
        if (homeDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            homeDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            homeDrawerLayout.openDrawer(GravityCompat.START);
        }
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
                        .add(R.id.fragment_container, AddGoalFragment.newInstance())
                        .addToBackStack(AddGoalFragment.class.getSimpleName())
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
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.fragment_container, QuotesFragment.getInstance())
                        .addToBackStack(QuotesFragment.class.getSimpleName())
                        .commit();
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