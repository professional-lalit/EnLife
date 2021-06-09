package com.enlife.app.screens.main.fragments.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.enlife.app.R;
import com.enlife.app.common.CustomApplication;
import com.enlife.app.database.models.Event;
import com.enlife.app.models.CalendarDay;
import com.enlife.app.screens.main.HomeActivity;
import com.enlife.app.screens.main.fragments.goals.addevent.AddEventBottomDialog;
import com.enlife.app.screens.main.fragments.home.calendar.CalendarDaysAdapter;
import com.enlife.app.screens.main.fragments.schedule.DailyScheduleDialog;
import com.enlife.app.screens.widgets.CustomAppBar;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;


public class HomeFragment extends Fragment implements HomeScreenContract.ViewContract, CustomAppBar.CustomActionBarCallback, AddEventBottomDialog.EventAddedCallback {

    private RecyclerView recyclerCalendar;
    private ImageView imgPreviousWeek;
    private ImageView imgNextWeek;
    private TextView txtMonthName;
    private CustomAppBar customAppBar;

    @Inject
    HomeFragmentPresenter presenterContract;

    public static HomeFragment newInstance(@Nullable Bundle bundle) {
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CustomApplication.getInstance()
                .applicationComponent
                .eventsComponentBuilder()
                .build()
                .inject(this);
        presenterContract.setViewContract(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
        initToolbar();
        setViews();
        presenterContract.onViewInitialized();
    }

    private void initViews() {
        recyclerCalendar = requireView().findViewById(R.id.recycler_calendar);
        imgNextWeek = requireView().findViewById(R.id.img_next_week);
        imgPreviousWeek = requireView().findViewById(R.id.img_previous_week);
        txtMonthName = requireView().findViewById(R.id.txt_month_name);
        customAppBar = requireView().findViewById(R.id.action_bar);
    }

    private void initToolbar() {
        customAppBar.title("")
                .callback(this)
                .backGroundColor(R.color.transparent)
                .homeIcon(R.drawable.ic_drawer_menu)
                .optionIcon(R.drawable.ic_search);
    }

    private void setViews() {
        imgNextWeek.setOnClickListener(v -> presenterContract.onNext());
        imgPreviousWeek.setOnClickListener(v -> presenterContract.onPrevious());
    }

    @Override
    public void setCalenderView(List<CalendarDay> calendarDays) {
        CalendarDaysAdapter calendarDaysAdapter = new CalendarDaysAdapter(calendarDays, (position, calendarDay) -> {
            presenterContract.onDaySelected(calendarDays, calendarDay);
            Date date = presenterContract.getCursorDate();
//            EventsBottomDialog dialog = EventsBottomDialog.createDialog(date);
//            dialog.setEventAddedCallback(this);
//            dialog.show(getChildFragmentManager(), EventsBottomDialog.class.getSimpleName());
            DailyScheduleDialog.createDialog(date)
                    .show(getChildFragmentManager(), DailyScheduleDialog.TAG);

        });
        recyclerCalendar.setAdapter(calendarDaysAdapter);
        recyclerCalendar.setLayoutManager(new GridLayoutManager(requireContext(), 7));
    }

    @Override
    public void setMonthTitle(String monthTitle) {
        txtMonthName.setText(monthTitle);
    }

    @Override
    public void onDayUpdated(int position) {
        recyclerCalendar.getAdapter().notifyItemChanged(position);
    }

    @Override
    public void onHomeButtonClicked() {
        if (requireActivity() instanceof HomeActivity) {
            ((HomeActivity) requireActivity()).homeClicked();
        }
    }

    @Override
    public void onOptionButtonClicked() {

    }

    @Override
    public void onEventAdded(Event event) {
        presenterContract.reloadEvents();
    }
}