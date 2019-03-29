package service;


import android.content.Context;

import java.util.List;

import Model.Attendance;
import Model.PaySlip;
import Model.PaySlipGet;
import Model.PayrollPeriodGet;
import Model.Status;

public interface AppRepository {
    void getAttendence(Context context,String to_date,String from_date, int emp_id, CallBackData<List<Attendance>>callBackData);
    void getAttendenceStatus(Context context,String to_date,String from_date, int emp_id, CallBackData<List<Status>>callBackData);
    void getPayrollPeriod(Context context, String token, int empId, CallBackData<List<PayrollPeriodGet>> callBackData);
    void getPaySlip(Context context, String token, int periodId, int empId, CallBackData<List<PaySlipGet>> callBackData);}


