package com.example.phongdc.peaapp.service;


import android.content.Context;

import java.util.List;

import Model.Attendance;
import Model.Status;

public interface AppRepository {
    void getAttendence(Context context, String to_date, String from_date, int emp_id, CallBackData<List<Attendance>> callBackData);
    void getAttendenceStatus(Context context, String to_date, String from_date, int emp_id, CallBackData<List<Status>> callBackData);
}
