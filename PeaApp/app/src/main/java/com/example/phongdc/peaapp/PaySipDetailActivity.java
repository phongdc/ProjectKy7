package com.example.phongdc.peaapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.phongdc.peaapp.Home.HomeEmployee;
import com.example.phongdc.peaapp.presenters.GetPaySlip;
import com.example.phongdc.peaapp.presenters.GetPayrollPeriodPresenter;
import com.example.phongdc.peaapp.views.PaySlipView;
import com.example.phongdc.peaapp.views.PayrollPeriod;

import java.util.ArrayList;
import java.util.List;

import Adapter.PaySlipAdapter;
import Model.ListItem;
import Model.PaySlipGet;
import Model.PayrollPeriodGet;

public class PaySipDetailActivity extends AppCompatActivity implements PaySlipView, PayrollPeriod {
    private TextView mtxtFromDate;
    private TextView mTxtToDate;
    private TextView mTxtNetPay;
    private PaySlipAdapter paySlipAdapter;
    private List<ListItem> mListItemList;
    private GetPaySlip mGetPaySlip;
    private RecyclerView mRecyclerView;
private String token = HomeEmployee.getToken();
    private int empId=  HomeEmployee.getUserID();
    private GetPayrollPeriodPresenter mGetPayrollPeriodPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salary_detail);
        initialview();
        initialData();
    }
    private  void initialview(){
        mtxtFromDate = findViewById(R.id.text_from_date);
        mTxtToDate = findViewById(R.id.text_to_date);
        mTxtNetPay = findViewById(R.id.text_net_pay);
        mRecyclerView = findViewById(R.id.rcv_pay_slip);
        LinearLayoutManager layoutManager = new LinearLayoutManager(PaySipDetailActivity.this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
    }
    private void initialData(){
        mGetPayrollPeriodPresenter = new GetPayrollPeriodPresenter(PaySipDetailActivity.this, PaySipDetailActivity.this,this);
        mGetPayrollPeriodPresenter.getPayrollPeriod(token,empId);
    }
    private  void updateUI(){
        if(paySlipAdapter == null){
            paySlipAdapter = new PaySlipAdapter(PaySipDetailActivity.this,mListItemList);
            mRecyclerView.setAdapter(paySlipAdapter);
        }else {
            paySlipAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void getSuccess(List<PayrollPeriodGet> mPayrollPeriodGet) {
        mGetPaySlip = new GetPaySlip(PaySipDetailActivity.this, PaySipDetailActivity.this,this);
        mGetPaySlip.getPaySlip(token,mPayrollPeriodGet.get(0).getId(),empId);
    }
    @Override
    public void getSuccessPaySlip(List<PaySlipGet> mPaySlip) {
        mtxtFromDate.setText(mPaySlip.get(0).getFromDate());
        mTxtToDate.setText(mPaySlip.get(0).getToDate());
        mTxtNetPay.setText(mPaySlip.get(0).getNetPay()+"");
        mListItemList = new ArrayList<>();
        mListItemList = mPaySlip.get(0).getmItemList();
        updateUI();
    }
    @Override
    public void getFail(String message) {
    }
}
