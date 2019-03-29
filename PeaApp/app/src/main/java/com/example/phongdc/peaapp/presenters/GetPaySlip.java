package com.example.phongdc.peaapp.presenters;

import android.app.Activity;
import android.content.Context;

import com.example.phongdc.peaapp.service.AppRepository;
import com.example.phongdc.peaapp.service.AppRepositoryImpl;
import com.example.phongdc.peaapp.service.CallBackData;
import com.example.phongdc.peaapp.views.PaySlipView;

import java.util.List;

import Model.PaySlipGet;

public class GetPaySlip {
    private Context context;
    private Activity activity;
    private AppRepository appRepository;
    private PaySlipView mPaySlipView;

    public GetPaySlip(Context context, Activity activity, PaySlipView mPaySlipView) {
        this.context = context;
        this.activity = activity;
        this.mPaySlipView = mPaySlipView;
        this.appRepository = new AppRepositoryImpl();
    }
    public  void getPaySlip(String token,int periodId,int empId){
        appRepository.getPaySlip(context, token, periodId, empId, new CallBackData<List<PaySlipGet>>() {
            @Override
            public void onSuccess(List<PaySlipGet> paySlip) {
                mPaySlipView.getSuccessPaySlip(paySlip);
            }

            @Override
            public void onFail(String message) {
                mPaySlipView.getFail(message);
            }
        });
    }
}
