package com.example.phongdc.peaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

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
        id = extras.getInt("ID");
        edtEmpName = findViewById(R.id.edtEmpName);
        edtPhone = findViewById(R.id.edtEmpPhone);
        edtAddress = findViewById(R.id.edtEmpAddress);
        tvEmpCode = findViewById(R.id.tvEmpCode);
        getEmpDetails();
    }
    private void getEmpDetails() {
        AsyncHttpClient client = new AsyncHttpClient();
      client.get("http://payroll.unicode.edu.vn/api/Employee/" +id , null, new JsonHttpResponseHandler(){
          @Override
          public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
             try {
                    JSONObject jsonObject = new JSONObject(response.toString());
                    String name = jsonObject.getString("employee_name");
                 String address = jsonObject.getString("address");
                 String phoneNum = jsonObject.getString("phone");
                 String code = jsonObject.getString("code");
                 edtEmpName.setText(name);
                 edtAddress.setText(address);
                 edtPhone.setText(phoneNum);
                 tvEmpCode.setText(code);
             }catch (Exception ex){
                 ex.printStackTrace();
             }
          }

      });
    }
    }