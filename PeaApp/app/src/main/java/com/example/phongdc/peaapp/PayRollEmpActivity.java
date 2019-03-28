package com.example.phongdc.peaapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.phongdc.peaapp.AsyncHttpClient.HttpUtils;
import com.example.phongdc.peaapp.Home.HomeEmployee;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Adapter.PayRollEmpRecyclerAdapter;
import Adapter.TimeFrameRecyclerAdapter;
import Model.PayRollEmp;
import Model.Payroll;
import Model.TimeFrame;
import cz.msebera.android.httpclient.Header;

public class PayRollEmpActivity extends AppCompatActivity {

    private TextView tvTitle;
    private RecyclerView rv_PayRollEmp;
    private List<PayRollEmp> payRollList;
    int a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payroll_period_emp);

        a = HomeEmployee.getUserID();

        findViewById();
        tvTitle.setText("PayRoll");
        payRollList = new ArrayList<>();
        rv_PayRollEmp.setLayoutManager(new LinearLayoutManager(this));
        getPayRollEmp();


    }
    private void findViewById(){
        tvTitle = findViewById(R.id.tvTitle);
        rv_PayRollEmp = findViewById(R.id.rv_PayRollEmp);

    }

    public void getPayRollEmp(){
        String token = HomeEmployee.getToken();
        HttpUtils.getByUrlAuth("http://payroll.unicode.edu.vn/api/payroll_period?empId=" + a,token, null, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    JSONObject json = new JSONObject(response.toString());
                    JSONArray jArray = json.getJSONArray("data");
                    for (int i = 0; i < jArray.length(); i++) {
                        PayRollEmp payRollemp = new PayRollEmp();
                        JSONObject object = jArray.getJSONObject(i);
                        payRollemp.setName(object.getString("name"));
                        payRollemp.setFromDate(object.getString("from_date"));
                        payRollemp.setToDate(object.getString("to_date"));

                        payRollList.add(payRollemp);
                    }
                    rv_PayRollEmp.setAdapter(new PayRollEmpRecyclerAdapter(payRollList, PayRollEmpActivity.this));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

}
