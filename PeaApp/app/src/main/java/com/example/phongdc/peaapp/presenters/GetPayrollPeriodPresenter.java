package com.example.phongdc.peaapp.presenters;

import android.app.Activity;
import android.content.Context;

import com.example.phongdc.peaapp.service.AppRepository;
import com.example.phongdc.peaapp.service.AppRepositoryImpl;
import com.example.phongdc.peaapp.service.CallBackData;
import com.example.phongdc.peaapp.views.PayrollPeriod;

import java.util.List;

import Model.PayrollPeriodGet;
public class GetPayrollPeriodPresenter {
    private Context context;
    private Activity activity;
    private AppRepository appRepository;
    private PayrollPeriod payrollPeriod;

    public GetPayrollPeriodPresenter(Context context, Activity activity, PayrollPeriod payrollPeriod) {
        this.context = context;
        this.activity = activity;
        this.payrollPeriod = payrollPeriod;
        this.appRepository = new AppRepositoryImpl();
    }
    public  void getPayrollPeriod(String token,int empId){
        appRepository.getPayrollPeriod(context, token, empId, new CallBackData<List<PayrollPeriodGet>>() {
            @Override
            public void onSuccess(List<PayrollPeriodGet> payrollPeriodGet) {
                payrollPeriod.getSuccess(payrollPeriodGet);
            }
            @Override
            public void onFail(String message) {
                payrollPeriod.getFail(message);
            }
        });
    }
}
