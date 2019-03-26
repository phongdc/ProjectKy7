package com.example.phongdc.peaapp.PayrollPeriod;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.phongdc.peaapp.AsyncHttpClient.HttpUtils;
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

import Model.EmpGroup;
import cz.msebera.android.httpclient.Header;

public class PayPeriodAddGroupEmployee extends AppCompatActivity {

    private int id;
    private String name;
    private List<EmpGroup> empGroups;
    private List<String> empGroupsName;
    Spinner spnEmpGroup;
    private int empGroupID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_period_add_group_emp);

        spnEmpGroup = (Spinner)findViewById(R.id.spnEmpGroup);
        empGroupsName = new ArrayList<>();
        empGroups = new ArrayList<EmpGroup>();
        getGroupEmployee();

        spnEmpGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String empGroup = spnEmpGroup.getItemAtPosition(spnEmpGroup.getSelectedItemPosition()).toString();

                for (int a = 0; a < empGroups.size(); a++){
                    if (empGroups.get(i).getName().matches(empGroup)){
                        empGroupID = (empGroups.get(i).getId());
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // DO Nothing here
            }
        });

    }

    public void getGroupEmployee(){

        HttpUtils.getByUrl("http://payroll.unicode.edu.vn/api/employee_group", null, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                try {
                    JSONObject json = new JSONObject(response.toString());
                    JSONArray jArray = json.getJSONArray("data");

                    for (int i = 0; i < jArray.length(); i++) {
                        EmpGroup empGroup = new EmpGroup();
                        JSONObject object = jArray.getJSONObject(i);
                        empGroup.setId(object.getInt("id"));
                        empGroup.setName(object.getString("name"));
                        empGroups.add(empGroup);
                        empGroupsName.add(object.getString("name"));
                    }
                    spnEmpGroup.setAdapter(new ArrayAdapter<>(PayPeriodAddGroupEmployee.this, android.R.layout.simple_spinner_dropdown_item, empGroupsName));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public void clickToAddGroupEmp(View view) {
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        final RequestParams params = new RequestParams();

        params.put("period_apply_id", 0);
        params.put("list_emp", "");
        params.put("group_emp", empGroupID);

        params.setUseJsonStreamer(true);

        asyncHttpClient.post("http://payroll.unicode.edu.vn/api/payroll_period/apply", params, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Toast.makeText(PayPeriodAddGroupEmployee.this,"Thêm Thành Công",Toast.LENGTH_SHORT ).show();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(PayPeriodAddGroupEmployee.this,"Thêm thất bại",Toast.LENGTH_SHORT ).show();
            }


            @Override
            public void onRetry(int retryNo) {
                super.onRetry(retryNo);
            }
        });
    }



}
