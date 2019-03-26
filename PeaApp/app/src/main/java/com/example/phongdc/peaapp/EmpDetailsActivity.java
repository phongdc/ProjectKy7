package com.example.phongdc.peaapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;


import org.json.JSONArray;
import org.json.JSONObject;

import Model.Employee;
import cz.msebera.android.httpclient.Header;

public class EmpDetailsActivity extends AppCompatActivity {

    private EditText edtEmpName;
    private EditText edtPhone;
    private EditText edtAddress;
    private TextView tvEmpCode;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp_details);
        Bundle extras = this.getIntent().getExtras();
        //id = extras.getInt("ID");
        edtEmpName = findViewById(R.id.edtEmpName);
        edtPhone = findViewById(R.id.edtEmpPhone);
        edtAddress = findViewById(R.id.edtEmpAddress);
        tvEmpCode = findViewById(R.id.tvEmpCode);
        getEmpDetails();
    }
    private void getEmpDetails() {
     HttpUtils.get("employee?id=7", null, new JsonHttpResponseHandler(){
          @Override
          public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
              try {
                  JSONObject data = (JSONObject) response.get("data");
                  for (int i = 0; i < data.length(); i++) {
                      //Employee employee = new Employee();
                      //JSONObject jsonObject = data.getJSONObject(i);
                      //Double salary = jsonObject.getDouble("salary");
                      String name = data.getString("employee_name");
                      String address = data.getString("address");
                      String phoneNum = data.getString("phone");
                      String code = data.getString("code");
                      edtEmpName.setText(name);
                      edtAddress.setText(address);
                      edtPhone.setText(phoneNum);
                      //tvEmpCode.setText(code);
                  }}catch(Exception e){
                      e.printStackTrace();
                  }

              }

      });
    }
    }