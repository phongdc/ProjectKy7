package com.example.phongdc.peaapp.presenters;

import android.app.Activity;
import android.content.Context;

import com.example.phongdc.peaapp.service.AppRepository;
import com.example.phongdc.peaapp.service.AppRepositoryImpl;
import com.example.phongdc.peaapp.service.CallBackData;
import com.example.phongdc.peaapp.views.AttendaneStatus;

import java.util.List;

import Model.Status;
public class GetAttendanceStatus {
    private Context context;
    private Activity activity;
    private AppRepository appRepository;
    private AttendaneStatus attendaneStatus;

    public GetAttendanceStatus(Context context, Activity activity, AttendaneStatus attendaneStatus) {
        this.context = context;
        this.activity = activity;
        this.attendaneStatus = attendaneStatus;
        this.appRepository = new AppRepositoryImpl();
    }
    public void getAttendanceStatus(String token,String toDate,String fromDate,int empId){
        appRepository.getAttendenceStatus(context,token,toDate, fromDate, empId, new CallBackData<List<Status>>() {
            @Override
            public void onSuccess(List<Status> statuses) {
                attendaneStatus.getAttdanceStatus(statuses);
            }

            @Override
            public void onFail(String message) {
            attendaneStatus.getFail(message);
            }
        });
    }
}
