package com.example.phongdc.peaapp.PayrollPeriod;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.phongdc.peaapp.AsyncHttpClient.HttpUtils;
import com.example.phongdc.peaapp.PayrollPeriod.PayPeriodAddListEmployee;
import com.example.phongdc.peaapp.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Adapter.PayPeriodRecyclerAdapter;
import Model.Employee;
import Model.PayrollPeriod;
import cz.msebera.android.httpclient.Header;

public class PayPeriodDetails extends AppCompatActivity {
    private TextView txtPeriodName;

    private int id;
    private String name;
    private List<Employee> empList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_period_details);
        Bundle extras = this.getIntent().getExtras();
        id = extras.getInt("ID");
        name = extras.getString("NAME");
        txtPeriodName = findViewById(R.id.payperiodname);
        empList = new ArrayList<Employee>();
        txtPeriodName.setText(name);

        getEmployee();
    }

    public void getEmployee(){

        HttpUtils.getByUrl("http://payroll.unicode.edu.vn/api/employee", null, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                try {
                    JSONObject json = new JSONObject(response.toString());
                    JSONArray jArray = json.getJSONArray("data");

                    for (int i = 0; i < jArray.length(); i++) {
                        Employee emp = new Employee();
                        JSONObject object = jArray.getJSONObject(i);
                        emp.setId(object.getInt("id"));
                        emp.setEmployee_name(object.getString("employee_name"));
                        emp.setCode(object.getString("code"));
                        empList.add(emp);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public void clickToAddEmp(View view) {
        Intent intent = new Intent(PayPeriodDetails.this, PayPeriodAddListEmployee.class);
        startActivity(intent);
    }

    public void clickToAddGroupEmp(View view) {
        Intent intent = new Intent(PayPeriodDetails.this, PayPeriodAddGroupEmployee.class);
        startActivity(intent);
    }



}