/**
 * Copyright (C) 2015, 2016 Dirk Lemmermann Software & Consulting (dlsc.com) 
 * 
 * This file is part of CalendarFX.
 */

package controllers;


import com.calendarfx.model.Calendar;
import com.calendarfx.model.Calendar.Style;
import com.calendarfx.model.CalendarEvent;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.model.Entry;
import com.calendarfx.model.Interval;
import com.calendarfx.view.CalendarView;
import entity.Event;
import java.sql.Date;
import java.time.Instant;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import static java.time.temporal.TemporalQueries.localDate;
import javafx.event.EventHandler;
import javafx.event.EventType;
import service.EventService;

public class CalendarApp  {

    EventService es = new EventService();

    public  StackPane start() throws Exception {
        CalendarView calendarView = new CalendarView();

        Calendar accepted = new Calendar("Accepted");
        Calendar rejected = new Calendar("Rejected");
        Calendar pending = new Calendar("Pending");

        accepted.setShortName("Accepted");
        rejected.setShortName("Rejected");
        pending.setShortName("Pending");

        loadCalander(accepted,rejected,pending);
     
        EventHandler<CalendarEvent> handler = evt -> foo(evt);
        
        accepted.addEventHandler(handler);

        accepted.setStyle(Style.STYLE1);
        rejected.setStyle(Style.STYLE6);
        pending.setStyle(Style.STYLE3);
      
        CalendarSource familyCalendarSource = new CalendarSource("Family");
        familyCalendarSource.getCalendars().addAll(accepted,rejected,pending);

        calendarView.getCalendarSources().setAll(familyCalendarSource);
        calendarView.setRequestedTime(LocalTime.now());
        
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(calendarView); // introPane);
        
        Thread updateTimeThread = new Thread("Calendar: Update Time Thread") {
            @Override
            public void run() {
                while (true) {
                    Platform.runLater(() -> {
                        calendarView.setToday(LocalDate.now());
                        calendarView.setTime(LocalTime.now());

                    });

                    try {
                        // update every 10 seconds
                        sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        };

        updateTimeThread.setPriority(Thread.MIN_PRIORITY);
        updateTimeThread.setDaemon(true);
        updateTimeThread.start();

return stackPane;
    }
    
   

    private EventHandler<CalendarEvent> foo(CalendarEvent evt) {

        int id = Integer.parseInt(evt.getEntry().getId()) ;
        if(evt.getOldInterval().getStartDate()!=evt.getEntry().getStartDate() || evt.getOldInterval().getEndDate()!=evt.getEntry().getEndDate())
        {
           LocalDate startDate = evt.getEntry().getInterval().getStartDate();
           LocalDate endDate = evt.getEntry().getInterval().getStartDate();
           es.updateIntervalle(startDate.atStartOfDay(),endDate.atStartOfDay(),id);
        }
        if (evt.ENTRY_TITLE_CHANGED.toString()=="ENTRY_TITLE_CHANGED")
        {
            String title = evt.getEntry().getTitle();
            es.update(new Event(title,id));
            
        }
        if (evt.isEntryAdded())
        {
            Entry e = evt.getEntry();
            es.add(new Event(e.getTitle(), 0, Date.valueOf(e.getStartDate()), Date.valueOf(e.getEndDate()),0, 0, "Pending", 0.0));
        }

        return  (event) -> {
            if (evt.isEntryRemoved())
            {
                es.delete(id);
            }
        };
    }

    public LocalDateTime toLocalDateTimeViaInstant(Date dateToConvert) {
    return dateToConvert.toLocalDate().atTime(0, 0)
      .atZone(ZoneId.systemDefault())
      .toLocalDateTime();
    }
    private void loadCalander(Calendar accepted, Calendar rejected, Calendar pending) {
         es.selectAllEventFrom()
                .forEach(e -> {
           String title = e.getTitle();
           int id = e.getId();
           Date startDate = e.getStartDate();
           Date endDate = e.getEndDate();
           Interval i = new Interval(toLocalDateTimeViaInstant(startDate),toLocalDateTimeViaInstant(startDate));
           Entry entry = new Entry(title, i);
           entry.setId(Integer.toString(id));
           if (e.getStatus().equals("Accept"))
           {
           accepted.addEntry(entry);
           entry.setCalendar(accepted);  
           }
           else if (e.getStatus().equals("Pending"))
           {
           pending.addEntry(entry);
           entry.setCalendar(pending);
           }
           else
           {
           rejected.addEntry(entry);
           entry.setCalendar(rejected);
           }
        });
    }
    
    
}
