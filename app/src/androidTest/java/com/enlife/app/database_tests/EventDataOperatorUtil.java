package com.enlife.app.database_tests;

import com.enlife.app.database.models.Event;
import com.enlife.app.utils.DateFormatter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EventDataOperatorUtil {

    public static List<Event> createEvents(int size, Date date) {
        List<Event> events = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            events.add(new Event(
                    0L,
                    "Event number" + i,
                    "Event description" + i,
                    new DateFormatter().getFormattedDate(DateFormatter.DateFormat.INDIAN_DATE_FORMAT, date),
                    false,
                    "{12123123.213:2143233432.3434}",
                    Event.RepeatMode.DAILY,
                    "9:00 AM",
                    "12:00 PM",
                    "",
                    0L,
                    0L
            ));
        }
        return events;
    }

}
