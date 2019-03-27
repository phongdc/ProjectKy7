package com.example.phongdc.peaapp.User;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.phongdc.peaapp.AsyncHttpClient.HttpUtils;
import com.example.phongdc.peaapp.Home.HomeEmployee;
import com.example.phongdc.peaapp.R;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import cz.msebera.android.httpclient.Header;

public class MyDetailActivity extends AppCompatActivity {
    private String nameApi ="Employee/detail?id=1";
    private TextView tvName;
    private TextView tvCompany;
    private TextView tvEmail;
    private TextView tvAddress;
    private TextView tvPhone, tvTitle, txtBd;
    private int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_detail);
        tvTitle = findViewById(R.id.tvTitle);
        tvTitle.setText("Thông tin cá nhân");
        tvName = findViewById(R.id.txtImg);
        tvCompany = findViewById(R.id.txtCom);
        tvEmail = findViewById(R.id.txtMail);
        tvAddress = findViewById(R.id.txtLc);
        tvPhone = findViewById(R.id.txtPhone);
        txtBd = findViewById(R.id.txtBd);
        Bundle extras = this.getIntent().getExtras();
        getDetail();
    }
    public  void  getDetail() {
        id = HomeEmployee.getUserID();
        HttpUtils.get("employee?id="+id , null, new JsonHttpResponseHandler(){
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
                        String email = data.getString("email");
                        String empRole = data.getString("salary");
                        tvName.setText(name);
                        tvAddress.setText(address);
                        tvPhone.setText(phoneNum);
                        tvEmail.setText(email);
                        txtBd.setText(empRole);
                    }}catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

}
