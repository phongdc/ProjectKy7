package com.example.phongdc.peaapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.exceptions.OutOfDateRangeException;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.applandeo.materialcalendarview.utils.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class CalendarEmployeeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_employee);
        setDataCalendar();

    }
    public void setDataCalendar(){
        List<EventDay> events = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        Calendar calendar1 = Calendar.getInstance();
        calendar1.add(Calendar.DAY_OF_MONTH, 0);

        Calendar calendar2 = Calendar.getInstance();
        calendar2.add(Calendar.DAY_OF_MONTH, 5);
        Calendar calendar3 = Calendar.getInstance();
        calendar3.add(Calendar.DAY_OF_MONTH, 6);
        Calendar calendar4 = Calendar.getInstance();
        calendar4.add(Calendar.DAY_OF_MONTH, 7);
//        events.add(new EventDay(calendar1,));
        events.add(new EventDay(calendar2,R.mipmap.checked));
        events.add(new EventDay(calendar3,R.mipmap.timer));
        events.add(new EventDay(calendar4,R.mipmap.x_icon));
        Calendar min = Calendar.getInstance();
        min.add(Calendar.DAY_OF_WEEK_IN_MONTH, -1);
        Calendar max = Calendar.getInstance();
        max.add(Calendar.DAY_OF_WEEK_IN_MONTH, 1);
        CalendarView calendarView = (CalendarView) findViewById(R.id.calendarView);
        calendarView.setMinimumDate(min);
        try {
            calendarView.setDate(calendar);
        } catch (OutOfDateRangeException e) {
            e.printStackTrace();
        }
        calendarView.setMaximumDate(max);
        calendarView.setEvents(events);
//        calendarView.setDisabledDays(getDisabledDays());
        calendarView.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {
                Calendar clickedDayCalendar = eventDay.getCalendar();
            try{
                String time = covertDateToString(clickedDayCalendar);
            }catch (ParseException e) {
                e.printStackTrace();
            }

            }
        });
    }
    private  String covertDateToString(Calendar calendar) throws ParseException {
        SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
        Date date = calendar.getTime();
        String result =  format1.format(date);
        return result;
    }
    private  String getCurrentTime() throws ParseException {
        SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        String result =  format1.format(date);
        return result;
    }
    private List<Calendar> getDisabledDays() {
        Calendar firstDisabled = DateUtils.getCalendar();
        firstDisabled.add(Calendar.DAY_OF_MONTH, 2);
        Calendar secondDisabled = DateUtils.getCalendar();
        secondDisabled.add(Calendar.DAY_OF_MONTH, 1);
        Calendar thirdDisabled = DateUtils.getCalendar();
        thirdDisabled.add(Calendar.DAY_OF_MONTH, 18);
        List<Calendar> calendars = new ArrayList<>();
        calendars.add(firstDisabled);
        calendars.add(secondDisabled);
        calendars.add(thirdDisabled);
        return calendars;
    }


    private Calendar getRandomCalendar() {
        Random random = new Random();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, random.nextInt(99));
        return calendar;
    }
}
