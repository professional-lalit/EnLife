package com.enlife.app.presenters_tests;

import com.enlife.app.database.models.Event;
import com.enlife.app.screens.main.dialog.EventsBottomContract;
import com.enlife.app.screens.main.dialog.EventsBottomDialogPresenter;
import com.enlife.app.utils.DateFormatter;

import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.List;

public class EventsBottomPresenterTest {

    private static class StubEventsBottomView implements EventsBottomContract.ViewContract {
        private List<Event> events;

        @Override
        public void onEventsLoaded(List<Event> events) {
            this.events = events;
        }

        public List<Event> getEvents() {
            return events;
        }
    }

    private final StubEventsBottomView stubEventsBottomView = new StubEventsBottomView();
    private final EventsBottomDialogPresenter SUT = new EventsBottomDialogPresenter(stubEventsBottomView);

    @Test
    public void verify_fetched_events_by_date() {
        //Arrange
        Date todayDate = new Date();
        String strTodayDate = new DateFormatter().getFormattedDate(DateFormatter.DateFormat.INDIAN_DATE_FORMAT, todayDate);
        //Act
        SUT.loadEvents(todayDate);
        //Assert
        List<Event> events = stubEventsBottomView.getEvents();
        for (Event event : events) {
            Assert.assertEquals(strTodayDate, event.getDate());
        }
    }

}
