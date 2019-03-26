package com.example.phongdc.peaapp.User;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.phongdc.peaapp.AsyncHttpClient.HttpUtils;
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
    private TextView tvPhone, tvTitle;
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
        getDetail();
    }
    public  void  getDetail() {
        HttpUtils.get(nameApi, null, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    JSONObject object = new JSONObject(response.toString());
                    tvName.setText(object.getString("employee_name"));

                    String address = object.getString("address");
                    tvAddress.setText(address);
                    tvEmail.setText(object.getString("email"));
                    tvPhone.setText(object.getString("phone"));


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
