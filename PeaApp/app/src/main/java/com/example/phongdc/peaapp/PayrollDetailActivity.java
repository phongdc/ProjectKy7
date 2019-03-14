package com.example.phongdc.peaapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Adapter.PayrollRecyclerAdapter;
import Model.Payroll;
import cz.msebera.android.httpclient.Header;

public class PayrollDetailActivity extends AppCompatActivity {

    private TextView tvTitle;
    private RecyclerView rv_payroll;
    private List<Payroll> payrolls;
    private String nameApi = "PayrollDetail";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payroll_detail);
        findViewById();
        tvTitle.setText("Bảng lương");
        payrolls = new ArrayList<>();
        rv_payroll.setLayoutManager(new LinearLayoutManager(this));
        getPayroll();
    }
    private void findViewById(){
        tvTitle = findViewById(R.id.tvTitle);
        rv_payroll = findViewById(R.id.rv_Payrolls);
    }
    private void getPayroll(){
        HttpUtils.get(nameApi, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        Payroll payroll = new Payroll();
                        JSONObject object = response.getJSONObject(i);
                        payroll.setId(object.getInt("id"));
                        //payroll.setPayroll_detail_category_id(object.getInt("payroll_detail_category_id"));
                        //payroll.setSalary_rule_group_id(object.getInt("salary_rule_group_id"));
                        payroll.setName(object.getString("name"));
                        //payroll.setValue(object.getInt("value"));
                        //payroll.setDescription(object.getString("description"));
                        //payroll.setDefault_formula_id(object.getInt("default_formula_id"));
                        //payroll.setFrequency(object.getString("frequency"));
                        //payroll.setVariability(object.getString("variability"));
                        // payroll.setDestination(object.getString("destination"));
                        payrolls.add(payroll);
                    }
                    rv_payroll.setAdapter(new PayrollRecyclerAdapter(payrolls, PayrollDetailActivity.this));
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });
    }
}
