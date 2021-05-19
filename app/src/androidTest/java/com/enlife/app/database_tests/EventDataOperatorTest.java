package com.enlife.app.database_tests;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.enlife.app.database.models.DateEventCount;
import com.enlife.app.database.models.Event;
import com.enlife.app.database.operators.DatabaseOperator;
import com.enlife.app.database.operators.EventDataOperator;
import com.enlife.app.utils.DateFormatter;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RunWith(AndroidJUnit4.class)
public class EventDataOperatorTest {

    private EventDataOperator SUT;
    private DateFormatter dateFormatter;

    @Before
    public void initDb() {
        SUT = new EventDataOperator();
        dateFormatter = new DateFormatter();
    }

    @After
    public void closeDb() {
        SUT.closeDatabase();
    }

    @Test
    public void add_events_and_read_events() {
        SUT.clearTable();

        List<Event> eventsToAdd = EventDataOperatorUtil.createEvents(30, new Date());
        for (Event event : eventsToAdd) {
            SUT.addData(event);
        }

        List<Event> eventsRetrieved = SUT.getList(
                dateFormatter.getFormattedDate(DateFormatter.DateFormat.INDIAN_DATE_FORMAT, new Date())
        );

        Assert.assertFalse(eventsRetrieved.isEmpty());

        for (int i = 0; i < eventsRetrieved.size(); i++) {
            Assert.assertEquals(eventsRetrieved.get(i).getTitle(), "Event number" + i);
        }
    }

    @Test
    public void update_event_and_verify_data() {

        final String EVENT_TITLE = "Event Modified Title";

        SUT.clearTable();

        //add events
        List<Event> eventsToAdd = EventDataOperatorUtil.createEvents(5, new Date());
        for (Event event : eventsToAdd) {
            SUT.addData(event);
        }

        //fetch
        List<Event> eventsRetrieved = SUT.getList(
                dateFormatter.getFormattedDate(DateFormatter.DateFormat.INDIAN_DATE_FORMAT, new Date())
        );

        //acquire event to be updated
        Event eventToBeModified = eventsRetrieved.get(2);
        long eventId = eventToBeModified.getId();

        Assert.assertNotNull(eventToBeModified);

        //update one of the event
        eventToBeModified.setTitle(EVENT_TITLE);
        SUT.updateData(eventId, eventToBeModified);

        //fetch
        eventsRetrieved = SUT.getList(
                dateFormatter.getFormattedDate(DateFormatter.DateFormat.INDIAN_DATE_FORMAT, new Date())
        );

        //verify modified title
        for (Event event : eventsRetrieved) {
            if (event.getId() == eventId) {
                Assert.assertEquals(event.getTitle(), EVENT_TITLE);
                break;
            }
        }
    }

    @Test
    public void verify_events_by_date() {
        //Arrange
        Date todayDate = new Date();
        String strTodayDate = new DateFormatter().getFormattedDate(DateFormatter.DateFormat.INDIAN_DATE_FORMAT, todayDate);
        //Act
        List<Event> events = SUT.getList(strTodayDate);
        //Assert
        for (Event event : events) {
            Assert.assertEquals(strTodayDate, event.getDate());
        }
    }

    @Test
    public void verify_day_event_count() {

        //Arrange
        SUT.clearTable();
        Calendar calendar = Calendar.getInstance();

        Date today = calendar.getTime();
        calendar.add(Calendar.DATE, -1);
        Date yesterday = calendar.getTime();
        calendar.add(Calendar.DATE, 2);
        Date tomorrow = calendar.getTime();

        List<Event> eventsToAdd1 = EventDataOperatorUtil.createEvents(2, yesterday);
        for (Event event : eventsToAdd1) {
            SUT.addData(event);
        }
        List<Event> eventsToAdd2 = EventDataOperatorUtil.createEvents(5, today);
        for (Event event : eventsToAdd2) {
            SUT.addData(event);
        }
        List<Event> eventsToAdd3 = EventDataOperatorUtil.createEvents(6, tomorrow);
        for (Event event : eventsToAdd3) {
            SUT.addData(event);
        }

        //Act
        List<DateEventCount> eventCountList = SUT.getDateEventCounts();

        //Assert
        Assert.assertFalse(eventCountList.isEmpty());
        for (int i = 0; i < eventCountList.size(); i++) {
            DateEventCount dateEventCount = eventCountList.get(i);

            if (dateEventCount.getDate().equals(dateFormatter.getFormattedDate(
                    DateFormatter.DateFormat.INDIAN_DATE_FORMAT, today))
            ) {
                Assert.assertEquals(5, dateEventCount.getCount());
            } else if (dateEventCount.getDate().equals(dateFormatter.getFormattedDate(
                    DateFormatter.DateFormat.INDIAN_DATE_FORMAT, yesterday))
            ) {
                Assert.assertEquals(2, dateEventCount.getCount());
            } else if (dateEventCount.getDate().equals(dateFormatter.getFormattedDate(
                    DateFormatter.DateFormat.INDIAN_DATE_FORMAT, tomorrow))
            ) {
                Assert.assertEquals(6, dateEventCount.getCount());
            }
        }
    }

}
