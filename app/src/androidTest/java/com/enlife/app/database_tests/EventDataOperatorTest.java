package com.enlife.app.database_tests;

import android.util.Log;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.enlife.app.database.models.Event;
import com.enlife.app.database.operators.DatabaseOperator;
import com.enlife.app.database.operators.EventDataOperator;
import com.enlife.app.utils.DateFormatter;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;
import java.util.List;

@RunWith(AndroidJUnit4.class)
public class EventDataOperatorTest {

    private DatabaseOperator databaseOperator;
    private DateFormatter dateFormatter;

    @Before
    public void createDb() {
        databaseOperator = new EventDataOperator();
        dateFormatter = new DateFormatter();
    }

    @After
    public void closeDb() {
        databaseOperator.closeDatabase();
    }

    @Test
    public void add_events_and_read_events() {
        databaseOperator.clearTable();

        List<Event> eventsToAdd = EventDataOperatorUtil.createEvents(5);
        for (Event event : eventsToAdd) {
            databaseOperator.addData(event);
        }

        List<Event> eventsRetrieved = databaseOperator.getList(
                dateFormatter.getFormattedDate(DateFormatter.DateFormat.INDIAN_DATE_FORMAT, new Date())
        );

        Assert.assertFalse(eventsRetrieved.isEmpty());

        for (int i = 0; i < eventsRetrieved.size(); i++) {
            Assert.assertEquals(eventsRetrieved.get(i).getTitle(), "Event number" + i);
        }
    }

    @Test
    public void update_event_and_check() {

        final String EVENT_TITLE = "Event Modified Title";

        databaseOperator.clearTable();

        //add events
        List<Event> eventsToAdd = EventDataOperatorUtil.createEvents(5);
        for (Event event : eventsToAdd) {
            databaseOperator.addData(event);
        }

        //fetch
        List<Event> eventsRetrieved = databaseOperator.getList(
                dateFormatter.getFormattedDate(DateFormatter.DateFormat.INDIAN_DATE_FORMAT, new Date())
        );

        //acquire event to be updated
        Event eventToBeModified = eventsRetrieved.get(2);
        long eventId = eventToBeModified.getId();

        Assert.assertNotNull(eventToBeModified);

        //update one of the event
        eventToBeModified.setTitle(EVENT_TITLE);
        databaseOperator.updateData(eventId, eventToBeModified);

        //fetch
        eventsRetrieved = databaseOperator.getList(
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

}
