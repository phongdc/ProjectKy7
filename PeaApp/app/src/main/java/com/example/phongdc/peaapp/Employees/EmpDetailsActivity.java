package com.example.phongdc.peaapp.Employees;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.example.phongdc.peaapp.AsyncHttpClient.HttpUtils;
import com.example.phongdc.peaapp.Home.HomeActivity;
import com.example.phongdc.peaapp.R;
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
    private EditText edtEmpEmail, edtEmpRole_Name;
    private TextView tvEmpCode, tvTitle;

    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp_details);
        Bundle extras = this.getIntent().getExtras();
        id = extras.getInt("ID");
        tvTitle = findViewById(R.id.tvTitle);
        tvTitle.setText("Thông tin nhân viên");
        edtEmpName = findViewById(R.id.edtEmpName);
        edtPhone = findViewById(R.id.edtEmpPhone);
        edtAddress = findViewById(R.id.edtEmpAddress);
        edtEmpEmail = findViewById(R.id.edtEmpEmail);
        edtEmpRole_Name = findViewById(R.id.edtEmpRole_Name);
        tvEmpCode = findViewById(R.id.tvEmpCode);
        getEmpDetails();
    }
    private void getEmpDetails() {
        String token = HomeActivity.getToken();
        HttpUtils.getAuth("employee?id="+id,token, null, new JsonHttpResponseHandler(){
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
                        String email = data.getString("email");
                        String empRole = data.getString("salary");
                        edtEmpName.setText(name);
                        edtAddress.setText(address);
                        edtPhone.setText(phoneNum);
                        edtEmpEmail.setText(email);
                        tvEmpCode.setText(code);
                        edtEmpRole_Name.setText(empRole);
                    }}catch(Exception e){
                    e.printStackTrace();
                }

            }

        });
    }
}