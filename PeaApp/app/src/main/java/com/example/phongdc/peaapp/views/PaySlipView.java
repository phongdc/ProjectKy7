package com.example.phongdc.peaapp.views;

import java.util.List;

import Model.PaySlipGet;

public interface PaySlipView {
    void getSuccessPaySlip(List<PaySlipGet> mPaySlip);
    void getFail(String message);
}
