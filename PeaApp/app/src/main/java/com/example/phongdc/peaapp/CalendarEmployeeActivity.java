package com.example.phongdc.peaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.exceptions.OutOfDateRangeException;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import Model.Status;
import presenters.GetAttendanceStatus;
import views.AttendaneStatus;

public class CalendarEmployeeActivity extends AppCompatActivity implements AttendaneStatus {
private GetAttendanceStatus getAttendanceStatus;
private List<String> date;
private List<Boolean> status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_employee);
            setDataCalendar();
    }
    public void setDataCalendar(){
        Calendar min = Calendar.getInstance();
        min.add(Calendar.DAY_OF_MONTH, -20);
        Calendar max = Calendar.getInstance();
        max.add(Calendar.DAY_OF_MONTH, 10);
        try {
            String minCalendar = covertDateToString(min);
            Log.e("min",minCalendar);
            String maxCalendar = covertDateToString(max);
            Log.e("max",maxCalendar);
            getAttendanceStatus = new GetAttendanceStatus(CalendarEmployeeActivity.this,CalendarEmployeeActivity.this,this);
            getAttendanceStatus.getAttendanceStatus(maxCalendar,minCalendar,2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    private  String covertDateToString(Calendar calendar) throws ParseException {
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        Date date = calendar.getTime();
        String result =  format1.format(date);
        return result;
    }
    private Calendar covertStringToDate(String dateString) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date date = sdf.parse(dateString);
        Calendar calender = Calendar.getInstance();
        calender.setTime(date);
        return calender;
    }
    private  String getCurrentTime() throws ParseException {
        SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        String result =  format1.format(date);
        return result;
    }
//    private List<Calendar> getDisabledDays() {
//        List<Calendar> calendars = new ArrayList<>();
//        for (int i = 0; i <status.size() ; i++) {
//            if(status.get(i) == false){
//                Calendar calendarFail = null;
//                try {
//                    calendarFail = covertStringToDate(date.get(i));
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//                calendars.add(calendarFail);
//            }
//        }
//        return calendars;
//    }
//
//    private Calendar getRandomCalendar() {
//        Random random = new Random();
//        Calendar calendar = Calendar.getInstance();
//        calendar.add(Calendar.MONTH, random.nextInt(99));
//        return calendar;
//    }
    private void setEvent()  {
        List<EventDay> events = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        Calendar min = Calendar.getInstance();
        min.add(Calendar.MONTH, -1);
        Calendar max = Calendar.getInstance();
        max.add(Calendar.MONTH, 1);
        for (int i = 0; i <date.size() ; i++) {
            if(status.get(i).booleanValue() ==true){
                Calendar calendar1 = null;
                try {
                    calendar1 = covertStringToDate(date.get(i));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                events.add(new EventDay(calendar1,R.mipmap.checked));
            }
            else {
                Calendar calendar1 = null;
                try {
                    calendar1 = covertStringToDate(date.get(i));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                events.add(new EventDay(calendar1,R.mipmap.x_icon));
            }
        }
        CalendarView calendarView = (CalendarView) findViewById(R.id.calendarView);
        calendarView.setMinimumDate(min);
        try {
            calendarView.setDate(calendar);
        } catch (OutOfDateRangeException e) {
            e.printStackTrace();
        }
        calendarView.setMaximumDate(max);
        calendarView.setEvents(events);
    //    calendarView.setDisabledDays(getDisabledDays());
        calendarView.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {
                Calendar clickedDayCalendar = eventDay.getCalendar();

                try{
                    if(eventDay.getImageResource()!=0){
                        String time = covertDateToString(clickedDayCalendar);
                        Intent intent = new Intent(CalendarEmployeeActivity.this,CalendarEmployeeDetailActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("time",time);
                        bundle.putInt("image",eventDay.getImageResource());
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(CalendarEmployeeActivity.this, "Bạn không có nhiêm vụ ", Toast.LENGTH_LONG).show();
                    }}catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    @Override
    public void getAttdanceStatus(List<Status> statusList) {
        date = new ArrayList<>();
        status = new ArrayList<>();
        for (int i = 0; i <statusList.size() ; i++) {
            date.add(statusList.get(i).getDate());
        }
        for (int i = 0; i <statusList.size() ; i++) {
            status.add(statusList.get(i).isStatus());
        }
        setEvent();
    }
    @Override
    public void getFail(String message) {

    }
}
