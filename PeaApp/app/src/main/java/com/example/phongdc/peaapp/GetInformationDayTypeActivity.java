package com.example.phongdc.peaapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Adapter.DayTypeAdapter;
import Model.DayType;
import cz.msebera.android.httpclient.Header;

public class GetInformationDayTypeActivity extends AppCompatActivity {
    private EditText edtDayNameInf, edtDotwInf, edtTodateInf, edtFromdateInf, edtPriorityInf;
    private String nameApi = "DayType";
    private TextView tvTitle;
    private SimpleDateFormat dateFormat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_information_day_type);
        tvTitle = findViewById(R.id.tvTitle);
        tvTitle.setText("Thông tin DayType");
        edtDayNameInf = findViewById(R.id.edtDayNameInf);
        edtDotwInf = findViewById(R.id.edtDotwInf);
        edtTodateInf = findViewById(R.id.edtTodateInf);
        edtFromdateInf = findViewById(R.id.edtFromdateInf);
        edtPriorityInf = findViewById(R.id.edtPriorityInf);
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //getDetail();
    }
    public  void  getDetail() {
        HttpUtils.get(nameApi, null, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject object = response.getJSONObject(i);
                        edtDayNameInf.setText(object.getString("name"));

                        String address = object.getString("days_of_the_week");
                        edtDotwInf.setText(address);
                        edtPriorityInf.setText(object.getString("priority"));
                        String fromdate = object.getString("from_date");
                        Date date = dateFormat.parse(fromdate);
                        edtFromdateInf.setText(dateFormat.format(date));

                        String fromdate2 = object.getString("to_date");
                        Date date2 = dateFormat.parse(fromdate2);
                        edtTodateInf.setText(dateFormat.format(date2));
                    }

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
