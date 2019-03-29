package com.example.phongdc.peaapp;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import Adapter.PaySlipAdapter;
import Model.ListItem;
import Model.PaySlip;
import Model.PaySlipGet;
import Model.PayrollPeriodGet;
import presenters.GetPaySlip;
import presenters.GetPayrollPeriodPresenter;
import views.PaySlipView;
import views.PayrollPeriod;
public class PaySipDetailActivity extends AppCompatActivity implements PaySlipView, PayrollPeriod{
    private TextView mtxtFromDate;
    private TextView mTxtToDate;
    private TextView mTxtNetPay;
    private PaySlipAdapter paySlipAdapter;
    private List<ListItem> mListItemList;
    private GetPaySlip mGetPaySlip;
    private RecyclerView mRecyclerView;
    private String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYW1laWQiOiI1OCIsInVuaXF1ZV9uYW1lIjoiZW1wMDFAZ21haWwuY29tLTUiLCJCcmFuZElkIjoiNSIsInJvbGUiOiJBY3RpdmVVc2VyIiwibmJmIjoxNTUzODI2Mjg1LCJleHAiOjE1NTkwMzU0ODUsImlhdCI6MTU1MzgyNjI4NSwiaXNzIjoiaHR0cDovL2Rldi5hdXRob3JpemUucmVzby52biIsImF1ZCI6Imh0dHA6Ly9kZXYuYXV0aG9yaXplLnJlc28udm4ifQ.aAEoKkQBpIFnR-oYTW3-CHgGFC8cYRuTxUISJjS6nW4";
    private int empId= 12;
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
        mGetPayrollPeriodPresenter = new GetPayrollPeriodPresenter(PaySipDetailActivity.this,PaySipDetailActivity.this,this);
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
        mGetPaySlip = new GetPaySlip(PaySipDetailActivity.this,PaySipDetailActivity.this,this);
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
