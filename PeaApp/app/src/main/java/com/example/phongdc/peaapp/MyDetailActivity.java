package com.example.phongdc.peaapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import cz.msebera.android.httpclient.Header;

public class MyDetailActivity extends AppCompatActivity {
    private String nameApi ="http://payroll.unicode.edu.vn/api/employee?id=4";
    private TextView tvName;
    private TextView tvCompany;
    private TextView tvBirthday;
    private TextView tvEmail;
    private TextView tvAddress;
    private TextView tvPhone, tvTitle;

    private SimpleDateFormat dateFormat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_detail);
        tvTitle = findViewById(R.id.tvTitle);
        tvTitle.setText("Thông tin cá nhân");
        tvName = findViewById(R.id.txtImg);
        tvCompany = findViewById(R.id.txtCom);
        tvBirthday = findViewById(R.id.txtBd);
        tvEmail = findViewById(R.id.txtMail);
        tvAddress = findViewById(R.id.txtLc);
        tvPhone = findViewById(R.id.txtPhone);
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        getDetail();
    }
    public  void  getDetail() {
        HttpUtils.get("http://payroll.unicode.edu.vn/api/employee?id=4", null, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    JSONObject object = new JSONObject(response.toString());
                    tvName.setText(object.getString("employee_name"));

                    String address = object.getString("address");
                    tvAddress.setText(address);
                    tvEmail.setText(object.getString("email"));
                    tvPhone.setText(object.getString("phone"));
                    String birthday = object.getString("birth_day");
                    Date date = dateFormat.parse(birthday);
                    tvBirthday.setText(dateFormat.format(date));

                }catch (Exception e){
                    e.printStackTrace();
                }

            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });
    }

}
